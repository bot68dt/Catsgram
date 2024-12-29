import java.util.stream.Stream;

public class OptionalInStreams {

    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5)
                .filter(number -> number % 2 == 0)
                .findFirst()//выбрать первый отфильтрованный элемент
                .ifPresentOrElse(
                        number -> System.out.println(number),
                        () -> System.out.println("Чётное число не найдено"));
//вывести на экран найденное число либо строку "Чётное число не найдено"
    }
}