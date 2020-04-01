package ru.bellintegrator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FirstTest extends WebDriverSettings {

    private void son(int s){
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/*
    @BeforeEach
    public void BeforeEachTest(){
        System.out.println("BeforeEach");
    }
*/
    @Test
    public void firstTest(){
        System.out.println("Test1");
    }

    @Test
    public void secondTest(){
        System.out.println("Test2");
    }

    @Test
    public void checkTitle(){
        System.out.println("checkTitle");
        chromeDriver.get("https://bellintegrator.ru/");
        String title = chromeDriver.getTitle();
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell Integrator"));
    }

    @Test
    public void  checkFindRPA(){
        System.out.println("checkFindRPA");
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        WebElement searchField = chromeDriver.findElement(By.xpath("//*[@id=\"input-search\"]"));
        WebElement searchButton = chromeDriver.findElement(By.id("button-search"));

        searchField.click();
        searchField.sendKeys("RPA");
        searchButton.click();

        //product-layout product-list col-xs-12

        List<WebElement> ListOfWebElement = chromeDriver
                .findElements(By.xpath("//*[@class=\"product-layout product-list col-xs-12\"]//h4"));

        System.out.println(ListOfWebElement.size());

        Boolean chek = false;
        for(WebElement we : ListOfWebElement)
            if(we.getText().equals("Автоматизация"))
                chek=true;
        Assertions.assertTrue(chek);

        son(5000);
    }

    @Test
    public void findPageObject(){
        System.out.println("findPageObject");
        chromeDriver.get("https://bellintegrator.ru/index.php?route=product/search");
        PageObject bellFind = new PageObject(chromeDriver);

        bellFind.find("RPA");

        Boolean chek = false;
        for(WebElement we : bellFind.getListOfWebElement())
            if(we.getText().equals("Автоматизация"))
                chek=true;
        Assertions.assertTrue(chek);
    }

    @Test
    public void findPageFactory(){
        System.out.println("findPageFactory");
        PageFactoryBell bellFind = PageFactory.initElements(chromeDriver,PageFactoryBell.class);
        bellFind.find("RPA");
        Boolean chek = false;
        for(WebElement we : bellFind.getListOfWebElement())
            if(we.getText().equals("Автоматизация"))
                chek=true;
        Assertions.assertTrue(chek);
    }

/*
    @AfterEach
    public void AfterEachTest(){
        System.out.println("AfterEach");
    }
*/
}
