package entities;

import java.io.*;  // Import the File class

public class Tutorial extends Lesson{
    public Tutorial(String venue, WorkingHours timings) throws IllegalArgumentException{
        super("TUT", venue, timings);

        if(!checkValidVenue(venue) || !timings.checkValidTimings(1f)){
            throw new IllegalArgumentException("Invalid venue or timings.");
        }
    }

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

    @Override
    public boolean modifyVenue(String newVenue) {
        if(checkValidVenue(newVenue)){
            setVenue(newVenue);
            return true;
        }

        return false;
    }

    @Override
    public boolean checkValidVenue(String newVenue) {
        BufferedReader validVenues;

        try {
            validVenues = new BufferedReader(new FileReader("/Users/aneez.jah/Documents/Java Projects/STARS Planner/Venue/tutorialVenues.txt"));

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
            return false;
        }

        return false;
    }
}