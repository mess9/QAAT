package selenide.PO;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GoogleSearchPage {

    public GoogleResultPage search(String query){
        $(By.name("q")).setValue(query).pressEnter();
        return page(GoogleResultPage.class);
    }


    public GoogeMail openMail(){
        $(byText("Почта")).pressEnter();
        return page(GoogeMail.class);
    }

}
