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

    public WorkingHours(String startTime, String endTime, int day, String week) throws IllegalArgumentException {
        this(startTime, endTime, day);
        week = week.toUpperCase();
        if(!week.equals("ODD") && !week.equals("EVEN")){
            throw new IllegalArgumentException("Invalid Timings");
        }else{
            this.week = week;
        }
    }

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

    public String getStartTime(){
        //returns the start time of the lesson as a string
        return String.format("%02d", this.startHour) + ":" + String.format("%02d", this.startMin);
    }

    public String getEndTime(){
        //returns the end time of the lesson as a string
        return String.format("%02d", this.endHour) + ":" + String.format("%02d", this.endMin);
    }

    public String getDay(){
        return day;
    }

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

    public boolean checkValidTimings(float minDuration, float maxDuration){
        if(this.findDuration() >= minDuration && this.findDuration() <= maxDuration){
            return true;
        }

        return false;
    }

    public boolean checkValidTimings(float duration){
        if(this.findDuration() == duration){
            return true;
        }

        return false;
    }
}
