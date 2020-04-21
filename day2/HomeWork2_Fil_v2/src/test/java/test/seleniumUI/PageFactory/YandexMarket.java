package test.seleniumUI.PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import test.CustomUtils;

public class YandexMarket {
    private WebDriver driver;
    private String url = "https://market.yandex.ru";


    //    @FindBy (how = How.XPATH, xpath = "//input[@id='header-search']" )
//    WebElement searchField;
//    @FindBy (how = How.XPATH, xpath = "//button[contains(.,'Найти')]" )
//    WebElement searchButton;
    @FindBy(how = How.XPATH, xpath = "//div[@data-zone-name=\"all-categories\"]")
    WebElement ButtonAllCategories;
    @FindBy(how = How.XPATH, xpath = "//button/a[contains(@href,\"catalog--elektronika\")]")
    WebElement ButtonCatalogElectronika;
    @FindBy(how = How.XPATH, xpath = "//div/a[contains(@href,\"mobilnye-telefony\") and not(contains(@href,\"knopochnye\"))]")
    WebElement sectionSmartphone;
    @FindBy(how = How.XPATH, xpath = "//legend[contains(.,'Производитель')]/ancestor::fieldset//span[contains(.,'Apple')]/..")
    WebElement checkboxApple;  //input[@type=\"checkbox\" and contains(@name,\"Apple\")]"

    //legend[contains(.,'Производитель')]/ancestor::fieldset//span[contains(.,'Apple')]/.. !!!


//    @FindBy (how = How.XPATH, xpath = "//div[contains(@class,\"n-snippet-list\") and not(contains(@class,\"wrapper\"))]" )
//    WebElement ItemList; //todo тут есть инфа о количестве в выдаче.(42)
//    @FindBy (how = How.XPATH, xpath = "//div[contains(@class,\"n-pager-more\")]" )
//    WebElement expandItemList; //todo тут есть сколько всего аппаратов в выдаче и на одну страницу
//    @FindBy (how = How.XPATH, xpath = "//a[contains(@class,\"n-pager__button-number\")and contains(@href,\"page\")][1]" )
//    WebElement buttonInNumberPage; //todo в атрибуте href есть указание на страницу в виде page=N - надо бы как то эту N менять и прощёлкивать всю выдачу до 10-й страницы

    //конструктор
    public YandexMarket(WebDriver driver) {
        this.driver = driver;
        driver.get(url);
    }

    //переход в нужный раздел
    public void goToSmartphones() {
        Actions moveSelectionToCategory = new Actions(driver);
        ButtonAllCategories.click();
        moveSelectionToCategory.moveToElement(ButtonCatalogElectronika).perform();
        moveSelectionToCategory.moveToElement(sectionSmartphone).perform();
        sectionSmartphone.click();
    }

    //выбираем яблоки
    public void checkApple() {
//       Actions moveSelectionToCategory = new Actions(driver);
//       moveSelectionToCategory.moveToElement(checkboxApple).perform();
//       moveSelectionToCategory.moveToElement(checkboxApple).click();
//      CustomUtils.stopSec(5);
       checkboxApple.click();
      CustomUtils.stopSec(5);




    }

    //span[gettext("Apple")]

}
