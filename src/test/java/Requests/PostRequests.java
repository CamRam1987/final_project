package Requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import utils.SetProperties;

import static io.restassured.RestAssured.given;

public class  PostRequests extends SetProperties {
    private String login_path="authentication/token/validate_with_login";
    private String creatgesession_path="authentication/session/new";
    private String createListPath="list";
    private String rateTvShowPath="tv/";
    private String rateMovie="movie/";
    private String rateFavoritePath="account/";
    GetRequests getRequests = new GetRequests();
    JSONObject jsonObject = new JSONObject();

    public PostRequests() {
        super();
    }

    public String getLoginWithLogin(){
        jsonObject
                .put("username", getUsername())
                .put("password", getPassword())
                .put("request_token",getRequests.getToken());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+ login_path)
                .then()
                .statusCode(200)
                //.log()
                //.body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token" );
    }


    public java.lang.Object createSession(){
        jsonObject
                .put("request_token",getLoginWithLogin());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+ creatgesession_path)
                .then()
                .statusCode(200)
                //.log()
                //.body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        return response.jsonPath().getString("session_id");
    }

    public String createList(){
        String name="camilos list";
        String description="List Description";
        String language="en";
        jsonObject
                .put("name",name)
                .put("description",description)
                .put("language",language);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host() + createListPath)
                .then()
                .statusCode(201)
                .log()
                .body()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
        Assert.assertEquals("The item/record was created successfully.",response.jsonPath().getString("status_message"));
        return response.jsonPath().getString("list_id");
    }

    public void rateTvShow(int idTvShow,int rate){
        jsonObject
                .put("Value",rate);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id",createSession())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+rateTvShowPath+idTvShow+"/rating")
                .then()
                .extract()
                .response();
        Assert.assertEquals("true",response.jsonPath().getString("success"));
    }

    public void rateMovie(int movieID, int rate){
    jsonObject
            .put("Value",rate);
    Response response=given()
            .contentType(ContentType.JSON)
            .queryParam("api_key",getApi_key())
            .queryParam("session_id",createSession())
            .body(jsonObject.toString())
            .when()
            .post(getUrl_host() + rateMovie + movieID +"/rating")
            .then()
            .extract()
            .response();
    Assert.assertEquals("true",response.jsonPath().getString("success"));
    }
    public void markAsFavorite(int accountID){
        String media_type="movie";
        String media_id="550";
        String favorite="true";
        jsonObject
                .put("media_type",media_type)
                .put("media_id",media_id)
                .put("favorite",favorite);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getUrl_host()+rateFavoritePath+accountID+"/favorite")
                .then()
                .log()
                .body()
                .extract()
                .response();

        Assert.assertEquals("true",response.jsonPath().getString("success"));
    }


}
