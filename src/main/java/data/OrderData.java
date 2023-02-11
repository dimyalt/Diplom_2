package data;

import java.util.ArrayList;

public class OrderData {
    private ArrayList<String> ingredients;

    public OrderData(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public OrderData() {
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
