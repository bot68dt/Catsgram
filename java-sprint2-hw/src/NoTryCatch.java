import java.util.Scanner;

public class NoTryCatch                                                 // т.к. конструкцию try-catch мы ещё не прошли, и в теории Шилдта я до неё не добрался - изобрёл для себя
{                                                                       // колесо, чтобы не ловить исключения при ошибках ввода
    int integer = 0;
    String string = null;                                               //класс принимает и выводит два значения: инт либо строку, зависит от того, какой флаг будет true

    NoTryCatch(boolean str, boolean intgr)
    {
        while (string == null && integer == 0)                          //пока хотя бы одна переменная не приняла значение с клавиатуры -выполняем тело цикла
        {
            Scanner scanner = new Scanner(System.in);                   //инициаллизируем сканер для считывания из консоли
            if (str)                                                    //если я поставил флаг true на строку
            {
                while(scanner.hasNextLine())
                {
                    string = scanner.nextLine();                        //пока у нас есть строка для считывания - пытаемся считать её и записать в переменную
                    return;
                }
            }
            if (intgr)                                                  //если я поставил флаг на число инт
            {
                System.out.println("Вводите число, не символы");
                while (scanner.hasNextInt())
                {
                    integer = scanner.nextInt();                        //пока у нас есть число для считывания - пытаемся считать его и записать в переменную
                    if (integer>=0)                                     // сразу смотрим, чтобы кто-то не сделал нам отрицательные значения в месяцах и шагах
                    {
                        return;
                    }
                }
            }
        }
    }
}
