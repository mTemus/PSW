package com.lab.grupa4;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Cwiczenie1 zad1 = new Cwiczenie1();
        Cwiczenie2 zad2 = new Cwiczenie2();


        Scanner scan = new Scanner(System.in);
        boolean x = true;
        int option;

        while (x) {

            System.out.println("1. Wyświetl ćwiczenie nr 1.");
            System.out.println("2. Wyświetl ćwiczenie nr 2.");
            System.out.println("3. Wyjście.");
            System.out.println("Wybierz opcję.");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    zad1.mainMethod();
                    break;
                case 2:
                    zad2.mainMethod();
                    break;
                case 3:
                    System.out.println("Wyjście z programu.");
                    x = false;
                    break;
            }
        }
    }
}
