package test.restAPI.in.reqres;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.io.FilenameUtils;

import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Tests {

    RequestSpecification requestSpec = Specification.requestSpec();
    ResponseSpecification responseSpec = Specification.responseSpec();


    /*API
    Задание 2.1
            1.	Используя сервис https://reqres.in/ получить список пользователей со второй страницы.
            2.	Убедится что аватары пользователей совпадают
            3.	Автотесты необходимо написать, используя данный стек:
            4.	Java, testNG, restAssured.
     */
    @Test
    public void checkMatchAvatars() throws Exception{
        Specification.installSpec(requestSpec, responseSpec);

        Response resp = given()
                .param("page", 2)
                .when()
                .get(EndPoints.APIusersPage)
                .then()//.log().all()
                .extract()
                .response();

        JsonPath jsonUserList = resp.jsonPath();

        ArrayList<String> urlAvatars = new ArrayList<>(); //лист с урл аватаров
        for (int i=0; i < jsonUserList.getInt("per_page"); i++) {
            urlAvatars.add(jsonUserList.getString("data["+i+"].avatar"));
        }

        for (int i=0; i < jsonUserList.getInt("per_page"); i++) {
            Assert.assertEquals(FilenameUtils.getName(urlAvatars.get(0)),FilenameUtils.getName(urlAvatars.get(i)));
        }

    }

    /*API
        Задание 2.1
            1.	Используя сервис https://reqres.in/ протестировать регистрацию пользователя в системе.
            2.	Необходимо создание двух тестов на успешный логи и логин с ошибкой из-за не введённого пароля
            3. Автотест необходимо написать, используя данный стек:
                Java, JUnit, Selenium, PageFactory
     */

//    @Test
//    public void positiveReg(){
//
//    }
//
//    @Test
//    public void negativeReg(){
//
//
//    }

    @Test
    public void testBlad(){


    }




    /*
    //всё что ниже - не относится к решению задания, а упражнения для понимания, как можно и как что устроено.
    @Test
    public void test_probe() {

        Response resp = given()
                .when()
                .get("https://reqres.in/api/users")
                .then().statusCode(200)
                .log()
                .all()
                .extract()
                .response();

        JsonPath jsonNewUser = resp.jsonPath();
        System.out.println(jsonNewUser);
    }

    @Test
    public void test_probe_with_spec() {
        RequestSpecification requestSpec = Specification.requestSpec();
        ResponseSpecification responseSpec = Specification.responseSpec();

        given()
                .spec(requestSpec)
                .contentType("application/json")
                .when()
                .get("/api/users")
                .then().spec(responseSpec)
                .log().all();
    }

    @Test
    public void test_probe_with_install_spec() {
        Specification.installSpec(requestSpec, responseSpec);

        given()
                .when()
                .get("/api/users")
                .then()
        ;
    }

    @Test
    public void test_probe_with_install_spec_plus_endPoints() {
        Specification.installSpec(requestSpec, responseSpec);
        given()
                .when()
                .get(EndPoints.usersPageN, "2")
                .then()
        ;
    }

    @Test
    public void test_probe_with_install_spec_plus_rest_assured_param() {
        Specification.installSpec(requestSpec, responseSpec);
        Response resp = given()
                .param("page", "2")
                .when()
                .get(EndPoints.APIusersPage)
                .then().log().all()
                .extract()
                .response();

        JsonPath jsonUserList = resp.jsonPath();
        System.out.println(jsonUserList);
    }



    @Test
    public void tratata() throws Exception {
        Specification.installSpec(requestSpec, responseSpec);

        Response resp = given()
                .param("page", 2)
                .when()
                .get(EndPoints.APIusersPage)
                .then()//.log().all()
                .extract()
                .response();

        JsonPath jsonUserList = resp.jsonPath();
        Map<Integer, String> idAvatar = new HashMap<>();

        for (int i = 0; i < jsonUserList.getInt("per_page"); i++) {
            Integer id;
            String ava;
            id = jsonUserList.getInt("data[" + i + "].id");
            ava = jsonUserList.getString("data[" + i + "].avatar");
            idAvatar.put(id, ava);
        }
        System.out.println(jsonUserList.getInt("per_page"));
        System.out.println(idAvatar);
        System.out.println(idAvatar.get(7));

        URL url = new URL(idAvatar.get(7));
        String str  = FilenameUtils.getName(url.getPath());
        System.out.println(str);


    }*/




}

