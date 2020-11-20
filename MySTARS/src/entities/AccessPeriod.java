package entities;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/*  This class is used to store and validate the Access Period belonging to each School
    within our planning system. Schools can then read, write and check if a given start date 
    and end date is valid as a access period. A AccessPeriod object is stored within each School.
*/

public class AccessPeriod {

    private String startDate; // The starting date & time of the period.
    private String endDate; // The ending date & time of the period.

    // Constructor

    public AccessPeriod(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /*
     * Method to check if the current system date & time is within the start date &
     * end date for the school. If it is within the period, then returns true else
     * false.
     */
    
    public boolean isValidPeriod() {
        // Only check if there is a date already available for the start & end dates.
        Date validStartDate;
        Date validEndDate;
        
        if (startDate != null && endDate != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date currentDate = new Date(); // Get the current date
            // Try catch block incase date cannot be parsed properly,
            // Parse needed to convert the date from string type to date type for comparison
            try {
                validStartDate = dateFormat.parse(this.startDate);
                validEndDate = dateFormat.parse(this.endDate);
                boolean isValid = (currentDate.compareTo(validStartDate) >= 0 && currentDate.compareTo(validEndDate) < 0);
                return isValid;
                
            } catch (ParseException e) {
                // Temporary for now, move to boundary class later...
                System.out.println("Cannot parse date; Error: " + e);
            }
            // Checking if current date is within the access window
	} // Added in '}' to close off the if statement (Jun Jie)

        // Temporary for now, can change to boundary classes later...
        else {
            System.out.println("Cannot get access period for school");
            // return false;
        }
	return false; // Shifted the return statement to after the else block
		      // There will be a compiler error if not done like this
		      // because the compiler isn't sure that the method will
		      // definitely return something (Jun Jie)
    }

    // Getter methods

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    // Setter methods

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    // This set method was not in the UML Diagram, I have commented it out for now
    /* public void setSchool(School school) {
        this.school = school;
    } */
}

