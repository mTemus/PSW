package com.lab.grupa4;

import java.util.Scanner;

public class Zad2 {

    String binarySeq = null;

    void binaryholes() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Napisz program zliczający liczbę dziur binarnych.");
        System.out.println("Wprowadź ciąg.");

        binarySeq = scan.next();
        System.out.println("Ciag: " + binarySeq);
        cntHole();

    }

    void cntHole() {
        int binaryHolesQuantity = 0;
        int i = 0;
        char[] charArr = new char[binarySeq.length()];

        for (char charOfString : binarySeq.toCharArray()) {
            charArr[i] = charOfString;
            i++;
        }
        for (int ones = 0; ones < charArr.length; ones++) {

            if (charArr[ones] == '1' && (ones != (charArr.length - 1))) {
                if (charArr[ones + 1] == '0') {

                    for (int zeros = ones + 1; zeros < charArr.length; zeros++) {

                        if (charArr[zeros] == '1') {
                            binaryHolesQuantity++;
                            ones = zeros - 1;
                            break;
                        }

                    }
                }
            }


        }

        System.out.println("Liczba dziur birnarnych: " + binaryHolesQuantity);

    }

}
