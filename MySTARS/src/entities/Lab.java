package entities;

import java.io.*;

public class Lab extends Lesson{
    public Lab(String venue, WorkingHours timings) throws IllegalArgumentException{
        super("Lab", venue, timings);

        if(!checkValidVenue(venue) || !timings.checkValidTimings(2f)){
            throw new IllegalArgumentException("Invalid venue or timings.");
        }
    }

    @Override
    public boolean modifyTiming(WorkingHours newTimings) {
        try {
            if(newTimings.checkValidTimings(2f)){
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
            validVenues = new BufferedReader(new FileReader("/Users/aneez.jah/Documents/Java Projects/STARS Planner/Venue/labVenues.txt"));

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