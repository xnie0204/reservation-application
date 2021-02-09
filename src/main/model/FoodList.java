package model;

import java.util.ArrayList;
import java.util.Date;

public class FoodList {
    private ArrayList<Food> foodList;
    private String reserveTime;
    private int totalPrice;
    private int totalOrderNum;

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
    //REQUIRE:totalPrice can't less than 0.
    //EFFECT: calculate the total price.
    public int getTotalPrice(){
        totalPrice = 0;
        for (Food f : foodList ){
            totalPrice +=
        }
        return totalPrice;
    }

    public int getTotalOrderNum(){
        totalOrderNum = 0;
        for (Food f: foodList){
            totalOrderNum += 1;
        }
        return totalOrderNum;
    }


}
