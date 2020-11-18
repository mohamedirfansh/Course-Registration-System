package entities;

import java.io.*;

public class Lecture extends Lesson{
    public Lecture(String venue, WorkingHours timings) throws IllegalArgumentException{
        super("LEC", venue, timings);

        if(!timings.checkValidTimings(1f, 3f) || !checkValidVenue(venue)){
            throw new IllegalArgumentException("Invalid venue or timings.");
        }
    }

    @Override
    public boolean modifyTiming(WorkingHours newTimings) {
        if(newTimings.checkValidTimings(1f, 3f)){
            return false;
        }

        this.timings = newTimings;
        return true;
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
