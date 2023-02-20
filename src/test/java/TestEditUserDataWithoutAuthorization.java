import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TestEditUserDataWithoutAuthorization {
    private final Random random = new Random();
    private final int randomNumber = random.nextInt(100);
    private final String userEmail = randomNumber + "testuseremail@yandex.ru";
    private final String newUserEmail = "new_testuseremail" + randomNumber + "@yandex.ru";
    private final String userPassword = "password1234";
    private final String newUserPassword = "new_password1234";
    private final String userName = "BurgerLoveMe";
    private final String newUserName = "ILoveBurger";
    private final String expectedResult = "\"You should be authorised\"";

    private String accessToken;
    private final UserClient newUser = new UserClient(userEmail, userPassword, userName);

    @Before
    public void setUp(){
        newUser.createUser();
        accessToken = newUser.getAccessToken();
    }
    @Test
    @DisplayName("Изменение email пользователя")
    public void setUserEmailWithoutAuthorization(){
        String result = newUser.editUserEmailWithoutAuthorization(newUserEmail);
        assertThat(result, containsString(expectedResult));
    }
    @Test
    @DisplayName("Изменение пароля пользователя")
    public void setUserPasswordWithoutAuthorization(){
        String result = newUser.editUserPasswordWithoutAuthorization(newUserPassword);
        assertThat(result, containsString(expectedResult));
    }
    @Test
    @DisplayName("Изменение имени пользователя")
    public void setUserNameWithoutAuthorization(){
        String result = newUser.editUserNameWithoutAuthorization(newUserName);
        assertThat(result, containsString(expectedResult));
    }
    @After
    public void tearsDown(){
        newUser.deleteUser(accessToken);
    }

}
