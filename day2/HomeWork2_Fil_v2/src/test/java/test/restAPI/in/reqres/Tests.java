package test.restAPI.in.reqres;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Tests {

    RequestSpecification requestSpec = Specification.requestSpec();
    ResponseSpecification responseSpec = Specification.responseSpec();


    //testNG
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
        Specification.installSpec(requestSpec,responseSpec);

        given()
                .when()
                .get("/api/users")
                .then()
        ;
    }

    @Test
    public void test_probe_with_install_spec_plus_endPoints(){
        Specification.installSpec(requestSpec,responseSpec);
        given()
                .when()
                .get(EndPoints.usersPageN,"2")
                .then()
                ;
    }

    @Test
    public void test_probe_with_install_spec_plus_rest_assured_param() {
        Specification.installSpec(requestSpec,responseSpec);
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

    //todo не хватает основ, понимания как эта хрень со списками вообще работает :(
/*    @Test //Задание 2.1 Убедится что аватары пользователей совпадают
    public void checkMatchAvatars(){
        Specification.installSpec(requestSpec,responseSpec);

        Response resp = given()
                .param("page", 2)
                .when()
                .get(EndPoints.APIusersPage)
                .then().log().all()
                .extract()
                .response();

        JsonPath jsonUserList = resp.jsonPath();
        JsonPath sortUserList = jsonUserList;

//        List<Map<Integer,String>> idAva = new ArrayList<>();
//        idAva = sortUserList.getMap("$data.id", "Integer", "");


        System.out.println(sortUserList.get("data").toString());

//        for (JsonPath J : jsonUserList)
//            jsonUserList.get()
//        jsonUserList.get()



    }*/

}
