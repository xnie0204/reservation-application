package model;


import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Loadable;
import persistence.Saveable;
import persistence.TimeLoad;
import persistence.TimeSave;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a list of food
public class FoodList implements Loadable, Saveable, TimeSave, TimeLoad {
    private ArrayList<Food> foodList;
    private String reserveTime;
    private int totalPrice;
    private int totalOrderNum;
    public static final String myFile = "src/data/myFile.txt";
    public static final String timeFile = "src/data/timeFile.txt";

    // EFFECTS: set is empty
    public FoodList() {
        foodList = new ArrayList<>();
    }

    //Modify: this
    //EFFECT: add food to food list
    public void addFood(Food f) {
        foodList.add(f);
    }

    //Modify：this
    //EFFECT: delete food form foodList
    public void deleteFood(Food f) {
        foodList.remove(f);
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
    public void setTime(String time) {
        if (changeToDataForm(time)) {
            reserveTime = time;
        } else {
            reserveTime = "please set again";
        }

    }

    //EFFECTS: change the reserve time to data format, and refuse the no legal data format.
    public boolean changeToDataForm(String time) {
        DateFormat timeFormat = new SimpleDateFormat("hh:mm");
        try {
            timeFormat.parse(time);
            return true;
        } catch (ParseException p) {
            System.out.println("invalid time format,set again");
            return false;
        }
    }


    //EFFECTS: get the list of reserve list.
    public ArrayList<Food> getList() {
        return foodList;
    }


    @Override
    public void load() {
        try {
            List<String> foodTxt = Files.readAllLines(Paths.get(myFile));
            String foodTxtString = foodTxt.get(0);
            JSONArray jsonArray = new JSONArray(foodTxtString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String price = jsonObject.getString("price");
                int priceNum = Integer.parseInt(price);
                foodList.add(new Food(name, priceNum));
            }
        } catch (IOException e) {
            System.out.println("Encountered IOException while loading food list.");
        }
    }


    @Override
    public void save() {
        try {
            PrintWriter printWriter = new PrintWriter(myFile, "UTF-8");
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            for (Food f : foodList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", f.getName());
                String priceString = String.valueOf(f.getPrice());
                jsonObject.put("price", priceString);
                jsonObjects.add(jsonObject);
            }
            JSONArray jsonArray = new JSONArray(jsonObjects);
            printWriter.println(jsonArray);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Encountered IOException while saving food list.");
        }
    }


    @Override
    public void saveTime() throws IOException {
        PrintWriter printWriter = new PrintWriter(timeFile, "UTF-8");
        printWriter.println(getTime());
        printWriter.close();
    }

    @Override
    public void loadTime() {
        try {
            Stream<String> stringStream = Files.lines(Paths.get(timeFile));
            StringBuilder timeData = new StringBuilder();
            stringStream.forEach(s -> timeData.append(s));
            String timeString = timeData.toString();
            setTime(timeString);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

