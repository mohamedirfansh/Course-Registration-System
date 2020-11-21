package entities;

/**
 * Lesson is an abstract class that can be used to extend to different types of classes in terms of this application.
 *
 * Class Attributes:
 * -> lessonType : String, which holds the type of class in the lesson
 * -> venue : String, which holds the location where the class is being held
 * -> timings : WorkingHours, whcih holds the time during which the lesson takes place
 */

public abstract class Lesson {
    private final String lessonType; //lec/tut/lab
    private String venue; //location
    WorkingHours timings; //dictionary that holds the lesson day and time

    /**
     * Class constructor called by the children of the class upon instantiation of the child classes.
     *
     * @param lessonType
     * @param venue
     * @param timings
     */
    public Lesson(String lessonType, String venue, WorkingHours timings){
        this.lessonType = lessonType;
        this.venue = venue;
        this.timings = timings;
    }

    /**
     * Getters for the different class attributes.
     */

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

    /**
     * Abstract method checkValidVenue() checks if the given venue is valid for the lesson in question.
     * Different children may implement this method differently.
     *
     * @param newVenue , which is the venue where the lesson is conducted
     * @return, true if the venue is valid for the lesson, false if the venue is not valid for the lesson.
     */
    public abstract boolean checkValidVenue(String newVenue);

    /**
     * Abstract method modifyTiming is used to modify the time during which a lesson takes place.
     * Different types of lessons may have different timings. Hence different children may implement this method
     * differently.
     *
     * @param newTimings, which is the new proposed timing for the lesson.
     * @return true if the timings were successfully modified, false if the timings are invalid.
     */
    public abstract boolean modifyTiming(WorkingHours newTimings);

    /**
     * Abstract method modifyVenue() can be used to modify the venue of the lesson. Only certain venues may be applicable to
     * certain lessons, hence children may implement this method differetnly with this restriction in mind.
     *
     * @param newVenue, which is the proposed venue for the course.
     * @return true if the venue was valid and successfully modified, false if the venue is invalid.
     */
    public abstract boolean modifyVenue(String newVenue);

}
