package selenide.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenide.PO.GoogeMail;
import selenide.PO.GoogleResultPage;
import selenide.PO.GoogleSearchPage;
import selenide.PO.OpenMainPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

//@ExtendWith({ScreenShooterExtension.class})
public class Tests {

   // @Rule
  //  public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();

    @BeforeEach
    public void option(){
        Configuration.timeout = 6000;
        Configuration.browser="chrome";
        Configuration.startMaximized=true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;


     //   screenshot("my_file_name");
/*
        System.setProperty("webdriver.chrome.driver",System.getenv("CHROME_DRIVER"));
        WebDriver driver;
        driver = new ChromeDriver(options);
        setWebDriver(driver);
*/
    }

    @Test
    public void firstTest(){
        open("https://www.google.ru/");
        $(By.name("q")).setValue("Открытие").pressEnter();
        $$(By.xpath("//div[@class='g']")).shouldHave(size(6));
        $$(by("class","g")).shouldHave(size(6));

        /*

        source()

        import static com.codeborne.selenide.Screenshots.getScreenShotAsFile;

        File screenshot = getScreenShotAsFile();


        $("#submit").click();


        waitUntil(Condition, milliseconds)
        waitWhile(Condition, milliseconds)



        visible / appear // e.g. $(“input”).shouldBe(visible)
        present / exist // условия присутствия элемента в DOM
        hidden / disappear // not(visible)
        readonly // e.g. $(“input”).shouldBe(readonly)
        name // e.g. $(“input”).shouldHave(name(“fname”))
        value // e.g. $(“input”).shouldHave(value(“John”))
        type // e.g. $(“#input”).shouldHave(type(“checkbox”))
        id // e.g. $(“#input”).shouldHave(id(“myForm”))
        empty // e.g. $(“h2”).shouldBe(empty)
        attribute(name) // e.g. $(“#input”).shouldHave(attribute(“required”))
        attribute(name, value) // e.g. $(“#list li”).shouldHave(attribute(“class”, “active checked”))
        cssClass(String) // e.g. $(“#list li”).shouldHave(cssClass(“checked”))
        focused
        enabled
        disabled
        selected
        matchText(String regex)
        text(String substring)
        exactText(String wholeText)
        textCaseSensitive(String substring)
        exactTextCaseSensitive(String wholeText)



        byText - поиск элемента по точному тексту
        withText - поиск элемента по тексту (подстроке)
        by - поиск элемента по атрибуту
        byTitle - поиск по атрибуту “title”
        byValue - поиск по атрибуту “value”
        */
        $$(by("class","g")).find(matchText("Открыти"));

        ElementsCollection resultSearch = $$(By.xpath("//div[@class='g']"));

        SelenideElement elem = resultSearch.find(text("Банк «Открытие» — Википедия"));

        System.out.println(elem.getText());

        SelenideElement elemOtkr = $(By.xpath("//div[@class='g']")).shouldHave(text("1111111Банк «Открытие» — вклады, кредитные и дебетовые ..."));

        elemOtkr.$(By.xpath(".//a[@href]")).click();

        switchTo().window(1);
        System.out.println(title());
        System.out.println($(By.xpath("//*[@class='main-page-exchange main-page-info__card']")).getText());
    }

    @Test
    public void firstTestOpen(){
        GoogleSearchPage searchPage = open("https://www.google.ru/", GoogleSearchPage.class);
        //GoogeMail mailPage = searchPage.openMail();
        GoogleResultPage resultPage = searchPage.search("Открытие");
       // System.out.println(resultPage.results());
        OpenMainPage openPage = resultPage.goLink("Банк «Открытие» — вклады, кредитные и дебетовые");

        //OpenMainPage openPage =open("https://www.open.ru/", OpenMainPage.class);

        Map<String,Float> courseMoney = new HashMap<>();
        courseMoney.put("USD Покупка", openPage.getCourse("USD","Покупка"));
        courseMoney.put("USD Продажа", openPage.getCourse("USD","Продажа"));
        courseMoney.put("EUR Покупка", openPage.getCourse("EUR","Покупка"));
        courseMoney.put("EUR Продажа", openPage.getCourse("EUR","Продажа"));

        System.out.println(courseMoney);

        Assertions.assertTrue(courseMoney.get("USD Покупка")<courseMoney.get("USD Продажа"),"Курс покупки USD не меньше курса продажи");
        Assertions.assertTrue(courseMoney.get("EUR Покупка")<courseMoney.get("EUR Продажа"),"Курс покупки EUR не меньше курса продажи");
    }

}
