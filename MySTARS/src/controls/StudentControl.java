package controls;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import boundaries.StudentUIMsg;
import entities.*;

/**
 * This is a control class for the Student object.
 * It contains the methods (main logic) that a 
 * student entity is supposed to be able to do in the system.
 */
public class StudentControl {

	private static DatabaseControl dbControl = new DatabaseControl();
	Scanner scn = new Scanner(System.in);
	
	/**
	 * Method that contains logic to add course for a
	 * student. Called by the student object.
	 * @param courseID
	 */
	public static boolean addCourse(Student currentStudent, String courseID, String index, boolean checkWaitList) {
		
		Course currentCourse = dbControl.getCourseData(courseID);
		if(currentCourse == null){
			return false;
		}

		Index currentIndex = currentCourse.findIndex(index);


		// Check to see if there actually is a course with that index, if there is not
		// then display the courseDoesNotExistMsg() and exit the method.
		if (currentIndex == null) {
			StudentUIMsg.courseDoesNotExistMsg();
			return false;
		}

		if (clashBetIndex(currentStudent, courseID, index)) {
			System.out.println("Clash of course timings.");
			return false;
		}

		//check if student is already in the course
		ArrayList<Index> allIndices = currentCourse.getCourseIndex();
		for(Index i : allIndices){
			if(i.findStudentEnrolled(currentStudent.getUserID()) != null){
				System.out.println("Student already enrolled to course.");
				return false;
			}
		}

		if(!currentIndex.registerStudent(currentStudent.getUserID())) {
			System.out.println("Failed to register student. Adding student to waitList.");

			if(currentIndex.addStudentToWaitList(currentStudent.getUserID())){
				System.out.println("Student added to waitlist.");
				HashMap<String, String> studentsWaitListedCourses = currentStudent.getWaitListedCourses();
				studentsWaitListedCourses.put(courseID, index);
				currentStudent.setWaitListedCourses(studentsWaitListedCourses);

				// Update the database with the new info
				dbControl.updateCourseData(courseID, currentCourse);
				dbControl.updateStudentData(currentStudent.getUserID(), currentStudent);
			}else{
				System.out.println("Student already in waitlist.");
			}

			return false;
		}

		StudentUIMsg.successfullyEnrolledMsg();
		//Update au
		int studentsAU = currentStudent.getNumberOfAUs();
		studentsAU += currentCourse.getAu();
		currentStudent.setAcademicUnits(studentsAU);
		
		// Retrieve student's list of registered courses and add the current
		// course and index to the list
		HashMap<String, String> studentsCourses = currentStudent.getRegisteredCourses();
		studentsCourses.put(courseID, index);
		currentStudent.setRegisteredCourses(studentsCourses);
		
		// Update the database with the new info
		dbControl.updateCourseData(courseID, currentCourse);
		dbControl.updateStudentData(currentStudent.getUserID(), currentStudent);
		
		return true;
	}

	public static boolean addCourse(Student currentStudent, String courseID, String index){
		return addCourse(currentStudent, courseID, index, true);
	}
	
	/**
	 * Method to drop the course for a student.
	 * Called by the student object.
	 */
	public static boolean dropCourse(Student currentStudent, String courseID, String index, boolean checkWaitList) {
		Course currentCourse = dbControl.getCourseData(courseID);
		//Check that the course is not null
		if(currentCourse != null) {

			//Check that the index is not null
			Index currentIndex = currentCourse.findIndex(index);
			if (currentIndex != null) {

				//Check the student exists in the course
				ArrayList<String> listOfStudents = currentIndex.getEnrolled();
				if (!(listOfStudents.contains(currentStudent.getUserID()))) {
					StudentUIMsg.notInIndexMsg();
					return false;
				}

				if(currentIndex.deregisterStudent(currentStudent.getUserID())){
					HashMap<String, String> studentsCourses = currentStudent.getRegisteredCourses();
					if(!studentsCourses.containsKey(courseID))
						return false;

					studentsCourses.remove(courseID, index);
					// Retrieve the student's AUs and update it with the latest info
					int studentsAU = currentStudent.getNumberOfAUs();
					studentsAU -= currentCourse.getAu();
					currentStudent.setAcademicUnits(studentsAU);
					currentStudent.setRegisteredCourses(studentsCourses);

					//need to enroll the student in front of the queue

					//check the waitlist to retrive the student at the front of the waitlist
					if(checkWaitList){
						String newStudentID = currentIndex.getFrontOfWaitList();

						//check if the student exists
						if(newStudentID != null) {
							Student newStudent = dbControl.getStudentData(newStudentID);
							addCourse(newStudent, courseID, index, false);
						}
					}
				} else if (currentIndex.removeStudentFromWaitList(currentStudent.getUserID()) && checkWaitList) {
					HashMap<String, String> studentsCourses = currentStudent.getWaitListedCourses();
					if(!studentsCourses.containsKey(courseID))
						return false;

					studentsCourses.remove(courseID, index);
					currentStudent.setWaitListedCourses(studentsCourses);
				} else {
					return false;
				}

				//Update the database.
				dbControl.updateCourseData(courseID, currentCourse);
				dbControl.updateStudentData(currentStudent.getUserID(), currentStudent);
				return true;
			}
		}

		return false;
	}

	public static boolean dropCourse(Student currentStudent, String courseID, String index){
		return dropCourse(currentStudent, courseID, index, true);
	}

	
	public static boolean changeIndex(Student currentStudent, String course, String newIndex) {
		
		Course currentCourse = dbControl.getCourseData(course);
		String prevIndex = currentStudent.getRegisteredCourses().get(course);

		if (prevIndex == null) {
			return false;
		}

		if(currentCourse != null) {
			if(dropCourse(currentStudent, course, prevIndex, false)){
				if(addCourse(currentStudent, course, newIndex, false)){
					return true;
				}else {
					addCourse(currentStudent, course, prevIndex, false);
				}
			}
		}
		return false;
	}

	public static boolean swapIndex(Student currentStudent, String courseID, String currIndex, String friendID, String friendPassword, String friendIndex) {

		Student friend = dbControl.getStudentData(friendID);
		if (friend == null) {
			System.out.println("Your friend's username is incorrect!");
			return false;
		}

		try {
			if (!Hash.encode(friendPassword).equals(dbControl.getStudentPassword(friendID))) { // Check friend's password here
				System.out.println("Your friend's password is incorrect!");
				return false;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (dropCourse(currentStudent, courseID, currIndex, false) && dropCourse(friend, courseID, friendIndex, false)) {
			if (addCourse(currentStudent, courseID, friendIndex, false)) {
				if (addCourse(friend, courseID, currIndex)) {
					return true;
				} else {
					dropCourse(currentStudent, courseID, friendIndex, false);
				}
			}
		}

		addCourse(currentStudent, courseID, currIndex, false);
		addCourse(friend, courseID, friendIndex, false);
		return false;
	}


	public static boolean clashBetIndex(Student currentStudent, String courseID, String index) {

		//students registered courses
		HashMap<String, String> studCourses = currentStudent.getRegisteredCourses();

		//students waitListed courses
		HashMap<String, String> waitListedCourses = currentStudent.getWaitListedCourses();

		//students registered courseID

		Set<String> courses = new HashSet<>();
		if(studCourses != null) {
			courses = studCourses.keySet();
		}

		if(waitListedCourses != null) {
			courses.addAll(waitListedCourses.keySet());
		}

		//Course object of the new course
		Course newCourse = dbControl.getCourseData(courseID);

		//Lectures in the new course
		Lesson[] newLecture = newCourse.getLectures();
//		System.out.println(newLecture[0].getTimings().findDuration());
		ArrayList<WorkingHours> newCourseTimings = new ArrayList<>();



		//all the timings for the lectures of the new course
		for(Lesson l : newLecture){
			if(l == null){
				break;
			}
			newCourseTimings.add(l.getTimings());
		}

		//Working hours of the new lessons
		for(Index i : newCourse.getCourseIndex()){
			if(i.getIndexCode().equals(index)){
				for(Lesson l : i.getLessons()){
					if(l == null){
						break;
					}
					newCourseTimings.add(l.getTimings());
				}
			}
		}

		for(WorkingHours h : newCourseTimings){
			System.out.println(h.findDuration());
		}

		//newCourseTimings contains all the timings for all lessons and lectures in the new course and index

		//loop through all the registered courses
		for(String s : courses){

			//Index code within the course to which the student is enrolled
			String tempIndexCode = studCourses.get(s);

			//getting the course object for the current iteration
			Course tempCourse = dbControl.getCourseData(s);
			//Lecture timings for the registered course
			Lesson[] tempLecture = tempCourse.getLectures();
			ArrayList<WorkingHours> registeredCourseTimings = new ArrayList<>();
			for(Lesson l : tempLecture){
				if(l == null){
					break;
				}
				registeredCourseTimings.add(l.getTimings());
			}

			//looping through all the indices in the course to find the index the student is registered to
			for(Index i : tempCourse.getCourseIndex()){

				//match found, now get the timings of the index for lectures and lessons
				if(i.getIndexCode().toUpperCase().equals(tempIndexCode.toUpperCase())){
					for(Lesson l : i.getLessons()){
						registeredCourseTimings.add(l.getTimings());
					}
				}

				//registeredCourseTimings contains all the timings in the registered course.
				//now check for clash by looping through all the timings

				for(WorkingHours h1 : newCourseTimings){
					for(WorkingHours h2 : registeredCourseTimings){
						if(h1.checkClash(h2)){
							return true;
						}else{
							continue;
						}
					}
				}
			}
		}

		return false;
	}

	public static void viewRegisteredCourses(Student currentStudent) {

		HashMap<String, String> listOfRegisteredCourses = currentStudent.getRegisteredCourses();

		System.out.println("Course\tIndex");
		for (Map.Entry<String, String> mapElement : listOfRegisteredCourses.entrySet()) {
			String key = mapElement.getKey();
			String value = mapElement.getValue();

			System.out.printf("%s\t%s\n", key, value);
		}
	}

	public static void checkVacancy(String course) {

		Course currentCourse = dbControl.getCourseData(course);
		
		if (currentCourse != null) {
			ArrayList<Index> listOfIndex = currentCourse.getCourseIndex();
	
			System.out.println("Index\t\tVacancy");
			for (Index i : listOfIndex) {
				System.out.printf("%s\t%d/%d\n", i.getIndexCode(), i.getVacancy(), i.getClassSize());
			}
		}
	}
}
