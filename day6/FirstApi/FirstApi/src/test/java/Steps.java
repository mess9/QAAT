import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Steps {

    @Step
    public static String goWebTours(){

        given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post("http://127.0.0.1:1080/WebTours/")
                .then()
               // .log().all()
                .statusCode(200);

        given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post("http://127.0.0.1:1080/cgi-bin/welcome.pl?signOff=true")
                .then()
               // .log().all()
                .statusCode(200);

        Response responce = given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post("http://127.0.0.1:1080/cgi-bin/nav.pl?in=home")
                .then()
                //.log().all()
                .statusCode(200)
                .extract().response();

       // System.out.println(responce.asString());

        Document resultPage = Jsoup.parse(responce.asString());
       // System.out.println("Title: "+resultPage.title());


        //open("file:///D:/Work/School/AT032020/1.html");
        //System.out.println("Title selenide: "+title());

        String sessionID = resultPage.body().getElementsByAttributeValue("name","userSession").attr("value");

        System.out.println("Session ID: "+ sessionID);

        Assert.assertFalse(
                sessionID.isEmpty(),
                "Session is not valid"
        );

        return sessionID;
    }

    @Step
    public static String getUserData(String login, String password, String sessionID){
        String userData="";
        ExtractableResponse<Response> response = given()
                .contentType("application/x-www-form-urlencoded")
                .body("userSession="+sessionID+"&username="+login+"&password="+password)
                .when()
                .post("http://127.0.0.1:1080/cgi-bin/login.pl")//.asString(); //.post(EndPoints.address, "/cgi-bin/login.pl")
                .then()
                //.log().all()
                .statusCode(200)
                .extract();

        System.out.println(response.cookies().toString());
        try {
            String encod =
                    URLDecoder.decode(
                            response.cookies().toString(),
                            StandardCharsets.UTF_8.name()
                    );
            System.out.println(encod);
            System.out.println(StringEscapeUtils.unescapeHtml4(encod));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return userData;

    }



    @Step
    public static String goWebToursWithSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec){

        given()
                .spec(requestSpec)
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post("/WebTours/")
                .then()
                .spec(responseSpec);

        given()
                .spec(requestSpec)
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post("/cgi-bin/welcome.pl?signOff=true")
                .then()
                .spec(responseSpec);

        Response responce = given()
                .spec(requestSpec)
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post("/cgi-bin/nav.pl?in=home")
                .then()
                .spec(responseSpec)
                .extract().response();

        Document resultPage = Jsoup.parse(responce.asString());

        String sessionID = resultPage.body().getElementsByAttributeValue("name","userSession").attr("value");

        System.out.println("Session ID: "+ sessionID);

        Assert.assertFalse(
                sessionID.isEmpty(),
                "Session is not valid"
        );

        return sessionID;
    }

    @Step
    public static String goWebToursWithSpec(){

        given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post("/WebTours/");

        given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post("/cgi-bin/welcome.pl?signOff=true");

        Response responce = given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post("/cgi-bin/nav.pl?in=home")
                .then()
                .extract().response();

        Document resultPage = Jsoup.parse(responce.asString());

        String sessionID = resultPage.body().getElementsByAttributeValue("name","userSession").attr("value");

        System.out.println("Session ID: "+ sessionID);

        Assert.assertFalse(
                sessionID.isEmpty(),
                "Session is not valid"
        );

        return sessionID;
    }
}
