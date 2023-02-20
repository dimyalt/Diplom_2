import data.OrderData;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TestReceiptOfAllOrdersByAnUnauthorizedUser {
    private final Random random = new Random();
    private final int randomNumber = random.nextInt(100);
    private final String newUserEmail = randomNumber + "testuseremail@yandex.ru";
    private final String newUserPassword = "password1234";
    private final String newUserName = "BurgerLoveMe";
    private final String expectedResultUnauthorizedUser = "You should be authorised";
    private final String expectedResultAuthorizedUser = "number";
    private String accessToken;
    private int expectedCodeWithoutToken = 401;
    private int expectedCodeWithToken = 200;
    private final UserClient newUser = new UserClient(newUserEmail, newUserPassword, newUserName);
    private final OrderData newIngredients = new OrderData(new ArrayList<>(Arrays.asList("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa6f")));
    private final OrderClient newOrder = new OrderClient(newIngredients);

    @Before
    public void setUp(){
        newUser.createUser();
        accessToken = newUser.getAccessToken();
        newOrder.createOrderWithAuthorization(accessToken);
    }

    @Test
    @DisplayName("Все заказы не авторизованного пользователя")
    public void allOrdersUnauthorizedUser(){
        accessToken = "";
        String result = newOrder.getOrders(accessToken, expectedCodeWithoutToken);
        assertThat(result, containsString(expectedResultUnauthorizedUser));
    }
    @Test
    @DisplayName("Все заказы авторизованного пользователя")
    public void allOrdersAuthorizedUser(){
        accessToken = newUser.getAccessToken();
        String result = newOrder.getOrders(accessToken, expectedCodeWithToken);
        assertThat(result, containsString(expectedResultAuthorizedUser));
    }
    @After
    public void tearsDown(){
        accessToken = newUser.getAccessToken();
        newUser.deleteUser(accessToken);
    }
}
