import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

class HelloHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "";

        // извлеките метод из запроса
        String method = httpExchange.getRequestMethod();

        switch (method) {
            case "POST":
                response = handlePostRequest(httpExchange);
            case "GET":
                response = handleGetRequest(httpExchange);
                // не забудьте про ответ для остальных методов
            default:
                System.out.println("Некорректный метод");

                httpExchange.sendResponseHeaders(200, 0);
                try (OutputStream os = httpExchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
        }

        
    }

    private String handlePostRequest (HttpExchange httpExchange) throws IOException {
        URI requestURI = httpExchange.getRequestURI();
        // извлеките path из запроса
        String path = requestURI.getPath();
        String[] splitStrings = path.split("/");
        // а из path — профессию и имя
        String profession = splitStrings[2];
        String name = splitStrings[3];

        // извлеките тело запроса
        InputStream inputStream = httpExchange.getRequestBody();
        String body = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        // объедините полученные данные из тела и пути запроса
        String response = body + ", " + profession + " " + name + "!";

        // извлеките заголовок и в зависимости от условий дополните ответ
        Headers requestHeaders = httpExchange.getRequestHeaders();
        List<String> wishGoodDay = requestHeaders.get("X-Wish-Good-Day");
        if ((wishGoodDay != null) && (wishGoodDay.contains("true"))) {
            response += "Хорошего дня!";
        }
        // верните полученную строку ответа
        return response;
    }
    
    private String handleGetRequest(HttpExchange httpExchange) {
        return "Здравствуйте!";
    }
    }

    public class Practicum {
        private static final int PORT = 8080;

        public static void main(String[] args) throws IOException {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
            httpServer.createContext("/hello", new HelloHandler());
            httpServer.start();
            System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
            httpServer.stop(2);
        }
    }