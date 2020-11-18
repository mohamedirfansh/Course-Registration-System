package entities;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

	/*  This class is used for the access period to the registration system.
		Since each school can have different access periods, the period of
		time that they can access the system is linked to the school. */

public class AccessPeriod {

	private String startDate;		// The starting date & time of the period.
	private String endDate;			// The ending date & time of the period.
	private School school;			// The particular school concerned.


	/*  Method to check if the current system date & time is within the start
		date & end date for the school. If it is within the period, then returns true else false.
	*/
	public boolean isValidPeriod(){
		// Only check if there is a date already available for the start & end dates.
		Date validStartDate;
		Date validEndDate;
		if (startDate != null && endDate != null){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date currentDate = new Date();	// Get the current date
			// Try catch block incase date cannot be parsed properly, 
			// Parse needed to convert the date from string type to date type for comparison
			try {
				validStartDate = dateFormat.parse(this.startDate);
				validEndDate = dateFormat.parse(this.endDate);
				if (currentDate.compareTo(validStartDate) >= 0 && currentDate.compareTo(validEndDate) < 0){
					return true;
				} 
				else {
					return false;
				}
			} catch (ParseException e){
				// Temporary for now, move to boundary class later...
				System.out.println("Cannot parse date");
			}
			// Checking if current date is within the access window
		}
		// Temporary for now, can change to boundary classes later...
		else {
			System.out.println("Cannot get access period for school");
		}
		return false;
	}

	// Getter methods

	public String getStartDate(){
		return startDate;
	}

	public String getEndDate(){
		return endDate;
	}

	public School getSchool(){
		return school;
	}

	// Setter methods

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public void setSchool(School school){
		this.school = school;
	}
}