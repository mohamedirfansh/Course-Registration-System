package entities;

public class WorkingHours {
    private static final String[] DayOfWeek = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private int startHour;
    private int endHour;
    private int startMin;
    private int endMin;
    private String day;
    private String week;

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

        if(startMin > Math.abs(startMin - 30)){
            startMin = 0;
        }else{
            startMin = 30;
        }

        if(endMin > Math.abs(endMin - 30)){
            endMin = 0;
        }else{
            endMin = 30;
        }

        this.startHour = startHour;
        this.endHour = endHour;
        this.startMin = startMin;
        this.endMin = endMin;
        this.day = DayOfWeek[day-1];
        this.week = "ALL";
    }

    public WorkingHours(String startTime, String endTime, int day, String week) {
        this(startTime, endTime, day);
        week = week.toUpperCase();
        if(!week.equals("ODD") && !week.equals("EVEN")){
            throw new IllegalArgumentException("Invalid Timings");
        }else{
            this.week = week;
        }
    }

    public String getStartTime(){
        //returns the start time of the lesson as a string
        return String.format("%02d", this.startHour) + ":" + String.format("%02d", this.startMin);
    }

    public String getEndTime(){
        //returns the end time of the lesson as a string
        return String.format("%02d", this.endHour) + ":" + String.format("%02d", this.endMin);
    }

    public String getDay(){
        //returns the day of the lesson as a string
        return day;
    }

    public int getDayNum(){
        for(int i = 0; i < DayOfWeek.length; ++i){
            if(DayOfWeek[i].equals(day)){
                return i;
            }
        }

        return -1;
    }

    public int findDuration(){
        //retrieves the duration of the lesson. This is mainly for error checking
        return endHour - startHour;
    }

    //Move this function to a control class. Leaving here for now for debugging purposes
    public void printWorkingHours(){
        System.out.println("Start Time : " + getStartTime() + ", End Time : " + getEndTime() + ", Day : " + getDay() + ", Duration : " + findDuration() + " hours, Week : " + week);
    }
}
