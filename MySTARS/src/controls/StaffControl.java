package controls;

import entities.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

/* StaffControl
This class handles all of the functionality for the staff (admin) user. Essentially
it allows staff users to modify school, student, course and index objects.

Attributes:
-> Database db: DatabaseControl object that enables our staff to read and write to the respective binary files.
*/ 


public class StaffControl {

    // Attributes
    private static DatabaseControl db = new DatabaseControl();
    
    // Methods

    /**
     * updateAccessPeriod: Updates a staff's school's access period using the newly
     * given start date and end date.
     * 
     * @param currentStaff The currently logged in staff's Staff object
     * @param startDate    The new start date of the school's access period in the
     *                     format of dd/MM/yyyy HH:mm
     * @param endDate      The new end date of the school's access period in the
     *                     format of dd/MM/yyyy HH:mm
     * @return A boolean variable. true if the update was successful else false. 
     */
    public static boolean updateAccessPeriod(Staff currentStaff, String startDate, String endDate) {
        
        int schoolID = currentStaff.getSchoolID();
        School school = db.getSchoolData(schoolID);
        return school.setAccessPeriod(startDate, endDate);

    }

    /**
     * addStudent: Adds a new student to a staff's school and to the student database.
     * 
     * @param currentStaff      The currently logged in staff's Staff object
     * @param name              Name of the new student
     * @param userID            New student's userID
     * @param userPW            New student's Password
     * @param gender            New student's gender
     * @param nationality       New student's nationality
     * @param schoolID          New student's schoolID
     * @param identificationKey New student's identification number
     * 
     * @return A boolean variable. true if the update was successful else false.
     */
    public static boolean addStudent(Staff currentStaff, String name, String userID, String userPW, String gender, String nationality, int schoolID, String identificationKey) {
        
        Student student;
		try {
			student = new Student(name, userID, userPW, gender, nationality, schoolID, identificationKey);
            db.addStudentPassword(userID, userPW);

			int schoolIDTwo = currentStaff.getSchoolID(); 
	        School school = db.getSchoolData(schoolIDTwo);
	        ArrayList<String> allStudents = school.getAllStudents();
	        allStudents.add(identificationKey);
            school.setAllStudents(allStudents);
            
            return (db.updateSchoolData(schoolID, school) && db.addStudentData(student)); 
            
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
    }

    /**
     * addCourse: Adds a new course to a staff's school and to the course database.
     * 
     * @param currentStaff      The currently logged in staff's Staff object
     * @param courseCode        New course's course code
     * @param courseName        New course's course name
     * @param schoolName        Name of school new course belongs to
     * @param au                New course's academic units
     * 
     * @return A boolean variable. true if the update was successful else false.
     */
    public static boolean addCourse(Staff currentStaff, String courseCode, String courseName, String schoolName, int au) {
        
        Course newCourse = new Course(courseCode, courseName, schoolName, au);
        int schoolIDThree = currentStaff.getSchoolID();
        School school = db.getSchoolData(schoolIDThree);
        ArrayList <String> allCourses = school.getAllCourses();
        allCourses.add(courseCode);
        school.setAllCourses(allCourses);
        
        return (db.updateSchoolData(schoolIDThree, school) && db.addCourseData(newCourse));
    }

    /**
     * updateCourse: Updates a course in the course database given a its course code.
     * 
     * @param courseCode        Updated course code
     * @param courseName        Updated course name
     * @param schoolName        Name of school course belongs to
     * @param au                Updated course's academic units
     * 
     * @return A boolean variable. true if the update was successful else false.
     */
    public static boolean updateCourse(Staff currentStaff, String courseCode, String courseName, String schoolName, int au) {

        // No need to update school since courseCode is unique and persistent
        Course newCourse = new Course(courseCode, courseName, schoolName, au);
        return db.updateCourseData(courseCode, newCourse);
    }

    /**
     * checkVacancy: Checks if there are vacancies in a Course.
     * 
     * @param courseCode Course code of choice
     * @param indexCode  Index code of choice
     * 
     * @return A boolean variable. true if the update was successful else false.
     */
    public static void checkVacancy(String courseCode) {

        Course currentCourse = db.getCourseData(courseCode);

        if (currentCourse != null) {
            ArrayList<Index> listOfIndex = currentCourse.getCourseIndex();

            System.out.println("Index\tVacancy");
            for (Index i : listOfIndex) {
                System.out.printf("%s\t%d\n", i.getIndexCode(),   i.getVacancy());
            }
        }
    }

    /**
     * getAllStudentsInCourse: Returns all students enrolled in a given course.
     * 
     * @param courseCode Course code of choice
     * 
     * @return An ArrayList of Student objects
     */
    public static ArrayList<Student> getAllStudentsInCourse(String courseCode) {
        
        try {
        Course course = db.getCourseData(courseCode);
        ArrayList<String> allStudentsID = new ArrayList<>();
        
        // Get all enrolled student IDs from all course indices
        ArrayList<Index> allIndices = course.getCourseIndex();
        for (Index i : allIndices) {
            allStudentsID.addAll(i.getEnrolled());
        }

        // Get all student objects belonging to enrolled student IDs
        ArrayList<Student> allStudents = new ArrayList<>();
        for (String id : allStudentsID) {
            Student stu = db.getStudentDataID(id);
            if (stu != null) {
                allStudents.add(stu);
            }
        }
        return allStudents;
    } catch(NullPointerException e) {
        System.out.println("");
        return new ArrayList<>();
    }
    }

    /**
     * getAllStudentsInIndex: Returns all students enrolled in a given course and index.
     * 
     * @param courseCode Course code of choice
     * @param indexCode Index code of choice
     * 
     * @return An ArrayList of Student objects
     */
    public static ArrayList<Student> getAllStudentsInIndex(String courseCode, String indexCode) {
        try {
        Course course = db.getCourseData(courseCode);
        ArrayList<Index> allIndices = course.getCourseIndex();
        ArrayList<String> allIndexStudentsID = new ArrayList<>();

        // Get all student IDs enrolled in chosen index of course 
        for (Index i : allIndices) {
            if (i.getIndexCode() == indexCode) {
                allIndexStudentsID.addAll(i.getEnrolled());
            }
        }
        
        // Get all student objects belonging to enrolled student IDs
        ArrayList<Student> allStudentsIndex = new ArrayList<>();
        for (String idx : allIndexStudentsID) {
            Student stud = db.getStudentDataID(idx);
            if (stud != null) {
                allStudentsIndex.add(stud);
            }
        }
        return allStudentsIndex;
    } catch(NullPointerException e) {
        System.out.println("");
        return new ArrayList<>();
        }
    }
}
