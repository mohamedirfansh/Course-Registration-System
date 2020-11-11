package entities;

import java.util.*;

public class Index {
    private static final int CLASS_SIZE = 30;
    private final String indexCode;
    private final String groupName;
    private int vacancy;
    private Queue<Student> waitList;
    private ArrayList<Student> enrolled;
    private ArrayList<Lesson> lessons;

    public Index(String indexCode, String groupName){
        this.indexCode = indexCode;
        this.groupName = groupName;
        this.vacancy = CLASS_SIZE;
        waitList = new LinkedList<>();
        enrolled = new ArrayList<>();
        lessons = new ArrayList<>();
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

    public boolean addLesson(String lessonType, String venue, String startTime, String endTime, int day){
        try{
            //Lesson does not exist without the index so it is created here when adding it
            lessons.add(new Lesson(lessonType, venue, startTime, endTime, day));
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    public Lesson deleteLesson(String lessonType, int day){
        if(lessons.isEmpty()){
            return null;
        }else {
            for (int i = 0; i < lessons.size(); ++i) {
                if (lessons.get(i).getLessonType().equals(lessonType) && lessons.get(i).getTimings().getDayNum() == day) {
                    Lesson returnLesson = lessons.get(i);
                    lessons.remove(i);
                    return returnLesson;
                }
            }
        }

        return null;
    }

    public boolean registerStudent(Student student){
        //Student relationship with this class is aggregation so it should be passed in from the outside
        if(student == null){
            return false;
        }

        if(findStudentEnrolled(student.getName()) != null){
            System.out.println("Student is already enrolled");
            return false;
        }else if(student == null){
            return false;
        }

        if(vacancy == 0){
            if(addStudentToWaitList(student)){
                System.out.println("Student added to waitlist.");
            }else{
                System.out.println("Student is already in the waitList");
            }
            return false;
        }else{
            --vacancy;
            enrolled.add(student);
            return true;
        }
    }

    private boolean addStudentToWaitList(Student student){
        if(findStudentInWaitList(student.getName()) != null || student == null){
            return false;
        }else{
            waitList.add(student);
            return true;
        }
    }

    private Student removeStudentFromWaitList(){
        return waitList.remove();
    }

    public boolean deregisterStudent(Student student){
        if(student == null){
            return false;
        }else{
            int pos = findStudentPosInEnrolled(student.getName());
            if(pos >= 0){
                enrolled.remove(pos);
                ++vacancy;
                Student temp = waitList.remove();
                if(temp != null){
                    registerStudent(student);
                }
                return true;
            }
        }

        return false;
    }

    private Student findStudentEnrolled(String studName){
        //find a student in the registered list and return the position so that the student can be removed from the arrayList
        ListIterator<Student> enrolledIterator = enrolled.listIterator();

        if(enrolled.isEmpty()){
            return null;
        }else{
            while(enrolledIterator.hasNext()){
                Student temp = enrolledIterator.next();
                if(temp.getName().equals(studName)){
                    return temp;
                }
            }
        }

        return null;
    }

    private int findStudentPosInEnrolled(String studName){
        ListIterator<Student> enrolledIterator = enrolled.listIterator();
        int pos = 0;

        if(enrolled.isEmpty()){
            return -1;
        }else{
            while(enrolledIterator.hasNext()){
                ++pos;
                Student temp = enrolledIterator.next();
                if(temp.getName().equals(studName)){
                    return pos;
                }
            }
        }

        return -1;
    }

    private Student findStudentInWaitList(String studName){
        Iterator<Student> waitListIterator = waitList.iterator();

        if(waitList.isEmpty()){
            return null;
        }else{
            while(waitListIterator.hasNext()){
                Student temp = waitListIterator.next();
                if(temp.getName().equals(studName)){
                    return temp;
                }
            }
        }

        return null;
    }

    public int findStudentPosInWaitList(String studName){
        Iterator<Student> waitListIterator = waitList.iterator();
        int pos = 0;

        if(waitList.isEmpty()){
            return -1;
        }else{
            while(waitListIterator.hasNext()){
                ++pos;
                if(waitListIterator.next().getName().equals(studName)){
                    return pos;
                }
            }
        }

        return -1;
    }

    //Move to control class
    public void printLessons(){
        ListIterator<Lesson> lessonIterator = lessons.listIterator();

        if(lessons.isEmpty()){
            System.out.println("no lessons");
        }else{
            while(lessonIterator.hasNext()){
                lessonIterator.next().printFullLesson();
            }
        }
    }


    //Move to control class
    public void printEnrolled(){
        ListIterator<Student> studentIterator = enrolled.listIterator();

        if(enrolled.isEmpty()){
            System.out.println("No students.");
        }else{
            System.out.println("\nStudents enrolled in index : ");
            while(studentIterator.hasNext()){
                studentIterator.next().printStudentInfo();
            }
        }
    }

    //Move to control class
    public void printWaitList(){
        if(waitList.isEmpty()){
            System.out.println("WaitList empty.");
        }else {
            System.out.println("\nStudents in the WaitList : ");
            for (Student s : waitList) {
//            s.printStudentInfo();
            }
        }
    }

    //Move to control classses
    public void printIndexInfo(){
        System.out.println("Group : " + groupName + ", Number of Students : " + (CLASS_SIZE - vacancy) + ", Number of vacancies : " + vacancy);
    }


    //Move all print functions into control classes
    public void printIndexInfo(boolean printEnrolled, boolean printWaitList, boolean printLessons){
        printIndexInfo();

        if(printEnrolled)
            printEnrolled();

        if(printWaitList)
            printWaitList();

        if(printLessons)
            printLessons();
    }
}

