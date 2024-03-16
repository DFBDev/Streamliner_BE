package smtpHandlers;
import dataHandlers_Model.dbRetrieveHandler;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class smtpConnect {
   public static void sender(String smtpOrigin, String smtpPass, String smtpDestination, String dbPass) throws MessagingException, SQLException { //Uses JakartaAPI for establishing SMTP connection to smtp.gmail.com.

       //Containers for retrieved database data.
       ArrayList<String> retrievedData = dbRetrieveHandler.retrieveData(dbPass); //retrieveData method returns array of properly formatted database data.
       String formattedData = ""; //Empty string for data pulled from retrievedData ArrayList (due to setText requiring string type).

       //Configurations for SMTP connection.
       Properties props = new Properties();
       props.put("mail.smtp.host", "smtp.gmail.com");
       props.put("mail.smtp.starttls.enable", "true");

       //Creating individual email session.
       Session session = Session.getInstance(props);

       //Extracting contents of retrievedData ArrayList to formattedData string.
       for (int i = 0; i < retrievedData.size(); i++) {
           formattedData = formattedData.concat(retrievedData.get(i));
       }

       //Creating email and configuring contents.
       MimeMessage email = new MimeMessage(session);
       email.setRecipients(Message.RecipientType.TO, smtpDestination);
       email.setSubject("Tutor Records");
       email.setText(formattedData);

       //Establishing SMTP connection to smtp.gmail.com w/ necessary credentials.
       Transport.send(email, smtpOrigin, smtpPass);
   }
}
