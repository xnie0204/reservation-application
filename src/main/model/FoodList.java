package model;


import exception.InvalidTimeException;
import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Loadable;
import persistence.Saveable;


import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

// Represents a list of food
public class FoodList implements Loadable, Saveable {
    private ArrayList<Food> foodList;
    private String reserveTime;
    private int totalPrice;
    private int totalOrderNum;
    public static final String myFile = "src/data/myFile.txt";

    // EFFECTS: set is empty
    public FoodList() {
        foodList = new ArrayList<>();
    }

    //Modify: this
    //EFFECT: add food to food list
    public void addFood(Food f) {
        foodList.add(f);
    }


    public void clearSaveFileFile(String destination) {
        try {
            PrintWriter writer = new PrintWriter(destination, "UTF-8");
            writer.print("{foods:[]}");
            writer.close();
        } catch (IOException e) {
            System.out.println("Encountered IOException while clear saving todo list.");
        }
    }

    //REQUIRES:totalPrice can't less than 0.
    //EFFECT: calculate the total price.
    public int getTotalPrice() {
        totalPrice = 0;
        for (Food f : foodList) {
            totalPrice += f.getPrice();
        }
        return totalPrice;
    }

    //REQUIRES:total oder num can't less than 0.
    //EFFECT: calculate the total reserve food number.
    public int getTotalOrderNum() {
        totalOrderNum = 0;
        for (Food f : foodList) {
            totalOrderNum += 1;
        }
        return totalOrderNum;
    }

    //EFFECT:represent the number in the food-list
    public int size() {
        return foodList.size();
    }

    public boolean contain(Food f) {
        return foodList.contains(f);
    }

    //EFFECT: get the reserve list's reserve time. If not set ,return "Not set yet"

    public String getTime() {
        if (reserveTime == null) {
            return "Not set yet";
        } else {
            return reserveTime;
        }
    }

    //MODIFIES: this
    //EFFECTS: set the reserve time.
    public void setTime(String time) throws InvalidTimeException {
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String earliestWorkTimeString = "08:00";
        String latestWorkTimeString = "22:00";
        Date earliestWorkTime;
        Date latestWorkTime;
        Date resertivetime;

        try {
            earliestWorkTime = timeFormat.parse(earliestWorkTimeString);
            latestWorkTime = timeFormat.parse(latestWorkTimeString);
            resertivetime = timeFormat.parse(time);

        } catch (ParseException e) {
            throw new InvalidTimeException("Invalid Time Format");
        }


        if (resertivetime.before(earliestWorkTime)) {
            throw new InvalidTimeException("not work time");
        }
        if (resertivetime.after(latestWorkTime)) {
            throw new InvalidTimeException("not work time");
        }

        this.reserveTime = time;


    }


    //EFFECTS: get the list of reserve list.
    public ArrayList<Food> getList() {
        return foodList;
    }

    //MODIFIES: this
    //EFFECTS: load the content from the save file(myFile)
    @Override
    public void load(String destination) {
        try {
            List<String> foodTxt = Files.readAllLines(Paths.get(destination));
            String foodTxtString = "";
            for (String str : foodTxt) {
                foodTxtString = foodTxtString + str;
            }
            JSONObject object = new JSONObject(foodTxtString);
            JSONArray jsonArray = object.getJSONArray("foods");
            String reserveTime = object.optString("reserve time");
            this.reserveTime = reserveTime;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String price = jsonObject.getString("price");
                int priceNum = Integer.parseInt(price);
                addFood(new Food(name, priceNum));
            }
        } catch (IOException e) {
            System.out.println("Encountered IOException while loading food list.");
        }
    }

    //EFFECTS: save the food list to save file(myFile)
    @Override
    public void save(String destination) {
        try {
            PrintWriter printWriter = new PrintWriter(destination, "UTF-8");
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            for (Food f : foodList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", f.getName());
                String priceString = String.valueOf(f.getPrice());
                jsonObject.put("price", priceString);
                jsonObjects.add(jsonObject);
            }
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray(jsonObjects);
            jsonObject.put("foods", jsonArray);
            jsonObject.put("reserve time", reserveTime);
            printWriter.println(jsonObject.toString());
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Encountered IOException while saving food list.");
        }
    }

    //EFFECTS: save reserve time to save file(timeFile)


    //MODIFIES: this, myFile
    //EFFECTS: clear all the food concluding myFile and food list
    public void clearAll() {
        clearSaveFileFile(myFile);
        foodList.clear();
    }

    //EFFECTS: show the given index food.
    public Food getFood(int index) {
        if ((index >= 0) && (index < foodList.size())) {
            return foodList.get(index);
        } else {
            return null;
        }
    }
}

