public class Converter                                                      //конвертируем шаги в километры и шаги в калории в отдельном классе
{
    private static final int step2Distance = 75;                                                 // константы из условия задания
    private static final int step2Cal = 50;
    private static final int kcal = 1000;
    private static final int step2Km = 100000;
    int sumSteps;

    public Converter (int stepsSum)                                         // конструктор получает на входе сумму всех шагов за месяц, далее - в методах работает с этим
    {
        sumSteps = stepsSum;
    }

    double convertToKm()                                                    // на всякий случай я решил перестраховаться и сразу сделать явное приведение к значению double (вычитал
    {                                                                       // у Шилдта в работе с простыми переменными. Вдруг мы будем вводить дробные константы, да и значения с .0 красивы
        return (double) (sumSteps * step2Distance) /step2Km;
    }

    double convertStepsToKcal()
    {
        return (double) (sumSteps * step2Cal) /kcal;
    }
}
