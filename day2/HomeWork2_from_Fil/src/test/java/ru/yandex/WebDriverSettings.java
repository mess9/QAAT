package ru.yandex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;


public class WebDriverSettings {

    WebDriver chromeDriver;

    @BeforeEach
    private void setupChromeDriver(){

        //WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().setSize(new Dimension(1280,1024));
        chromeDriver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(500, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);

    }

    @AfterEach
    private void closeTest(){
        chromeDriver.quit();
        System.out.println("End Test.");
    }

}
