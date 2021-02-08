package model;

//Represent a burger's name and price
public class Burger {
    private String name;
    private int price;

    /* REQUIRES: burgerName has a non-zero length
     *             burgerPrice >= 0
     * EFFECTS: name and price on burger is set to burgerName and burgerPrice;
     */
    public Burger(String burgerName, int burgerPrice) {
        this.name = burgerName;
        this.price = burgerPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public


}
