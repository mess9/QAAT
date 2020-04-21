package test.restAPI.in.reqres;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.io.FilenameUtils;


import java.net.URL;
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
        Задание 2.2
            1.	Используя сервис https://reqres.in/ протестировать регистрацию пользователя в системе.
            2.	Необходимо создание двух тестов на успешный логи и логин с ошибкой из-за не введённого пароля
            3. Автотест необходимо написать, используя данный стек:
                Java, JUnit, Selenium, PageFactory
     */
    @Test
    public void positiveReg(){
        Specification.installSpec(requestSpec, responseSpec);

        Map<String, String> data= new HashMap<String, String>();
        data.put("email","michael.lawson@reqres.in");
        data.put("password","teacher");

        Response resp = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post(EndPoints.register)
                .then().log().all()
                .extract()
                .response()
                ;

        JsonPath jsonRespReg = resp.jsonPath();

        Assert.assertNotNull("id - не получен!", jsonRespReg.getString("id"));
        Assert.assertNotNull("token - не получен!", jsonRespReg.getString("token"));

    }

    //по неясной причине данный тест работает в одиночку, и проваливается если запускать все вместе
    //видимо потому что подхватывает неверный код ожидания. хотя в двух местах задал 400. всё равно ждёт 200
    //Expected status code <200> but was <400>.
    @Test
    public void negativeReg(){
        Map<String, String> data= new HashMap<String, String>();
        data.put("email","michael.lawson@reqres.in");
        //data.put("password","teacher");

        Response resp = given()
                .when()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .log().all()
                .extract()
                .response()
                ;

        JsonPath jsonRespReg = resp.jsonPath();

    Assert.assertEquals(jsonRespReg.getString("error"),"Missing password" );
    Assert.assertEquals(resp.getStatusCode(),400);

    }

    /*
    Задание 2.3
    Используя сервис https://reqres.in/ убедится что операция LIST <RESOURCE> возвращает данные отсортированные по годам
    Автотест необходимо написать, используя данный стек:
    */
    @Test
    public void test_trest() {

        //тут будет решение.



    }






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


    }
}





