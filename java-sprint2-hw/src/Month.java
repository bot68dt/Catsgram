public class Month                                                                          // Класс месяц, из объектов которого будет состоять ArrayList
{
    int[] month;                                                                            // массив с фактическим числом шагов
    int[] stepGoals;                                                                        //  массив с планируемым числом шагов
    int daysInMonth;                                                                        // количество шагов в 1 мес.
    String monthName;                                                                       // название месяца (можно с цифрами - январь 2024) м т.д.

   public Month (String name, int days)                                                     // конструктор класса месяц
   {
       monthName = name;
       month = new int[days];
       daysInMonth = days;
       stepGoals = new int[days];
       for (int i=0; i<stepGoals.length;i++)
       {
           stepGoals[i] = 10000;                                                            // по условию задачи - по умолчанию цель - 10к шагов в день
           month[i] = 0;                                                                 // т.к. я сделал довольно сложный ввод каждого месяца - для удобства проверяющего
       }                                                                                    // инициализирую все дни массива

   }

   void addSteps(int day,int steps)                                                         // добавляю функцию ручной перезаписи шагов за день в массиве
   {
       if (day-1<month.length)                                                              // условие - чтобы исключить после создания месяца в n-дней попытку записи дня n+1
       month[day-1] = steps;
       else
           System.out.println("Введённый день находится за границами месяца");
   }

   void addGoal(int day,int stepGoal)                                                       // аналогично верхнему методу, но - добавляем к каждому дню желаемое кол-во шагов
    {
        if (day-1<stepGoals.length)
        stepGoals[day-1] = stepGoal;
        else
            System.out.println("Введённый день находится за границами месяца");
    }

    void printMonth()                                                                       // стандартный вывод массива с шагами фактическими на печать
    {
        for(int i=0;i<month.length;i++)
        {
            System.out.println((i+1) + " день: " + month[i] + " шагов.");
        }
    }

    int sumStepsFromMonth()                                                                 //  суммирую все шаги за месяц и вывожу их в качестве значения метода, поэтому он не void
    {
        int sumSteps = 0;
        for (int j : month)
        {
            sumSteps += j;
        }
        return sumSteps;
    }

    int maxSteps()                                                                          //  узнаю кол-во шагов в самый продуктивный день
    {
        int maxSteps = 0;
        for (int j : month)                                                                 // данного усовершенствованного For не было в лекциях - прочёл у Шилдта на 142 странице
        {
            if (maxSteps < j)
                maxSteps = j;
        }
        return maxSteps;
    }

    int bestSeries()                                                                         // узнаю лучшую серию из нескольких продуктивных дней подряд
    {
        int count = 0;
        int maxcount = 0;

        for(int i=0;i<month.length;i++)
        {
            if (month[i]>=stepGoals[i] && maxcount<=count)
                maxcount = ++count;
            else
                count = 0;
        }
        return maxcount;
    }

}


