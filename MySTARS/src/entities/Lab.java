package entities;

public class Lab extends Lesson{
    private static int count = 1;

    public Lab(String lessonType, String venue, String startTime, String endTime, int day, String week) {
        super("Lab", venue, startTime, endTime, day, week);
        --count;
    }

    @Override
    public boolean modifyTiming(String startTimeNew, String endTimeNew, int day) {
        try {
            this.timings = new WorkingHours(startTimeNew, endTimeNew, day);
            return true;
        } catch (IllegalArgumentException e){
            System.out.println("Timings are not valid.");
            return false;
        }
    }

    @Override
    public boolean modifyVenue(String newVenue) {
        return false;
    }
}
