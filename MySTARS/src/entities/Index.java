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

    public Queue<Student> getWaitList() {
        return waitList;
    }

    public ArrayList<Student> getEnrolled() {
        return enrolled;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

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

                case "LEC":
                    if(countLessonsOfType("LEC") >= 2){
                        System.out.println("Lectures already exists for this index.");
                        return false;
                    }

                    temp = new Lecture(venue, timings);
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

    public boolean registerStudent(Student student){
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

    private Student removeStudentFromWaitList(Student student){
        Queue<Student> temp = new LinkedList();
        Student s = null;
        while(!waitList.isEmpty()){
            s = temp.remove();

            if(s.getName().toUpperCase().equals(student.getName().toUpperCase())){
                while(!waitList.isEmpty()){
                    temp.add(waitList.remove());
                }
                break;
            }else{
                temp.add(s);
            }
        }

        waitList = temp;
        return s;
    }

    public boolean deregisterStudent(Student student){
        if(student == null){
            return false;
        }else{
            int pos = findStudentPosInEnrolled(student.getName().toUpperCase());
            int waitListPos = findStudentPosInWaitList(student.getName().toUpperCase());
            if(pos >= 0){
                enrolled.remove(pos);

                if(!waitList.isEmpty()){
                    registerStudent(waitList.remove());
                }else{
                    ++vacancy;
                }
                return true;
            }else if(waitListPos >= 0){
                if(removeStudentFromWaitList(student) != null){
                    return true;
                }
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
                if(temp.getName().toUpperCase().equals(studName.toUpperCase())){
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
                Student temp = enrolledIterator.next();
                if(temp.getName().toUpperCase().equals(studName.toUpperCase())){
                    return pos;
                }
                ++pos;
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

    private int findStudentPosInWaitList(String studName){
        Iterator<Student> waitListIterator = waitList.iterator();
        int pos = 0;

        if(waitList.isEmpty()){
            return -1;
        }else{
            while(waitListIterator.hasNext()){
                if(waitListIterator.next().getName().equals(studName)){
                    return pos;
                }
                ++pos;
            }
        }

        return -1;
    }

    public int countLessonsOfType(String lessonType){
        int count = 0;
        for(Lesson l : lessons){
            if(l.getLessonType().equals(lessonType.toUpperCase())){
                ++count;
            }
        }

        return count;
    }

    private Lesson checkClash(WorkingHours timings){
        for(Lesson l : lessons){
            if(l.getTimings().checkClash(timings)){
                return l;
            }
        }

        return null;
    }
}
