package smtpHandlers;

import jakarta.mail.MessagingException;

public class smtpPayloadProcessor {
    public static Boolean processData(String payload) throws MessagingException {
        String[] payloadArray = payload.split("&");
        String emailOrigin = payloadArray[0].substring(12);
        String emailDestination = payloadArray[1].substring(10);
        String smtpPW = payloadArray[2].substring(7);

        if (emailOrigin.isEmpty() || emailDestination.isEmpty() || smtpPW.isEmpty()){
            return false;
        }
        else {
            smtpSend.sender(emailOrigin, smtpPW.replaceAll("\\s", ""), emailDestination);
            return true;
        }
    }
}
