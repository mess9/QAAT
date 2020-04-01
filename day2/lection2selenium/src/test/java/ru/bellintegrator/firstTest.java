package ru.bellintegrator;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.List;

public class firstTest extends WebDriverSettings {

/*    private void son(int s){
         try {
             Thread.sleep(s);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }*/ //method sleep

    @Test
    public void firstTest() {
        System.out.println("Test1");
    }

    @Test
    public void checkTitle() {
        System.out.println("check Title");
        chromeDriver.get("https://bellintegrator.ru/");
        String title = chromeDriver.getTitle();
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell Integrator"));
    }

    @Test
    public void checkFindRPA() {
        System.out.println("check Title");
        chromeDriver.get("http://bellintegrator.ru/index.php?route=product/search");
        WebElement searchField = chromeDriver.findElement(By.xpath("//*[@id=\"input-search\"]"));
        WebElement searchBatton = chromeDriver.findElement(By.id("button-search"));

        searchField.click();
        searchField.sendKeys("RPA");
        searchBatton.click();

        List<WebElement> ListOfWebElement = chromeDriver.findElements(By.xpath("//*[@class=\"product-layout product-list col-xs-12\"]//h4"));
        System.out.println("всего обнаружено записей по заданному селктору -> " + ListOfWebElement.size());

        Boolean check = false;
        for (WebElement we : ListOfWebElement) {
            if (we.getText().equals("Автоматизация")) ;
            {
                check = true;
            }
        }
        Assertions.assertTrue(check);
        System.out.println("Текст \"автоматзиация\" в выдаче найден");
        //son(5000);
    }

    @Test
    public void findPageObject() {
        System.out.println("find page object");
        chromeDriver.get("http://bellintegrator.ru/index.php?route=product/search");
        PageObject BellFind = new PageObject(chromeDriver);
        BellFind.find("RPA");

        boolean check = false;
        for (WebElement we : BellFind.getListOfWebElement()) {
            if (we.getText().equals("Автоматизация")) {
                check = true;
            }

        }
        Assertions.assertTrue(check);
        System.out.println("Текст \"автоматзиация\" в выдаче найден");
    }

    @Test
    public void findPageFactory() {
        System.out.println("find Page Factory");
        PageFactoryBellintegrator BellFind = PageFactory.initElements(chromeDriver, PageFactoryBellintegrator.class);
        BellFind.find("RPA");

        boolean check = false;
        for (WebElement we : BellFind.getListOfWebElement()) {
            if (we.getText().equals("Автоматизация")) ;
            {
                check = true;
            }
        }
        Assertions.assertTrue(check);
        System.out.println("Текст \"автоматзиация\" в выдаче найден");

    }
}
