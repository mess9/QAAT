package com.google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class pageObject {

    private WebDriver chromeDriver;
    private WebElement searchField;
    private WebElement searchButton;
    private List<WebElement> searchResultList;
    private List<WebElement> USD_EUR;

    //constructor for Google
    public pageObject(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        searchField = chromeDriver.findElement(By.xpath("//*[@id=\"tsf\"]//input[@type=\"text\"]"));
        searchButton = chromeDriver.findElement(By.xpath("//*[@id=\"tsf\"]//input[@value=\"Поиск в Google\"]"));
    }

    //metod search on Google
    public void searchRequest(String request){
        searchField.click();
        searchField.sendKeys(request);
        searchButton.click();
    }

    //metod get search result on Google
    public List<WebElement> getListSearchResult(){
        searchResultList = chromeDriver.findElements(By.xpath("//*[@class=\"g\"]//a"));
        return searchResultList;
    }

    //metod get currency on open.ru
    public List<WebElement> getUSD_EUR_List(){
        USD_EUR = chromeDriver.findElements(By.xpath("//table[@class=\"main-page-exchange__table main-page-exchange__table--online\"]"));
                //findElements(By.xpath("//*[@class\"main-page-exchange__rate\""));
        //findElements(By.xpath("//table[@class=\"main-page-exchange__table main-page-exchange__table--online\"]//span[@class\"main-page-exchange__rate\""));

        return USD_EUR;
    }

}
