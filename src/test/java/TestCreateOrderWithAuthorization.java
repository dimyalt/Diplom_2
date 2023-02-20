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

public class TestCreateOrderWithAuthorization {
    private final Random random = new Random();
    private final int randomNumber = random.nextInt(100);
    private final String newUserEmail = randomNumber + "testuseremail@yandex.ru";
    private final String newUserPassword = "password1234";
    private final String newUserName = "BurgerLoveMe";
    private String accessToken;
    private final UserClient newUser = new UserClient(newUserEmail, newUserPassword, newUserName);
    private final OrderData newIngredients = new OrderData(new ArrayList<>(Arrays.asList("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa6f")));
    private final String expectedResult = "ingredients";
    private final OrderClient newOrder = new OrderClient(newIngredients);

    @Before
    public void setUp(){
        newUser.createUser();
        accessToken = newUser.getAccessToken();
    }

    @Test
    @DisplayName("Создание заказа авторизованным пользователем")
    public void createOrder(){
        String result = newOrder.createOrderWithAuthorization(accessToken);
        assertThat(result, containsString(expectedResult));
    }

    @After
    public void tearsDown(){
        newUser.deleteUser(accessToken);
    }
}
