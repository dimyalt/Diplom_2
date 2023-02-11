import data.OrderData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrderClient {
    private final OrderData newIngredients;
    private final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";
    private final String ORDER_URL = "/orders";

    public OrderClient(OrderData newIngredients) {
        this.newIngredients = newIngredients;
    }

    public void setUpBaseURL() {
        RestAssured.baseURI = BASE_URL;
    }

    public String createOrderWithoutAuthorization(){
        setUpBaseURL();
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(newIngredients)
                        .post(ORDER_URL);
        response.then().assertThat().statusCode(200);
        return response.body().asString();
    }

    public String createOrderWithAuthorization(String userAccessToken){
        setUpBaseURL();
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .headers("Authorization", userAccessToken)
                        .and()
                        .body(newIngredients)
                        .post(ORDER_URL);
        response.then().assertThat().statusCode(200);
        return response.body().asString();
    }

    public String createOrder(int code){
        setUpBaseURL();
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(newIngredients)
                        .post(ORDER_URL);
        response.then().assertThat().statusCode(code);
        return response.body().asString();
    }
    public String getOrders(String userAccessToken, int code){
        setUpBaseURL();
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .headers("Authorization", userAccessToken)
                        .and()
                        .get(ORDER_URL);
        response.then().assertThat().statusCode(code);
        return response.body().asString();
    }

}
