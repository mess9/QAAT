package test.seleniumUI.ru.YandexMarket;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import test.seleniumUI.PageFactory.YandexMarket;
import test.seleniumUI.WebDriverSettings;

public class Tests extends WebDriverSettings {

    @Test
    public void findApple(){
        YandexMarket appleFind = PageFactory.initElements(driver, YandexMarket.class);
        appleFind.goToSmartphones();
        appleFind.checkApple();

    }





}
