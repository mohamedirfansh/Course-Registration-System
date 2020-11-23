package controls;

import entities.*;
import entities.Student;
import java.util.ArrayList;
import java.util.HashMap;

public class StaffControl {

    // Attributes
    private static DatabaseControl db = new DatabaseControl();
    
    // Constructor
    public StaffControl() {}

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
        
        Student student = new Student(name, userID, userPW, gender, nationality, schoolID, identificationKey);
        int schoolIDTwo = currentStaff.getSchoolID(); 
        School school = db.getSchoolData(schoolIDTwo);
        ArrayList<String> allStudents = school.getAllStudents();
        allStudents.add(identificationKey);
        school.setAllStudents(allStudents);

        return (db.updateSchoolData(schoolID, school) && db.addStudentData(student)); 
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
    public static boolean updateCourse(String courseCode, String courseName, String schoolName, int au) {

        // No need to update school since courseCode is unique and persistent
        Course newCourse = new Course(courseCode, courseName, schoolName, au);
        return db.updateCourseData(courseCode, newCourse);
    }

    /**
     * checkVacancy: Checks if there are vacancies in a given course's index.
     * 
     * @param courseCode Course code of choice
     * @param indexCode  Index code of choice
     * 
     * @return A boolean variable. true if the update was successful else false.
     */
    public static int checkVacancy(String courseCode, String indexCode) {
        
        Course course = db.getCourseData(courseCode);
        HashMap<String, Integer> vacantIndices = course.getVacantIndices();
        boolean isVacant = vacantIndices.containsKey(indexCode);
        if (isVacant) {
            return vacantIndices.get(indexCode);
        } else {
            return 0;
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
            allStudents.add(db.getStudentData(id));
        }
        return allStudents;
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
            allStudentsIndex.add(db.getStudentData(idx));
        }
        return allStudentsIndex;
    }
}