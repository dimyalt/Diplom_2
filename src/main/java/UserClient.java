import data.UserData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class UserClient {
    private final String email;
    private final String password;
    private final String name;
    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";
    private final String REGISTER_URL = "/auth/register";
    private final String LOGIN_URL = "/auth/login";
    private final String USER_URL = "/auth/user";
//    private void spec() throws InterruptedException {
//        Thread.sleep(500);
//    }

    public UserClient(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public void setUpBaseURL() {
        RestAssured.baseURI = BASE_URL;
    }
    public String createUser(){
        setUpBaseURL();
        UserData userJson = new UserData(email, password, name);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(userJson)
                        .post(REGISTER_URL);
        response.then().assertThat().statusCode(200);
        return response.body().asString();
    }
    public String createBadUser(){
        setUpBaseURL();
        UserData userJson = new UserData(email, password, name);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(userJson)
                        .post(REGISTER_URL);
        response.then().assertThat().statusCode(403);
        return response.body().asString();
    }
    public String getAccessToken(){
        UserData userJson = new UserData(email, password, name);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(userJson)
                        .when().post(LOGIN_URL);
        response.then().assertThat().statusCode(200);
        String responseBody = response.body().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        return jsonPath.getString("accessToken");
    }
    public String badLogin(String email, String password, String name){
        UserData userJson = new UserData(email, password, name);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(userJson)
                        .when().post(LOGIN_URL);
        response.then().assertThat().statusCode(401);
        return response.body().asString();
    }
    public void deleteUser(String userAccessToken){
        given()
                .headers("Authorization", userAccessToken,"Content-type", "application, json")
                .when()
                .delete(USER_URL)
                .then().statusCode(202);
    }
    public String editUserEmail(String newEmail, String userAccessToken){
        UserData userJson = new UserData(newEmail, password);
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .headers("Authorization", userAccessToken)
                        .and()
                        .body(userJson)
                        .when()
                        .patch(USER_URL);
        response.then().assertThat().statusCode(200);
        return response.body().asString();
    }
    public String editUserPassword(String newPassword, String userAccessToken){
        UserData userJson = new UserData(email, newPassword);
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .headers("Authorization", userAccessToken)
                        .and()
                        .body(userJson)
                        .when()
                        .patch(USER_URL);
        response.then().assertThat().statusCode(200);
        return response.body().asString();
    }
    public String editUserName(String newName, String userAccessToken){
        UserData userJson = new UserData(newName);
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .headers("Authorization", userAccessToken)
                        .and()
                        .body(userJson)
                        .when()
                        .patch(USER_URL);
        response.then().assertThat().statusCode(200);
        return response.body().asString();
    }
    public String editUserEmailWithoutAuthorization(String newEmail){
        UserData userJson = new UserData(newEmail, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(userJson)
                        .when()
                        .patch(USER_URL);
        response.then().assertThat().statusCode(401);
        return response.body().asString();
    }
    public String editUserPasswordWithoutAuthorization(String newPassword){
        UserData userJson = new UserData(email, newPassword);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(userJson)
                        .when()
                        .patch(USER_URL);
        response.then().assertThat().statusCode(401);
        return response.body().asString();
    }
    public String editUserNameWithoutAuthorization(String newName){
        UserData userJson = new UserData(newName);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(userJson)
                        .when()
                        .patch(USER_URL);
        response.then().assertThat().statusCode(401);
        return response.body().asString();
    }
}
