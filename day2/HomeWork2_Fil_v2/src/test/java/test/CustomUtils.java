package test;

import java.util.List;
import java.util.Map;

public class CustomUtils {

    // ожидание на N секунд
    public static void stopSec(int s){
        try {
            Thread.sleep(s*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testPassed(){
        System.out.println("✓ - ✓ - ✓ - Test passed - ✓ - ✓ - ✓");
    }

    public static Double strToFloatCurrency(List<Map<String, String>> collect, String Currency, String buy_or_sell){
        String value = collect.stream()
                .filter(x -> x.get("Валюта").equals(Currency)).findFirst()
                .get() //почему два гета подряд?
                .get(buy_or_sell).replace(",", ".");
        Double currencyDouble = Double.valueOf(value);
        return currencyDouble;

    }

}

