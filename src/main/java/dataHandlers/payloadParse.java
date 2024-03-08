package dataHandlers;
import java.lang.String;

public class payloadParse {
    public static Boolean parseData(String payload) {
        String[] payloadArray = payload.split("&");
        String firstName = payloadArray[0].substring(3);
        String lastName = payloadArray[1].substring(3);
        String subject = payloadArray[2].substring(8);
        String timeIn = payloadArray[3].substring(7);
        String timeOut = payloadArray[4].substring(8);

        if (firstName.isEmpty() || lastName.isEmpty() || subject.isEmpty() || timeIn.isEmpty() || timeOut.isEmpty()){
            return false;
        }
        else {
            System.out.print(
                    "\nFirstName: " + firstName + "\n" + "LastName: " + lastName + "\n"
                            + "Subject: " + subject + "\n" + "TimeIn: " + timeIn + "\n"
                            + "TimeOut: " + timeOut + "\n"
            );
            return true;
        }
    }
}
