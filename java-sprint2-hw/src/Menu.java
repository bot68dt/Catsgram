public class Menu                       //вывод меню в отдельном классе, чтобы не захламлял метод main
{
    static void printMenu()
    {
        System.out.println("-".repeat(20));
        System.out.println("Выберите одну из команд:");
        System.out.println("1. Создать месяц для отслеживания шагов:");
        System.out.println("2. Ввести количество шагов за определённый день:");
        System.out.println("3. Ввести цель по количеству шагов за определённый день:");
        System.out.println("4. Вывод статистики за месяц:");
        System.out.println("5. Завершить работу");
        System.out.println();
        System.out.println("-".repeat(20));
        System.out.println();
    }
}
