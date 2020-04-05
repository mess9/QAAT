package test.ui.ru.open;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import test.CustimUtils;
import test.ui.WebDrivarSettings;
import test.ui.pageObject.GoogleResultSearch;
import test.ui.pageObject.OpenMainPage;

import java.util.List;
import java.util.Map;

public class Tests extends WebDrivarSettings{

    @Test
    @Description(value="Alerts")
    public void alertTest(){

        Alert alert = driver.switchTo().alert();

        System.out.println(alert.getText());
        alert.dismiss();
        alert.accept();
    }

    @Test
    @Description(value = "Тест открытия")
    public void testWithStep(){
        GoogleResultSearch googlePage = new GoogleResultSearch(driver, "открытие");
        List<Map<String,Object>> resultsSearch = googlePage.getCollectResult();
        Steps.checkContainsLink(driver, resultsSearch,"https://www.open.ru/");
        Steps.goPageText(driver, googlePage,"Банк «Открытие» — вклады, кредитные и дебетовые");
        OpenMainPage openPage = new OpenMainPage(driver);
        List<Map<String,String >> collectExchangeRate = openPage.getCollectExchangeRates();
        Steps.checkCourse(openPage,collectExchangeRate,"USD");
        Steps.checkCourse(openPage,collectExchangeRate,"EUR");
    }

    //@Test
    public void checkOpen(){
        OpenMainPage openPage = new OpenMainPage(driver);

        List<Map<String,String >> collectExchangeRate = openPage.getCollectExchangeRates();
        System.out.println(collectExchangeRate);
        Assert.assertTrue("USD Exchange Rate not valid",
                        Double.valueOf(
                                            collectExchangeRate.stream()
                                                .filter(x-> x.get("Валюта обмена").equals("USD"))
                                                .findFirst()
                                                .get().get("Покупка").replace(",","."))
                        <
                                Double.valueOf(
                                        collectExchangeRate.stream()
                                                .filter(x-> x.get("Валюта обмена").equals("USD"))
                                                .findFirst()
                                                .get().get("Продажа").replace(",","."))
                );

        Assert.assertTrue("EUR Exchange Rate not valid",
                Double.valueOf(
                        collectExchangeRate.stream()
                                .filter(x-> x.get("Валюта обмена").equals("EUR"))
                                .findFirst()
                                .get().get("Покупка").replace(",","."))
                        <
                        Double.valueOf(
                                collectExchangeRate.stream()
                                        .filter(x-> x.get("Валюта обмена").equals("EUR"))
                                        .findFirst()
                                        .get().get("Продажа").replace(",","."))
        );
    }

   // @Test
    public void checkExchangeRate(){
        GoogleResultSearch googlePage = new GoogleResultSearch(driver, "открытие");
        List<Map<String,Object>> resultsSearch = googlePage.getCollectResult();
        Assert.assertTrue("URL not found",
                resultsSearch.stream().anyMatch(x -> x.get("URL").toString().contains("https://www.open.ru/"))
                );

        Assert.assertTrue("did not go to the page: 'Банк «Открытие» — вклады, кредитные и дебетовые'",
                googlePage.goFindPage("Банк «Открытие» — вклады, кредитные и дебетовые")
                );

        OpenMainPage openPage = new OpenMainPage(driver);

        List<Map<String,String >> collectExchangeRate = openPage.getCollectExchangeRates();
        System.out.println(collectExchangeRate);
        Assert.assertTrue("USD Exchange Rate not valid",
                Double.valueOf(
                        collectExchangeRate.stream()
                                .filter(x-> x.get("Валюта обмена").equals("USD"))
                                .findFirst()
                                .get().get("Покупка").replace(",","."))
                        <
                        Double.valueOf(
                                collectExchangeRate.stream()
                                        .filter(x-> x.get("Валюта обмена").equals("USD"))
                                        .findFirst()
                                        .get().get("Продажа").replace(",","."))
        );

        Assert.assertTrue("EUR Exchange Rate not valid",
                Double.valueOf(
                        collectExchangeRate.stream()
                                .filter(x-> x.get("Валюта обмена").equals("EUR"))
                                .findFirst()
                                .get().get("Покупка").replace(",","."))
                        <
                        Double.valueOf(
                                collectExchangeRate.stream()
                                        .filter(x-> x.get("Валюта обмена").equals("EUR"))
                                        .findFirst()
                                        .get().get("Продажа").replace(",","."))
        );
    }


}
