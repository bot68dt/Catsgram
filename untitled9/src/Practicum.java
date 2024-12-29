import java.util.Optional;

public class Practicum {
    public static void main(String[] args) {
        // Доработайте данный метод, чтобы на экран выводилось
        // сообщение в соответствии с заданием

        SearchService searchService = new SearchService();

        Optional<Candy> can = searchService.search("Африка");
        if (can.equals(Optional.empty()))
            System.out.println("Данный товар не найден");
        else
        {Candy can1 = can.get();
            System.out.println("Товар " + can1.name + " доступен в количестве " + can1.amount + " кг по цене " + can1.price + " руб за кг");}
    }
}