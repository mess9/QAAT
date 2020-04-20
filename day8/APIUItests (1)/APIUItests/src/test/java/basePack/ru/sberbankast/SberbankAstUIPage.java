package basePack.ru.sberbankast;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class SberbankAstUIPage{
    WebDriver chromeDriver;
    public SberbankAstUIPage(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
    }
    public List<PurchasesItem> getPurchasesListFromUI(int itemsAmount){
        chromeDriver.get("https://www.sberbank-ast.ru/");
        chromeDriver.findElement(By.xpath("//span[@class='submenu-link' and text()='Закупки']")).click();
        chromeDriver.findElement(By.xpath("//a[text()='Закупки по 44-ФЗ']")).click();
        chromeDriver.findElement(By.xpath("//input[@id='searchInput']")).sendKeys("страхование\n");

        threadWait(4000);
        int page = 1;
        List<PurchasesItem> purchases = new ArrayList<>();

        while(purchases.size() < itemsAmount) {
            chromeDriver.findElements(By.xpath("//div[@class='purch-reestr-tbl-div']")).forEach(x-> //получаем элементы поиска
                    {
                        purchases.add(new PurchasesItem(
                                x.findElement(By.xpath(".//span[@class='es-el-code-term']")).getText(), //номер закупки
                                x.findElement(By.xpath(".//input[@content='leaf:objectHrefTerm']")).getAttribute("value"), // ссылка на закупку
                                x.findElement(By.xpath(".//span[@class='es-el-amount']")).getText() //сумма покупки
                        ));
                    }
            );
            chromeDriver.findElement(By.xpath(String.format("//span[@class='pager-button pagerElem' and @content='%d']", ++page))).click(); //кнопка следующей страницы
            System.out.println(purchases.size());
            threadWait(4000);
        }
        //purchases.forEach(x->System.out.println(x.purchaseNumber + " " + x.purchaseLink + " " + x.purchaseSum));
        return purchases;

    }

    private void threadWait(int timeMS) {
        try {
            Thread.sleep(timeMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
