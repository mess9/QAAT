package basePack;

import basePack.ru.sberbankast.PurchasesItem;
import basePack.ru.sberbankast.SberbankAstUIPage;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SberbankAstUITest extends WebDriverSettings{

    @Test
    public void SberbankAstUITest(){
        SberbankAstUIPage page = new SberbankAstUIPage(chromeDriver);
        List<PurchasesItem> purchases = page.getPurchasesListFromUI(120);
        System.out.println("Total  purchases " + purchases.size());
        List<PurchasesItem> validPurchases = purchases.stream().filter(x->x.purchaseSum>4000000).collect(Collectors.toList());//фильтруем закупки меньше 4 млн рублей
        System.out.println("Number of valid purchases: " + validPurchases.size());
        validPurchases.forEach(x->System.out.println(x.toString()));
        Assert.assertTrue("Number of valid purchases < 10",validPurchases.size()>=10);
    }
}
