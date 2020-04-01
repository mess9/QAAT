package com.company;

import java.util.Arrays;

import static java.lang.Math.random;

// Класс одномерной матрицы реализует интерфейс
public class OneDimencialMatrix implements MatrixInterface{

    private int[] array;
    private  int amount;
    private String nameMatrix;

    public int getAmount() {
        return amount;
    }

    //Конструктор класса, заполняем 5 случайными элементами
    public OneDimencialMatrix (){
        nameMatrix="OneDimencialMatrix";
        amount = 5;
        array = new int[amount];
        for(int i=0; i<array.length;i++)
            array[i]=(int)((random()*100)-50);
    }

    //Конструктор класса, перегружаем
    public OneDimencialMatrix (int amount){
        nameMatrix="OneDimencialMatrix";
        this.amount = amount;
        array = new int[amount];
        for(int i=0; i<array.length;i++) {
            array[i] = (int) ((random() * 100) - 50);
        }

    }

    //print matrix
    public void printMatrix(){
        for(int i=0; i<array.length;i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    //print name matrix
    public void printName(){
        System.out.println(nameMatrix);
    }

    //реализация метода интерфейса - поиск минимального значения
    @Override
    public int min(){
/*        int min = array[0];
        for (int i=0;i<array.length;i++){
            if (min>array[i])
                min = array[i];
        }
        return min;*/ //тоже рабочий поиска минимума
        return Arrays.stream(array).min().getAsInt();

    }
    //реализация метода интерфейса - поиск максимального значения
    @Override
    public int max() {
/*        int max = array[0];
        for (int i=0;i<array.length;i++){
            if (max<array[i])
                max = array[i];
        }
        final int arr_max = max;
        return arr_max;*/ //тоже рабочий поиска максимума
        return Arrays.stream(array).max().getAsInt();
    }



}
