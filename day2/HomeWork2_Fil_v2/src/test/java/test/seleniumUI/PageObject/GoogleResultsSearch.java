package test.seleniumUI.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoogleResultsSearch {

    private WebDriver driver;

    private WebElement searchField;
    private WebElement searchButton;
    private List<WebElement> webSearchItem = new ArrayList();
    private List<Map<String, Object>> collectResult = new ArrayList<>();

    //селекторы
    private String selectorSerachFind = "//input[@type=\"text\"]";
    private String selectorSearchButton = "//input[@value=\"Поиск в Google\"]";
    private String selectorSearchItem = "//div[@class='g']";
    private String selectroURL = ".//a[@href]";
    private String selectorNamePage = ".//h3";
    private String selectorDescription = ".//div[@class='s']";


    //конструктор просто
    public GoogleResultsSearch(WebDriver driver) {
        this.driver = driver;
        driver.get("https://google.com");
        webSearchItem = driver.findElements(By.xpath(selectorSearchItem));
    }
    /*
    конструктор.
    1.принимает на вход поисковой запрос.
    2.переходит на страницу гугла
    3. вбивет туда запрос, тыкает на кнопочку
    4. получает распарсенный массив выдачи -> webSearchItem
    */
    public GoogleResultsSearch(WebDriver driver, String searchRequest) {
        this.driver = driver;
        driver.get("https://google.com");
        searchRequest(searchRequest);
        webSearchItem = driver.findElements(By.xpath(selectorSearchItem)); //эта строка тут зачем?
        // что бы сработал метод getCollectResult, но почему это не распарсить выдачу сразу?
        //или например можно же получать этот выдачу в том же методе в котором и парсинг происходит?
    }

    public GoogleResultsSearch(WebDriver driver, String searchRequest, boolean parse){
        this.driver = driver;
        driver.get("https://google.com");
        searchRequest(searchRequest);
        if (parse){
            webSearchItem = driver.findElements(By.xpath(selectorSearchItem));
            getCollectResult();
        }
    }

    //метод поиска
    private void searchRequest(String request) {
        searchField = driver.findElement(By.xpath(selectorSerachFind));
        searchButton = driver.findElement(By.xpath(selectorSearchButton));
        searchField.click();
        searchField.sendKeys(request);
        searchButton.click();
    }

    //получаем карту с содержимым строка, объект из списка веб элементов
    public List<Map<String,Object>> getCollectResult(){
        for  (WebElement result : webSearchItem){
            collectResult.add(Map.of(
                    "WEB_ELEMENT" , result,
                    "URL", result.findElement(By.xpath(selectroURL)).getAttribute("href"),
                    "NAME_PAGE", result.findElement(By.xpath(selectorNamePage)).getText(),
                    "DESCRIPTION", result.findElement(By.xpath(selectorDescription)).getText()
            ));
        }
        return collectResult;
    }

    //проверка что в поисковой выдаче, есть нужная искомая страница.
    public boolean goFindPage(String namePage){
        WebElement pageLink = (WebElement) collectResult.stream()
                .filter(x -> x.get("NAME_PAGE").toString().contains(namePage)).findFirst().get().get("WEB_ELEMENT"); //ищем в сохранённом объекте, по имени страницы её веб элемент
        pageLink.findElement(By.xpath(selectorNamePage)).click();  //в найденном веб элементе кликаем по ссылке перехода на сайт

        //т.к. ссылка может открыть на другой вкладке, проверяем все вкладки на нахождение той, у которой в тайтле присутствует имя нашей страницы.
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String tab : tabs){
            driver.switchTo().window(tab);
            if (driver.getTitle().contains(namePage))
                return true;
        }

        return false;

    }



}
