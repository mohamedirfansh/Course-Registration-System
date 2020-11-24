package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable; // need this for object serialization (Jun Jie)

/**
 * The course class is an entity that holds information regarding a course. A school can contain several courses whose data is stored in
 * instances of this class.
 *
 * Class Attributes:
 * -> courseName : String, which is the name of the course.
 * -> courseCode : String, which is a unique code that can be used to identify the course.
 * -> schoolName : String, which is the school that the course belongs to.
 * -> au : int, which is the number of creadits alloted to the course.
 * -> courseIndex : ArrayList<Index>, which is a list of indices or classes within the course.
 * -> lectures : Lesson[2], which is a list of lectures for the course, since lectures are shared by all indices.
 * -> serialVersionUID : long, needed to verify that serialization and de-serialization is done correctly
 */
public class Course implements Serializable {
    private final String courseName;
    private final String courseCode;
    private final String schoolName;
    private final int au;
    private ArrayList<Index> courseIndex;
    private Lesson[] lectures = new Lecture[2];
    private static final long serialVersionUID = 2L;

    /**
     * Class constructor that is used on object instantiation.
     *
     * @param courseName, the name of the course being created.
     * @param courseCode, the unique code for the course being created.
     * @param schoolName, the name of the school to which the course belongs.
     * @param au, the number of credits for the course.
     *            There are certain restrictions on the number of credits in this case. A course cannot have more than 4 or less than 1 credit.
     *
     * @throws IllegalArgumentException, when the parameters passed to the constructor are not valid.
     */
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
    }


    /**
     * addCourseLecture() can be used to add lectures to the course, since lectures are shared for all indices of the course.
     *
     * @param venue, the venue where the lecture will take place
     * @param timings, the timings for the lecture
     * @return true if the lecture was added successfully.
     *          false if the lecture was not added successfully due to invalid timings, or if too many lectures already exist for the course.
     */
    //Remember to add a mechanism in the control class to look for clashes between lectures and other lessons.
    public boolean addCourseLecture(String venue, WorkingHours timings){
        int count = 0;
        int countDuration = 0;
        for(int i = 0; i < lectures.length; ++i){
            if(lectures[i] != null){
                ++count;
                countDuration += timings.findDuration();
            }
        }

        if(count >= 2 || countDuration >= 3){
            return false;
        }

        try {
            lectures[count] = new Lecture(venue, timings);
        }catch(IllegalArgumentException i){
            return false;
        }

        return true;
    }

    /**
     * Getters for the class attributes.
     */
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

    public Lesson[] getLectures() {
        return lectures;
    }

    /**
     * addIndex() can be used to add a new index to the course.
     *
     * @param indexCode, which is the unique identification code for the index
     * @param groupName, which is the name being given to the index.
     * @return true, if the index was successfully added to the course.
     *          false, if the index already exists in the course.
     */
    public boolean addIndex(String indexCode, String groupName){
        if(findIndexPos(indexCode) >= 0 || indexCode == null || groupName == null){
            return false;
        }
        courseIndex.add(new Index(indexCode, groupName));
        return true;
    }

    /**
     * removeIndex() can be used to delete a course index, using its unique identification code.
     *
     * @param indexCode, which is the identifier for the index being removed.
     * @return true, if the index was successfully removed.
     *          false, if the index does not exist.
     */
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

    /**
     * getWaitListSizes() can be used to find the size of the waitList for each index. It uses a hashmap to store the key value
     * pair where the key is the index code, and the value is the number of students in the index waitList.
     *
     * @return a hashmap<String, Integer> containing the number of students in the waitList for each index.
     */
    public HashMap<String, Integer> getWaitListSizes(){
        HashMap<String, Integer> waitLists = new HashMap<>();
        for(Index i : courseIndex){
            if(i.getVacancy() == 0){
                waitLists.put(i.getIndexCode(), i.getWaitList().size());
            }
        }

        return waitLists;
    }

    /**
     * findIndexPos() is a function used by class modifier functions when modifying an index in the course. It can be used to find the
     * position of an index in the arrayList of indices for easy retrieval using the unique index code.
     *
     * @param indexCode, which is the unique identifier for an index within the course.
     * @return an integer representing the position of the index in the array list thus enabling easy access to the index data.
     *          The function returns -1 if the index does not exist in this course.
     */
    public int findIndexPos(String indexCode){
        for(int i = 0; i < courseIndex.size(); ++i){
            if(courseIndex.get(i).getIndexCode().equals(indexCode)){
                return i;
            }
        }

        return -1;
    }

    /**
     * This function is similar to findIndexPos(), but instead of returning the position of the index, it returns the index
     * instance containing all its information.
     *
     * @param indexCode, which is the unique identifier for an index within the course.
     * @return an Index object. Returns null if the index does not exist in the course.
     */
    // Changed from private to public method
    public Index findIndex(String indexCode){
        for(Index i : courseIndex){
            if(i.getIndexCode().toUpperCase().equals(indexCode.toUpperCase())){
                return i;
            }
        }

        return null;
    }

    public String toString() {
        return "Course name: " + this.courseCode + "\tCourse Code " + this.courseName + "\tNumber of AUs " + this.au;
    }
}

