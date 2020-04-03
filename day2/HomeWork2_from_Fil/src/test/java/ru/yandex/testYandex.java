package ru.yandex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class testYandex extends WebDriverSettings {

/*    private void son(int s) {
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } */ //method sleep

    public void testPassed(){
        System.out.println("✓ - ✓ - ✓ - Test passed - ✓ - ✓ - ✓");
    }

    @Test
    public void checkYandex() {
        System.out.println("туда или не яндекс?");
        chromeDriver.get("https://yandex.ru");
        String titleTag = chromeDriver.getTitle();
        System.out.println(titleTag);
        Assertions.assertTrue(titleTag.contains("Яндекс"));
    }

    //Задание 1.1 реализовано с помощью java, junit, selenium
    @Test
    public void findGladiolus() {
        System.out.println("ищем гладиолус");
        chromeDriver.get("https://yandex.ru");
        WebElement searchField = chromeDriver.
                findElement(By.xpath("//*[@id=\"text\"]"));
        WebElement searchButton = chromeDriver.
                findElement(By.xpath("//button[contains(.,'Найти')]"));

        searchField.click();
        searchField.sendKeys("гладиолус");
        searchButton.click();
        List<WebElement> SearchResultList = chromeDriver.findElements(By.xpath("//h2/a"));
        System.out.println("всего записей обнаружено по заданному селектору - > " + SearchResultList.size());

        boolean check = false;
        for (WebElement webElement : SearchResultList) {
            if (webElement.getAttribute("href").contains("wikipedia")) {
                check = true;
                System.out.println("ссылка на википедию найдена -> " + webElement.getAttribute("href"));
            }
        }
        Assertions.assertTrue(check);
        testPassed();
    }

    //задание 1.1 реализованное с помощью java, junit, selenium + pageobject
    @Test
    public void findGladioulesPO(){
        System.out.println("ищем гладиолус используя PageObject");
        chromeDriver.get("https://yandex.ru");
        pageObject searchYandex = new pageObject(chromeDriver);
        searchYandex.searchRequest("гладиолус");

        boolean check = false;
        for (WebElement searchResult : searchYandex.getListSearchResult()) {
            if (searchResult.getAttribute("href").contains("wikipedia")) {
                check = true;
            }
        }
        Assertions.assertTrue(check);
        testPassed();
    }
}
