package test.seleniumUI.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenMainPage {

    private WebDriver driver;
    private WebElement ExchangeRatesBlock;
    private List<Map<String, String>> collectExchangeRate = new ArrayList<>();

    //selectors
    private String selectorExchangeRatesBlock = "//*[@class='main-page-exchange main-page-info__card']";
    private String selectorTableHeaders = ".//tbody/tr[contains(@class,'header')]/td";
    private String selectorTableRows = ".//tbody/tr[contains(@class,'row')]";


    //конструктор (если открытие не открыто то открываем открытие)
    public OpenMainPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().contains("Открытие"))
            driver.get("https://www.open.ru");
        ExchangeRatesBlock = driver.findElement(By.xpath(selectorExchangeRatesBlock)); //получаем саму таблицу целиком
    }

    public List<Map<String, String>> getExchangeBlock() {
        //ExchangeRatesBlock.findElements(By.xpath(selectorExchangeRatesBlock)); //получаем саму таблицу целиком
        List<WebElement> tableHeaders = ExchangeRatesBlock.findElements(By.xpath(selectorTableHeaders)); //получаем лист хидеров
        List<WebElement> tableRows = ExchangeRatesBlock.findElements(By.xpath(selectorTableRows)); //получаем элементы столбцов таблицы

        //формируем красивую карту с ключ(из header) значение (из row)
        for (int i = 0; i < tableRows.size(); ++i) {
            Map<String, String> collectionRow = new HashMap<>();
            for (int j = 0; j < tableHeaders.size(); ++j) {
                collectionRow.put(
                        tableHeaders.get(j).getText(),
                        tableRows.get(i).findElement(By.xpath("./td[" + (j + 1) + "]")).getText()
                );
            }
            collectExchangeRate.add(collectionRow);
        }

        return collectExchangeRate;
    }
}
