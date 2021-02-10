package model;

//Represent a burger's name and price
public class Food {
    private String name;
    private int price;

    //REQUIRES: burgerName has a non-zero length
     //            burgerPrice >= 0
     // EFFECTS: name and price on burger is set to burgerName and burgerPrice;
    public Food(String foodName, int foodPrice) {
        this.name = foodName;
        this.price = foodPrice;
    }

    //EFFECTS: represent get food's name
    public String getName() {
        return name;
    }

    //EFFECTS:represent get food's price.
    public int getPrice() {
        return price;
    }
}
