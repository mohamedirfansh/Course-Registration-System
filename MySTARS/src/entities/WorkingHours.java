package entities;

/**
 * The WorkingHours class stores a time range, and has custom functions to check the validity of the timings passed to it, as well as to convert them into a usable format.
 * The validity of the timings are determined specific to the application being used. In this case, the use case scenario is for
 * lesson timings.
 *
 * Class Attributes :
 * – startHour : int, holds the starting hour in 24-hour format
 * – endHour : int, holds the ending hour in 24-hour format
 * – startMin : int, holds the minute of the start hour
 * – endMin : int, holds the minute of the end hour
 * – day : String, holds the day on which the lesson occurs
 * – week : String, holds one of three values [ODD, EVEN, ALL], indicating on which weeks the lesson occurs.
 * – DayOfWeek : enum, contains all the days in the week.
 */

public class WorkingHours {
    private static final String[] DayOfWeek = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private int startHour;
    private int endHour;
    private int startMin;
    private int endMin;
    private String day;
    private String week;


    /**
     * Class constructor which is called upon object creation
     *
     * @param startTime : String, a time of day given in 24-hour format indicating the starting time. Example : "12:30"
     * @param endTime : String, a time of day given in 24-hour format indicating the ending time.
     * @param day : int, an integer value for the day of the week where the time range applies. Example : 1 (Sunday), 2 (Monday).
     * @throws IllegalArgumentException if any of the arguments passed do not satisfy certain constraints. These contstraints are highlighted below.
     *      In the current implementation, the following constraints apply:
     *          -> The time start before 8:00, or end after 20:00
     *          -> The minutes have to be in the range of 0-59, as any other value for minute is invalid.
     *          -> The time range cannot occur on weekends.
     *          -> The ending time cannot be lower than the starting time.
     *          -> The week cannot hold values besides ["ALL", "EVEN", "ODD"]
     *
     * In addition to this, the constructor also rounds off all time ranges to the nearest 30 minutes.
     */

    public WorkingHours(String startTime, String endTime, int day) throws IllegalArgumentException{
        String[] lessonBegin = startTime.split(":", 2);
        String[] lessonEnd = endTime.split(":", 2);

        int startHour = Integer.parseInt(lessonBegin[0]);
        int endHour = Integer.parseInt(lessonEnd[0]);
        int startMin = Integer.parseInt(lessonBegin[1]);
        int endMin = Integer.parseInt(lessonEnd[1]);

        if(startHour < 8 || startHour > 18 || endHour < 9 || endHour > 20 || (endHour - startHour == 0) || day <= 1 || day >= 7 ||
                startMin > 59 || startMin < 0 || endMin > 59 || endMin < 0) {
            throw new IllegalArgumentException("Invalid Timings");
        }

        if(startMin != 30 && startMin != 0) {
            if (startMin > 45) {
                ++startHour;
                startMin = 0;
            } else if (startMin < 15) {
                startMin = 0;
            }else{
                startMin = 30;
            }
        }

        if(endMin != 30 && endMin != 0) {
            if (startMin > 45) {
                ++endHour;
                endMin = 0;
            } else if (endMin < 15) {
                endMin = 0;
            }else{
                endMin = 30;
            }
        }

        if(endHour - startHour <= 0){
            throw new IllegalArgumentException("Duration should be at least 1 hour.");
        }

        this.startHour = startHour;
        this.endHour = endHour;
        this.startMin = startMin;
        this.endMin = endMin;
        this.day = DayOfWeek[day-1];
        this.week = "ALL";
    }


    /**
     * Overloaded class constructor, with the additional parameters described below.
     * @param week : String, indicates if the time range applies on all, even, or odd weeks.
     * @throws IllegalArgumentException, when the below constraint is violated.
     *      -> The week cannot hold values besides ["ALL", "EVEN", "ODD"]
     */
    public WorkingHours(String startTime, String endTime, int day, String week) throws IllegalArgumentException {
        this(startTime, endTime, day);
        week = week.toUpperCase();
        if(!week.equals("ODD") && !week.equals("EVEN")){
            throw new IllegalArgumentException("Invalid Timings");
        }else{
            this.week = week;
        }
    }

    /**
     * Getters for the member attributes.
     */

    public static String[] getDayOfWeek() {
        return DayOfWeek;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getStartMin() {
        return startMin;
    }

    public int getEndMin() {
        return endMin;
    }

    /**
     * Function to retrieve the start time in string format.
     * @return a string showing the starting time of the time range in 24-Hour format
     */
    public String getStartTime(){
        //returns the start time of the lesson as a string
        return String.format("%02d", this.startHour) + ":" + String.format("%02d", this.startMin);
    }

    /**
     * Function to retrive the end time in string format.
     * @return a string showing the ending time of the range in 24-hour format
     */
    public String getEndTime(){
        //returns the end time of the lesson as a string
        return String.format("%02d", this.endHour) + ":" + String.format("%02d", this.endMin);
    }

    public String getDay(){
        return day;
    }

    /**
     * Function to retrieve the day as an integer
     * @return an integer value indicating which day of the week the range is implemented in.
     */
    public int getDayNum(){
        for(int i = 0; i < DayOfWeek.length; ++i){
            if(DayOfWeek[i].equals(day)){
                return i + 1;
            }
        }

        return -1;
    }

    public String getWeek(){
        return this.week;
    }

    /**
     * Function to find the duration of the time rage of the current instance.
     * @return a float value which holds the duration of the range. Example : 1.5 hours for 1 hour and 30 minute range.
     */
    public float findDuration(){
        //retrieves the duration of the lesson. This is mainly for error checking
        int hours = endHour - startHour;
        float minutes;

        if(startMin > endMin){
            hours -= 1;
            minutes = startMin - endMin;
        }else{
            minutes = endMin - startMin;
        }
        minutes /= 60;

        return (float)hours + minutes;
    }

    /**
     * Function that can be used to compare two given time ranges and check if they clash.
     * @param timings : WorkingHours, this argument holds the timings that we want to compare to the timings of the current instance.
     * @return boolean true for a clash in timings, and false if the timings do not clash.
     */
    public boolean checkClash(WorkingHours timings){
        if(this.getDayNum() != timings.getDayNum()){
            return false;
        }

        if(timings.getEndHour() <= this.startHour){
            if(timings.getEndMin() <= this.startMin){
                return false;
            }
        }else if(timings.getStartHour() >= this.endHour){
            if(timings.getStartMin() >= this.endMin){
                return false;
            }
        }

        return true;
    }

    /**
     * checkValidTimings() checks if the time range satisfies a certain duration range
     * @param minDuration : float, which is the lower bound of the time range duration
     * @param maxDuration : float, which is the upper bound of the time range duration
     * @return a boolean indicated if the timings adhere to the upper and lower bounds of the duration passed to the function
     */
    public boolean checkValidTimings(float minDuration, float maxDuration){
        if(this.findDuration() >= minDuration && this.findDuration() <= maxDuration){
            return true;
        }

        return false;
    }

    /**
     * checkValidTimings() checks if the time range is of a certain duration. While in the previous case
     * the duration could be within a range of values, in this case the duration has to be a certain value.
     * @param duration : float, which is the duration that the time range must adhere to
     * @return boolean indicating if the duration is satisfied.
     */
    public boolean checkValidTimings(float duration){
        if(this.findDuration() == duration){
            return true;
        }

        return false;
    }
}