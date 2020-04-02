package ru.bellintegrator;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSettings {
    WebDriver chromeDriver;


    @BeforeEach
    public void setupBellTest() {
        System.out.println("before");
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "c:\\Games\\ChromeDriver\\chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(50, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @AfterEach
    public void closeBellTest() {
        chromeDriver.quit();
        System.out.println("the end test.");
    }

}
