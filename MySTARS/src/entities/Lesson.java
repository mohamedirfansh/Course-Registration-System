package entities;

public abstract class Lesson {
    private final String lessonType; //lec/tut/lab
    private String venue; //location
    WorkingHours timings; //dictionary that holds the lesson day and time

    public Lesson(String lessonType, String venue, WorkingHours timings){
        this.lessonType = lessonType;
        this.venue = venue;
        this.timings = timings;
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

    public abstract boolean checkValidVenue(String newVenue);
    public abstract boolean modifyTiming(WorkingHours newTimings);
    public abstract boolean modifyVenue(String newVenue);

}
