package ru.yandex;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class testYandex extends WebDriverSettings {

        private void son(int s){
         try {
             Thread.sleep(s);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     } //method sleep

    @Test
    public void checkYandex(){
        System.out.println("туда или не яндекс?");
        chromeDriver.get("https://yandex.ru");
        String titleTag = chromeDriver.getTitle();
        System.out.println(titleTag);
        Assertions.assertTrue(titleTag.contains("Яндекс"));
    }

    @Test
    public void findGladiolus(){
        System.out.println("ищем гладиолус");
        chromeDriver.get("https://yandex.ru");
        WebElement searchField = chromeDriver.
                findElement(By.xpath("//*[@id=\"text\"]"));
        WebElement searchButton = chromeDriver.
                findElement(By.xpath("//button[contains(.,'Найти')]\n"));

        searchField.click();
        //son(5000);
        searchField.sendKeys("гладиолус");
        searchButton.click();
        //List<WebElement> SearchResultList = chromeDriver.findElements(By.xpath("//*[@id=\"search-result\"]//h2/a"));
        List<WebElement> SearchResultList = chromeDriver.findElements(By.xpath("//h2/a[@href]"));
        System.out.println("всего записей обнаружено по заданному селектору - > " + SearchResultList.size());
        //System.out.println(SearchResultList.get().toString());
        for (int href=0; href< SearchResultList.size();href++)
            System.out.println(SearchResultList.get(href).getAttribute("href"));

        Assertions.assertTrue();

    }


}
