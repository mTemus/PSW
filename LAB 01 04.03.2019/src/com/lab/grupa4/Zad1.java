package com.lab.grupa4;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Zad1 {

    void hashcnt() {
        Scanner scan = new Scanner(System.in);
        Set intList = new HashSet();
        boolean x = true;
        int cnt = 0;
        System.out.println("Napisz program zliczający liczbę unikatowych wartości całkowitych wprowadzonych przez użytkownika.");

        while(x){
            System.out.println("1. Dodaj liczbę");
            System.out.println("2. Wyświetl zbiór");
            System.out.println("3. Policz elementy zbioru.");
            System.out.println("4. Wyjdź.");
            System.out.println("Wprowadź cyfrę: ");
            int input = scan.nextInt();

            switch(input){
                case 1:
                    System.out.println("Podaj liczbę: ");
                    intList.add(scan.nextInt());
                    break;
                case 2:
                    System.out.println("Elementy zbioru: ");
                    System.out.println(intList);
                    break;
                case 3:
                    for(Object object : intList){
                        cnt++;
                    }
                    System.out.println("Liczba unikalnych wartości: " + cnt);
                case 4:
                    x = false;
                    break;
            }



        }




    }


}
