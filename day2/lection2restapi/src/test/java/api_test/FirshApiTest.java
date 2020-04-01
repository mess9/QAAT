package api_test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FirshApiTest {

    @Test
    public void firstTest(){


        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log().all();
    }

}
