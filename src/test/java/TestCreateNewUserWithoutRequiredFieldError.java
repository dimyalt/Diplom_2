import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TestCreateNewUserWithoutRequiredFieldError {
    private final Random random = new Random();
    private final int randomNumber = random.nextInt(100);
    private final String newUserEmail = randomNumber + "testuseremail@yandex.ru";
    private final String newUserPassword = "";
    private final String newUserName = "BurgerLoveMe";
    private final String expectedResult = "\"success\":false";
    private final UserClient newUser = new UserClient(newUserEmail, newUserPassword, newUserName);

    @Test
    @DisplayName("Создание пользователя без обязательного поля \"пароль\"")
    public void createNewUserWithoutRequiredField(){
        String result = newUser.createBadUser();
        assertThat(result, containsString(expectedResult));
    }

}
