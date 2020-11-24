package boundaries;

import java.util.ArrayList;
import java.util.Scanner;
import controls.StaffControl;
import controls.DatabaseControl;
import entities.*;

/**
 * Provides an interface UI and the corresponding methods for a staff member to access and modify the database
 * with staff level access.
 */
public class StaffUI {

    private static boolean loggedIn = true;
    private static DatabaseControl db = new DatabaseControl();

    public static void staffUIInit(Staff currentStaff) {

        while (loggedIn) {
            // Menu
            System.out.println("1) Update your school's access period");
            System.out.println("2) Add a new student to your school");
            System.out.println("3) Add a new course to your school");
            System.out.println("4) Update a course belonging to your school");
            System.out.println("5) Check a course's vacancy");
            System.out.println("6) Print a course's list of enrolled students");
            System.out.println("7) Print a index's list of enrolled students");
            System.out.println("8) Logout of MyStars");

            Scanner sc = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("Option: ");
            char option = sc.next().charAt(0);

            // Switch statement
            switch(option) {
                case '1':
                    System.out.println("Please enter the updated Acccess' period Start Date in the following format dd/MM/yyyy HH:mm :");
                    sc.nextLine();
                    String startDate = sc.nextLine();
                    System.out.println("Please enter the updated Acccess' period End Date in the following format dd/MM/yyyy HH:mm :");
                    String endDate = sc.nextLine();
                    updateAccPeriod(currentStaff, startDate, endDate);
                    break;

                case '2':
                    System.out.println("Please enter the Student's Name:");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.println("Please enter the Student's User ID:");
                    String userID = sc.next();
                    System.out.println("Please enter the Student's User Password:");
                    String userPW = sc.next();
                    System.out.println("Please enter the Student's Gender:");
                    String gender = sc.next();
                    System.out.println("Please enter the Student's Nationality:");
                    sc.nextLine();
                    String nationality = sc.nextLine();
                    int schoolID = currentStaff.getSchoolID(); // Can only add students to your own school
                    System.out.println("Please enter the Student's Identification Key:");
                    String identificationKey = sc.next();
                    addStudentToSchool(currentStaff, name, userID, userPW, gender, nationality, schoolID, identificationKey);
                    break;

                case '3':
                    System.out.println("Please enter the Course's Name:");
                    sc.nextLine();
                    String courseName = sc.nextLine();
                    System.out.println("Please enter the Course's Code:");
                    String courseCode = sc.next();
                    System.out.println("Please enter the Course's School name:");
                    sc.nextLine();
                    String schoolName = sc.nextLine();
                    System.out.println("Please enter the Course's Academic Units:");
                    int au = sc.nextInt();
                    addCourseToSchool(currentStaff, courseCode, courseName, schoolName, au);
                    System.out.println("=============Updated Course List================");
                    getAllCourses(currentStaff);
                    System.out.println("=================================================");
                    break;

                case '4':
                    System.out.println("=============List of Existing Courses===========");
                    getAllCourses(currentStaff);
                    System.out.println("=================================================");
                    System.out.println("Please enter the Course's updated Name:");
                    sc.nextLine();
                    String coName = sc.nextLine();
                    System.out.println("Please enter the Course's updated Code:");
                    String coCode = sc.next();
                    System.out.println("Please enter the Course's updated School name:");
                    sc.nextLine();
                    String schName = sc.nextLine();
                    System.out.println("Please enter the Course's updated Academic Units:");
                    int coAU = sc.nextInt();
                    updateCourseInSchool(currentStaff, coCode, coName, schName, coAU);
                    break;

                case '5':
                    System.out.println("=============List of Existing Courses===========");
                    getAllCourses(currentStaff);
                    System.out.println("=================================================");
                    System.out.println("Please enter the Course's Code:");
                    String checkCourseCode = sc.next();
                    checkCourseVacancy(checkCourseCode);
                    break;

                case '6':
                    System.out.println("=============List of Existing Courses===========");
                    getAllCourses(currentStaff);
                    System.out.println("=================================================");
                    System.out.println("Please enter the Course's Code:");
                    String vacCourseCode = sc.next();
                    printCourseStudentList(vacCourseCode);
                    break;

                case '7':
                    System.out.println("=============List of Existing Courses===========");
                    getAllCourses(currentStaff);
                    System.out.println("=================================================");
                    System.out.println("Please enter the Course's Code:");
                    String vacCourseCode2 = sc.next();
                    getAllIndexes(vacCourseCode2);
                    System.out.println("Please enter the Index's Code:");
                    String vacIndexCode2 = sc.next();
                    printIndexStudentList(vacCourseCode2, vacIndexCode2);
                    break;

                case '8':
                    System.out.println("Thanks for using myStars. See you again!");
                    loggedIn = false;
                    sc.close();
                    break;

                default:
                    System.out.println("No such option! Please enter a valid option.");
                    sc.close();
                    break;
            }
        }
    }


    /**
     * getAllCourses: Get all course at the school the staff belongs to.
     *
     * @param currentStaff The currently logged in staff's Staff object
     *
     * @return Prints all courses
     */
    public static void getAllCourses(Staff currentStaff) {
        try {
            // Display all courses
            int schoolID = currentStaff.getSchoolID();
            School school = db.getSchoolData(schoolID);
            ArrayList<String> allCourses = school.getAllCourses();
            System.out.println("Courses available:");
            for (String course : allCourses) {
                System.out.println(course);
            }
        } catch(NullPointerException e) {
            System.out.println("");
        }
    }

    // B. Get all indexes for a given course within a staff's school for display
    /**
     * getAllIndexes: Get all indexes belonging to a given course.
     *
     * @param courseCode Course code of interest
     *
     * @return Prints all indexes
     */
    public static void getAllIndexes(String courseCode) {
        try {
            // Display all indexes
            Course course = db.getCourseData(courseCode);
            ArrayList<Index> allIndexes = course.getCourseIndex();
            System.out.println("Indexes available for " + courseCode + ":");
            for (Index idx : allIndexes) {
                System.out.println(idx.getIndexCode());
            }
        } catch(NullPointerException e) {
            System.out.println("");
        }
    }

    /**
     * updateAccPeriod: Updates a staff's school's access period using the newly
     * given start date and end date.
     *
     * @param currentStaff The currently logged in staff's Staff object
     * @param startDate    The new start date of the school's access period in the
     *                     format of dd/MM/yyyy HH:mm
     * @param endDate      The new end date of the school's access period in the
     *                     format of dd/MM/yyyy HH:mm
     * @return Prints result of update
     */
    public static void updateAccPeriod(Staff currentStaff, String startDate, String endDate) {
        if (StaffControl.updateAccessPeriod(currentStaff, startDate, endDate)) {
            System.out.println("Access period was successfully updated!");
        } else {
            System.out.println("Invalid access period was entered! Please try again");
        }
    }

    /**
     * addStudentToSchool: Adds a new student to a staff's school and to the student
     * database.
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
     * @return Prints result of update
     */
    public static void addStudentToSchool(Staff currentStaff, String name, String userID, String userPW, String gender, String nationality, int schoolID, String identificationKey) {
        if (StaffControl.addStudent(currentStaff, name, userID, userPW, gender, nationality, schoolID, identificationKey)) {
            System.out.println("Student added successfully to school!");
        } else {
            System.out.println("Error in adding student to school! Please try again");
        }
    }

    /**
     * addCourseToSchool: Adds a new course to a staff's school and to the course
     * database.
     *
     * @param currentStaff The currently logged in staff's Staff object
     * @param courseCode   New course's course code
     * @param courseName   New course's course name
     * @param schoolName   Name of school new course belongs to
     * @param au           New course's academic units
     *
     * @return Prints result of update
     */
    public static void addCourseToSchool(Staff currentStaff, String courseCode, String courseName, String schoolName, int au) {
        if (StaffControl.addCourse(currentStaff, courseCode, courseName, schoolName, au)) {
            System.out.println("Course added successfully to school!");
        } else {
            System.out.println("Error in adding course to school! Please try again");
        }
    }

    /**
     * updateCourseInSchool: Updates a course in the course database given a its
     * course code.
     *
     * @param courseCode Updated course code
     * @param courseName Updated course name
     * @param schoolName Name of school course belongs to
     * @param au         Updated course's academic units
     *
     * @return Prints result of update
     */
    public static void updateCourseInSchool(Staff currentStaff, String courseCode, String courseName, String schoolName, int au) {
        if (StaffControl.updateCourse(currentStaff, courseCode, courseName, schoolName, au)) {
            System.out.println("Course updated successfully!");
        } else {
            System.out.println("Error updating course! Please try again");
        }
    }

    /**
     * checkCourseVacancy: Checks if there are vacancies in a Course.
     *
     * @param courseCode Course code of choice
     *
     * @return Prints result of update
     */
    public static void checkCourseVacancy(String courseCode) {
        StaffControl.checkVacancy(courseCode);
    }

    /**
     * printCourseStudentList: Returns all students enrolled in a given course.
     *
     * @param courseCode Course code of choice
     *
     * @return Prints result of update
     */
    public static void printCourseStudentList(String courseCode) {
        ArrayList<Student> allStudents = StaffControl.getAllStudentsInCourse(courseCode);
        if (allStudents.isEmpty()) {
            System.out.println("There are no students enrolled in this course!");
        } else {
            System.out.println("Students enrolled in " + courseCode + ":");
            for (Student s : allStudents) {
                System.out.println("Student ID: " + s.getIDKey() + " | " + "Student Name: " + s.getName());
            }
        }
    }

    /**
     * getAllStudentsInIndex: Returns all students enrolled in a given course and
     * index.
     *
     * @param courseCode Course code of choice
     * @param indexCode  Index code of choice
     *
     * @return Prints result of update
     */
    public static void printIndexStudentList(String courseCode, String indexCode) {
        ArrayList<Student> allStudents = StaffControl.getAllStudentsInIndex(courseCode, indexCode);
        if (allStudents.isEmpty()) {
            System.out.println("There are no students enrolled in this index!");
        } else {
            System.out.println("Students enrolled in " + indexCode + ":");
            for (Student s : allStudents) {
                System.out.println("Student ID: " + s.getIDKey() + " | " + "Student Name: " + s.getName());
            }
        }
    }
}

