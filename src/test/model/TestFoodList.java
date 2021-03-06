package model;

import exception.InvalidTimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestFoodList extends JsonTest {
    private FoodList foodList;
    private Food beefBurger;
    private Food cola;

    @BeforeEach
    public void runBefore() {
        foodList = new FoodList();
        beefBurger = new Food("BeefBurger", 8);
        cola = new Food("cola", 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, foodList.size());
    }

    @Test
    public void testAddFood() {
        foodList.addFood(cola);
        assertEquals(1, foodList.size());
        assertTrue(foodList.contain(cola));
        assertEquals(1, foodList.getList().size());

        foodList.addFood(beefBurger);
        assertEquals(2, foodList.size());
        assertTrue(foodList.contain(beefBurger));
        assertTrue(foodList.contain(cola));
    }


    @Test
    public void testGetTotalPrice()  {
        assertEquals(0, foodList.getTotalPrice());

        foodList.addFood(cola);
        foodList.addFood(beefBurger);
        assertEquals(9, foodList.getTotalPrice());
    }

    @Test
    public void testGetTotalOrderNum() {
        assertEquals(0, foodList.getTotalOrderNum());

        foodList.addFood(cola);
        foodList.addFood(beefBurger);
        assertEquals(2, foodList.getTotalOrderNum());
    }

    @Test
    public void testSetTime() {
        assertEquals("Not set yet", foodList.getTime());

        try {
            foodList.setTime("12:00");
        } catch (InvalidTimeException e) {
            fail("excepted not throw");
        }
        assertEquals("12:00", foodList.getTime());

    }

    @Test
    public void testSetWrongTime(){
        try {
            foodList.setTime("HAHAHAAHHA");
            fail("excepted to be thrown");
        } catch (InvalidTimeException e) {

        }

    }

    @Test
    public void testSetBeforWorkTime(){
        try{
            foodList.setTime("7:00");
            fail("excepted to be thrown");
        } catch (InvalidTimeException e) {

        }
    }

    @Test
    public void testSetAfterWorkTime(){
        try{
            foodList.setTime("23:00");
            fail("excepted to be thrown");
        } catch (InvalidTimeException e) {

        }
    }



    @Test
    public void testSaveSuccesfullyInRightFile() {
        foodList.addFood(cola);
        foodList.addFood(beefBurger);
        foodList.save(FoodList.myFile);
        try {
            List<String> lines = Files.readAllLines(Paths.get(FoodList.myFile));
            String stringTxt = lines.get(0);
            String assumeTxt = "{\"foods\":[{\"price\":\"1\",\"name\":\"cola\"},{\"price\":\"8\",\"name\":\"BeefBurger\"}]}";
            assertEquals(assumeTxt, stringTxt);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testLoadSuccuesful() {
        try {
            foodList.addFood(cola);
            foodList.addFood(beefBurger);
            foodList.save(FoodList.myFile);

            FoodList testFoodList = new FoodList();
            testFoodList.load(FoodList.myFile);
            assertEquals(2, testFoodList.size());
            assertTrue(checkFood(foodList.getFood(0), cola));
            assertTrue(checkFood(foodList.getFood(1), beefBurger));
        } catch (Exception e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testSaveInvalidFile() {
        try {
            foodList.addFood(cola);
            foodList.addFood(beefBurger);
            foodList.save("./data/my\0illegal:fileName.json");
            PrintWriter writer = new PrintWriter(new File("./data/my\0illegal:fileName.json"));

            fail("IOException was expected");
        } catch (IOException e) {
        }
    }

    @Test
    public void testSaveEmptyFile() {
        try {
            foodList.save(FoodList.myFile);
            List<String> lines = Files.readAllLines(Paths.get(FoodList.myFile));
            String stringTxt = lines.get(0);
            String assumeTxt = "{\"foods\":[]}";
            assertEquals(assumeTxt, stringTxt);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testLoadEmptyFile() {
        try {
            foodList.save(FoodList.myFile);
            FoodList testFoodList = new FoodList();
            testFoodList.load(FoodList.myFile);
            assertEquals(0, testFoodList.size());
        } catch (Exception e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testLoadNoExitedFile(){
        try {
            FoodList testFoodList = new FoodList();
            testFoodList.load("");
            PrintWriter writer = new PrintWriter(new File(""));
            fail("IOException was expected");
        } catch (IOException e) {
        }

    }




    @Test
    public void testClearAll() {
        foodList.addFood(cola);
        foodList.addFood(beefBurger);
        foodList.save(FoodList.myFile);
        foodList.clearAll();
        try {
            assertEquals(0, foodList.size());
            List<String> lines = Files.readAllLines(Paths.get(FoodList.myFile));
            String stringTxt = lines.get(0);
            String assumeTxt = "{foods:[]}";
            assertEquals(assumeTxt, stringTxt);
        } catch (IOException e) {
            fail("clear all false");
        }
    }

    @Test
    public void testGetFood() {
        foodList.addFood(cola);
        foodList.addFood(beefBurger);
        assertEquals(cola, foodList.getFood(0));
        assertEquals(null, foodList.getFood(-1));
        assertEquals(null, foodList.getFood(3));
    }

    @Test
    public void testClearSaveFileFile() {
        try{
        foodList.addFood(cola);
        foodList.addFood(beefBurger);
        foodList.save(FoodList.myFile);
        foodList.clearSaveFileFile(FoodList.myFile);
        List<String> lines = Files.readAllLines(Paths.get(FoodList.myFile));
        String stringTxt = lines.get(0);
        String assumeTxt = "{foods:[]}";
        assertEquals(assumeTxt, stringTxt);
    }catch(IOException e){
        fail("clear all false");
    }

}
    @Test
    public void testClearInvalidSaveFile(){
        try{
            foodList.clearSaveFileFile("./data/my\0illegal:fileName.json");
            PrintWriter writer = new PrintWriter(new File("./data/my\0illegal:fileName.json"));
            fail("IOException was expected");
        } catch (Exception e) {
        }
    }
}







