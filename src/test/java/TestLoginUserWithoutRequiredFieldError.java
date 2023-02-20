import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class TestLoginUserWithoutRequiredFieldError {
    private final Random random = new Random();
    private final int randomNumber = random.nextInt(100);
    private final String newUserEmail = randomNumber + "testuseremail@yandex.ru";
    private final String badUserEmail = "";
    private final String newUserPassword = "password1234";
    private final String badUserPassword = "";
    private final String newUserName = "BurgerLoveMe";
    private final String expectedResult = "\"success\":false";
    private String accessToken;
    private final UserClient newUser = new UserClient(newUserEmail, newUserPassword, newUserName);

    @Before
    public void setUp(){
        newUser.createUser();
        accessToken = newUser.getAccessToken();
    }
    @Test
    @DisplayName("Логин пользователя с пустым полем \"Email\"")
    public void testLoginUserWithoutEmailField(){
        String result = newUser.badLogin(badUserEmail, newUserPassword, newUserName);
        assertThat(result, containsString(expectedResult));
    }
    @Test
    @DisplayName("Логин пользователя с пустым полем \"Password\"")
    public void testLoginUserWithoutPasswordField(){
        String result = newUser.badLogin(newUserEmail, badUserPassword, newUserName);
        assertThat(result, containsString(expectedResult));
    }
    @After
    public void tearsDown(){
        newUser.deleteUser(accessToken);
    }


}
