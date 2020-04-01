import static java.lang.Math.random;

// Класс одномерной матрицы реализует интерфейс
public class OneDemencialMatrix implements MatrixInterface{

    private int[] array;
    private  int amoumt;
    private String nameMatrix;

    public int getAmoumt() {
        return amoumt;
    }

    //Конструктор класса, заполняем 5 случайными элементами
    public OneDemencialMatrix (){
        nameMatrix="OneDemencialMatrix";
        amoumt = 5;
        array = new int[amoumt];
        for(int i=0; i<array.length;i++)
            array[i]=(int)((random()*12)-6);
    }

    //Конструктор класса, перегружаем
    public OneDemencialMatrix (int amoumt){
        nameMatrix="OneDemencialMatrix";
        this.amoumt = amoumt;
        array = new int[amoumt];
        for(int i=0; i<array.length;i++)
            array[i]=(int)((random()*12)-6);
    }

    //реализация метода


    public void printMatrix(){
        for(int i=0; i<array.length;i++)
            System.out.println(array[i]);
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
