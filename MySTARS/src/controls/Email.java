package controls;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Properties;

/**
 * Class email is a specific implementation of the notification interface, which has been adapted to send emails notifications
 * to the students.
 *
 * Class Attributes:
 * -> properties : Properties, a hashmap of properties to initialize the host and port
 * -> session : Session, which is used to authenticate and hold an instance of the sender email and password, which
 *              is the system email and password in this case.
 * -> message : Message, which is the actual email to be sent, and inlcudes the email body and subject, and other email attributes.
 * -> senderEmail : String, which is the system email from which the email will be sent.
 * -> senderPassword : String, which is the password to the system email from which the email will be sent.
 */
public class Email implements Notification{
    private static Properties properties;
    private static Session session;
    private Message message;
    private static final String senderEmail = "ss3mystars@gmail.com";
    private static final String senderPassword = "groupss3";
    private static final String START_MESSAGE = "Preparing to send email...\n";
    private static final String END_MESSAGE = "Email send successfully.\n";

    /**
     * Class constructor which initializes the properties and email session.
     */
    public Email() {
        properties = new Properties();
        properties.put("mail.smtp.auth" , "true");
        properties.put("mail.smtp.starttls.enable" , "true");
        properties.put("mail.smtp.host" , "smtp.gmail.com");
        properties.put("mail.smtp.port" , "587");

        session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
    }

    /**
     * Sends an email to the student containing the course that he/she is successfully registered to.
     * @param studID : String, the ID of the user to whom the email needs to be sent.
     * @param courseID : String, the ID of the course the student has been registered to.
     * @param courseName : String, the name of the course the student has been registered to
     * @return true, if the email is sent successfully,
     *          false, if the email failed to send
     */
    @Override
    public boolean sendRegisterSuccessfulMessage(String studID, String courseID, String courseName) {
        System.out.println(START_MESSAGE);
        String recipient = studID + "@e.ntu.edu.sg";

        String subject = "MySTARS : Course Successfully Registered";
        String messageBody = "You have successfully been registered to the following course(s):\n";
        messageBody += "Course ID\tCourse Name\n";
        messageBody += "-------------------------------------------------------------------------------------\n";
        messageBody += courseID + "\t\t" + courseName + "\n";
        messageBody += "-------------------------------------------------------------------------------------\n";

        message = createMessage(recipient, subject, messageBody);

        if(message == null){
            return false;
        }else{
            try{
                Transport.send(message);
                System.out.println(END_MESSAGE);
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }


    /**
     * Sends an email to the student containing the course that he/she is waitlisted to.
     * @param studID : String, the ID of the user to whom the email needs to be sent.
     * @param courseID : String, the ID of the course the student has been waitlisted to.
     * @param courseName : String, the name of the course the student has been waitlisted to.
     * @return true, if the email is sent successfully.
     *          false, if the email failed to send.
     */
    @Override
    public boolean sendWaitListedMessage(String studID, String courseID, String courseName) {
        System.out.println(START_MESSAGE);
        String recipient = studID + "@e.ntu.edu.sg";

        String subject = "MySTARS : Added to course waitlist";
        String messageBody = "You have been waitlisted to the following course(s):\n";
        messageBody += "Course ID\tCourse Name\n";
        messageBody += "-------------------------------------------------------------------------------------\n";
        messageBody += courseID + "\t\t" + courseName + "\n";
        messageBody += "-------------------------------------------------------------------------------------\n";

        message = createMessage(recipient, subject, messageBody);

        if(message == null){
            return false;
        }else{
            try{
                Transport.send(message);
                System.out.println(END_MESSAGE);
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }


    /**
     *
     * @param studID : String, the ID of the user to whom the email needs to be sent.
     * @param courseID : String, the ID of the course the student has dropped.
     * @param courseName : String, the name of the course the student has dropped.
     * @return true, if the email is sent successfully.
     *          false, if the email failed to send.
     */
    @Override
    public boolean sendDropCourseMessage(String studID, String courseID, String courseName) {
        System.out.println(START_MESSAGE);
        String recipient = studID + "@e.ntu.edu.sg";

        String subject = "MySTARS : Course Dropped Successfully";
        String messageBody = "You have dropped the following course(s):\n\n";
        messageBody += "Course ID\tCourse Name\n";
        messageBody += "-------------------------------------------------------------------------------------\n";
        messageBody += courseID + "\t\t" + courseName + "\n";
        messageBody += "-------------------------------------------------------------------------------------\n";

        message = createMessage(recipient, subject, messageBody);

        if(message == null){
            return false;
        }else{
            try{
                Transport.send(message);
                System.out.println(END_MESSAGE);
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }


    /**
     * Send an email to a student containing all the courses he/she is registered to.
     * @param studID : String, the ID of the user to whom the message needs to be sent.
     * @param courseList : HashMap<String, String>, a list of courseID's as a key, and the courseName as a value, to which the
     *                   student is registered.
     * @return true, if the email is sent successfully.
     *          false, if the email failed to send.
     */
    @Override
    public boolean sendEnrolledCourseListMessage(String studID, HashMap<String, String> courseList) {
        System.out.println(START_MESSAGE);
        String recipient = studID + "@e.ntu.edu.sg";

        String subject = "MySTARS : Your courses for the semester";
        String messageBody = "You have been enrolled to the following course(s) for this academic semester:\n\n";
        messageBody += "Course ID\tCourse Name\n";
        messageBody += "-------------------------------------------------------------------------------------\n";

        for(int i = 0; i < courseList.size(); ++i){
            String courseName = (String) courseList.values().toArray()[i];
            String courseID = (String )courseList.keySet().toArray()[i];

            messageBody += courseID + "\t\t" + courseName + "\n";
        }
        messageBody += "-------------------------------------------------------------------------------------\n";

        message = createMessage(recipient, subject, messageBody);

        if(message == null){
            return false;
        }else{
            try{
                Transport.send(message);
                System.out.println(END_MESSAGE);
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }

    /**
     * createMessage is a function that is used to initialize the message object and populate the body,
     * subject, sender, receiver etc.
     *
     * @param recipient : String, the address to which the email is being sent.
     * @param subject : String, the subject field for the email.
     * @param body : String, the body of the email.
     * @return : Message, a Message object containing the initialized email which can be sent.
     */
    public static Message createMessage(String recipient, String subject, String body){
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(body);
            return message;
        }catch(Exception e){
            return null;
        }
    }

}
