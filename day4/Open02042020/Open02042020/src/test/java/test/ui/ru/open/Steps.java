package test.ui.ru.open;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import test.CustimUtils;
import test.ui.pageObject.GoogleResultSearch;
import test.ui.pageObject.OpenMainPage;

import java.util.List;
import java.util.Map;

public class Steps {

    @Step("Проверка наличия ссылки {link}")
    public static void checkContainsLink(WebDriver driver,List<Map<String,Object>> resultsSearch, String link){
        Assert.assertTrue("URL not found",
                resultsSearch.stream().anyMatch(x -> x.get("URL").toString().contains(link))
        );
        CustimUtils.getScreen(driver);
    }

    @Step("Перейдём по ссылке с текстом {text}")
    public static void goPageText(WebDriver driver,GoogleResultSearch page, String text){
        Assert.assertTrue("did not go to the page: '"+text+"'",
                page.goFindPage(text)
        );
    }

    @Step("Проверка курса {moneyType}")
    public static void checkCourse(OpenMainPage page, List<Map<String,String >> collectExchangeRate, String moneyType){
        Assert.assertTrue(moneyType+" Exchange Rate not valid",
                Double.valueOf(
                        collectExchangeRate.stream()
                                .filter(x-> x.get("Валюта обмена").equals(moneyType))
                                .findFirst()
                                .get().get("Покупка").replace(",","."))
                        <
                        Double.valueOf(
                                collectExchangeRate.stream()
                                        .filter(x-> x.get("Валюта обмена").equals(moneyType))
                                        .findFirst()
                                        .get().get("Продажа").replace(",","."))
        );
        CustimUtils.getScreen(page.getDriver(), page.getExchangeRates());
    }
}
