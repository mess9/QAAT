import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.lang.reflect.Field;

public class TestODM {

    OneDemencialMatrix testMatrix = new OneDemencialMatrix();
    TwoDemencialMatrix test2DMatrix = new TwoDemencialMatrix();



    @AfterMethod
    public void background(){
        testMatrix = new OneDemencialMatrix();
        test2DMatrix = new OneDemencialMatrix();

    }

    @Test
    public void checkName(){
        String name="";
        try {
            Field field=testMatrix.getClass().getDeclaredField("nameMatrix");
            field.setAccessible(true);
            name = (String) field.get(testMatrix);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(name);
        Assert.assertEquals(name,"OneDemencialMatrix");
    }

}
