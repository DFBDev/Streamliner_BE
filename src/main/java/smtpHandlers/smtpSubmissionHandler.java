package smtpHandlers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.net.URLDecoder;
import java.lang.Exception;

public class smtpSubmissionHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            //Locating, parsing and passing the body/payload of received request.
            InputStream reqStream = httpExchange.getRequestBody();
            String payload = URLDecoder.decode(new String(reqStream.readAllBytes(), StandardCharsets.UTF_8), StandardCharsets.UTF_8);
            smtpSubPayloadProcessor.processData(payload); //Parses data and passes to smtpSend; can throw SMTP/SQL exception.
            reqStream.close();

            //Response headers for successful request if flow not interrupted (200 OK status-code).
            System.out.print("\nEMAIL SENT!\n");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.sendResponseHeaders(200,0);
        }
        catch (Exception e) {
            //Response headers for unsuccessful request.
            System.out.print("\nERROR: " + e);
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.sendResponseHeaders(400,0);
        }
    }
}
