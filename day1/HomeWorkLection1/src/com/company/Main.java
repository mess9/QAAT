package com.company;

public class Main {



    public static void main(String[] args) {


/*        //пример рефлексии - выводим имя матрицы. из приватного значения.
        try {
            Field field=myMatrixOne.getClass().getDeclaredField("nameMatrix");
            field.setAccessible(true);
            System.out.println(field.get(myMatrixOne));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/ //example of reflection

        //конструируем необходимые объекты, с необходимыми параметрами(или без них)
        OneDimencialMatrix myMatrixOne = new OneDimencialMatrix(5);//объект класса одномерная матрица
        TwoDimencialMatrix my2DMatrix = new TwoDimencialMatrix(11,true); //объект класса двумерная матрица
        OneDimencialMatrixZeroSum ZeroSumMatrix = new OneDimencialMatrixZeroSum(3); //объект класса матрицы с нулевой суммой
        TwoDemencialMatrixZeroSum ZeroSumMatrix2D = new TwoDemencialMatrixZeroSum(6); //объект класса двумерной матрицы с нулевой суммой
//
        //операции с одномерной матрицей
//        myMatrixOne.printName();
//        myMatrixOne.printMatrix();
//        System.out.println(myMatrixOne.min());
//        System.out.println(myMatrixOne.max());

        //операции с двумерной матрицей
//        my2DMatrix.printName();
//        my2DMatrix.CrossZeroImMatrix();
//        my2DMatrix.print2DMatrix();
//        my2DMatrix.ArraySnakeToList();
//        my2DMatrix.printSnakeList();
//        System.out.println(my2DMatrix.min());
//        System.out.println(my2DMatrix.max());


        //операции с матрицей с нулевой суммой
//        ZeroSumMatrix.printName();
//        ZeroSumMatrix.printMatrix();
//        ZeroSumMatrix.printSumMatrix();
//        System.out.println(ZeroSumMatrix.min());
//        System.out.println(ZeroSumMatrix.max());
//        ZeroSumMatrix2D.print2DMatrix();
//        ZeroSumMatrix2D.printSumMatrix();

        //попробовал использовать функцию hashCode
//        System.out.println(ZeroSumMatrix.hashCode());


    }
}
