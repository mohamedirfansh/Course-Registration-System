package controls;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface Notification {
    void sendMessage(String studID, String subject, String body, String sender, String senderPassword);
//    void createMessage();
//    void courseRegNotif();
//    void courseDropNotif();
}
//send the email
//create email
//format the email
//what are the differences between the different types of emails.
//course registered email needs the course