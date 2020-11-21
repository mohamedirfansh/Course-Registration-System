package control;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email implements Notification{
    Properties properties = new Properties();
    Session session;
    Message message;

    public Email(boolean auth, boolean starttls, String host, String port) {
        properties.put("mail.smtp.auth" , String.valueOf(auth));
        properties.put("mail.smtp.starttls.enable" , String.valueOf(starttls));
        properties.put("mail.smtp.host" , host);
        properties.put("mail.smtp.port" , port);
    }

    @Override
    public void sendMessage(String studID, String subject, String body, String senderEmail, String senderPassword) {
        System.out.println("Preparing to send email...\n");


        session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        String recipient = studID + "@e.ntu.edu.sg";

        message = createMessage(session, senderEmail, recipient, subject, body);

        try{
            Transport.send(message);
        }catch(Exception e){
            return;
        }

        System.out.println("Message sent.");

    }

    public static Message createMessage(Session session, String from, String recipient, String subject, String body){
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(body);
            return message;
        }catch(Exception e){
            return null;
        }
    }

}
