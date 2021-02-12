package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFoodList {
    private FoodList foodList;
    private Food beefBurger;
    private Food cola;

    @BeforeEach
    public void runBefore(){
        foodList = new FoodList();
        beefBurger = new Food("BeefBurger", 8);
        cola = new Food("cola",1);
    }
    @Test
    public void testConstructor(){
        assertEquals(0,foodList.size());
    }

    @Test
    public void testAddFood(){
        foodList.addFood(cola);
        assertEquals(1,foodList.size());
        assertTrue(foodList.contain(cola));
        assertEquals(1,foodList.getList().size());

        foodList.addFood(beefBurger);
        assertEquals(2,foodList.size());
        assertTrue(foodList.contain(beefBurger));
        assertTrue(foodList.contain(cola));
    }

    @Test
    public void testDeleteFood(){
        assertFalse(foodList.contain(cola));

        foodList.addFood(cola);
        foodList.addFood(beefBurger);
        assertEquals(2,foodList.size());
        foodList.deleteFood(cola);
        assertEquals(1,foodList.size());
        assertFalse(foodList.contain(cola));

    }

    @Test
    public void testGetTotalPrice(){
        assertEquals(0,foodList.getTotalPrice());

        foodList.addFood(cola);
        foodList.addFood(beefBurger);
        assertEquals(9,foodList.getTotalPrice());
    }

    @Test
    public void testGetTotalOrderNum(){
        assertEquals(0,foodList.getTotalOrderNum());

        foodList.addFood(cola);
        foodList.addFood(beefBurger);
        assertEquals(2,foodList.getTotalOrderNum());
    }

    @Test
    public void testSetTime(){
        assertEquals("Not set yet",foodList.getTime());

        foodList.setTime("12:00");
        assertEquals("12:00",foodList.getTime());
    }


    }





