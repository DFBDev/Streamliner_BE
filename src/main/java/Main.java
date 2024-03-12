import reqHandlers_Controller.submissionHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException { //Firing up HTTPServer instance w/ context pathways.
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.createContext("/", new submissionHandler());
        server.createContext("/favicon.ico");
        server.start();
        System.out.print("\nServer started on port 8080! \n");
    }
}