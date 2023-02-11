import data.OrderData;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


@RunWith(Parameterized.class)
public class TestCreatingOrderWithDifferentTypesOfIngredients {
    private final OrderData newIngredients;
    private final String expectedResult;
    private final int statusCode;

    public TestCreatingOrderWithDifferentTypesOfIngredients(OrderData newIngredients, String expectedResult, int statusCode) {
        this.newIngredients = newIngredients;
        this.expectedResult = expectedResult;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {new OrderData(new ArrayList<>(Arrays.asList("61c0c5a71d1f82001bdaaa6d","61c0c5a71d1f82001bdaaa6f"))), "Бессмертный флюоресцентный бургер", 200},
                {new OrderData(new ArrayList<>(Arrays.asList("61c0c5a71d1f82001bdaaaaa","61c0c5a71d1f82001bdaaaaa"))), "One or more ids provided are incorrect", 400},
                {new OrderData(new ArrayList<>()), "Ingredient ids must be provided", 400},
        };
    }

    @Test
    @DisplayName("Создание заказа не авторизованным пользователем")
    public void createOrder(){
        final OrderClient newOrder = new OrderClient(newIngredients);
        String result = newOrder.createOrder(statusCode);
        assertThat(result, containsString(expectedResult));
    }

}