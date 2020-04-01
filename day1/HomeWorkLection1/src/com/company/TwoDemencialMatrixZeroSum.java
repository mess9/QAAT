package com.company;

import static java.lang.Math.random;

public class TwoDemencialMatrixZeroSum extends TwoDimencialMatrix {

    private int[][] array2D;
    private int amount;
    private String nameMatrix;
    private int sum;


    public TwoDemencialMatrixZeroSum(int amount) {
        nameMatrix = "OneDimencialMatrixZeroSum";
        this.amount = amount;
        array2D = new int[amount][amount];
        sum = 0;
        do {
            fillRandomArray();
            summ(array2D);
        } while (sum != 0);
    }

    //функция подсчёта суммы элементов массива
    private int summ(int[][] array2D) {
        for (int i = 0; i < array2D.length; i++) {
            for (int j=0; j < array2D.length; j++) {
                sum += array2D[i][j];
            }
        }
        return sum;
    }

    //функция заливки массива рандомными значениями.
    private int[][] fillRandomArray() {
        for (int i = 0; i < array2D.length; i++) {
            for (int f = 0; f < array2D.length; f++) {
                array2D[i][f] = (int) ((random() * 100) - 50);
            }
        }
        return array2D;
    }

    public void printSumMatrix(){
        System.out.println("\n"+"Сумма элементов массива = " + sum);
    }


}






