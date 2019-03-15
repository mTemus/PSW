package com.lab.grupa4;

import java.util.Random;
import java.util.Scanner;

public class Cwiczenie2 {

    Scanner scan = new Scanner(System.in);
    Random numbersA = new Random();
    Random numbersB = new Random();

    void mainMethod() {
        boolean x = true;

        while (x) {
            System.out.println("Podaj liczbę wierszy.");
            int height = scan.nextInt();
            System.out.println("Podaj liczbę kolumn.");
            int width = scan.nextInt();
            if ((height % width) == 0) {
                int[][] matrixA = new int[height][width];
                int[][] matrixB = new int[height][width];
                int[][] matrixResult = new int[height][width];

                int[] arrPremade = {-10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

                for (int line = 0; line < height; line++) {
                    for (int column = 0; column < width; column++) {

                        int randomNumberA = numbersA.nextInt(arrPremade.length);
                        int randomNumberB = numbersB.nextInt(arrPremade.length);

                        matrixA[line][column] = arrPremade[randomNumberA];
                        matrixB[line][column] = arrPremade[randomNumberB];
                    }
                }

                System.out.println("Macierz A wygląda następująco:");
                showMatrix(height, width, matrixA);

                System.out.println("Macierz B wygląda następująco:");
                showMatrix(height, width, matrixB);

                while (x) {

                    System.out.println("1. Dodawanie macierzy.");
                    System.out.println("2. Odejmowanei macierzy.");
                    System.out.println("3. Mnożenie macierzy.");
                    System.out.println("4. Wyjście.");
                    System.out.println("Podaj opcję: ");
                    int input = scan.nextInt();

                    switch (input) {
                        case 1:
                            addMatrixes(height, width, matrixA, matrixB, matrixResult);
                            break;

                        case 2:
                            substractionMatrixes(height, width, matrixA, matrixB, matrixResult);
                            break;
                        case 3:
                            multiplicationMatrixes(height, width, matrixA, matrixB, matrixResult);
                            break;
                        case 4:
                            x = false;
                            break;
                    }
                }
            } else
                System.out.println("Macierz o podanych wymiarach nie jest kwadratowa!");
        }
    }

    private void showMatrix(int height, int width, int[][] matrix) {
        for (int line = 0; line < height; line++) {
            for (int column = 0; column < width; column++) {

                System.out.print("[" + matrix[line][column] + "]");
            }
            System.out.print("\n");
        }
    }

    private void addMatrixes(int height, int width, int[][] matrixOne, int[][] matrixTwo, int[][] resultMatrix) {

        for (int line = 0; line < height; line++) {
            for (int column = 0; column < width; column++) {

                resultMatrix[line][column] = matrixOne[line][column] + matrixTwo[line][column];
            }

        }
        System.out.println("Macierz po wykonaniu fukncji dodawania wygląda następujaco: ");
        showMatrix(height, width, resultMatrix);

    }

    private void substractionMatrixes(int height, int width, int[][] matrixOne, int[][] matrixTwo, int[][] resultMatrix) {
        for (int line = 0; line < height; line++) {
            for (int column = 0; column < width; column++) {

                resultMatrix[line][column] = matrixOne[line][column] - matrixTwo[line][column];
            }
        }
        System.out.println("Macierz po wykonaniu fukncji odejmowania wygląda następujaco: ");
        showMatrix(height, width, resultMatrix);
    }

    private void multiplicationMatrixes(int height, int width, int[][] matrixOne, int[][] matrixTwo, int[][] resultMatrix) {

        int cell = 0;

        for (int line = 0; line < height; line++) {

            for (int column = 0; column < width; column++) {

                for (int tmpColumn = 0; tmpColumn < width; tmpColumn++) {
                    System.out.println("for tmp");
                    System.out.println(matrixOne[line][tmpColumn]);
                    System.out.println(matrixTwo[tmpColumn][line]);

                    cell += (matrixOne[line][tmpColumn] * matrixTwo[tmpColumn][line]);
                }
                resultMatrix[line][column] = cell;
                cell = 0;
            }
        }

        System.out.println("Macierz po wykonaniu fukncji mnożenia wygląda następujaco: ");
        showMatrix(height, width, resultMatrix);
    }


}