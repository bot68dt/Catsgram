import java.util.ArrayList;

public class MonthData                                                                                                  //основной код, ArrayList объектов Month, в каждом из которых
{                                                                                                                       // сидят по 2 массива с доп. переменными
    ArrayList<Month> monthData = new ArrayList<>();

    boolean noNullPls(int integer)                                                                                      //избегаем через метод true/false ввода нулевых мес. и дней
    {
        boolean mark = false;
        if (integer==0)
        {
            System.out.println("В этой программе мы не любим нули :), попробуйте ещё раз");
            mark =true;
        }
        return mark;
    }

    boolean checkMonthName(String input)                                                                                //смотрим, есть ли такой же месяц в Листе по атрибуту имени мес.
    {
        boolean check = false;
        for (Month i : monthData)                                                                                       //здесь и далее использую продвинутый for, очень удобно
        {
            if (i.monthName.equals(input))
            {
                System.out.println("Такой месяц есть, информация добавлена");
                check = true;
            }
        }
        return check;
    }

    void createMonth()                                                                                                  //решил добавить опцию ручного ввода мес. с любым названием
    {
        System.out.println("Введите название месяца: ");
        NoTryCatch input1 = new NoTryCatch(true, false);
        System.out.println("Дней в месяце: ");
        NoTryCatch input2 = new NoTryCatch(false, true);
        if(!noNullPls(input2.integer) & !checkMonthName(input1.string))                                                 //если у нас не 0 в цифрах, то проверяем втор. часть неравенства
            monthData.add(new Month(input1.string, input2.integer));
    }
    void addWrongMonth(String name, int daysEntered)                                                                    //решил дать возможность добавления месяца из других пунктов меню
    {
        System.out.println("Такого месяца нет.Хотите добавить данный месяц? да/нет");
        NoTryCatch input1 = new NoTryCatch(true, false);
        switch (input1.string)
        {
            case "да":
                System.out.println("Введите количество дней в месяце: ");
                NoTryCatch input2 = new NoTryCatch(false, true);
                if (!noNullPls(input2.integer) & input2.integer>=daysEntered)
                monthData.add(new Month(name, input2.integer));
                else
                    System.out.println("Введённое количество дней в месяце меньше введённого дня месяца, попробуйте снова");
            case "нет":
                return;
            default:
        }
    }

    void addSteps2Month()                                                                                               //закидываем кастомное кол-во шагов в нужный день, нужный мес.
    {

        System.out.println("Введите название месяца: ");
        NoTryCatch input1 = new NoTryCatch(true, false);
        System.out.println("Введите день месяца: ");
        NoTryCatch input2 = new NoTryCatch(false, true);
        if (noNullPls(input2.integer))
            return;
        System.out.println("Введите количество шагов: ");
        NoTryCatch input3 = new NoTryCatch(false, true);

        if (!checkMonthName(input1.string))
            addWrongMonth(input1.string, input2.integer);
        for (Month i : monthData)
        {
            if (i.monthName.equals(input1.string))
            {
                i.addSteps(input2.integer, input3.integer);
                break;
            }
        }

    }
    void addGoal2Month()                                                                                                //закидываем желаемое кол-во шагов в нужный день, нужный мес.
    {

        System.out.println("Введите название месяца: ");
        NoTryCatch input1 = new NoTryCatch(true, false);
        System.out.println("Введите день месяца: ");
        NoTryCatch input2 = new NoTryCatch(false, true);
        if (noNullPls(input2.integer))                                                                                  //захотел оставить эти костыли и не делать одно неравенство,
            return;                                                                                                     //чтобы быстрее проводить проверку
        System.out.println("Введите цель по количеству шагов в день: ");
        NoTryCatch input3 = new NoTryCatch(false, true);
        if (noNullPls(input3.integer))
            return;

        if (!checkMonthName(input1.string))
            addWrongMonth(input1.string, input2.integer);
        for (Month i : monthData)
        {
            if (i.monthName.equals(input1.string))
            {
                i.addGoal(input2.integer, input3.integer);
                break;
            }
        }
    }
    void printStatisticsFromMonth()                                                                                     //выводим статистику по месяцу, если он есть в списке
    {
        System.out.println("Введите название месяца: ");
        NoTryCatch input1 = new NoTryCatch(true, false);

        for (Month i : monthData)
        {
            if (i.monthName.equals(input1.string))
            {
                System.out.println("Количество пройденных шагов по дням: ");
                i.printMonth();
                System.out.println("Общее количество шагов за месяц: " + i.sumStepsFromMonth());
                System.out.println("Максимальное пройденное количество шагов в месяце: " + i.maxSteps());
                System.out.println("Среднее количество шагов: " + (i.sumStepsFromMonth() / i.month.length));
                Converter converter = new Converter(i.sumStepsFromMonth());
                System.out.println("Пройденная дистанция (в километрах): " + converter.convertToKm());
                System.out.println("Количество сожжённых килокалорий: " + converter.convertStepsToKcal());
                System.out.println("Лучшая серия: максимальное количество подряд идущих дней, в течение которых количество шагов за день было равно или выше целевого: " + i.bestSeries());
                return;
            }
        }
        System.out.println("Нет такого месяца.");
    }

}
