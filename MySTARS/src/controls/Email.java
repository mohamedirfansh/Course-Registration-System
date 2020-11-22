package controls;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Properties;

public class Email implements Notification{
    private static Properties properties;
    private static Session session;
    private Message message;
    private static final String senderEmail = "ss3mystars@gmail.com";
    private static final String senderPassword = "groupss3";
    private static final String START_MESSAGE = "Preparing to send email...\n";
    private static final String END_MESSAGE = "Email send successfully.\n";

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
