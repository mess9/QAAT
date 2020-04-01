package ru.bellintegrator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageObject {

    private WebDriver chromeDriver;
    public WebElement searchField;
    private WebElement searchButton;
    private List<WebElement> listOfWebElement;

    public PageObject (WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        searchField = chromeDriver.findElement(By.xpath("//*[@id=\"input-search\"]"));
        searchButton = chromeDriver.findElement(By.id("button-search"));
    }

    public void find(String stringFind){
        searchField.click();
        searchField.sendKeys(stringFind);
        searchButton.click();
    }

    public List<WebElement> getListOfWebElement(){
        listOfWebElement = chromeDriver.findElements(By.xpath("//*[@class=\"product-layout product-list col-xs-12\"]//h4"));
        return listOfWebElement;
    }

}
