package basePack;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSettings {
    protected WebDriver chromeDriver;

    @Before
    public void setupBellTest(){
        //System.out.println("Before");
        System.setProperty("webdriver.chrome.driver","C:\\temp\\chromedriver.exe");
        chromeDriver=new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(40, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    @After
    public void closeBellTest(){
        //System.out.println("After");
        chromeDriver.quit();
    }
}
