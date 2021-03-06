package model;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class JsonTest {
    protected boolean checkFood(Food food1, Food food2){
       return ((food1.getName().equals(food2.getName()))
       && (food1.getPrice() == (food2.getPrice())));

    }

}
