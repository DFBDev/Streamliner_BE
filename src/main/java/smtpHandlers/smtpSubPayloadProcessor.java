package smtpHandlers;
import jakarta.mail.MessagingException;
import java.sql.SQLException;

public class smtpSubPayloadProcessor {
    public static void processData(String payload) throws MessagingException, SQLException {
        //Parsing form info received via request payload.
        String[] payloadArray = payload.split("&");
        String emailOrigin = payloadArray[0].substring(12);
        String emailDestination = payloadArray[1].substring(10);
        String smtpPW = payloadArray[2].substring(7);
        String dbPW = payloadArray[3].substring(8);

        //Passing credentials to SMTP connection handler.
        smtpConnect.sender(emailOrigin, smtpPW.replaceAll("\\s", ""), emailDestination, dbPW);
    }
}
