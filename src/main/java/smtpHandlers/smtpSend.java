package smtpHandlers;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class smtpSend {
   public static void sender(String smtpOrigin, String smtpPass, String smtpDestination) throws MessagingException { //JakartaAPI for establishing SMTP connection to smtp.gmail.com.
       Properties props = new Properties();
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.starttls.enable", "true");
       Session sess = Session.getInstance(props);

       MimeMessage email = new MimeMessage(sess);
       email.setRecipients(Message.RecipientType.TO, smtpDestination);
       email.setSubject("Tutor Records"); //Potential custom subject option?
       email.setText("This is a test!"); //Will need way to convert db data to text form.

       Transport.send(email, smtpOrigin, smtpPass);
   }
}
