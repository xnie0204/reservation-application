package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {
    private  Burger BurgerA;
    private  Burger BurgerB;

    @BeforeEach
    public void runBefore(){
        BurgerA = new Burger("BeefBurger",8);
        BurgerB = new Burger("ChickenBurger",6);
    }

    @Test
    public void testConstructor(){
        assertEquals(8,BurgerA.getPrice());
        assertEquals(6,BurgerB.getPrice());

        assertEquals("BeefBurger", BurgerA.getName());
        assertEquals("ChickenBurger",BurgerB.getName());
    }

//    @Test
//    public void testToString(){
//        assertEquals("BeefBurger($8.00)",BurgerA.toString());
//        assertEquals("ChickenBurger($6.00)",BurgerB.toString());
//    }
}
