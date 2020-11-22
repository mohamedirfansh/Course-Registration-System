package boundaries;

import java.util.ArrayList;
import java.util.Scanner;
import control.StaffController;
import entities.Student;
import entities.Staff;

public class StaffUI {

    private static boolean loggedIn = true;

    // Driver
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
            System.out.println("8) Logout of myStars");

            Scanner sc = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("Option: ");
            int option = sc.nextInt();

            // Switch statement
            switch(option) {
                case 1:
                    System.out.println("Please enter the updated Acccess' period Start Date:");
                    String startDate = sc.next();
                    System.out.println("Please enter the updated Acccess' period End Date:");
                    String endDate = sc.next();
                    updateAccPeriod(currentStaff, startDate, endDate);
                    break;

                case 2:
                    System.out.println("Please enter the Student's Name:");
                    String name = sc.next();
                    System.out.println("Please enter the Student's User ID:");
                    String userID = sc.next();
                    System.out.println("Please enter the Student's User Password:");
                    String userPW = sc.next();
                    System.out.println("Please enter the Student's Gender:");
                    String gender = sc.next();
                    System.out.println("Please enter the Student's Nationality:");
                    String nationality = sc.next();
                    String schoolID = currentStaff.getSchoolID(); // Can only add students to your own school
                    System.out.println("Please enter the Student's Identification Key:");
                    String identificationKey = sc.next();
                    addStudentToSchool(currentStaff, name, userID, userPW, gender, nationality, schoolID, identificationKey);
                    break;
                
                case 3:
                    System.out.println("Please enter the Course's Name:");
                    String courseName = sc.next();
                    System.out.println("Please enter the Course's Code:");
                    String courseCode = sc.next();
                    System.out.println("Please enter the Course's School name:");
                    String schoolName = sc.next();
                    System.out.println("Please enter the Course's Academic Units:");
                    int au = sc.nextInt();
                    addCourseToSchool(currentStaff, courseCode, courseName, schoolName, au);
                    break;
                
                case 4:
                    System.out.println("Please enter the Course's Name:");
                    String coName = sc.next();
                    System.out.println("Please enter the Course's Code:");
                    String coCode = sc.next();
                    System.out.println("Please enter the Course's School name:");
                    String schName = sc.next();
                    System.out.println("Please enter the Course's Academic Units:");
                    int coAU = sc.nextInt();
                    updateCourseInSchool(currentStaff, coCode, coName, schName, coAU);
                    break;

                case 5:
                    System.out.println("Please enter the Course's Code:");
                    String checkCourseCode = sc.next();
                    System.out.println("Please enter the Index's Code:");
                    String checkIndexCode = sc.next();
                    checkCourseVacancy(checkCourseCode, checkIndexCode);
                    break;
                
                case 6:
                    System.out.println("Please enter the Course's Code:");
                    String vacCourseCode = sc.next();
                    printCourseStudentList(vacCourseCode);
                    break;

                case 7:
                    System.out.println("Please enter the Course's Code:");
                    String vacCourseCode2 = sc.next();
                    System.out.println("Please enter the Index's Code:");
                    String vacIndexCode2 = sc.next();
                    printIndexStudentList(vacCourseCode2, vacIndexCode2);
                    break;

                case 8:
                    System.out.println("Thanks for using myStars. See you again!");
                    loggedIn = false;
                    break;

                default:
                    System.out.println("No such option! Please enter a valid option.");
                    break;
            }   
        }
    }

    // Methods
    // 1. Update school's Access Period
    public static void updateAccPeriod(Staff currentStaff, String startDate, String endDate) {
        if (StaffController.updateAccessPeriod(currentStaff, startDate, endDate)) {
            System.out.println("Access period was successfully updated!");
        } else {
            System.out.println("Invalid access period was entered! Please try again");
        }
    }

    // 2. Add student to school
    public static void addStudentToSchool(Staff currentStaff, String name, String userID, String userPW, String gender, String nationality, String schoolID, String identificationKey) {
        if (StaffController.addStudent(currentStaff, name, userID, userPW, gender, nationality, schoolID, identificationKey)) {
            System.out.println("Student added successfully to school!");
        } else {
            System.out.println("Error in adding student to school! Please try again");
        }
    }

    // 3. Add course to school
    public static void addCourseToSchool(Staff currentStaff, String courseCode, String courseName, String schoolName, int au) {
        if (StaffController.addCourse(currentStaff, courseCode, courseName, schoolName, au)) {
            System.out.println("Course added successfully to school!");
        } else {
            System.out.println("Error in adding course to school! Please try again");
        }
    }

    // 4. Update course belonging to school and course database
    public static void updateCourseInSchool(Staff currentStaff, String courseCode, String courseName, String schoolName, int au) {
        if (StaffController.updateCourse(currentStaff, courseCode, courseName, schoolName, au)) {
            System.out.println("Course updated successfully!");
        } else {
            System.out.println("Error updating course! Please try again");
        }
    }

    // 5. Check course vacancy
    public static void checkCourseVacancy(String courseCode, String indexCode) {
        int numVacancy = StaffController.checkVacancy(courseCode, indexCode);
        if (numVacancy > 0) {
            System.out.println("Course: " + courseCode);
            System.out.println("Index: " + indexCode);
            System.out.println("Vacancies: " + numVacancy);
        } else {
            System.out.println("The index " + indexCode + " is currently full!");
        }
    }

    // 6. Print student's list (By course and by index)
    public static void printCourseStudentList(String courseCode) {
        ArrayList<Student> allStudents = StaffController.getAllStudentsInCourse(courseCode);
        if (allStudents.isEmpty()) {
            System.out.println("There are no students enrolled in this course!");
        } else {
            System.out.println("Students enrolled in " + courseCode + ":");
            for (Student s : allStudents) {
                System.out.println("ID: " + s.getIDKey() + "   " + "Name: " + s.getName());
            }
        }
    }

    public static void printIndexStudentList(String courseCode, String indexCode) {
        ArrayList<Student> allStudents = StaffController.getAllStudentsInIndex(courseCode, indexCode);
        if (allStudents.isEmpty()) {
            System.out.println("There are no students enrolled in this index!");
        } else {
            System.out.println("Students enrolled in " + indexCode + ":");
            for (Student s : allStudents) {
                System.out.println("ID: " + s.getIDKey() + "   " + "Name: " + s.getName());
            }
        }
    }
}

