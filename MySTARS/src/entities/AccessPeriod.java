package entities;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.io.Serializable;

/**
 * This class is used for the access period to the registration system.
 * Since each school can have different access periods, the period of
 * time that they can access the system is linked to the school.
 *
 * Class Attributes:
 * -> startDate : String, The starting date & time of the period.
 * -> endDate : String, The ending date and tiem of the period
 *
 */
public class AccessPeriod implements Serializable {

	private String startDate;
	private String endDate;
	private static final long serialVersionUID = 2L;

	/**
	 * Constructor for the AccessPeriod
	 * @param startDate, the starting date of the access period
	 * @param endDate, the ending date of the access period
	 */
	public AccessPeriod(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * Method to check if the current system date & time is within the start
	 * date & end date for the school. If it is within the period, then returns true else false.
	 * @return A boolean variable of whether the current period is valid for the school.
	 */
	public boolean isValidPeriod(){
		// Only check if there is a date already available for the start & end dates.
		Date validStartDate = null;
		Date validEndDate = null;

		if (startDate != null && endDate != null){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date currentDate = new Date();	// Get the current date
			// Try catch block in case date cannot be parsed properly, 
			// Parse needed to convert the date from string type to date type for comparison
			try {
				validStartDate = dateFormat.parse(this.startDate);
				validEndDate = dateFormat.parse(this.endDate);

				// Checking if current date is within the access window (-ve if currDate is before validEndDate)
				return (currentDate.compareTo(validEndDate) <= 0 && validStartDate.compareTo(validEndDate) < 0);

			} catch (ParseException e){
				System.out.println("Date entered in the wrong format! Please enter a valid date format.");
				return false;
			}

		}
		// Temporary for now, can change to boundary classes later...
		else {
			System.out.println("Cannot get access period for school");
			return false;
		}
	}

	// Getter methods

	/**
	 * Getter method to get the start date of Access Period
	 * @return startDate
	 */
	public String getStartDate(){
		return startDate;
	}

	/**
	 * Getter method to get the end date of Access Period
	 * @return endDate
	 */
	public String getEndDate(){
		return endDate;
	}

	// Setter methods

	/**
	 * Setter method to set the start date of the Access Period
	 * @param startDate
	 */
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	/**
	 * Setter method to set the end date of the Access Period
	 * @param endDate
	 */
	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

}

