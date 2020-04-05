package test.seleniumUI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverSettings {

    protected WebDriver driver;

    @BeforeEach
    protected void startTest(){

        //настройки хром драйвера
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--windows-size=1024,768");
        driver = new ChromeDriver(options);
        //chromeDriver.manage().window().setSize(new Dimension(1280,1024));
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(500, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
    }

    @AfterEach
    private void closeTest(){
        driver.quit();
        System.out.println("End Test.");
    }

}
