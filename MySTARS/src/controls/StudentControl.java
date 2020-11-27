package controls;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import boundaries.StudentUIMsg;
import entities.*;

/**
 * StudentControl is used to provide student level access of the database and student privileges
 * after successful login to the database.
 *
 * Class Attributes:
 * -> db : DatabaseControl, which defines a database control instance for the staff to access and modify the database.
 * -> notif : Notification, which defines a email class to send notifications to the students on successful registration,
 * 			waitlisting, swapping of courses etc.
 */
public class StudentControl {

	private static DatabaseControl dbControl = new DatabaseControl();
	private static Notification notif = new Email();

	/**
	 * The addCourse function is called to create a link between a student and a course, specifically the course index.
	 * A two way link is cretaed between the student and the course by appending the student to the course class, and by
	 * appending the course index to the student class.
	 *
	 * @param currentStudent, the student for whom the course is being added
	 * @param courseID, the code of the course that is being enrolled into
	 * @param index, the index of the course that is being enrolled into
	 * @param checkWaitList, defines whether the student should be added to the waitList if there are no vacancies
	 *                       avaiable in the course.
	 * @return boolean, true if the course was enrolled to successfully, and the student was added to the course.
	 * 			false, in every other case, even if the student is added to the waitlist.
	 */
	public static boolean addCourse(Student currentStudent, String courseID, String index, boolean checkWaitList, boolean checkClash) {
		
		Course currentCourse = dbControl.getCourseData(courseID);
		if(currentCourse == null){
			StudentUIMsg.courseDoesNotExistMsg();
			return false;
		}

		Index currentIndex = currentCourse.findIndex(index);
		if (currentIndex == null) {
			StudentUIMsg.courseDoesNotExistMsg();
			return false;
		}

		//check if student is already in the course
		ArrayList<Index> allIndices = currentCourse.getCourseIndex();
		for(Index i : allIndices){
			if(i.findStudentEnrolled(currentStudent.getIDKey()) != null){
				System.out.println("Student already enrolled to course.");
				return false;
			}
		}

		if (clashBetIndex(currentStudent, courseID, index) && checkClash) {
			System.out.println("Clash of course timings.");
			return false;
		}

		if(!currentIndex.registerStudent(currentStudent.getIDKey())) {
			System.out.println("No vacancies in index. Attempting to add student to waitList...\n");
			if(currentIndex.addStudentToWaitList(currentStudent.getUserID()) && checkWaitList == true){
				HashMap<String, String> studentsWaitListedCourses = currentStudent.getWaitListedCourses();
				studentsWaitListedCourses.put(courseID, index);
				currentStudent.setWaitListedCourses(studentsWaitListedCourses);

				// Update the database with the new info
				dbControl.updateCourseData(courseID, currentCourse);
				dbControl.updateStudentData(currentStudent.getUserID(), currentStudent);
				notif.sendWaitListedMessage(currentStudent.getUserID(), courseID, index);
				System.out.println("Student added to waitList successfully.\n");
			}else{
				//System.out.println("Current waitlist:");
				// currentIndex.printWaitList();
				// currentIndex.removeStudentFromWaitList("Kenny1999");
				//currentIndex.removeStudentFromWaitList("C190122");

				System.out.println("Student already in waitlist.");
			}

			return false;
		}

		StudentUIMsg.successfullyEnrolledMsg();
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

		//Send course registered notification to the student
		if(checkWaitList){
			notif.sendRegisterSuccessfulMessage(currentStudent.getUserID(), courseID, index);
		}
		
		return true;
	}

	/**
	 * Overloaded addCourse() function. This function passes a default value of true to the checkWaitList argument, such that
	 * the waitlist is always checked. However, in some cases, we don't want the student to be enrolled to the waitlist, for instance
	 * when swapping indices. Hence only the internal functions of the class call this function with checkWaitList = false.
	 * All the external functions call addCourse() with checkWaitList = true.
	 *
	 * @param currentStudent, the student for whom the course is being added
	 * @param courseID, the code of the course that is being enrolled into
	 * @param index, the index of the course that is being enrolled into
	 * @return boolean, true if the course was enrolled to successfully, and the student was added to the course.
	 * 			false, in every other case, even if the student is added to the waitlist.
	 */
	public static boolean addCourse(Student currentStudent, String courseID, String index){
		return addCourse(currentStudent, courseID, index, true, true);
	}

	public static boolean addCourse(Student currentStudent, String courseID, String index, boolean checkWaitList){
		return addCourse(currentStudent, courseID, index, checkWaitList, true);
	}

	/**
	 * dropCourse() is used to drop a course for a student, and accordingly drop the student from the course
	 * to break the two way association between them.
	 *
	 * @param currentStudent, the student object for which the course is being dropped.
	 * @param courseID, the code of the course being dropped.
	 * @param index, the index within the course that is being dropped by the student.
	 * @param checkWaitList, this parameter decides if the student at the front of the waitList is added to
	 *                       the course upon successfuly deregistration of the currentStudent. However, it is set to false
	 *                       when swapping courses, since we don't want swapCourse to enroll the stduent at the front of
	 *                       the waitList, in case swapping fails.
	 *
	 * @return true, if the course was dropped successfully. returns false in every other case.
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
				if (!(listOfStudents.contains(currentStudent.getIDKey()))) {
					StudentUIMsg.notInIndexMsg();
					return false;
				}

				if(currentIndex.deregisterStudent(currentStudent.getIDKey())){
					HashMap<String, String> studentsCourses = currentStudent.getRegisteredCourses();
					if(!studentsCourses.containsKey(courseID))
						return false;

					studentsCourses.remove(courseID, index);
					// Retrieve the student's AUs and update it with the latest info
					int studentsAU = currentStudent.getNumberOfAUs();
					studentsAU -= currentCourse.getAu();
					currentStudent.setAcademicUnits(studentsAU);
					currentStudent.setRegisteredCourses(studentsCourses);
					
					dbControl.updateCourseData(courseID, currentCourse);
					dbControl.updateStudentData(currentStudent.getUserID(), currentStudent);

					//need to enroll the student in front of the queue

					//check the waitlist to retrive the student at the front of the waitlist
					if(checkWaitList){
						String newStudentID = currentIndex.getFrontOfWaitList();
						//System.out.println("StudentID of student in wait list: " + newStudentID);
						currentIndex.printWaitList();

						//check if the student exists
						if(newStudentID != null) {
							Student newStudent = dbControl.getStudentData(newStudentID);	
							String IDkey = newStudent.getIDKey();
							if(addCourse(newStudent, courseID, index, false, false)){
								notif.sendRegisterSuccessfulMessage(newStudent.getUserID(), courseID, index);
							}

						}
					}
					dbControl.updateCourseData(courseID, currentCourse);
					dbControl.updateStudentData(currentStudent.getUserID(), currentStudent);

				} else if (currentIndex.removeStudentFromWaitList(currentStudent.getUserID()) && checkWaitList) {
					HashMap<String, String> studentsCourses = currentStudent.getWaitListedCourses();
					
					if(!studentsCourses.containsKey(courseID))
						return false;

					studentsCourses.remove(courseID, index);
					currentStudent.setWaitListedCourses(studentsCourses);
					dbControl.updateCourseData(courseID, currentCourse);
					dbControl.updateStudentData(currentStudent.getUserID(), currentStudent);
				} else {
					return false;
				}

				//Update the database.
				if(checkWaitList){
					notif.sendDropCourseMessage(currentStudent.getUserID(), courseID, index);
				}

				
				return true;
			}
		}

		return false;
	}

	/**
	 * Overloaded dropCourse() function. This function passes a default value of true to the checkWaitList argument, such that
	 * the waitlist is always checked. However, in some cases, we don't want the students in the front of the waitList
	 * to be enrolled when a student drops the course, for instance when changing index, if the student temporarily drops the index
	 * and fails to be enrolled in the new index. Hence only the internal functions of the class call this function with checkWaitList = false.
	 * All the external functions call addCourse() with checkWaitList = true.
	 *
	 * @param currentStudent, the student object for which the course is being dropped.
	 * @param courseID, the code of the course being dropped.
	 * @param index, the index within the course that is being dropped by the student.
	 *
	 * @return true, if the course was dropped successfully. returns false in every other case.
	 */
	public static boolean dropCourse(Student currentStudent, String courseID, String index){
		return dropCourse(currentStudent, courseID, index, true);
	}


	/**
	 * changeIndex() is used by the student to switch to another index, if vacancies are available. If the
	 * vacancies are not available in the new index, the student is re-registered back to the old index. If the
	 * change is successful, then the student at the front of the waitlist is enrolled to the new vacancy created.
	 *
	 * @param currentStudent, the student object that is trying to change the course index.
	 * @param course, the course for which the change is being carried out.
	 * @param newIndex, the new index to which the student is trying to register.
	 *
	 * @return true, if the change of index is successful for the student and the vacancy thus created is filled by the
	 * 			student at the front of the waitlist.
	 * 		False, in every other case.
	 */
	public static boolean changeIndex(Student currentStudent, String course, String newIndex) {
		
		Course currentCourse = dbControl.getCourseData(course);
		String prevIndex = currentStudent.getRegisteredCourses().get(course);

		if (prevIndex == null) {
			return false;
		}

		if(currentCourse != null) {
			if(dropCourse(currentStudent, course, prevIndex, false)){
				if(addCourse(currentStudent, course, newIndex, false)){
					String frontOfWaitList = currentCourse.findIndex(prevIndex).getFrontOfWaitList();
					Student waitListedStudent = dbControl.getStudentData(frontOfWaitList);

					if(waitListedStudent != null) {
						addCourse(waitListedStudent, course, prevIndex);
					}
					notif.sendRegisterSuccessfulMessage(currentStudent.getUserID(), course, newIndex);
					return true;
				}else {
					addCourse(currentStudent, course, prevIndex, false);
				}
			}
		}
		return false;
	}

	/**
	 * swapIndex() is used by a student to swap the index of a particular course which he is enrolled in, with a different index
	 * in the same course that another student is enrolled in. Concurrently, the second student is added to the index to which
	 * the first student is initially enrolled.
	 *
	 * @param currentStudent, the student requesting the swap of index.
	 * @param courseID, the course for which the index swap is being carried out.
	 * @param currIndex, the current index of the student requesting the swap.
	 * @param friendID, the userID of the student being swapped with.
	 * @param friendPassword, the password of the student being swapped with.
	 * @param friendIndex, the index held by the student being swapped with for the same course.
	 *
	 * @return true, if the swap is carried out successfully. In this case, each student is deregistered from their
	 * 			current index and regsitered to each others indices for the same course.
	 * 		false, in any other case. If the students fail to register to the other index, then both students are added
	 * 		back to their old indices.
	 */
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
				if (addCourse(friend, courseID, currIndex, false)) {
					notif.sendRegisterSuccessfulMessage(currentStudent.getUserID(), courseID, friendIndex);
					notif.sendRegisterSuccessfulMessage(friend.getUserID(), courseID, currIndex);
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

	/**
	 * clashBetIndex() is used to check if a course index timings clash with any timings of the other courses held by the
	 * student.
	 *
	 * @param currentStudent, the current student for which clashes are being checked in the timetable.
	 * @param courseID, the code of the course against which clashes are being checked.
	 * @param index, the index of the course against which clashes are being checked.
	 *
	 * @return true, if the new index clashes with any of the existing courses to which the student is either
	 * 			registered or waitlisted to.
	 * 			false, if the new index does not clahs with any of the existing courses to which the student is
	 * 		either registered or waitListed to.
	 */
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
			//courses.addAll(waitListedCourses.keySet());
		}

		//Course object of the new course
		Course newCourse = dbControl.getCourseData(courseID);

		//Lectures in the new course
		Lesson[] newLecture = newCourse.getLectures();
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


	/**
	 * viewRegisteredCoures() is used to view all the courses to which a student is registered.
	 *
	 * @param currentStudent, the student for whom the registered courses are being displayed.
	 */
	public static void viewRegisteredCourses(Student currentStudent) {

		HashMap<String, String> listOfRegisteredCourses = currentStudent.getRegisteredCourses();

		System.out.println("Course\tIndex");
		for (Map.Entry<String, String> mapElement : listOfRegisteredCourses.entrySet()) {
			String key = mapElement.getKey();
			String value = mapElement.getValue();

			System.out.printf("%s\t%s\n", key, value);
		}
	}

	/**
	 * checkVacancy is used to check the vacancies available in each index for a particular course.
	 *
	 * @param course, the course for which the indices are being checked for vacancies.
	 */
	public static void checkVacancy(String course) {

		Course currentCourse = dbControl.getCourseData(course);
		
		if (currentCourse != null) {
			ArrayList<Index> listOfIndex = currentCourse.getCourseIndex();
	
			System.out.println("Index\t\tVacancy");
			for (Index i : listOfIndex) {
				System.out.printf("%s\t%d/%d\n", i.getIndexCode(), i.getVacancy(), i.getClassSize());
			}
		}
		else {
			System.out.println("Course entered is not valid.");
		}
	}
}
