package com.google;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;


public class WebDriverSettings {

    WebDriver chromeDriver;

    @BeforeEach
    private void setupChromeDriver(){

        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--windows-size=1024,768");
        chromeDriver = new ChromeDriver(options);
        //chromeDriver.manage().window().setSize(new Dimension(1280,1024));
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
