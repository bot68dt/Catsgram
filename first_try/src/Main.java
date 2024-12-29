import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK_Back = "\u001B[40m";
        final String ANSI_RED_Back = "\u001B[41m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

        int a = 50;
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(Pattern.compile("[+=]"));
        int w =0;

        String[][] xy = new String[a / 2][a * 4];
        w = Integer.parseInt(scanner.next());
        System.out.print(w);
        while (true)
        {
            for (int i = 0; i < a / 2; i++) {
                for (int j = 0; j < a * 3; j++) {
                    if (j <= i * 6 && i < a / 4) {
                        xy[i][j] = "|";

                    } else if (i == a / 4 && j <= i * 6 - 6) {
                        xy[i][j] = "|";

                    } else
                        xy[i][j] = " ";
                }


            }
            int k = 0;
            for (int i = a / 2 - 1; i > a / 4; i--, k++) {
                for (int j = 0; j < a * 2; j++) {
                    if (j <= k * 6) {
                        xy[i][j] = "|";

                    }
                }


            }
            for (int i = 0; i < a / 2; i++) {
                for (int j = 0; j < a * 3; j++) {
                    if (xy[i][j]=="|")
                        System.out.print(ANSI_RED_Back + xy[i][j] + ANSI_RESET);
                    else
                        System.out.print(ANSI_BLACK_Back+xy[i][j]+ANSI_RESET);
                }
                System.out.println();
            }
           Thread.sleep(5000);

            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            Runtime runtime = Runtime.getRuntime();

            try {
                 runtime.exec("cmd");
            }
            catch (Exception e) {}


        }

    }
}

