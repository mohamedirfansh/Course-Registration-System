package entities;

import java.util.ArrayList;
import java.util.List;

public class Course {
    /*
    Class that holds the information regarding courses. Each course has a name, code, credits, school, and a certain number of indices.

    Considerations that haven't been implemented:
    -> Course type (GERPE, UE, CORE, MAJOR PE)
    ->

    Methods:
    Admin:
    -> Add a new index
    -> Modify access timings
    ->

    Student:
    -> View the indices and their available vacancies
    -> Swap index for a particular course
    -> View the students enrolled in each course
     */
    private final String courseName;
    private final String courseCode;
    private final String schoolName;
    private final int au;
    private ArrayList<Index> courseIndex;
    private Lesson[] lectures;

    public Course(String courseName, String courseCode, String schoolName, int au){
        this.courseName = courseName.toUpperCase();
        this.courseCode = courseCode.toUpperCase();
        this.schoolName = schoolName.toUpperCase();
        this.au = au;
        this.courseIndex = new ArrayList<>();
        this.lectures = new Lesson[2];

        System.out.println("Course Added.");
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public int getAu() {
        return au;
    }

    public ArrayList<Index> getCourseIndex() {
        return courseIndex;
    }

    public boolean addIndex(String indexCode, String groupName){
        //index does not exist without the course so it should be initialized here
        if(findIndex(indexCode) >= 0){
            return false;
        }

        courseIndex.add(new Index(indexCode, groupName));
        return true;
    }

    private int findIndex(String indexCode){
        for(int i = 0; i < courseIndex.size(); ++i){
            if(courseIndex.get(i).getIndexCode().equals(indexCode)){
                return i;
            }
        }

        return -1;
    }

    //I think all the below functions should also be in a control class

    public void swapIndex(){
        //function for the student to check the available vacancies and swap current index
    }

    public void enrollmentList(){
        //function to list all the students enrolled in the course, ordered by the index they are enrolled in
    }

    public void listVacancies(){
        //function to list all the vacancies in each index for the students and admin to check vacancies
    }


}
