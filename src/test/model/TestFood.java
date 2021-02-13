package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFood {
    private Food BurgerA;
    private Food BurgerB;

    @BeforeEach
    public void runBefore() {
        BurgerA = new Food("BeefBurger", 8);
        BurgerB = new Food("ChickenBurger", 6);
    }

    @Test
    public void testConstructor() {
        assertEquals(8, BurgerA.getPrice());
        assertEquals(6, BurgerB.getPrice());

        assertEquals("BeefBurger", BurgerA.getName());
        assertEquals("ChickenBurger", BurgerB.getName());
    }

    @Test
    public void testSetPrice() {
        assertEquals(8, BurgerA.getPrice());
        assertEquals("BeefBurger", BurgerA.getName());

        BurgerA.setPrice(10);
        assertEquals(10, BurgerA.getPrice());
        assertEquals("BeefBurger", BurgerA.getName());

        BurgerA.setPrice(-10);
        assertEquals(10000000, BurgerA.getPrice());
    }

    @Test
    public void testSetName() {
        assertEquals("BeefBurger", BurgerA.getName());
        assertEquals(8, BurgerA.getPrice());


        BurgerA.setName("SuperBurger");
        assertEquals("SuperBurger", BurgerA.getName());
        assertEquals(8, BurgerA.getPrice());
    }
}
