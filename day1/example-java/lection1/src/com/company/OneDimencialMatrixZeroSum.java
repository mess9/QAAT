package com.company;

import static java.lang.Math.random;

// Класс одномерной матрицы реализует интерфейс
public class OneDimencialMatrixZeroSum extends OneDimencialMatrix{

    private int[] array;
    private  int amount;
    private String nameMatrix;
    private int sum;

    public int getAmount() {   //??? забыл что делает эта конструкция :(
        return amount;
    }

    //Конструктор класса, заполняем 5 случайными элементами
    public OneDimencialMatrixZeroSum(){
        nameMatrix="OneDimencialMatrixZeroSum";
        amount = 5;
        array = new int[amount];
        sum = 0;
        do{
            fillRandomArray();
            summ(array);
        }while(sum != 0);

    }
        //функция заливки массива рандомными значениями.
        private int[] fillRandomArray() {
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) ((random() * 100) - 50);
            }
            return array;
        }
        //функция подсчёта суммы элементов массива
        private int summ(int[] args) {
            for (int value : array) { //calculate the summ of array elements
                sum += value;
            }
            return sum;
        }

    //Конструктор класса, перегружаем
    public OneDimencialMatrixZeroSum(int amount){
        nameMatrix="OneDimencialMatrixZeroSum";
        this.amount = amount;
        array = new int[amount];
        sum = 0;
        do{
            fillRandomArray();
            summ(array);
        }while(sum != 0);
    }

    //print matrix
    public void printMatrix(){
        for (int item : array) {
            System.out.print(item + " ");
        }
    }

    public void printSumMatrix(){
        System.out.println("\n"+"Сумма элементов массива = " + sum);
    }

    //print name matrix
    public void printName(){
        System.out.println(nameMatrix);
    }

    @Override
    public int min(){
        int min = array[0];
        for (int value : array) {
            if (min > value)
                min = value;
        }
        final int arr_min = min;
        return arr_min;
    }

    @Override
    public int max() {
        int max = array[0];
        for (int value : array) {
            if (max < value)
                max = value;
        }
        final int arr_max = max;
        return arr_max;
    }



}
