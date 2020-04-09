package selenide.PO;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.*;

public class GoogleResultPage {

    public ElementsCollection results(){
        return $$(By.xpath("//div[@class='g']"));
    }


    public OpenMainPage goLink(String nameLink){

        SelenideElement resultLink = $$(By.xpath("//div[@class='g']")).find(matchText(nameLink));

        resultLink.$(By.xpath(".//a[@href]")).click();

        switchTo().window(1);
        Assertions.assertTrue(title().contains(nameLink),"Не удалось переключится на вкладку: "+nameLink);

        return page(OpenMainPage.class);
    }

}
