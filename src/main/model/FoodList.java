package model;


import javafx.util.converter.LocalDateTimeStringConverter;

import java.util.ArrayList;
import java.util.List;

// Represents a list of food
public class FoodList {
    private ArrayList<Food> foodList;
    private String reserveTime;
    private int totalPrice;
    private int totalOrderNum;

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
        reserveTime = time;
    }


    //EFFECTS: get the list of reserve list.
    public ArrayList<Food> getList() {
        return foodList;
    }


}
