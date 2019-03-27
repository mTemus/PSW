package com.lab.grupa4;

import java.util.*;

public class Cwiczenie1 {

    Scanner scan = new Scanner(System.in);

    int input = 0;
    boolean x = true;


    Random random = new Random();


    void mainMethod() {

        System.out.println("Podaj dlugość tablicy.");
        int width = scan.nextInt();

        double[] arrPremade = {2, 3, 3.5, 4, 4.5, 5};
        double[] arrUser = new double[width];

        for (int i = 0; i < width; i++) {
            int randomNumber = random.nextInt(arrPremade.length);
            arrUser[i] = arrPremade[randomNumber];
        }

        System.out.println("Tablica wygląda następująco:");
        System.out.print("[");
        for (double x : arrUser) {
            System.out.print(x + " ");
        }

        System.out.print("]\n");

        while (x) {

            System.out.println("1. Wyświetlenie średniej wartości.");
            System.out.println("2. Wyświetlenie maksymalnej oraz minimalnej wartości w tablicy");
            System.out.println("3. Wyświetlenie wartości mniejszych oraz większych od średniej wartości w tablicy.");
            System.out.println("4. Wyświetlenie odchylenia standardowego.");
            System.out.println("5. Wyjście.");
            System.out.println("Podaj opcję: ");
            input = scan.nextInt();

            switch (input) {
                case 1:
                    System.out.println("Srednia wartości z tablicy wynosi: " + arrAverage(arrUser));
                    break;
                case 2:
                    arrMinMax(arrUser);
                    break;
                case 3:
                    arrAboveUnderAverage(arrUser);
                    break;
                case 4:
                    arrStandardDeviation(arrUser);
                    break;
                case 5:
                    x = false;
                    break;
            }
        }
    }

    double arrAverage(double[] arrUser) {

        double average = 0;

        for (int i = 0; i < arrUser.length; i++) {
            average += arrUser[i];
        }
        average /= arrUser.length;

        return average;
    }

    void arrMinMax(double[] arrUser) {

        double min = Arrays.stream(arrUser).min().getAsDouble();
        double max = Arrays.stream(arrUser).max().getAsDouble();

        System.out.println("Najmniejsza wartość w tablicy wynosi: " + min);
        System.out.println("Największa wartość w tablicy wynosi: " + max);
    }

    void arrAboveUnderAverage(double[] arrUser) {

        double average = 0;
        List aboveAverage = new ArrayList();
        List underAverage = new ArrayList();

        average = arrAverage(arrUser);

        for (int i = 0; i < arrUser.length; i++) {
            if (arrUser[i] < average)
                underAverage.add(arrUser[i]);
            else if (arrUser[i] > average)
                aboveAverage.add(arrUser[i]);
        }
        System.out.println("Srednia: " + average);
        System.out.println("Wartości większe od średniej: " + aboveAverage);
        System.out.println("Wartości mniejsze od średniej: " + underAverage);
    }

    void arrStandardDeviation(double[] arrUser) {
        double average = arrAverage(arrUser);
        double standardDeviation = 0;
        double standardDeviationI = 0;

        for (int i = 0; i < arrUser.length; i++) {
            standardDeviationI = arrUser[i] - average;
            standardDeviation += Math.pow(standardDeviationI, 2);
        }
        standardDeviation /= arrUser.length;
        standardDeviation = Math.sqrt(standardDeviation);

        System.out.println("Odchylenie standardowe wynosi: " + standardDeviation);

    }

}
