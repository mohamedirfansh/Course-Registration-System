package entities;

import java.io.Serializable;
import java.util.*;

/**
 * Index contains the information about a particular index in the course. Each course has a list of indices
 * with different lesson timings per index.
 *
 * Class Attributes:
 * -> CLASS_SIZE: int, which specifies the class size for each index, or how many students can be enrolled in one index.
 *                  This value is a constant shared between all instances.
 * -> indexCode: String, which is a unique ID that can be used to identify a particular index within a course. Example: 10110
 * -> groupName: String, which is a name given to the index. Example : SS3
 * -> vacancy: int, which stores the number of vacancies remaining in this particular index instance. When the index is created
 *              vacancy = CLASS_SIZE
 * -> waitList : Queue<String>, which stores a list of student ID's that are currently in the waitList, waiting to get registered
 *                 to the index.
 * -> enrolled : ArrayList<String>, which stores a list of student ID's that are currently regsitered to the course.
 * -> lessons : ArrayList<Lesson>, which is a list of lessons and its timings for this index instance.
 */
public class Index implements Serializable {
    private static final int CLASS_SIZE = 10;
    private final String indexCode;
    private final String groupName;
    private int vacancy;
    private Queue<String> waitList;
    private ArrayList<String> enrolled;
    private ArrayList<Lesson> lessons;
    public static final long serialVersionUID = 2L;

    /**
     * Class constructor which is used to create an index for a course.
     * @param indexCode, the unique identification string for an index within a course. A course cannot have duplicate indices.
     * @param groupName, A name given to the index.
     *
     * The waitList, enrolled, and lesson list are also initialized in the constructor to empty lists.
     */
    public Index(String indexCode, String groupName){
        this.indexCode = indexCode;
        this.groupName = groupName;
        this.vacancy = CLASS_SIZE;
        waitList = new LinkedList<>();
        enrolled = new ArrayList<>();
        lessons = new ArrayList<>();
    }

    public String getFrontOfWaitList(){
        if(waitList.isEmpty()){
            return null;
        }

        return waitList.remove();
    }

    /**
     * Getters for the class attributes.
     */
    
    public int getClassSize() {
    	return CLASS_SIZE;
    }

    public String getIndexCode() {
        return indexCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getVacancy() {
        return vacancy;
    }

    public Queue<String> getWaitList() {
        return waitList;
    }

    public ArrayList<String> getEnrolled() {
        return enrolled;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    /**
     * addLesson() can be used to add a new lesson to the index. The function also imposes certain constraints on adding
     * lessons within the same index. It creates a new lesson for the index using the parameters passed to it.
     *
     * @param lessonType, which is the type of lesson being added to the index (lecture/lab/tutorial etc.).
     *                    An index can only have a certain number of each type of course, so the number of lessons of each type
     *                    cannot grow without bound.
     *                    This attribute can only hold certain values for lesson types that exist.
     *                    Example : Tutorial has to be specified as "tut"
     *                              Lab has to be specified as "lab"
     *                              Lecture has to be specified as "lec"
     *
     *                    When new lesson types are added this function can be extended by adding another switch case
     *                    for the new lesson type.
     *
     * @param venue, which is the location where the lesson will occur.
     * @param timings, which is the time during which the lesson will occur. Two lessons within an index cannot occur
     *                 at the same time.
     *
     * @return true, if the lesson was added successfully without any conflicts.
     *          false, if the lesson was not added successfully. This could be due to time conflicts, or because
     *          there the limit for this type of lesson has been reached.
     */
    public boolean addLesson(String lessonType, String venue, WorkingHours timings){
        Lesson temp = checkClash(timings);
        if(temp != null){
            System.out.println("Timings clash with " + temp.getLessonType());
            return false;
        }

        try {
            switch (lessonType.toUpperCase()) {
                case "TUT":
                    if(countLessonsOfType("TUT") >= 1){
                        System.out.println("Tutorial already exists for this index.");
                        return false;
                    }

                    temp = new Tutorial(venue, timings);
                    lessons.add(temp);
                    break;

                case "LAB":
                    if(countLessonsOfType("LAB") >= 1){
                        System.out.println("Labs already exists for this index.");
                        return false;
                    }

                    temp = new Lab(venue, timings);
                    lessons.add(temp);
                    break;

                default:
                    System.out.println("Invalid lesson type.");
            }
        }catch(IllegalArgumentException e){
            return false;
        }

        return true;
    }


    /**
     * deleteLesson() can be used to remove a lesson from an index. The lesson can be uniquely identified by its lesson type, and the
     * day on which it occurs. Since the same lesson cannot occur twice on the same day.
     *
     * @param lessonType, which is the type of lesson being deleted. Example : lec, tut, lab etc.
     * @param day, which is the day number on which the lesson occurs. Example : day 2 corresponds to Monday, the second day of the week.
     *
     * @return A Lesson instance, which can be used to identify the lesson that is deleted. If the function returns null, it indicates that the lesson
     *          to be deleted does not exist.
     */
    public Lesson deleteLesson(String lessonType, int day){
        if(lessons.isEmpty()){
            return null;
        }else {
            for (int i = 0; i < lessons.size(); ++i) {
                if (lessons.get(i).getLessonType().toUpperCase().equals(lessonType.toUpperCase()) && lessons.get(i).getTimings().getDayNum() == day) {
                    Lesson returnLesson = lessons.get(i);
                    System.out.println(returnLesson.getLessonType());
                    lessons.remove(i);
                    return returnLesson;
                }
            }
        }

        return null;
    }


    /**
     * The registerStudent() function can be used to enroll a student to a course index. The index can only hold
     * a number of students equal to the CLASS_SIZE.
     *
     * When this limit is exceeded, students that try to enroll are added to a queue waitList. Students on the waitlist are automatically
     * enrolled to the course in a FCFS fashion when a student who is enrolled drops the course.
     *
     * @param studID, the student ID of the student trying to register to the course.
     * @return true if the student has successfully registered to the course.
     *          false if the student failed to enroll to the course and was added to the waitlist. It also returns false if the student ID passed is a null value.
     *
     * As students are enrolled to the course, the course vacancies are also updated.
     */
    public boolean registerStudent(String studID){
        if(studID == null){
            return false;
        }

        if(findStudentEnrolled(studID.toUpperCase()) != null){
            System.out.println("Student is already enrolled");
            return false;
        }else if(studID == null || vacancy == 0){
            return false;
        }

        --vacancy;
        enrolled.add(studID);
        return true;
    }

    /**
     * addStudentToWaitList() is a private class function that is used to place a student at the end of the waitlist.
     * It is a private method that is called by the registerStudent() function, when the index has no more vacancies when a student attempts to enroll
     * to the index.
     *
     * @param studID, which is the ID of the student that failed to register and needs to be added to the waitList.
     * @return true, if the student was added to the waitList successfully.
     *          False, if the student already exists in the waitList or if the student ID passed is invalid.
     */
    public boolean addStudentToWaitList(String studID){
        if(findStudentInWaitList(studID) != null || studID == null){
            return false;
        }else{
            waitList.add(studID);
            return true;
        }
    }

    /**
     * The removeStudentFromWaitList() function can be used to remove a student from the waitList queue, given that the student exists in the queue.
     * It is a private function that is called by the deregisterStudent() function when a student tries to deregister from the index. If the student does
     * not exist in the enrolled list, the waitList is checked to see if the student is in the waitList.
     *
     * @param studID, which is the ID of the student trying to deregister from the course
     * @return the student ID of the student that was removed from the waitList. Returns null if the student did not exist in the waitList.
     */
    public boolean removeStudentFromWaitList(String studID){
        Queue<String> temp = new LinkedList();
        String s;
        while(!waitList.isEmpty()){
            s = waitList.remove();

            if(s.toUpperCase().equals(studID.toUpperCase())){
                while(!waitList.isEmpty()){
                    temp.add(waitList.remove());
                }
                waitList = temp;
                return true;
            }else{
                temp.add(s);
            }
        }

        waitList = temp;

        return false;
    }

    /**
     * A function used to print the waitList of students.
     */
    public void printWaitList(){
        Queue<String> temp = new LinkedList();
        while(!waitList.isEmpty()){
            String s = waitList.remove();
            System.out.println(s);
            temp.add(s);
        }

        waitList = temp;
    }

    /**
     * deregisterStudent() can be used to deregister a student from the index, given that the student exists in the enrolled list or the waitlist.
     * When a student is removed from the enrolled list, the student at the front of the waitList queue is registered to the course.
     *
     * @param studID, the student ID of the student being deregistered.
     * @return true if the student was found in the enrolled list and was removed successfully.
     *          If the student if not found in the enrolled list, the function returns false and two cases can occur.
     *              1. The waitList is searched to find the student, and if the student is found the student is removed from the waitlist.
     *              2. The waitList is searched and the student is not found in the waitList, and false is returned.
     *
     * The index vacancies are updated accordingly when the students are added and removed from the enrolled list and waitList.
     */
    public boolean deregisterStudent(String studID){
        if(studID == null){
            return false;
        }else{
            int pos = findStudentPosInEnrolled(studID.toUpperCase());
            if(pos >= 0) {
                enrolled.remove(pos);
                ++vacancy;

                return true;
            }
        }

        return false;
    }

    /**
     * findStudentEnrolled is a function that is primarily used to check if a student exists in the enrolled list. It is
     * a private method that is called by other functions in the class for error handling purposes.
     *
     * @param studID, the student ID of the student being searched for in the enrolled list.
     * @return the studentID of the student if the student is found in the list.
     */
    public String findStudentEnrolled(String studID){
        //find a student in the registered list and return the position so that the student can be removed from the arrayList
        ListIterator<String> enrolledIterator = enrolled.listIterator();

        if(enrolled.isEmpty()){
            return null;
        }else{
            while(enrolledIterator.hasNext()){
                String temp = enrolledIterator.next();
                if(temp.toUpperCase().equals(studID.toUpperCase())){
                    return temp;
                }
            }
        }

        return null;
    }

    /**
     * findStudentPosInEnrolled() is a function that is primarily used to check if a student exists in the enrolled list.
     * It is used to find the position of the enrolled student in the arrayList, in case the student needs to be removed from the list.
     *
     * @param studID, the student ID of the student being searched for in the enrolled list.
     * @return the positon of the student in the arraylist of enrolled students. Returns -1 if the student does not exist in the arrayList.
     */
    public int findStudentPosInEnrolled(String studID){
        ListIterator<String> enrolledIterator = enrolled.listIterator();
        int pos = 0;

        if(enrolled.isEmpty()){
            return -1;
        }else{
            while(enrolledIterator.hasNext()){
                String temp = enrolledIterator.next();
                if(temp.toUpperCase().equals(studID.toUpperCase())){
                    return pos;
                }
                ++pos;
            }
        }

        return -1;
    }

    /**
     * findStudentEnrolled is a function that is primarily used to check if a student exists in the waitList. It is
     * a private method that is called by other functions in the class for error handling purposes.
     *
     * @param studID, the student ID of the student being searched for in the waitList.
     * @return the studentID of the student if the student is found in the waitList.
     */
    public String findStudentInWaitList(String studID){
        Iterator<String> waitListIterator = waitList.iterator();

        if(waitList.isEmpty()){
            return null;
        }else{
            while(waitListIterator.hasNext()){
                String temp = waitListIterator.next();
                if(temp.equals(studID)){
                    return temp;
                }
            }
        }

        return null;
    }

    /**
     * findStudentPosInEnrolled() is a function that is primarily used to check if a student exists in the waitList.
     * It is used to find the position of the enrolled student in the queue, in case the student needs to be removed from the list.
     *
     * @param studID, the student ID of the student being searched for in the waitList.
     * @return the positon of the student in the queue of waitListed students. Returns -1 if the student does not exist in the queue.
     */
    public int findStudentPosInWaitList(String studID){
        Iterator<String> waitListIterator = waitList.iterator();
        int pos = 0;

        if(waitList.isEmpty()){
            return -1;
        }else{
            while(waitListIterator.hasNext()){
                if(waitListIterator.next().equals(studID)){
                    return pos;
                }
                ++pos;
            }
        }

        return -1;
    }

    /**
     * countLessonsOfType() is used to count the number of lessons of each type in the index. It is primarily used to check for errors
     * when adding new lessons, and to check if a certain lesson threshold is being exceeded.
     *
     * @param lessonType, which is the type of lesson that needs to be counted.
     * @return an integer which indicates the number of lessons of the specified type currently in the index.
     */
    public int countLessonsOfType(String lessonType){
        int count = 0;
        for(Lesson l : lessons){
            if(l.getLessonType().equals(lessonType.toUpperCase())){
                ++count;
            }
        }

        return count;
    }

    /**
     * checkClash() is called when adding or modifying lesson timings within an index, to check if the lesson clashes with any other lesson
     * in the lesson list.
     *
     * @param timings, which is the new timing being added to the index.
     * @return the lesson that clashes with the timings. Null if there is no lesson that clashes with the new timings.
     */
    private Lesson checkClash(WorkingHours timings){
        for(Lesson l : lessons){
            if(l.getTimings().checkClash(timings)){
                return l;
            }
        }

        return null;
    }
}
