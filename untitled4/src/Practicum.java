public class Practicum {
    public static void main(String[] args) {
        FindRepeats check = new FindRepeats();
        int count = check.numberOfRepeats("раз, раз, раз, раз fgfgfgfg , раз", "раз");
        System.out.println(count);
    }
}
