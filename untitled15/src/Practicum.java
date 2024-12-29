import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class PostsHandler implements HttpHandler {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final List<Post> posts;

    public PostsHandler(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Endpoint endpoint = getEndpoint(exchange.getRequestURI().getPath(), exchange.getRequestMethod());

        switch (endpoint) {
            case GET_POSTS: {
                handleGetPosts(exchange);
                break;
            }
            case GET_COMMENTS: {
                handleGetComments(exchange);
                break;
            }
            case POST_COMMENT: {
                handlePostComments(exchange);
                break;
            }
            default:
                writeResponse(exchange, "Такого эндпоинта не существует", 404);
        }
    }

    private void handleGetPosts(HttpExchange exchange) throws IOException {
        // верните ответ, представляющий список постов. Код ответа должен быть 200.
        // информация по каждому посту должна начинаться с новой строки.
        // для преобразования объекта поста в строку воспользуйтесь его методом toString
        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "text/plain; charset=utf-8");
        String responseString = new String();
        for(Post s : posts)
            responseString+= s.toString() + "\n";
        writeResponse(exchange, responseString, 200);
    }

    private void handleGetComments(HttpExchange exchange) throws IOException {
        Optional<Integer> postIdOpt = getPostId(exchange);
        Headers headers = exchange.getResponseHeaders();
        headers.set("Content-Type", "text/plain; charset=utf-8");
        String responseString = new String();
        boolean f = false;
        if (postIdOpt.isEmpty())
            writeResponse(exchange, "Некорректный идентификатор поста", 400);
        else {

            for(Post s : posts)
                if (s.getId() == postIdOpt.get()){
                    f = true;
                    List<Comment> s1 = s.getComments();
                    for(Comment s2 : s1)
                        responseString+= s2.toString() + "\n";
                    writeResponse(exchange, responseString, 200);
                }
            if (!f)
                writeResponse(exchange, "Пост с идентификатором " + postIdOpt + " не найден", 404);
        }
        /* Верните комментарии указанного поста. Информация о каждом комментарии
           должна начинаться с новой строки. Код статуса — 200.
           Если запрос был составлен неверно, верните сообщение об ошибке с кодом 400.
           Если пост с указанным идентификатором не найден, верните сообщение об этом с кодом 404. */
    }

    private Optional<Integer> getPostId(HttpExchange exchange) {
        /* Реализуйте метод получения идентификатора поста.
           Если идентификатор не является числом, верните Optional.empty(). */
        Optional<Integer> optionalNumber = Optional.of(-1);
        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String[] splitStrings = path.split("/");
        if (splitStrings.length>2)
            optionalNumber = Optional.of(Integer.parseInt(splitStrings[2]));
        else
            optionalNumber = Optional.empty();
        return optionalNumber;
    }

    private void handlePostComments(HttpExchange exchange) throws IOException {
        writeResponse(exchange, "Этот эндпоинт пока не реализован", 200);
    }

    private Endpoint getEndpoint(String requestPath, String requestMethod) {
        String[] pathParts = requestPath.split("/");

        if (pathParts.length == 2 && pathParts[1].equals("posts")) {
            return Endpoint.GET_POSTS;
        }
        if (pathParts.length == 4 && pathParts[1].equals("posts") && pathParts[3].equals("comments")) {
            if (requestMethod.equals("GET")) {
                return Endpoint.GET_COMMENTS;
            }
            if (requestMethod.equals("POST")) {
                return Endpoint.POST_COMMENT;
            }
        }
        return Endpoint.UNKNOWN;
    }

    private void writeResponse(HttpExchange exchange,
                               String responseString,
                               int responseCode) throws IOException {
        try (OutputStream os = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(responseCode, 0);
            os.write(responseString.getBytes(DEFAULT_CHARSET));
        }
        exchange.close();
    }

    enum Endpoint {GET_POSTS, GET_COMMENTS, POST_COMMENT, UNKNOWN}
}

public class Practicum {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        // инициализация начальных данных
        List<Post> posts = new ArrayList<>();
        Post post1 = new Post(1, "Это первый пост, который я здесь написал.");
        post1.addComment(new Comment("Пётр Первый", "Я успел откомментировать первым!"));
        posts.add(post1);

        Post post2 = new Post(22, "Это будет второй пост. Тоже короткий.");
        posts.add(post2);

        Post post3 = new Post(333, "Это пока последний пост.");
        posts.add(post3);

        // настройка и запуск HTTP-сервера
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(PORT), 0);
        httpServer.createContext("/posts", new PostsHandler(posts));
        httpServer.start(); // запускаем сервер

        System.out.println("HTTP-сервер запущен на " + PORT + " порту!");
        // завершаем работу сервера для корректной работы тренажёра
        httpServer.stop(1);
    }
}

class Post {
    private int id;
    private String text;
    private List<Comment> comments = new ArrayList<>();

    public Post(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", comments=" + comments +
                '}';
    }
}

class Comment {
    private String user;
    private String text;

    public Comment(String user, String text) {
        this.user = user;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "user='" + user + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}