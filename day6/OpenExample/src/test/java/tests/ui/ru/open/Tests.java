package tests.ui.ru.open;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.ui.WebDriverSettings;
import tests.ui.pageObject.GoogleResultSearch;
import tests.ui.pageObject.OpenMainPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tests extends WebDriverSettings{


    @Test
    @Description(value="Alerts")
    public void alertTests(){
        driver.get("");
        stopSec(10);
        driver.findElement(By.xpath("")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.dismiss();
    }

    @Test
    @Description(value = "Тест открытия")
    public void checkExchangeRateWithStep() {
        GoogleResultSearch googlePage = new GoogleResultSearch(driver, "открытие");
        stopSec(15);
        List<Map<String,Object>> resultsSearch = googlePage.getCollectResults();
        Steps.checkContainsLink(driver, resultsSearch,"https://www.open.ru/");
        Steps.goPageText(googlePage,"Банк «Открытие» — вклады, кредитные и дебетовые");

        OpenMainPage openPage = new OpenMainPage(driver);
        List<Map<String,Object>> collectExchangeRates = openPage.getCollectExchangeRates();
        Steps.checkCourse(openPage, collectExchangeRates, "USD");
        Steps.checkCourse(openPage, collectExchangeRates, "EUR");
    }

    @Test
    public void checkOtkr(){
        OpenMainPage openPage = new OpenMainPage(driver);

        List<Map<String,Object>> collectExchangeRates = openPage.getCollectExchangeRates();

        Assert.assertTrue("USD Exchange Rate not valid",
                Double.valueOf(
                        collectExchangeRates.stream()
                                .filter(x -> x.get("Валюта обмена").equals("USD"))
                                .findFirst()
                                .get().get("Покупка").toString().replace(",",".")
                        )
                        <
                        Double.valueOf(
                                collectExchangeRates.stream()
                                        .filter(x -> x.get("Валюта обмена").equals("USD"))
                                        .findFirst()
                                        .get().get("Продажа").toString().replace(",",".")
                        )
        );

        Assert.assertTrue("EUR Exchange Rate not valid",
                Double.valueOf(
                        collectExchangeRates.stream()
                                .filter(x -> x.get("Валюта обмена").equals("EUR"))
                                .findFirst()
                                .get().get("Покупка").toString().replace(",",".")
                )
                        <
                        Double.valueOf(
                                collectExchangeRates.stream()
                                        .filter(x -> x.get("Валюта обмена").equals("EUR"))
                                        .findFirst()
                                        .get().get("Продажа").toString().replace(",",".")
                        )
        );
    }

    @Test
    public void checkExchangeRate(){
        GoogleResultSearch googlePage = new GoogleResultSearch(driver, "открытие");

        List<Map<String,Object>> resultsSearch = googlePage.getCollectResults();

        Assert.assertTrue("URL not found",
                resultsSearch.stream().anyMatch(x -> x.get("URL").toString().contains("https://www.open.ru/"))
        );

        Assert.assertTrue("did not go to the page Банк «Открытие» — вклады, кредитные и дебетовые"
                ,googlePage.goFindPage("Банк «Открытие» — вклады, кредитные и дебетовые"));

        OpenMainPage openPage = new OpenMainPage(driver);

        List<Map<String,Object>> collectExchangeRates = openPage.getCollectExchangeRates();

        Assert.assertTrue("USD Exchange Rate not valid",
                Double.valueOf(
                        collectExchangeRates.stream()
                                .filter(x -> x.get("Валюта обмена").equals("USD"))
                                .findFirst()
                                .get().get("Покупка").toString().replace(",",".")
                )
                        <
                        Double.valueOf(
                                collectExchangeRates.stream()
                                        .filter(x -> x.get("Валюта обмена").equals("USD"))
                                        .findFirst()
                                        .get().get("Продажа").toString().replace(",",".")
                        )
        );

        Assert.assertTrue("EUR Exchange Rate not valid",
                Double.valueOf(
                        collectExchangeRates.stream()
                                .filter(x -> x.get("Валюта обмена").equals("EUR"))
                                .findFirst()
                                .get().get("Покупка").toString().replace(",",".")
                )
                        <
                        Double.valueOf(
                                collectExchangeRates.stream()
                                        .filter(x -> x.get("Валюта обмена").equals("EUR"))
                                        .findFirst()
                                        .get().get("Продажа").toString().replace(",",".")
                        )
        );

    }

}
