package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestFoodList extends JsonTest{
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
        assertEquals("Not set yet" ,foodList.getTime());

        foodList.setTime("12:00");
        assertEquals("12:00",foodList.getTime());

        foodList.setTime("HAHAHAAHHA");
        assertEquals("please set again",foodList.getTime());
    }

    @Test
    public void testChangeToDataForm(){
        assertTrue(foodList.changeToDataForm("12:00"));
        assertFalse(foodList.changeToDataForm("??????"));
    }

    @Test
    public void testSaveSuccesfullyInRightFile() throws FileNotFoundException {
        foodList.addFood(cola);
        foodList.addFood(beefBurger);
        foodList.save(FoodList.myFile);
        try{
            List<String>lines = Files.readAllLines(Paths.get(FoodList.myFile));
            String stringTxt = lines.get(0);
            String assumeTxt = "[{\"price\":\"1\",\"name\":\"cola\"},{\"price\":\"8\",\"name\":\"BeefBurger\"}]";
            assertEquals(assumeTxt,stringTxt);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testLoadSuccuesful(){
        try{
            foodList.addFood(cola);
            foodList.addFood(beefBurger);
            foodList.save(FoodList.myFile);

            FoodList testFoodList = new FoodList();
            testFoodList.load(FoodList.myFile);
            assertEquals(2,testFoodList.size());
            assertTrue(checkFood(foodList.getFood(0),cola ));
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
        } catch (IOException e){
        }
    }

    @Test
    public void testSaveEmptyFile(){
        try{
            foodList.save(FoodList.myFile);
            List<String>lines = Files.readAllLines(Paths.get(FoodList.myFile));
            String stringTxt = lines.get(0);
            String assumeTxt = "[]";
            assertEquals(assumeTxt,stringTxt);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testLoadEmptyFile(){
        try{
            foodList.save(FoodList.myFile);
            FoodList testFoodList = new FoodList();
            testFoodList.load(FoodList.myFile);
            assertEquals(0,testFoodList.size());
        } catch (Exception e) {
           fail("Couldn't read from file");
        }
    }

    @Test
    public void testSaveNoSetTime(){
        try{
            foodList.saveTime(FoodList.timeFile);
            Stream<String> stringStream = Files.lines(Paths.get(foodList.timeFile));
            StringBuilder timeData = new StringBuilder();
            stringStream.forEach(s -> timeData.append(s));
            String timeString = timeData.toString();
            String timeAssum = "Not set yet";
            assertEquals(timeAssum , timeString);
        } catch (IOException e) {
            fail("can't save time");
        }
    }

    @Test
    public void testSaveSetTime(){
        try{
            foodList.setTime("12:00");
            foodList.saveTime(FoodList.timeFile);
            Stream<String> stringStream = Files.lines(Paths.get(foodList.timeFile));
            StringBuilder timeData = new StringBuilder();
            stringStream.forEach(s -> timeData.append(s));
            String timeString = timeData.toString();
            String timeAssum = "12:00";
            assertEquals(timeAssum , timeString);
        } catch (IOException e) {
            fail("can't save time");
        }
    }


    }







