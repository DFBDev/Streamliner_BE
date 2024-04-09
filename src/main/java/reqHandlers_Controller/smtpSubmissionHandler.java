package reqHandlers_Controller;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import smtpHandlers.smtpSubPayloadProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.net.URLDecoder;
import java.lang.Exception;

public class smtpSubmissionHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) { //Determining if incoming request is preflight (OPTIONS) request.
                httpExchange.getResponseHeaders().add("Access-Control-Allow-Private-Network", "true"); //PNA should be allowed since endpoint is local.
                httpExchange.sendResponseHeaders(200,0);
            }
            else if (httpExchange.getRequestMethod().equalsIgnoreCase("POST")) { //Determining  if incoming request is POST request.
                //Locating, parsing and passing the body/payload of received request.
                InputStream reqStream = httpExchange.getRequestBody();
                String payload = URLDecoder.decode(new String(reqStream.readAllBytes(), StandardCharsets.UTF_8), StandardCharsets.UTF_8);
                smtpSubPayloadProcessor.processData(payload); //Parses data and passes to smtpSend; can throw SMTP/SQL exception.
                reqStream.close();

                //Response headers for successful request if flow not interrupted (204 status-code since response body not needed; prevents infinite pending on client).
                httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                httpExchange.sendResponseHeaders(204,0);
            }
        }
        catch (Exception e) {
            //Response headers for unsuccessful request.
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.sendResponseHeaders(400,0);
        }
    }
}
