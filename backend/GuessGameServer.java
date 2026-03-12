import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Random;

public class GuessGameServer {

    static int number = new Random().nextInt(100) + 1;

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/guess", new GuessHandler());

        server.setExecutor(null);
        server.start();

        System.out.println("Server running at http://localhost:8000");
    }

    static class GuessHandler implements HttpHandler {

        public void handle(HttpExchange exchange) throws IOException {

            // CORS allow
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            String response = "";

            try {

                String query = exchange.getRequestURI().getQuery();

                if (query == null) {
                    response = "No number provided!";
                } else {

                    int guess = Integer.parseInt(query.split("=")[1]);

                    if (guess < number) {
                        response = "Too Low!";
                    } 
                    else if (guess > number) {
                        response = "Too High!";
                    } 
                    else {
                        response = "🎉 Correct!";
                    }
                }

            } catch (Exception e) {
                response = "Invalid input!";
            }

            exchange.sendResponseHeaders(200, response.length());

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}