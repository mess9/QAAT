package tests.ui.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenMainPage {

    String selectorExchangeRates = "//*[@class='ReactCollapse--collapse']";
    String selectorTableHeaders = ".//tbody/tr[contains(@class,'header')]/td";
    String selectorTableRow = ".//tbody/tr[contains(@class,'row')]";

    private WebDriver driver;
    private WebElement exchangeRates;
    private List<Map<String,Object>> collectExchangeRates = new ArrayList<>();

    public WebElement getExchangeRates() {
        return exchangeRates;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public List<Map<String, Object>> getCollectExchangeRates() {
        List<WebElement> tableHeaders = exchangeRates.findElements(By.xpath(selectorTableHeaders));
        List<WebElement> tableRow = exchangeRates.findElements(By.xpath(selectorTableRow));

        for(int i=0; i<tableRow.size();++i){
            Map<String,Object> collectRow = new HashMap<>();
            for(int j=0; j<tableHeaders.size();++j){
                collectRow.put(
                        tableHeaders.get(j).getText(), tableRow.get(i).findElement(By.xpath("./td["+(j+1)+"]")).getText()
                        );
            }
            collectExchangeRates.add(collectRow);
        }

        return collectExchangeRates;
    }

    public OpenMainPage(WebDriver driver){
        this.driver = driver;
        if(!driver.getTitle().contains("Открытие"))
            driver.get("https://www.open.ru/");
        exchangeRates = driver.findElement(By.xpath(selectorExchangeRates));
    }


}
