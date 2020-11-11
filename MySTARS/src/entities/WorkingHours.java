package entities;

import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class WorkingHours {
    /*
    Class that holds 2 datetime objects: the start time and the end time of a lesson

    Attributes:
        startTime : the start time and day of the lesson
        endTime : the end time and the day of the lesson

    Methods:
        findDuration :
            returns the duration of a lesson in hours.

        printWorkingHours :
            print method to print the start time, end time, day, and the duration of a lesson.

     */

    //enumeration that holds all the days of the week
    private enum DayOfWeek {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    private Calendar startTime = new GregorianCalendar();
    private Calendar endTime = new GregorianCalendar();

    public WorkingHours(String startTime, String endTime, int day) throws IllegalArgumentException{
        String[] lessonBegin = startTime.split(":", 2);
        String[] lessonEnd = endTime.split(":", 2);

        int startHour = Integer.parseInt(lessonBegin[0]);
        int endHour = Integer.parseInt(lessonEnd[0]);
        int startMin = Integer.parseInt(lessonBegin[1]);
        int endMin = Integer.parseInt(lessonEnd[1]);

        if(startHour < 8 || startHour > 18 || endHour < 9 || endHour > 20 || (endHour - startHour == 0) || day <= 1 || day >= 7) {
            throw new IllegalArgumentException("Invalid Timings");
        }

        this.startTime.set(Calendar.HOUR_OF_DAY, startHour);
        this.startTime.set(Calendar.DAY_OF_WEEK, day);
        this.startTime.set(Calendar.SECOND, 0);
        this.startTime.set(Calendar.MINUTE, startMin);
        this.endTime.set(Calendar.HOUR_OF_DAY, endHour);
        this.endTime.set(Calendar.MINUTE, endMin);
        this.endTime.set(Calendar.DAY_OF_WEEK, day);
        this.endTime.set(Calendar.SECOND, 0);
    }

    public String getStartTime(){
        //returns the start time of the lesson as a string
        return startTime.get(Calendar.HOUR_OF_DAY) + ":" + startTime.get(Calendar.MINUTE);
    }

    public String getEndTime(){
        //returns the end time of the lesson as a string
        return endTime.get(Calendar.HOUR_OF_DAY) + ":" + endTime.get(Calendar.MINUTE);
    }

    public String getDay(){
        //returns the day of the lesson as a string
        return DayOfWeek.values()[endTime.get(Calendar.DAY_OF_WEEK) - 1].name();
    }

    public int getDayNum(){
        return endTime.get(Calendar.DAY_OF_WEEK);
    }

    public int findDuration(){
        //retrieves the duration of the lesson. This is mainly for error checking
        return (int) ChronoUnit.HOURS.between(startTime.toInstant(), endTime.toInstant());
    }

    //Move this function to a control class. Leaving here for now for debugging purposes
    public void printWorkingHours(){
        System.out.println("Start Time : " + getStartTime() + ", End Time : " + getEndTime() + ", Day : " + getDay() + ", Duration : " + findDuration() + " hours");
    }
}

