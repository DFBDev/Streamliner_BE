package dataHandlers_Model;
import java.lang.String;
import java.sql.SQLException;

public class payloadProcessor {
    public static Boolean processData(String payload) throws SQLException { //Parses payload data and passes to input handler for database (given valid input).

        //Parsing payload.
        String[] payloadArray = payload.split("&");
        String firstName = payloadArray[0].substring(3);
        String lastName = payloadArray[1].substring(3);
        String subject = payloadArray[2].substring(8);
        String timeIn = payloadArray[3].substring(7);
        String timeOut = payloadArray[4].substring(8);
        String dbPass = payloadArray[5].substring(7);

        //Validating payload and passing data to model handler if valid.
        if (firstName.isEmpty() || lastName.isEmpty() || subject.isEmpty() || timeIn.isEmpty() || timeOut.isEmpty() || dbPass.isEmpty()){
            return false;
        }
        else {
            dbInputHandler.sqlHandlerMethod(firstName, lastName, subject, timeIn, timeOut, dbPass);
            return true;
        }
    }
}
