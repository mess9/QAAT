import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    @Test()
    public void testWebTourse(){

        String sessionID = Steps.goWebTours();
        Steps.getUserData("belltest","11111",sessionID);

    }

    @Test
    public void testSpec(){
        RequestSpecification getSpec = Specification.requestSpec();
        ResponseSpecification checkSpec = Specification.responseSpec();
        Specification.installSpec(getSpec,checkSpec);
        //Steps.goWebToursWithSpec(getSpec, checkSpec);
        Steps.goWebToursWithSpec();
    }


   // @Test()
    public void Купцову(){
        Map<String, Object> dataGlobal= new HashMap<String, Object>();
        Map<String, Object> dataFirst= new HashMap<String, Object>();
        Map<String, String> dataSecond= new HashMap<String, String>();

        dataSecond.put("age","31");

        dataFirst.put("data",dataSecond);

        dataGlobal.put("name","Kirill");
        dataGlobal.put("job","teacher");
        dataGlobal.put("user","dataFirst");

        given()
                .contentType("application/json")
                .body(dataGlobal)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all();
    }


   // @Test()
    public void firstTest(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .body("page", notNullValue())
                .body("per_page", notNullValue())
                .body("total", notNullValue())
                .body("total_pages", notNullValue())
                .body("data.id",not(hasItem(nullValue())))
                .body("data.first_name", hasItem("Lindsay"));
    }

   // @Test()
    public void secondTest(){
        Map<String, String> data= new HashMap<String, String>();
        data.put("name","Kirill");
        data.put("job","teacher");
        Response responce = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .response();
        JsonPath jsoneResponce = responce.jsonPath();
        Assert.assertEquals(jsoneResponce.get("name").toString(),data.get("name"),"Name is not valid");
        Assert.assertEquals(jsoneResponce.get("job").toString(),data.get("job"),"Job is not valid");
    }
}
