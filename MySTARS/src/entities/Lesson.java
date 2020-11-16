package entities;

public abstract class Lesson {
    private final String lessonType; //lec/tut/lab
    private String venue; //location
    WorkingHours timings; //dictionary that holds the lesson day and time

    public Lesson(String lessonType, String venue, String startTime, String endTime, int day){
        this.lessonType = lessonType;
        this.venue = venue;
        this.timings = new WorkingHours(startTime, endTime, day);
    }

    public Lesson(String lessonType, String venue, String startTime, String endTime, int day, String week){
        this.lessonType = lessonType;
        this.venue = venue;
        this.timings = new WorkingHours(startTime, endTime, day, week);
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

    //Consider moving this function to a control class instead of keeping it in the entity
    public void printLessonHours(){
        timings.printWorkingHours();
    }

    //Consider moving this function to a control class instead of keeping it in the entity
    public void printFullLesson(){
        System.out.println("\nLesson Type : " + lessonType + "\nVenue : " + venue);
        printLessonHours();
    }

    public abstract boolean modifyTiming(String startTimeNew, String endTimeNew, int day);
    public abstract boolean modifyVenue(String newVenue);
}
