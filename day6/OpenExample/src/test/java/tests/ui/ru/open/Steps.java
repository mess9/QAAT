package tests.ui.ru.open;

import java.io.File;
import org.apache.commons.io.FileUtils;
import io.qameta.allure.Attachment;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.platform.engine.UniqueId;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import tests.ui.pageObject.GoogleResultSearch;
import tests.ui.pageObject.OpenMainPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Steps {

    @Step
    public static void checkContainsLink(WebDriver driver, List<Map<String, Object>> page, String link){
        System.out.println(page);
        Assert.assertTrue("URL '"+link+"' не найдена",
                page.stream().anyMatch(x -> x.get("URL").toString().contains(link))
        );
        getScreen(driver);
    }

    @Step("Перейдём по ссылке с текстом {text}")
    public static void goPageText(GoogleResultSearch page, String text){
        Assert.assertTrue("did not go to the page Банк «Открытие» — вклады, кредитные и дебетовые"
                ,page.goFindPage("Банк «Открытие» — вклады, кредитные и дебетовые"));
    }

    @Step("Проверка курса {moneyType}")
    public static void checkCourse(OpenMainPage page, List<Map<String,Object>> tableExchange, String moneyType){
        Assert.assertTrue("USD Exchange Rate not valid",
                Double.valueOf(
                        tableExchange.stream()
                                .filter(x -> x.get("Валюта обмена").equals(moneyType))
                                .findFirst()
                                .get().get("Покупка").toString().replace(",",".")
                )
                        <
                        Double.valueOf(
                                tableExchange.stream()
                                        .filter(x -> x.get("Валюта обмена").equals(moneyType))
                                        .findFirst()
                                        .get().get("Продажа").toString().replace(",",".")
                        )
        );
        getScreen(page.getDriver(),page.getExchangeRates());
    }


    @Attachment
    private static byte[] getScreen(WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources", "screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    @Attachment
    private static byte[] getScreen(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
        File screenshot = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources", "screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }
}
