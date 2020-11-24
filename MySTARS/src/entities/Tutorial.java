package entities;

import java.io.*;  // Import the File class

/**
 * Class Tutorial extends from lesson, and is a specific type of lesson.
 * It has no unique attributes of its own but implements the abstract functions provided by its parent, Lesson.
 * Tutorial also imposes further constraints on the possible venues, and timings for the lesson.
 */
public class Tutorial extends Lesson{

    /**
     *
     * @param venue, which is the location of the lesson
     * @param timings, which is the time during which the lesson takes place.
     * @throws IllegalArgumentException when the lesson duration is not 1 hour, or when the specified venue is not valid for
     *      a tutorial.
     */
    public Tutorial(String venue, WorkingHours timings) throws IllegalArgumentException{
        super("TUT", venue, timings);

        if(!checkValidVenue(venue) || !timings.checkValidTimings(1f)){
            throw new IllegalArgumentException("Invalid venue or timings.");
        }
    }

    /**
     * Function to modify the timing of the tutorial, overidden from Lesson.
     * @param newTimings, which is the new proposed timing for the lesson. In this case, the duration of the lesson
     *                    has to be 1 hour. Any other duration is considered invalid.
     * @return true if the timings are valid and successfully modified without clashing any other timings.
     *         false if the timings are invalid.
     */
    @Override
    public boolean modifyTiming(WorkingHours newTimings) {
        try {
            if(!newTimings.checkValidTimings(1f)){
                return false;
            }

            this.timings = newTimings;
            return true;
        } catch (IllegalArgumentException e){
            System.out.println("Timings are not valid.");
            return false;
        }
    }

    /**
     * modifyVenue() can be used to change the venue of the tutorial, given that the venue is valid for a tutorial.
     * @param newVenue, which is the proposed venue for the lesson.
     * @return true if the venue is valid and successfully changed.
     *         false if the venue is invalid for a tutorial.
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
     * In this case we check if the venue is valid for a tutorial
     *
     * @param newVenue , which is the venue where the lesson is conducted
     * @return, true if the venue is valid for the lesson, false if the venue is not valid for the lesson.
     */
    @Override
    public boolean checkValidVenue(String newVenue) {
        BufferedReader validVenues;

        try {
            validVenues = new BufferedReader(new FileReader("/home/jjkoh/Desktop/y2s1/cz2002/OODP-Project/MySTARS/src/data/tutorialVenues.txt"));

            String temp = validVenues.readLine();
            while (temp != null) {
                if (newVenue.toUpperCase().equals(temp.toUpperCase())) {
                    validVenues.close();
		    System.out.println("Successfully added TUT");
                    return true;
                }
                temp = validVenues.readLine();
            }
        }catch(IOException i){
            System.out.println("Unable to parse file for tutorial.");
            return false;
        }

        return false;
    }
}
