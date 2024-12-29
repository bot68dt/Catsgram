public class Practicum {
    public static void main(String[] args) {
        String rules = "На зелёный цвет — стой на месте;\n" +
                "на зелёный цвет — приготовься;\n" +
                "на зелёный — осмотрись, а затем смело шагай.";

        String replaced = rules;
        replaced = replaced.replaceFirst("зелёный", "красный");
        replaced =replaced.replaceFirst("зелёный", "жёлтый");
        replaced = replaced.replace("цвет", "свет");
        System.out.println(replaced);
    }
}