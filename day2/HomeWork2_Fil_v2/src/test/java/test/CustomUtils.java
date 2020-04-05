package test;

public class CustomUtils {

    // ожидание на N секунд
    public static void stopSec(int s){
        try {
            Thread.sleep(s*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testPassed(){
        System.out.println("✓ - ✓ - ✓ - Test passed - ✓ - ✓ - ✓");
    }

}
