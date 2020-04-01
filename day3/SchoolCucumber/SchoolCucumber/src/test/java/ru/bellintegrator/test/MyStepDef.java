package ru.bellintegrator.test;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class MyStepDef extends Steps {

    @Given("перейти на сайт '(.*)'")
    public void перейти_на_сайт(String site) {
        открытьХром();
        chromeDriver.get(site);
    }

    @Then("тайтл равен '(.*)'")
    public void тайтлРавенBellIntegrator(String checkTitle) {
        String title = chromeDriver.getTitle();
        Assert.assertTrue(title.contains(checkTitle));
    }

    @Then("закончить работу")
    public void закончитьРаботу() {
        закрытьХром();
    }

    @Given("вывести список")
    public void вывестиСписок(List<String> arg) {
        arg.stream().forEach(x -> System.out.println(x));
    }

    @Then("вывести таблицу")
    public void вывестиТаблицу(DataTable arg) {
        System.out.println(arg.toString());
    }

    @Then("вывести название доступных пунктов")
    public void вывестиНазваниеДоступныхПунктов(DataTable arg) {
        List<Map<String,String>> table = arg.asMaps(String.class,String.class);
        for(int i=0; i<table.size();++i){
            if(table.get(i).get("Доступен").equals("true"))
                System.out.println(table.get(i).get("Название")+" "+table.get(i).get("Количество подменю"));
        }
    }

    @Then("^вывод (.*) и (.*) из списка$")
    public void выводКлючИЗначениеИзСписка(String a, String b) {
        System.out.println(a+b);
    }
}
