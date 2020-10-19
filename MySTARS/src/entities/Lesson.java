package entities;

import java.util.*;

public class Lesson {
    private static Scanner scanner = new Scanner(System.in);

    /*
    Class for the entity lesson.
    -> There can be different types of lessons (Lecture/Tutorial/Lab)
    -> Each lesson takes place on certain days and certain times

    Attributes:
    -> LessonID, String : This shows the group
    -> LessonType, String : Classifies the lesson as a lecture, tutorial, or lab lesson.
    -> Venue, String : Specifies where the lesson will take place.
    -> Timings, Map(Dictionary) : A dictionary that holds the day and the corresponding time for a lesson.

    Class Methods:
    -> addTiming(day, time), return type - boolean:
        Method to add a day/time pair for a lesson. It does not allow lessons to take place at the same time.
        If the lesson timings are valid, the timing is added to the dictionary and the method returns true.
        If the lesson timings clash, the timing is invalid and the method returns false.

        Arguments:
            day - String that can hold the day of week
            time - String that contains user entered time. Should be converted to a time object within the method

        Returns:
            boolean - indicating if the operation is successful

    -> removeTiming(day), return type - boolean:
        Method to remove a day/time pair for a lesson.

        Arguments:
            day - day during which the lesson occurs

        returns:
            boolean - indicating if the removal was successful

    -> modifyTiming(day, starttime, endtime)
        Method to modify a lesson timing

        arguments:
            day - day during which the lesson occurs
            starttime - the modified start time
            endtime - the modified endtime

        returns:
            boolean - indicating if the modification was successful

    -> printLessonHours()
        Method that prints all the lesson timings for the current instance.

        returns:
            void

    -> printFullLesson()
        Method to print all the lesson details including the lesson hours.

        returns:
            void

    modifyVenue()
        Method to modify the venue where a lesson takes place.

        returns:
            void
     */


    private String lessonID; //groups
    private String lessonType; //lec/tut/lab
    private String venue; //location
    private String week;
    ArrayList<WorkingHours> timings; //dictionary that holds the lesson day and time

    public Lesson(String lessonID, String lessonType, String venue, String week){
        this.lessonID = lessonID;
        this.lessonType = lessonType;
        this.venue = venue;
        this.week = week;
        timings = new ArrayList<>();
    }

    public String getLessonID() {
        return lessonID;
    }

    public String getLessonType() {
        return lessonType;
    }

    public String getVenue() {
        return venue;
    }

    public ArrayList<WorkingHours> getTimings() {
        return timings;
    }

    public String getWeek() {
        return week;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public boolean addTiming(int day, String startTime, String endTime){
        String[] lessonBegin = startTime.split(":", 2);
        String[] lessonEnd = endTime.split(":", 2);

        int startHour = Integer.parseInt(lessonBegin[0]);
        int endHour = Integer.parseInt(lessonEnd[0]);
        int startMin = Integer.parseInt(lessonBegin[1]);
        int endMin = Integer.parseInt(lessonEnd[1]);

        if(startHour < 8 || startHour > 18 || endHour < 9 || endHour > 20 || (endHour - startHour == 0) || day <= 1 || day >= 7){
            System.out.println("Invalid timings.");
            return false;
        }

        for(int i = 0; i < timings.size(); ++i){
            if(day == timings.get(i).getDayNum()){
                System.out.println("Same lesson cannot occur twice in a day.");
                return false;
            }
        }

        timings.add(new WorkingHours(startHour, endHour, startMin, endMin, day));
        return true;
    }

    public boolean removeTiming(int day){
        //removes the lesson using the day
        for(int i = 0; i < timings.size(); ++i){
            if(day == timings.get(i).getDayNum()){
                timings.remove(i);
                return true;
            }
        }

        return false;
    }

    public boolean modifyTiming(int day, String startTimeNew, String endTimeNew){
        if(removeTiming(day)){
            if(addTiming(day, startTimeNew, endTimeNew)){
                return true;
            }
        }

        return false;
    }

    public void modifyVenue(){
        System.out.print("Enter the new venue: ");
        String venue = scanner.nextLine();

        setVenue(venue);
    }

    public void printLessonHours(){
        for(int i = 0; i < timings.size(); ++i){
            timings.get(i).printWorkingHours();
        }
    }

    public void printFullLesson(){
        System.out.println("Group : " + lessonID + "\nLesson Type : " + lessonType + "\nVenue : " + venue + "\nWeeks : " + week);
        printLessonHours();
    }
}
