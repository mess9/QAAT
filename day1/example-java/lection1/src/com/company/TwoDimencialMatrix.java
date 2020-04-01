package com.company;

import java.util.ArrayList;
import static java.lang.Math.random;

public class TwoDimencialMatrix implements MatrixInterface {

    private int[][] array2D;
    private int amount;
    private String nameMatrix;
    private ArrayList<Integer> snakeList = new ArrayList<Integer>();

    //create constructor
    public TwoDimencialMatrix(){
        nameMatrix = "TwoDimencialMatrix";
        amount = 5 ;
        array2D = new int[amount][amount];
        for(int i=0; i<array2D.length;i++) {
            for (int f=0; f<array2D.length;f++){
                array2D[i][f] = (int)((random()*100)-50);
            }
        }

    }

    //create constructor + args
    public TwoDimencialMatrix(int amount){
        nameMatrix = "TwoDimencialMatrix";
        this.amount = amount ;
        array2D = new int[amount][amount];
        for(int i=0; i<array2D.length;i++) {
            for (int f=0; f<array2D.length;f++){
                array2D[i][f] = (int)((random()*100)-50);
            }
        }
    }

    //create constructor + args + flag
    public TwoDimencialMatrix(int amount, boolean positive) {
        nameMatrix = "TwoDimencialMatrix";
        this.amount = amount;
        array2D = new int[amount][amount];
        if (positive == true) {
            for (int i = 0; i < array2D.length; i++) {
                for (int f = 0; f < array2D.length; f++) {
                    array2D[i][f] = (int) (random() * 50);
                }
            }
        } else {
            for (int i = 0; i < array2D.length; i++) {
                for (int f = 0; f < array2D.length; f++) {
                    array2D[i][f] = (int) ((random() * 100) - 50);
                }
            }
        }
    }

    //преобразовываем матрицу в лист по спирали(по часовой стрелке)
    public void ArraySnakeToList() {
        int x = 0;
        int y = 0;
        int matrixElement = 0;
        int N = array2D.length;
        int range = N;

        while (matrixElement < (N * N)) {
            for (int i = 0; i < range; x++, i++) { //right
                snakeList.add(array2D[y][x]);
                matrixElement++;
            }
            range -= 1;
            x -= 1;
            y += 1;
            for (int i = 0; i < range; y++, i++) { //down
                snakeList.add(array2D[y][x]);
                matrixElement++;
            }
            x -= 1;
            y -= 1;
            for (int i = 0; i < range; x--, i++) { //left
                snakeList.add(array2D[y][x]);
                matrixElement++;
            }
            range -= 1;
            x += 1;
            y -= 1;
            for (int i = 0; i < range; y--, i++) { //up
                snakeList.add(array2D[y][x]);
                matrixElement++;
            }
            x += 1;
            y += 1;
        }
    }

    //вывод списка с пройденной змейкой матрецей
    public void printSnakeList(){
        System.out.println(snakeList);
    }

    //рисуем крестики нулями в матрицах
    public void CrossZeroImMatrix() {
        int margin;
        int x, x1, x2;
        int y, y1, y2;

        if (array2D.length <= 2) {
            System.out.println("Такую мутрицу мы не будем изменять");
        }

        if (array2D.length % 2 == 0) { //проверка чётности
            margin = (array2D.length / 2) - 1; //вычисляем отступ от края матрицы

            for (x = 0, y1 = margin, y2 = margin + 1; x < array2D.length; x++) { //заполняем две центральных горизонтали - нулями
                array2D[y1][x] = 0;
                array2D[y2][x] = 0;
            }
            for (y = 0, x1 = margin, x2 = margin + 1; y < array2D.length; y++) { //заполняем две центральных вертикали - нулями
                array2D[y][x1] = 0;
                array2D[y][x2] = 0;
            }
        }
        else {
            margin = array2D.length/2;

            for (x = 0, y = margin ; x< array2D.length;x++){    //заполняем центральную горизонталь - нулями
                array2D[y][x] = 0;
            }
            for (y=0, x= margin; y<array2D.length;y++){     //заполняем центральную вертикаль - нулями
                array2D[y][x] = 0;
            }
        }
    }

    //print matrix
    public void print2DMatrix() {
        for (int i = 0; i < array2D.length; i++) {
            for (int f = 0; f < array2D.length; f++) {
                System.out.print(array2D[i][f] + " ");
            }
            System.out.println();
        }
    }

    //print name matrix
    public void printName() {
        System.out.println(nameMatrix);
    }

    @Override
    public int min() {
        int min = array2D[0][0];
        for (int i=0;i<array2D.length;i++){
            for (int f=0;f<array2D.length;f++){
                if (min>array2D[i][f]) {
                    min = array2D[i][f];
                }
            }
        }
        final int arr2D_min = min;

        return arr2D_min;
    }

    @Override
    public int max() {
        int max = array2D[0][0];
        for (int i=0;i<array2D.length;i++){
            for (int f=0;f<array2D.length;f++){
                if (max<array2D[i][f]) {
                    max = array2D[i][f];
                }
            }
        }
        final int arr2D_max = max;

        return arr2D_max;
    }
}
