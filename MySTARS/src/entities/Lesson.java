package entities;

//Need to make this class an interface or an abstract class to enable easier extension to add new types of lessons

public class Lesson {
    private final String lessonType; //lec/tut/lab
    private String venue; //location
    WorkingHours timings; //dictionary that holds the lesson day and time

    public Lesson(String lessonType, String venue, String startTime, String endTime, int day){
        this.lessonType = lessonType;
        this.venue = venue;
        this.timings = new WorkingHours(startTime, endTime, day);
    }

    public String getLessonType() {
        return lessonType;
    }

    public String getVenue() {
        return venue;
    }

    public WorkingHours getTimings() {
        return timings;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public boolean modifyTiming(String startTimeNew, String endTimeNew, int day){
        try {
            this.timings = new WorkingHours(startTimeNew, endTimeNew, day);
            return true;
        } catch (IllegalArgumentException e){
            System.out.println("Timings are not valid.");
            return false;
        }
    }

    public void modifyVenue(String venue){
        this.venue = venue;
    }


    //Consider moving this function to a control class instead of keeping it in the entity
    public void printLessonHours(){
        timings.printWorkingHours();
    }


    //Consider moving this function to a control class instead of keepin git in the entity
    public void printFullLesson(){
        System.out.println("\nLesson Type : " + lessonType + "\nVenue : " + venue);
        printLessonHours();
    }
}
