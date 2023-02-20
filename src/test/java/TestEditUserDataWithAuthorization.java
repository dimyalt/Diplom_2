import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TestEditUserDataWithAuthorization {
    private final Random random = new Random();
    private final int randomNumber = random.nextInt(100);
    private final String userEmail = randomNumber + "testuseremail@yandex.ru";
    private final String newUserEmail = "new_testuseremail24@yandex.ru";
    private final String userPassword = "password1234";
    private final String newUserPassword = "new_password1234";
    private final String userName = "BurgerLoveMe";
    private final String newUserName = "ILoveBurger";
    private final String expectedResult = "\"success\":true";
    private String accessToken;
    private final UserClient newUser = new UserClient(userEmail, userPassword, userName);

    @Before
    public void setUp(){
        newUser.createUser();
        accessToken = newUser.getAccessToken();
    }
    @Test
    @DisplayName("Изменение email пользователя")
    public void editUserEmail(){
        String result = newUser.editUserEmail(newUserEmail, accessToken);
        assertThat(result, containsString(newUserEmail));
    }
    @Test
    @DisplayName("Изменение пароля пользователя")
    public void editUserPassword(){
        String result = newUser.editUserPassword(newUserPassword, accessToken);
        assertThat(result, containsString(expectedResult));
    }
    @Test
    @DisplayName("Изменение имени пользователя")
    public void editUserName(){
        String result = newUser.editUserName(newUserName, accessToken);
        assertThat(result, containsString(newUserName));
    }
    @After
    public void tearsDown(){
        newUser.deleteUser(accessToken);
    }
}
