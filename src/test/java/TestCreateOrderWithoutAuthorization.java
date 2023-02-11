import data.OrderData;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class TestCreateOrderWithoutAuthorization {
    private final OrderData newIngredients = new OrderData(new ArrayList<>(Arrays.asList("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa6f")));
    private final String expectedResult = "\"order\":{\"number\":";
    private final OrderClient newOrder = new OrderClient(newIngredients);
    @Test
    @DisplayName("Создание заказа не авторизованным пользователем")
    public void createOrder(){
        String result = newOrder.createOrderWithoutAuthorization();
        assertThat(result, containsString(expectedResult));
    }

}
