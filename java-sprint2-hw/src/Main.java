public class Main                                                                                                       //самое лёгкое и не оч интересное - основной класс Main
{
    public static void main(String[] args)
    {
        MonthData arrayOfMonths = new MonthData();                                                                      //непосредственное создание листа объектов с массивами1

        while (true)
        {
            Menu.printMenu();
            NoTryCatch num = new NoTryCatch(false, true);
            switch (num.integer)
            {
                case 1:
                    arrayOfMonths.createMonth();
                    break;
                case 2:
                    arrayOfMonths.addSteps2Month();
                    break;
                case 3:
                    arrayOfMonths.addGoal2Month();
                    break;
                case 4:
                    arrayOfMonths.printStatisticsFromMonth();
                    break;
                case 5:
                    System.out.println("Работа приложения завершена");
                    return;
                default:
                    System.out.println("Такой команды нет");
            }
        }
    }
}