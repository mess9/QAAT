package tests.ui.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoogleResultSearch {

    private String selectorUrl = ".//div[@class='r']/a[@href]";
    private String selectorNamePage = ".//div[@class='r']/a[@href]/H3";
    private String selectorDiscription = ".//div[@class='s']";

    private WebDriver driver;
    private List<WebElement> webElementsResults = new ArrayList<WebElement>();
    private List<Map<String,Object>> collectResults = new ArrayList<Map<String, Object>>();


    public List<Map<String, Object>> getCollectResults() {
        for(WebElement result : webElementsResults){
            collectResults.add(Map.of(
                    "WEB_ELEMENT", result,
                    "URL", result.findElement(By.xpath(selectorUrl)).getAttribute("href"),
                    "NAME_PAGE", result.findElement(By.xpath(selectorNamePage)).getText(),
                    "DISCRIPTION", result.findElement(By.xpath(selectorDiscription)).getText()
            ));
        }
        return collectResults;
    }

    public GoogleResultSearch(WebDriver driver, String search){
        this.driver = driver;
        if(!driver.getTitle().contains("Поиск в Google"));
            driver.get("https://www.google.ru/search?q="+search);
        webElementsResults = driver.findElements(By.xpath("//div[@class='g']"));
    }



    public boolean goFindPage(String namePage){

        WebElement pageLink = (WebElement) collectResults.stream()
                .filter(x -> x.get("NAME_PAGE").toString().contains(namePage))
                .findFirst().get().get("WEB_ELEMENT");

        pageLink.findElement(By.xpath(selectorNamePage)).click();
        List <String> tabs = new ArrayList(driver.getWindowHandles());
        for(String tab : tabs){
            driver.switchTo().window(tab);
            if(driver.getTitle().contains(namePage))
                return true;
        }
        return false;
    }



}
