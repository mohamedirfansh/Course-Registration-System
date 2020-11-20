package control;

import entities.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import control.databaseController; // If static

public class staffController {

    // Methods
    public static boolean updateAccessPeriod(Staff currentStaff, String startDate, String endDate) {
        String schoolID = currentStaff.getSchoolID();
        // Get school object using schoolID from database
        // Dummy code (Comment out before compiling):

        School school = databaseController.getSchoolData(schoolID);
        boolean updateSuccessful = school.setAccessPeriod(startDate, endDate);
        return updateSuccessful;
    }

    public static boolean addStudent(Staff currentStaff, String name, String userID, String userPW, String gender, String nationality, String schoolID, String identificationKey) {
        
        Student student = new Student(name, userID, userPW, gender, nationality, schoolID, identificationKey);

        // Pull school from database
        String schoolID = currentStaff.getSchoolID(); 
        School school = databaseController.getSchoolData(schoolID);

        // Add student to school and write to database
        ArrayList<Student> allStudents = school.getAllStudents();
        allStudents.add(student);
        school.setAllStudents(allStudents);
        if (databaseController.updateSchoolData(schoolID, school)) { // db controller handles errors
            return true;
        } else {
            return false;
        }
    }

    public static boolean addCourse(Staff currentStaff, String courseCode, String courseName, String schoolName, int au) {
        
        Course newCourse = new Course(courseCode, courseName, schoolName, au);
        
        // 1) add Course to Course database
        databaseController.addCourseData(newCourse); // Does db controller support appending? or do i get all data and append myself

        // 2) update School database 
        String schoolID = currentStaff.getSchoolID();
        School school = databaseController.getSchoolData(schoolID);
        ArrayList <Course> allCourses = school.getAllCourses();
        allCourses.add(newCourse);
        school.setAllCourses(allCourses);
        if (databaseController.updateSchoolData(schoolID, school)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean updateCourse(Staff currentStaff, String courseCode, String courseName, String schoolName, int au) {
        
        // Update course within Course database
        Course newCourse = new Course(courseCode, courseName, schoolName, au);
        databaseController.updateCourseData(courseCode, newCourse);

        // Update course within School object (Only update courses belonging to your school)
        String schoolID = currentStaff.getSchoolID();
        School school = databaseController.getSchoolData(schoolID); 
        ArrayList<Course> allCourses = school.getAllCourses();
         for (int i; i < allCourses.size(); i++) {
             if (allCourses.get(i).getCourseCode() == courseCode) { 
                allCourses.set(i, newCourse);
                school.setAllCourses(allCourses);
                databaseController.updateSchoolData(schoolName, school);
                return true;
             } else {
                 return false;
             }
         }
    }

    public static int checkVacancy(String courseCode, String indexCode) {
        
        // Get Course object from Courses
        Course course = databaseController.getCourseData(courseCode); // Assume error handling in database controller?
        HashMap<String, Integer> vacantIndices = course.getVacantIndices(); // Error handling in course?
        boolean isVacant = vacantIndices.containsKey(indexCode);
        if (isVacant) {
            int numVacancy = vacantIndices.get(indexCode);
            return numVacancy;
        } else {
            return 0;
        }
    }

    // PRINT WITHIN CONTROLLER? OR RETURN ARRAY LIST AND PRINT IN UI
    public static ArrayList<Student> getAllStudentsInCourse(String courseCode) {
        Course course = databaseController.getCourseData(courseCode); // Assume error handling in database controller?
        // Iterate over all indexes in the course
        ArrayList<Student> allStudents;
    
        ArrayList<Index> allIndices = course.getCourseIndex();
        for (Index i: allIndices) {
            allStudents.addAll(i.getEnrolled());
        }
        return allStudents;
    }

    public static ArrayList<Student> getAllStudentsInIndex(String courseCode, String indexCode) {
        Course course = databaseController.getCourseData(courseCode); // Assume error handling in database controller?

        ArrayList<Index> allIndices = course.getCourseIndex();
        Index myIndex;
        for (Index i : allIndices) {
            if (i.getIndexCode() == indexCode) {
                myIndex = i;
            }
        }
        ArrayList<Student> indexStu = myIndex.getEnrolled();
        return indexStu;
    }
}
