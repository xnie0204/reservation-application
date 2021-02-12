package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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
    //EFFECT: delete food form foodlist
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


    public String showTime() {
        return reserveTime;
    }

    public void setTime(String time) {
        this.reserveTime = time;
    }

    public ArrayList<Food> getList() {
        return foodList;
    }


}
