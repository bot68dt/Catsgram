import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

class PostsHandler implements HttpHandler {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();

        String method = exchange.getRequestMethod();

        // получите информацию об эндпоинте, к которому был запрос
        Endpoint endpoint = getEndpoint(path, method);

        switch (endpoint) {
            case GET_POSTS: {
                writeResponse(exchange, "Получен запрос на получение постов", 200);
                break;
            }
            case GET_COMMENTS: {
                writeResponse(exchange, "Получен запрос на получение комментариев", 200);
                break;
            }
            case POST_COMMENT: {
                writeResponse(exchange, "Получен запрос на добавление комментария", 200);
                break;
            }
            default:
                writeResponse(exchange, "Такого эндпоинта не существует", 404);
        }
    }

    private Endpoint getEndpoint(String requestPath, String requestMethod) {
        // реализуйте этот метод, проанализировав путь и метод запроса
        String[] splitStrings = requestPath.split("/");
        Endpoint end = Endpoint.UNKNOWN;
        switch (requestMethod) {
            case "GET": {
                if (splitStrings.length==2) {
                    end = Endpoint.GET_POSTS;
                    break;
                } else {
                    end = Endpoint.GET_COMMENTS;
                    break;
                }
            }
            case "POST": {
                end = Endpoint.POST_COMMENT;
                break;
            }
            default:
        }
        return end;
    }

    private void writeResponse(HttpExchange exchange,
                               String responseString,
                               int responseCode) throws IOException {
            /*
             Реализуйте отправку ответа, который содержит responseString в качестве тела ответа
             и responseCode в качестве кода ответа.
             Учтите, что если responseString — пустая строка, то её не нужно передавать в ответе.
             В этом случае ответ отправляется без тела.
             */

        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "text/plain; charset=utf-8");
        exchange.sendResponseHeaders(responseCode, 0);
        if(!responseString.isEmpty())
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseString.getBytes());
            }

    }


    enum Endpoint {GET_POSTS, GET_COMMENTS, POST_COMMENT, UNKNOWN}
}

public class Practicum {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {

        // добавьте код для конфигурирования и запуска сервера
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/post", new PostsHandler());
        httpServer.start();

        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
        // завершаем работу сервера для корректной работы тренажёра
        //httpServer.stop(1);
    }
}