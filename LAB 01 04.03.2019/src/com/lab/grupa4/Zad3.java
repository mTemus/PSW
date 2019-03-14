package com.lab.grupa4;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Zad3 {
    Set hash1 = new HashSet();
    Set hash2 = new HashSet();
    Set hash3 = new HashSet();
    Set hash4 = new HashSet();
    Set hash5 = new HashSet();

    void hashes() {
        Scanner scan = new Scanner(System.in);
        boolean x = true;

        System.out.println("Napisz program, który dla zadanych zbiorów o wartościach wprowadzonych przez użytkownika wykona " +
                "sumę zbiorów," +
                "różnicę zbiorów," +
                "część wspólną zbiorów" +
                "oraz różnicę symetryczną zbiorów.");

        while(x){
            System.out.println("0. Wyjdź.");
            System.out.println("1. Dodaj liczby do zbioru A.");
            System.out.println("2. Dodaj liczby do zbioru B.");
            System.out.println("3. Wyświetl sumę zbiorów.");
            System.out.println("4. Wyświetl różnicę zbiorów.");
            System.out.println("5. Wyświetl część wspólną zbiorów.");
            System.out.println("6. Wyświetl różnicę symetryczną zbiorów.");
            System.out.println("Zbiór A: " + hash1);
            System.out.println("Zbiór B: " + hash2);
            System.out.println("Wprowadź cyfrę: ");

            int input = scan.nextInt();
            switch(input){
                case 1:
                    System.out.println("Podaj liczbę: ");
                    hash1.add(scan.nextInt());
                    System.out.println("Elementy zbioru: ");
                    System.out.println("Zbiór 1 : " + hash1);
                    break;
                case 2:
                    System.out.println("Podaj liczbę: ");
                    hash2.add(scan.nextInt());
                    System.out.println("Elementy zbioru: ");
                    System.out.println("Zbiór 2 : " + hash2);
                    break;
                case 3:
                    hashSum();
                    break;
                case 4:
                    hashDif();
                    break;
                case 5:
                    hashPart();
                    break;
                case 6:
                    hashSymDif();
                    break;
                case 0:
                    x = false;
                    break;
            }
        }
    }

    void hashSum() {
        hash3.addAll(hash1);
        hash3.addAll(hash2);

        System.out.println("Suma zbiorów wynosi: " + hash3);
        hash3.clear();

    }

    void hashDif() {
        hash3.addAll(hash1);
        hash3.removeAll(hash2);

        System.out.println("Różnica zbiorów wynosi: " + hash3);
        hash3.clear();
    }

    void hashPart() {
        hashCopy();
        hash3.retainAll(hash4);

        System.out.println("Część wspólna zbiorów: " + hash3);
        hashClear();
    }

    void hashSymDif(){
        hashCopy();
        hash3.removeAll(hash2);
        hash5.addAll(hash3);
        hash4.removeAll(hash1);
        hash5.addAll(hash4);

        System.out.println("Różnica symetryczna zbiorów: " + hash5);
        hashClear();
    }

    void hashCopy() {
        hash3.addAll(hash1);
        hash4.addAll(hash2);
    }

    void hashClear(){
        hash3.clear();
        hash4.clear();
    }

}
