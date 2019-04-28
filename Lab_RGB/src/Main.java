import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanMain = new Scanner(System.in);
        boolean x = true;
        RGBController zadanie = new RGBController();
        RGB color1 = new RGB();
        RGB color2 = new RGB();


        while (x) {
            System.out.println("1. Uzupelnienie skladowych kolorów.");
            System.out.println("2. Wyświetlenie kolorów oraz ich skladowych.");
            System.out.println("3. Zmiksowanie kolorów.");
            System.out.println("4. Wyjście z programu.");
            System.out.println("Wybierz opcję.");

            int input = scanMain.nextInt();

            switch (input) {
                case 1:
                    zadanie.setColors(color1, color2);
                    break;
                case 2:
                    zadanie.getColors(color1, color2);
                    break;
                case 3:
                    zadanie.mixColors(color1, color2);
                    break;
                case 4:
                    x = false;
                    break;
            }

        }


    }


}