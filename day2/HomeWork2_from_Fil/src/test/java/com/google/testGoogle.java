package com.google;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class testGoogle extends WebDriverSettings {

    String Str_USD_buy, Str_USD_sell, Str_EUR_buy, Str_EUR_sell;
    Float USD_buy, USD_sell, EUR_buy, EUR_sell;
    int summ;

    public void testPassed() {
        System.out.println("✓ - ✓ - ✓ - Test passed - ✓ - ✓ - ✓");
    }

    @Test
    public void checkCurrency() {
        System.out.println("проверяем что курс продажи большем курса покупки");

        //переменные которые будем сравнивать


        //переходим на гугл и ищем там открытие
        chromeDriver.get("https://google.com");
        pageObject searchGoogle = new pageObject(chromeDriver);
        searchGoogle.searchRequest("открытие");

        // проверка на то что в выдаче вообще есть то что мы ищем.
        boolean check = false;
        String URL = new String(); //записать полученный урл

        for (int i = 0; i < searchGoogle.getListSearchResult().size(); i++) {       //идём по листу
            System.out.println(searchGoogle.getListSearchResult().get(i).getAttribute("href"));         // выводим найденные ссылки
            if (searchGoogle.getListSearchResult().get(i).getAttribute("href").contains("https://www.open.ru/")) {       // сравниваем с искомым
                URL = searchGoogle.getListSearchResult().get(i).getAttribute("href");       //запоминаем найденное
                break;
            }
        }

        chromeDriver.get(URL); //переходим на страницу банка
        searchGoogle.getUSD_EUR_List(); //получаем табличку с курсами валют

        //проходим по этой табличке, ищем нужное и сохраняем.
//        for (int i=0; i < searchGoogle.getUSD_EUR_List().size();i++){
//            System.out.println(searchGoogle.getUSD_EUR_List().get(i).getText());
//            summ = summ + i;
//            System.out.println("----");
//            System.out.println(summ);
        System.out.println(searchGoogle.getUSD_EUR_List().get(0).getText());
        System.out.println(searchGoogle.getUSD_EUR_List().size());


/*            if (searchGoogle.getUSD_EUR_List().get(i).getText().contains("USD")){
                int sell_index = i + 1;
                int buy_index = i + 3;
                Str_USD_sell = searchGoogle.getUSD_EUR_List().get(sell_index).getText();
                Str_USD_buy = searchGoogle.getUSD_EUR_List().get(buy_index).getText();

                Str_USD_sell = Str_USD_sell.replace(',','.');
                Str_USD_buy = Str_USD_buy.replace(',','.');

                USD_sell = Float.parseFloat(Str_USD_sell);
                USD_buy = Float.parseFloat(Str_USD_buy);
            }
            if (searchGoogle.getUSD_EUR_List().get(i).getText().contains("EUR")){
                int sell_index = i + 1;
                int buy_index = i + 3;
                Str_EUR_sell = searchGoogle.getUSD_EUR_List().get(sell_index).getText();
                Str_EUR_buy = searchGoogle.getUSD_EUR_List().get(buy_index).getText();

                Str_EUR_sell = Str_EUR_sell.trim().replace(',','.');
                Str_EUR_buy = Str_EUR_buy.trim().replace(',','.');

                EUR_sell = Float.parseFloat(Str_EUR_sell);
                EUR_buy = Float.parseFloat(Str_EUR_buy);
            }*/
    }


/*        //проверки
        Assertions.assertTrue(USD_sell < USD_buy); // check - 1
        if (USD_sell < USD_buy){
            System.out.println("курс продажи доллара меньше курса его покупки");
        }
        Assertions.assertTrue(EUR_sell < EUR_buy); // check - 2
        if (EUR_sell < EUR_buy){
            System.out.println("курс продажи евро меньше курса его покупки");
        }*/


}


