package entities;

import java.util.ArrayList;
import java.util.HashMap;

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

    public Course(String courseName, String courseCode, String schoolName, int au) throws IllegalArgumentException{
        this.courseName = courseName.toUpperCase();
        this.courseCode = courseCode.toUpperCase();
        this.schoolName = schoolName.toUpperCase(); //need to add a check to see if the school exists

        if(au <= 4 && au >= 1) {
            this.au = au;
        }else{
            throw new IllegalArgumentException("Invalid Arguments.");
        }

        this.courseIndex = new ArrayList<>();

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
        if(findIndexPos(indexCode) >= 0){
            return false;
        }
        courseIndex.add(new Index(indexCode, groupName));
        return true;
    }

    public boolean removeIndex(String indexCode){
        int pos = findIndexPos(indexCode);
        if(pos == -1){
            System.out.println("Index does not exist.");
        }else{
            courseIndex.remove(pos);
            return true;
        }

        return false;
    }

    public HashMap<String, Integer> getVacantIndices(){
        HashMap<String, Integer> vacantIndex = new HashMap<>();
        for(Index i : courseIndex){
            if(i.getVacancy() > 0){
                vacantIndex.put(i.getIndexCode(), i.getVacancy());
            }
        }

        return vacantIndex;
    }

    public HashMap<String, Integer> getWaitListSizes(){
        HashMap<String, Integer> waitLists = new HashMap<>();
        for(Index i : courseIndex){
            if(i.getVacancy() == 0){
                waitLists.put(i.getIndexCode(), i.getVacancy());
            }
        }

        return waitLists;
    }

    private int findIndexPos(String indexCode){
        for(int i = 0; i < courseIndex.size(); ++i){
            if(courseIndex.get(i).getIndexCode().equals(indexCode)){
                return i;
            }
        }

        return -1;
    }

    private Index findIndex(String indexCode){
        for(Index i : courseIndex){
            if(i.getIndexCode().toUpperCase().equals(indexCode.toUpperCase())){
                return i;
            }
        }

        return null;
    }
}
