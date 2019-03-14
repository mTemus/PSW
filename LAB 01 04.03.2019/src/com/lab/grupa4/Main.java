package com.lab.grupa4;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

    Zad1 zadanie1 = new Zad1();
    Zad2 zadanie2 = new Zad2();
    Zad3 zadanie3 = new Zad3();

    Scanner scan = new Scanner(System.in);

    boolean x = true;

    while(x){

        System.out.println("0. Wyjdź.");
        System.out.println("1. Uruchom ćwiczenie nr 1.");
        System.out.println("2. Uruchom ćwiczenie nr 2.");
        System.out.println("3. Uruchom ćwiczenie nr 3.");
        System.out.println("Wprowadź cyfrę: ");

        int input = scan.nextInt();

        switch(input){
            case 0:
                x = false;
                break;
            case 1:
                zadanie1.hashcnt();
                break;
            case 2:
                zadanie2.binaryholes();
                break;
            case 3:
                zadanie3.hashes();
                break;
        }
    }
    }
}
