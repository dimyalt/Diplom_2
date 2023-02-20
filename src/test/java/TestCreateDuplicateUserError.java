import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class TestCreateDuplicateUserError {
    private final Random random = new Random();
    private final int randomNumber = random.nextInt(100);
    private final String newUserEmail = randomNumber + "testuseremail@yandex.ru";
    private final String newUserPassword = "password1234";
    private final String newUserName = "BurgerLoveMe";
    private final String expectedResult = "\"success\":false";
    private String accessToken;
    private final UserClient newUser = new UserClient(newUserEmail, newUserPassword, newUserName);

    @Before
    public void setUp(){
        newUser.createUser();
    }
    @Test
    @DisplayName("Создание дубликата пользователя")
    public void createDuplicateUser(){
        accessToken = newUser.getAccessToken();
        String result = newUser.createBadUser();
        assertThat(result, containsString(expectedResult));
    }
    @After
    public void tearsDown(){
        newUser.deleteUser(accessToken);
    }
}
