import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class TestCreateNewUserOk {
    private final Random random = new Random();
    private final int randomNumber = random.nextInt(100);
    private final String newUserEmail = randomNumber + "testuseremail@yandex.ru";
    private final String newUserPassword = "password1234";
    private final String newUserName = "BurgerLoveMe";
    private final String expectedResult = "\"success\":true";
    private String accessToken;
    private final UserClient newUser = new UserClient(newUserEmail, newUserPassword, newUserName);

    @Test
    @DisplayName("Создание пользователя")
    public void createNewUser(){
        String result = newUser.createUser();
        accessToken = newUser.getAccessToken();
        assertThat(result, containsString(expectedResult));

    }
    @After
    public void tearsDown(){
        newUser.deleteUser(accessToken);
    }
}
