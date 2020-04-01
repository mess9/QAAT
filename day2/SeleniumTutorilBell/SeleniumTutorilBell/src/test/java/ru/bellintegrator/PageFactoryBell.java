package ru.bellintegrator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PageFactoryBell {
    private WebDriver chromeDriver;
    private String url = "https://bellintegrator.ru/index.php?route=product/search";

    @FindBy(how = How.ID, id="input-search")
    WebElement searchField;

    @FindBy(how = How.ID, id="button-search")
    WebElement searchButton;

    @FindAll(@FindBy(how = How.XPATH, using = "//*[@class=\"product-layout product-list col-xs-12\"]//h4"))
    List<WebElement> listOfWebElement;

    public PageFactoryBell(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
        chromeDriver.get(url);
    }

    public void find(String stringFind){
        searchField.click();
        searchField.sendKeys(stringFind);
        searchButton.click();
    }

    public List<WebElement> getListOfWebElement() {
        return listOfWebElement;
    }
}
