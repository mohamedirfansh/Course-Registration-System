package controls;

import java.util.HashMap;

/**
 * A notification interface that contains methods to send different types of messages depending on the use case.
 *
 * Different types of notifications can be added to the interface to expand the use cases.
 */
public interface Notification {

    /**
     * sendRegisterSuccessfulMessage() is used to send a notification to the student in case of successfully registration
     * to a course.
     *
     * @param studID : String, the ID of the user to whom the message needs to be sent.
     * @param courseID : String, the ID of the course the student has been registered to.
     * @param courseName : String, the name of the course the student has been registered to
     * @return true, if the email was sent successfully.
     *          false, if the email failed to send.
     */
    boolean sendRegisterSuccessfulMessage(String studID, String courseID, String courseName);

    /**
     * sendWaitListedMessage() is used to send a notification to a student in the case that the student is added to a waitlist
     * of a course.
     *
     * @param studID : String, the ID of the user to whom the message needs to be sent.
     * @param courseID : String, the ID of the course the student has been waitlisted to.
     * @param courseName : String, the name of the course the student has been waitlisted to.
     * @return true, if the email was sent successfully.
     *          false, if the email failed to send.
     */
    boolean sendWaitListedMessage(String studID, String courseID, String courseName);

    /**
     * sendDropCourseMessage() is used to send a notification to a student when the student drops a course that he/she is
     * registered to.
     *
     * @param studID : String, the ID of the user to whom the message needs to be sent.
     * @param courseID : String, the ID of the course the student has dropped.
     * @param courseName : String, the name of the course the student has dropped.
     * @return true, if the email was sent successfully.
     *          false, if the email failed to send.
     */
    boolean sendDropCourseMessage(String studID, String courseID, String courseName);

    /**
     * sendEnrolledCourseListMessage() is used to send the student a message containing all the courses that he/she is registered to.
     * @param studID : String, the ID of the user to whom the message needs to be sent.
     * @param courseList : HashMap<String, String>, a list of courseID's as a key, and the courseName as a value, to which the
     *                   student is registered.
     * @return true, if the email was sent successfully.
     *          false, if the email failed to send.
     */
    boolean sendEnrolledCourseListMessage(String studID, HashMap<String, String> courseList);
}
