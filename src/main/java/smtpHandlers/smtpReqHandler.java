package smtpHandlers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.net.URLDecoder;
import java.lang.Exception;

public class smtpReqHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            //Locating and parsing the body/payload of incoming request.
            InputStream reqStream = httpExchange.getRequestBody();
            String payload = URLDecoder.decode(new String(reqStream.readAllBytes(), StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            Boolean status = smtpPayloadProcessor.processData(payload); //Returns payload validity status & executes insertion query (if payload valid).
            reqStream.close();

            //Status-code / response handlers.
            if (!status) { //Throws invalid error for invalid user input.
                throw new Exception("EMAIL COULD NOT BE SENT DUE TO INCOMPLETE DATA.\n");
            }
            else { //Valid payload response handling (200 OK status-code)
                System.out.print("\nEMAIL SENT!\n");
                httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                httpExchange.sendResponseHeaders(200,0);
            }
        }
        catch (Exception e) { //Sends 400 status-code to indicate incomplete payload info.
            System.out.print("\nERROR: " + e);
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.sendResponseHeaders(400,0);
        }
    }
}
