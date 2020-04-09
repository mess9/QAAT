package test.seleniumUI.ru.open;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import test.CustomUtils;
import test.seleniumUI.PageObject.GoogleResultsSearch;
import test.seleniumUI.PageObject.OpenMainPage;
import test.seleniumUI.PageObject.YandexResultSearch;
import test.seleniumUI.WebDriverSettings;

import java.util.List;
import java.util.Map;

public class Tests extends WebDriverSettings {

    @Test
    public void checkURL() {
        GoogleResultsSearch googlePage = new GoogleResultsSearch(driver, "открытие"); //поиск по запросу - открытие
        List<Map<String, Object>> resultSearch = googlePage.getCollectResult(); //результат распарсен в лист результатов.

        Assert.assertTrue("URL not found", //проверили содержимое выдачи, что под указатем URL есть искомый адрес
                resultSearch.stream().anyMatch(x -> x.get("URL").toString().contains("https://www.open.ru")));

        Assert.assertTrue("did not go to the page: Банк «Открытие»",
                googlePage.goFindPage("Банк «Открытие»") //проверили что работает переход на нужную страницу
        );
    }

    @Test
    public void checkChangeToURL() {
        //как правильно реализовать рекурсию такого рода?
        //public void checkChangeToURL(String pageTitle, String searchRequest){
        //checkChangeToURL("Банк «Открытие»", "открытие");

        GoogleResultsSearch googlePage = new GoogleResultsSearch(driver, "открытие", true);
        //googlePage.getCollectResult(); //обязательно нужно заполнить collectResult
        // (может это лучше делать прямо в конструкторе?)
        // реализовал это в конструкторе с флагом parse
        Assert.assertTrue("did not go to the page: " + "Банк «Открытие»", googlePage.goFindPage("Банк «Открытие»"));
    }

    @Test
    public void checkSearchResult() {
        GoogleResultsSearch googlePage = new GoogleResultsSearch(driver, "открытие");
        List<Map<String, Object>> resultSearch = googlePage.getCollectResult();
        System.out.println(googlePage.getCollectResult().size());
        System.out.println(resultSearch);
    }

    @Test
    public void checkOpenExchange() {
        OpenMainPage openPage = new OpenMainPage(driver);

        //выводит то что мы распарсили стаблички обменных курсов
        List<Map<String, String>> collectExchangeRate = openPage.getExchangeBlock();
        System.out.println(collectExchangeRate);

        //проверка - USD
        Assert.assertTrue("USD exchange rate on valid",
                CustomUtils.strToFloatCurrency(collectExchangeRate, "USD", "Покупка")
                        <
                        CustomUtils.strToFloatCurrency(collectExchangeRate, "USD", "Продажа"));
        //проверка - EUR
        Assert.assertTrue("EUR exchange rate on valid",
                CustomUtils.strToFloatCurrency(collectExchangeRate, "EUR", "Покупка")
                        <
                        CustomUtils.strToFloatCurrency(collectExchangeRate, "EUR", "Продажа"));
    }

    @Test
    public void findGladiolusWiki() {
        YandexResultSearch findGladiolus = new YandexResultSearch(driver, "гладиолус");
        //проверка
        boolean check = false;
        for (WebElement sr : findGladiolus.getListSearchResultHREF())
            if (sr.getAttribute("href").contains("wikipedia"))
                check = true;

        Assert.assertTrue("ссылка на википедю в выдаче не обнаружена", check);
    }

    @Test
    public void findGladiolusWikiV2() {
        YandexResultSearch findGladiolus = new YandexResultSearch(driver, "гладиолус");
        //проверка
//        System.out.println(findGladiolus.getCollectResult().size());
//        System.out.println(findGladiolus.getCollectResult());

        Assert.assertTrue("ссылка на википедию в выдаче не обнаружена",
                (findGladiolus.getCollectResult()
                        .stream()
                        .anyMatch(x -> x.get("URL").toString().contains("wikipedia")))
        );
    }


}
