package com.lab.grupa4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Cwiczenie1 {

    Scanner scan = new Scanner(System.in);

    int width = 0;
    int input = 0;
    boolean x = true;
    double[] arrPremade = {2, 3, 3.5, 4, 4.5, 5};
    double[] arrUser = new double[width];

    Random random = new Random();

    void fillingArray() {
        for (int i = 0; i < width; i++) {
            System.out.println("fillingArray");
            int randomNumber = random.nextInt(arrPremade.length);
            arrUser[i] = arrPremade[randomNumber];
            System.out.println(arrUser[i]);
        }
    }

    void mainMethod() {

        System.out.println("Podaj dlugość tablicy.");
        width = scan.nextInt();
        fillingArray();

        System.out.println("Tablica wygląda następująco:");
        System.out.println(arrUser);

        System.out.println("Podaj opcję: ");
        input = scan.nextInt();

        while (x) {
            switch (input) {
                case 1:
                    tabAverage();
                    break;
                case 2:
                    arrMinMax();
                    break;

            }
        }
    }

    void tabAverage() {

        double average = 0;

        for (int i = 0; i < width; i++) {
            average += arrUser[i];
        }
        average /= width;

        System.out.println("Srednia wartości z tablicy wynosi: " + average);
    }

    void arrMinMax() {

        double min = Arrays.stream(arrUser).min().getAsDouble();
        double max = Arrays.stream(arrUser).max().getAsDouble();

        System.out.println("Najmniejsza wartość w tablicy wynosi: " + min);
        System.out.println("Największa wartość w tablicy wynosi: " + max);
    }

}
