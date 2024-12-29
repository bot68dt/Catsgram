import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchService {
    // Создаём объект класса, отвечающий за склад магазина
    private final Warehouse warehouse = new Warehouse();
    // Создаём объект класса, отвечающий за работу с поставщиками
    public final SRM srm = new SRM();

    // Основной метод поиска
    // Проверяет наличие товара с указанным именем на складе магазина
    // Если товар отсутствует, то проверяются склады поставщиков,
    // предпочтение отдаётся тому поставщику, у которого наименьшая цена товара.
    // Для поиска товара на складе поставщиков используется метод supplierSearch
    // Если товар нигде не найден, то возвращается пустой Optional
    public Optional<Candy> search(String candyName) {
        Optional<Candy> opt = warehouse.search(candyName);
        if (!opt.equals(Optional.empty()))// Реализуйте данный метод, с использованием методов Optional
            return opt;
        else if (!supplierSearch(candyName).equals(Optional.empty())) {
            Optional<Candy> opt1 = supplierSearch(candyName);
            return opt1;
        } else return Optional.empty();
    }

    // Ищет товар с указанным именем на складах поставщиков
    // Возвращает Optional с самым дешевым вариантом товара среди всех
    // поставщиков или пустой Optional, если товар не найден
    private Optional<Candy> supplierSearch(String candyName) {
        List<Candy> list = srm.listSuppliers().stream().map(supplierName -> {
            Optional<Candy> opt = Optional.ofNullable(srm.getProduct(supplierName, candyName));
            return opt;
        }).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        Optional<Candy> can = list.stream().min(Comparator.comparing(candy -> candy.price));

        return can;
        // используйте метод min из Stream API для нахождения товара с наименьшей ценой

    }
}