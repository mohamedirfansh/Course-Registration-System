package controls;

import java.util.HashMap;

public interface Notification {
    boolean sendRegisterSuccessfulMessage(String studID, String courseID, String courseName);
    boolean sendWaitListedMessage(String studID, String courseID, String courseName);
    boolean sendDropCourseMessage(String studID, String courseID, String courseName);
    boolean sendEnrolledCourseListMessage(String studID, HashMap<String, String> courseList);
}
