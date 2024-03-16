package dataHandlers_Model;
import java.lang.String;
import java.sql.SQLException;

public class dbSubPayloadProcessor {
    public static Boolean processData(String payload) throws SQLException { //Parses and validates payload data (transports data if valid).

        //Parsing payload.
        String[] payloadArray = payload.split("&");
        String firstName = payloadArray[0].substring(3);
        String lastName = payloadArray[1].substring(3);
        String subject = payloadArray[2].substring(8);
        String timeIn = payloadArray[3].substring(7);
        String timeOut = payloadArray[4].substring(8);
        String dbPass = payloadArray[5].substring(7);

        //Validating payload and passing data to database input handler if valid.
        if (firstName.isEmpty() || lastName.isEmpty() || subject.isEmpty() || timeIn.isEmpty() || timeOut.isEmpty() || dbPass.isEmpty()){
            return false;
        }
        else {
            dbInputHandler.inputData(firstName, lastName, subject, timeIn, timeOut, dbPass);
            return true;
        }
    }
}
