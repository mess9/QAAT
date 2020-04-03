package ru.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class pageObject {

    private WebDriver chromeDriver;
    private WebElement searchField;
    private WebElement searchButton;
    private List<WebElement> searchResultList;

    //constructor
    public pageObject(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        searchField = chromeDriver.findElement(By.xpath("//*[@id=\"text\"]"));
        searchButton = chromeDriver.findElement(By.xpath("//button[contains(.,'Найти')]"));
    }

    //metod search
    public void searchRequest(String request){
        searchField.click();
        searchField.sendKeys(request);
        searchButton.click();
    }

    //metod get search result
    public List<WebElement> getListSearchResult(){
        searchResultList = chromeDriver.findElements(By.xpath("//h2/a"));
        return searchResultList;
    }
}
