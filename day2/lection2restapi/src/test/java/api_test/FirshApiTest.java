package api_test;

//import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FirshApiTest {

    @Test
    public void firstTest2(){

        Response resp = given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log()
                .all()
                .extract()
                .response()
                ;
        JsonPath jsonNewUser = resp.jsonPath();
        Assert.assertFalse(jsonNewUser.get("page").toString().isEmpty(), "page is null");
        jsonNewUser.getList("data.id").forEach(x->Assert.assertFalse(x.toString().isEmpty(), "data.id is null"));

    }

    @Test
    public void jsonArrayTest(){
        JSONArray ja = new JSONArray();
        ja.put("name");
        ja.put("fam");

    }


    @Test
    public void jsonObjectTest(){
        JSONObject jsonUser = new JSONObject();
        JSONObject jsonAge = new JSONObject();
        JSONObject jsonData = new JSONObject();

        jsonData.put("name", "morpheus");
        jsonData.put("job", "leader");
        jsonData.put("user", jsonUser);
        jsonUser.put("data",jsonAge);
        jsonAge.put("age","31");

        System.out.println(jsonData);


        given()
                .contentType("application/json")
                .when()
                .body(jsonData)
                .post("https://reqres.in/api/users")
                .then()
                .log().all();



    }

    @Test
    public void mapmapTest(){
        Map<String,Object> dataGlobal = new HashMap<String, Object>();
        Map<String,Object> dataFirst = new HashMap<String, Object>();
        Map<String,String> dataSecond = new HashMap<String, String>();
        Object[] massiv = new Object[3];

        dataSecond.put("age", "31");
        massiv[0] = dataSecond;
        dataSecond = new HashMap<String, String>();
        dataSecond.put("age", "32");
        massiv[1] = dataSecond;
        dataSecond = new HashMap<String, String>();
        dataSecond.put("age", "33");
        massiv[2] = dataSecond;



        //dataSecond.put("age", "31");
        dataFirst.put("data", massiv);
        dataGlobal.put("name", "morpheus");
        dataGlobal.put("job", "leader");
        dataGlobal.put("user", dataFirst);

        given()
                .contentType("application/json")
                .when()
                .body(dataGlobal)
                .post("https://reqres.in/api/users")
                .then()
                .log().all();



    }

    @Test
    public void firstTest(){


        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log().all()
                .body("page", notNullValue())
                .body("per_page", notNullValue())
                .body("data.id", not(hasItem(nullValue())))
        ;
    }

    @Test
    public void updateTest(){
        Map<String,String> data = new HashMap<String, String>();
        data.put("name","Fil");
        data.put("job", "student");

        Response newUserResponse = (Response)
                given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log()
                .all()
                .statusCode(201)
                .extract()
                .response()
        ;

        JsonPath jsonNewUserResponse = newUserResponse.jsonPath();
        Assert.assertEquals(jsonNewUserResponse.get("name"),data.get("name"));
        Assert.assertEquals(jsonNewUserResponse.get("job"),data.get("job"));

    }

    
}
