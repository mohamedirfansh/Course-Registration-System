package controls;

import entities.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class staffController {
    // Variables
    private Staff currentStaff;

    // Constructor
    public staffController(Staff currentStaff) {
        this.currentStaff = currentStaff;
    }

    // Methods
    public void updateAccessPeriod(String startDate, String endDate) {
        String schoolID = currentStaff.getSchoolID();
        // Get school object using schoolID from database
        // Dummy code (Comment out before compiling):

        DatabaseControl db = new DatabaseControl();
        School school = db.getSchoolData(schoolID);
        boolean updateSuccessful = school.setAccessPeriod(startDate, endDate);
        if (updateSuccess) {
            System.out.println("The access period for " + school.getSchoolName() + " has been successful!");
            System.out.println(school.getAccessPeriod());
        } else {
            System.out.println("The access period you have inputted is invalid! Please try again.");
        }
    }

    public void addStudent(String name, String gender, String nationality, String userID, String hashedPassword, ArrayList<Course> registeredCourses, int academicUnits, String marticID) {
        
        // Create new student (CHECK AGAINST LATEST STUDENT CLASS)
        Student student = new Student(name, gender, nationality, userID, hashedPassword, registeredCourses, academicUnits, marticID);

        // Pull school from database
        String schoolID = currentStaff.getSchoolID();
        DatabaseControl db = new DatabaseControl();
        School school = db.getSchoolData(schoolID);

        // Add student to school and write to database
        ArrayList<Student> allStudents = school.getAllStudents();
        allStudents.add(student);
        school.setAllStudents(allStudents);
        db.updateSchoolData(schoolID, school);
        System.out.println(student.getName() + " has successfully been added to " + school.getSchoolName() + " !");
    }

    public void addCourse(String courseCode, String courseName, String schoolName, int au, ArrayList<Index> courseIndex) {
        Course newCourse = new Course(courseCode, courseName, schoolName, au, courseIndex);

        // Instantiate Database Controller
        DatabaseControl db = new DatabaseControl();
        
        // 1) add Course to Course database
        db.addCourseData(newCourse);
        System.out.println("Course: " + courseName + " has been successfully added to Courses!");

        // 2) update School database 
        String schoolID = currentStaff.getSchoolID();
        School school = db.getSchoolData(schoolID);
        ArrayList <Course> allCourses = school.getAllCourses();
        allCourses.add(newCourse);
        school.setAllCourses(allCourses);
        db.updateSchoolData(schoolID, school);
        System.out.println("Course: " + courseName + " has been successfully added to " + school.getSchoolName());
    }

    public void updateCourse(String courseCode, String courseName, String schoolName, int au, ArrayList<Index> courseIndex) {
        // Since we are storing Course Objects and not CourseIDs, if we update a Course, we have to update both the Course database and the School database
        // Consider storing just Course indexes/ Student matricIDs & Staff IDs? In a database you would decompose typically and not store duplicates of Course 
        // objects.
        
        // Instantiate Database Controller
        DatabaseControl db = new DatabaseControl();

        // Update course within Course database
        Course oldCourse = db.getCourseData(courseCode);
        Course newCourse = new Course(courseCode, courseName, schoolName, au, courseIndex);
        db.updateCourseData(courseCode, newCourse);
        System.out.println("Course was successfully updated within Courses!")

        // Update course data within School object (Got to standardise between schoolName and schoolIndex)
        School school = db.getSchoolData(schoolName); // Should be ID
        ArrayList<Course> allCourses = school.getAllCourses();
         for (int i; i < allCourses.size(); i++) {
             if (allCourses.get(i).getCourseCode() == courseCode) { // Course object needs a getter for Course Code
                 allCourses.set(i, newCourse);
                 school.setAllCourses(allCourses);
                db.updateSchoolData(schoolName, school);
                System.out.println("Course was successfully updated within School!");
             } else {
                 System.out.println("Course being updated does not exist! Please update an existing course.");
             }
         }
    }

    public void checkVacancy(String courseCode, String indexCode) {
        
        // Instantiate Database Controller
        DatabaseControl db = new DatabaseControl();

        // Get Course object from Courses
        Course course = db.getCourseData(courseCode); // Assume error handling in database controller?
        HashMap<String, Integer> vacantIndices= course.getVacantIndices(); // Error handling in course?
        boolean isVacant = vacantIndices.containsKey(indexCode);
        if (isVacant) {
            int numVacancy = vacantIndices.get(indexCode);
            System.out.println(courseCode + " has vacancies! " + "Current number of vacancies is " + numVacancy);
        } else {
            System.out.println(courseCode + " is full!");
        }
    }

    public void printCourseStuList(String courseCode) {
        Database db = new DatabaseControl();
        Course course = db.getCourseData(courseCode); // Assume error handling in database controller?
        // Iterate over all indexes in the course
        ArrayList<Student> allStudents;
        // NEED A PUBLIC GETTER FOR INDEXES IN A COURSE
        ArrayList<Index> allIndices = course.getAllIndices();
        for (Index i: allIndices) {
            allStudents.addAll(i.getAllStudents()); // NEED A PUBLIC GETTER FOR STUDENTS ENROLLED IN INDEX
        }
        for (Student s: allStudents) {
            System.out.println(s.getName());
        }
    }

    public void printIndexStuList(String courseCode, String indexCode) {
        Database db = new DatabaseControl();
        Course course = db.getCourseData(courseCode); // Assume error handling in database controller?

        // NEED A PUBLIC GETTER FOR INDEXES IN A COURSE
        Index index = course.findIndex(indexCode);
        ArrayList<Student> indexStu = index.getAllStudents();

        for (Student s : indexStu) {
            System.out.println(s.getName());
        }
    }
}
