package com.google;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class WebDriverSettings {

    WebDriver chromeDriver;

    @BeforeEach
    public void setupChromeDriver(){

        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().setSize(new Dimension(1280,1024));
        chromeDriver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(500, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);

    }

    @AfterEach
    public void closeTest(){
        chromeDriver.quit();
        System.out.println("End Test.");
    }

}
