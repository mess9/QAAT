package ru.bellintegrator;

import com.google.gson.JsonElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


import java.util.List;

public class PageFactoryBellintegrator {

    private WebDriver chromeDriver;
    private String url = "http://bellintegrator.ru/index.php?route=product/search" ;

    @FindBy (how = How.ID, id = "input-search")
    WebElement searchField;

    @FindBy ( how = How.ID, id = "button-search")
    WebElement searchButton;

    @FindAll(@FindBy(how = How.XPATH, using = "//*[@class=\"product-layout product-list col-xs-12\"]//h4" ))
    List<WebElement> listOfWebElement;

    //конструктор
    public PageFactoryBellintegrator(WebDriver chromeDriver){
        this.chromeDriver= chromeDriver;
        chromeDriver.get(url);
    }

    //метод поиска
    public void find(String stringFind){
        searchField.click();
        searchField.sendKeys(stringFind);
        searchButton.click();
    }

    //метод возвращающий лист элементов
    public List<WebElement> getListOfWebElement(){
        return listOfWebElement;
    }

}
