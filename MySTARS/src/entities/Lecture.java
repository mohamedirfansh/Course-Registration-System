package entities;

import java.io.*;

/**
 * Class Lecture extends from lesson, and is a specific type of lesson.
 * It has no unique attributes of its own but implements the abstract functions provided by its parent, Lesson.
 * Lecture also imposes further constraints on the possible venues, and timings for the lesson.
 */
public class Lecture extends Lesson{

    /**
     * Constructor for the class which calls the parent constructor
     * @param venue, which is the location of the lesson
     * @param timings, which is the time during which the lesson takes place.
     * @throws IllegalArgumentException when the lesson duration is not between 1 and 3 hours, or when the specified venue is not valid for
     *      a lecture.
     */
    public Lecture(String venue, WorkingHours timings) throws IllegalArgumentException{
        super("LEC", venue, timings);

        if(!timings.checkValidTimings(1f, 3f) || !checkValidVenue(venue)){
            throw new IllegalArgumentException("Invalid venue or timings.");
        }
    }

    /**
     * Function to modify the timing of the tutorial, overidden from Lesson.
     * @param newTimings, which is the new proposed timing for the lesson. In this case, the duration of the lesson
     *                    has to be between 1 and 3 hours. Any other duration is considered invalid.
     * @return true if the timings are valid and successfully modified without clashing any other timings.
     *         false if the timings are invalid.
     */
    @Override
    public boolean modifyTiming(WorkingHours newTimings) {
        if(newTimings.checkValidTimings(1f, 3f)){
            return false;
        }

        this.timings = newTimings;
        return true;
    }

    /**
     * modifyVenue() can be used to change the venue of the lecture, given that the venue is valid for a lecture.
     * @param newVenue, which is the proposed venue for the lesson.
     * @return true if the venue is valid and successfully changed.
     *         false if the venue is invalid for a lecture.
     */
    @Override
    public boolean modifyVenue(String newVenue) {
        if(checkValidVenue(newVenue)){
            setVenue(newVenue);
            return true;
        }

        return false;
    }

    /**
     * Abstract method checkValidVenue() checks if the given venue is valid for the lesson in question.
     * In this case we check if the venue is valid for a lecture.
     *
     * @param newVenue , which is the venue where the lesson is conducted
     * @return, true if the venue is valid for the lesson, false if the venue is not valid for the lesson.
     */
    @Override
    public boolean checkValidVenue(String newVenue) {
        BufferedReader validVenues;
        try {
            validVenues = new BufferedReader(new FileReader("/Users/aneez.jah/Documents/Java Projects/STARS Planner/Venue/lectureVenues.txt"));

            String temp = validVenues.readLine();
            while (temp != null) {
                if (newVenue.toUpperCase().equals(temp.toUpperCase())) {
                    validVenues.close();
                    return true;
                }
                temp = validVenues.readLine();
            }
        }catch(IOException i){
            System.out.println("Unable to parse file.");
        }

        return false;
    }

}
