package test.seleniumUI.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YandexResultSearch {

    private String selectorSearchField = "//*[@id='text']";
    private String selectorSearchButton = "//button[contains(.,'Найти')]";
    private String selectorSearchResultListHREF = "//h2/a";
    private String selectorSearchResult = "//*[@id=\"search-result\"]/li[not(@data-fast-wzrd)]";
    private String selectroURL = ".//a[contains(@class,\"organic__url\")]" ;
    private String selectroNamePage = ".//h2//*[contains(@class,\"text\")]" ;
    private String selectorDescription = ".//span[@class=\"extended-text__short\"]" ; //c описание накладочка вышла




    private WebDriver driver;
    private WebElement searchField;
    private WebElement searchButton;
    private List<WebElement> searchResultListHREF = new ArrayList<>();
    private List<WebElement> searchResultList;
    private List<Map<String,Object>> collectSearchResult = new ArrayList<>();

    //конструктор
    public YandexResultSearch(WebDriver driver, String searchRequest) {
        this.driver = driver;
        driver.get("https://yandex.ru");
        searchField = driver.findElement(By.xpath(selectorSearchField));
        searchButton = driver.findElement(By.xpath(selectorSearchButton));
        searchRequest(searchRequest);
        searchResultList = driver.findElements(By.xpath(selectorSearchResult));

    }

    //метод поиска
    public void searchRequest(String request) {
        searchField.click();
        searchField.sendKeys(request);
        searchButton.click();
    }

    //метод получения получения поисковой выдачи содержащей URL
    public List<WebElement> getListSearchResultHREF() {
        searchResultListHREF = driver.findElements(By.xpath(selectorSearchResultListHREF));
        return searchResultListHREF;
    }

    //получаем карту с содержимым строка, объект из списка веб элементов
    public List<Map<String,Object>> getCollectResult(){
        for (WebElement result : searchResultList ){
            collectSearchResult.add(Map.of(
                    "WEB_ELEMENT", result,
                    "URL", result.findElement(By.xpath(selectroURL)).getAttribute("href"),
                    "NAME_PAGE", result.findElement(By.xpath(selectroNamePage)).getText()
                    //на описание так и не смог подобрать универсальный селектор (
                    //поэтому пусть будет не универсальный, на основные блоки, к которым есть норм описание
                    //@СДЕЛАТЬ@ реализовать проверку на заполнение этого ключа нулевыми элементами @СДЕЛАТЬ@
                    //"DESCRIPTION", result.findElement(By.xpath(selectorDescription)).getText()
            ));
        }
        return collectSearchResult;
    }
}
