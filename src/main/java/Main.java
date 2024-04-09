import reqHandlers_Controller.dbSubmissionHandler;
import com.sun.net.httpserver.HttpServer;
import reqHandlers_Controller.smtpSubmissionHandler;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        //Firing up HTTPServer instance w/ context pathways.
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.createContext("/", new dbSubmissionHandler()); //Endpoint for submitting form data to database.
        server.createContext("/smtp", new smtpSubmissionHandler()); //Endpoint for submitting email request (email content is db data).
        server.createContext("/favicon.ico");
        server.start();
        System.out.print("\nServer started on port 8080! \n");
    }
}