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

    //REQUIRES: price  can't less than0
    //MODIFIES: this
    //EFFECTS: set or change  the price
    public void setPrice(int price) {
        this.price = price;
    }

    //REQUIRES: String lenth can't be 0
    //MODIFIES: this
    //EFFECTS:set or change the name
    public void setName(String name) {
        this.name = name;
    }
}
