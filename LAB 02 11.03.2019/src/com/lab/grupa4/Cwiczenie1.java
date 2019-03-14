package com.lab.grupa4;

import java.util.Random;
import java.util.Scanner;

public class Cwiczenie1 {

    Scanner scan = new Scanner(System.in);

    int width = 0;
    int input = 0;
    boolean x = true;
    double[] tabPremade = {2, 3, 3.5, 4, 4.5, 5};
    double[] tabUser = new double[width];

    Random random = new Random();

    void fillingTable() {
        for (int i = 0; i < width; i++) {
            int randomNumber = random.nextInt(tabPremade.length);
            tabUser[i] = tabPremade[randomNumber];
        }
    }

    void mainMethod() {

        System.out.println("Podaj dlugość tablicy.");
        width = scan.nextInt();
        fillingTable();

        System.out.println("Tablica wygląda następująco:");
        System.out.println(tabUser);

        System.out.println("Podaj opcję: ");
        input = scan.nextInt();

        while (x) {
            switch (input) {
                case 1:
                    tabAverage();
                    break;

            }
        }


    }

    void tabAverage() {

        double average = 0;

        for (int i = 0; i < width; i++) {
            average += tabUser[i];
        }
        average /= width;

        System.out.println("Srednia wartości z tablicy wynosi: " + average);
    }


}
