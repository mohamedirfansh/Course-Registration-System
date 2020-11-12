package entities;

public class Tutorial extends Lesson{
    private static int count = 1;

    public Tutorial(String lessonType, String venue, String startTime, String endTime, int day) {
        super("Tutorial", venue, startTime, endTime, day);
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
