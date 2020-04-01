import static java.lang.Math.random;

public class TwoDemencialMatrix implements MatrixInterface {

    private int[][] array2D;
    private int amount;
    private String nameMatrix;

//конструктор объекта - 2-мерная матрица размерностью 5.
    public TwoDemencialMatrix (){
        nameMatrix="OneDemencialMatrix";
        amount = 5;
        array2D = new int[amoumt][amount];
        for(int i=0; i<array2D.length;i++) {
            for (int f=0; f<array2D.length;f++){
                array2D[i][f] = (int)((random()*100));
            }
        }

    }
//конструктор объекта - 2-мерная матрица размерностью - amount.
    public TwoDemencialMatrix (int amoumt){
        nameMatrix="OneDemencialMatrix";
        this.amount = amount;
        array2D = new int[amoumt][amount];
        for(int i=0; i<array2D.length;i++) {
            for (int f=0; f<array2D.length;f++){
                array2D[i][f] = (int)((random()*100));
            }
        }

    }

//    //метод вывода двумерной матрицы.
//    public void printMatrix(){
//        for(int i=0; i<array.length;i++)
//            System.out.println(array[i]);
//    }
    public void print2DMatrix(){
        for (int i=0; i<array2D.length;i++){
            for (int f=0;f<array2D.length;f++){
                System.out.print(array2D[i][f]+ " ");
            }
            System.out.println();
        }
    }


    public void printName(){
        System.out.println(nameMatrix);
    }

    public int min() {
        return 0;
    }

    public int max() {
        return 0;
    }
}