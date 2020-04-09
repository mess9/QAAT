import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {

    public static RequestSpecification requestSpec(){
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://127.0.0.1:1080/")
                .setPort(1080)
                //.log(LogDetail.ALL)
                .build();
        //RestAssured.requestSpecification = requestSpec;
        return requestSpec;
    }

    public static ResponseSpecification responseSpec(){
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        //RestAssured.responseSpecification = responseSpec;
        return responseSpec;
    }

    public static void installSpec(RequestSpecification requestSpec){
        RestAssured.requestSpecification = requestSpec;
    }

    public static void installSpec(ResponseSpecification responseSpec){
        RestAssured.responseSpecification = responseSpec;
    }

    public static void installSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }
}
