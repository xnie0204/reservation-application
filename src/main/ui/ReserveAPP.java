package ui;

import model.Food;
import model.FoodList;

import java.util.ArrayList;
import java.util.Scanner;

public class ReserveAPP {
    private Scanner scanner;
    private FoodList foodList;
    private Food beefBurger;
    private Food cola;
    private Food superBurger;
    private String time;


    public ReserveAPP() {
        foodList = new FoodList();
        beefBurger = new Food("BeefBurger", 8);
        cola = new Food("cola", 1);
        superBurger = new Food("superBurger", 10);
        scanner = new Scanner(System.in);
        runReserveApp();
    }

    public void runReserveApp() {
        foodList = new FoodList();

        String operation = "";


        while (true) {
            System.out.println(" Please select an option");
            System.out.println("[1] Add food to your reserve list");
            System.out.println("[2] Delete food from your reserve list");
            System.out.println("[3] Check your current reserve list");
            System.out.println("[4] set or change your reserve time");
            operation = scanner.nextLine();

            if (operation.equals("1")) {
                doAddFood();
            } else if (operation.equals("2")) {
                doDeletFood();
            } else if (operation.equals("3")) {
                doCheckList();
            } else if (operation.equals("4")) {
                doSetTime();
            } else {
                System.out.println("Selection not valid...");
            }
            separatorLine();
        }
    }

    private void doAddFood() {

        String operation = "";
        separatorLine();
        showList();
        separatorLine();

        System.out.println("Please select the food you want to add");
        System.out.println("[1] Beef Burger($8)");
        System.out.println("[2] Cola($1)");
        System.out.println("[3] Super Burger($10)");

        operation = scanner.nextLine();
        if (operation.equals("1")) {
            foodList.addFood(beefBurger);
        } else if (operation.equals("2")) {
            foodList.addFood(cola);
        } else if (operation.equals("3")) {
            foodList.addFood(superBurger);
        } else {
            System.out.println("Selection not valid...");
        }
        separatorLine();
    }

    private void doDeletFood() {
        String operation = "";
        separatorLine();
        showList();
        separatorLine();
        System.out.println("Please select the food you want to delete");
        System.out.println("[1] Beef Burger($8)");
        System.out.println("[2] Cola($1)");
        System.out.println("[3] Super Burger($10)");

        operation = scanner.nextLine();
        if (operation.equals(1)) {
            foodList.deleteFood(beefBurger);
        } else if (operation.equals("2")) {
            foodList.deleteFood(cola);
        } else if (operation.equals("3")) {
            foodList.deleteFood(superBurger);
        } else {
            System.out.println("Selection not valid...");
        }
        separatorLine();
    }

    private void doCheckList() {
        showList();
        separatorLine();
    }

    private void showList() {
        showFoodList(foodList.getList());
        System.out.println("Total order num " + foodList.getTotalOrderNum());
        System.out.println("Total price $" + foodList.getTotalPrice());
        System.out.println(foodList.showTime());
        separatorLine();
    }

    private void showFoodList(ArrayList<Food> foodList) {
        System.out.println("Reserve List");
        for (Food f : foodList) {
            System.out.println(f.getName() + "($" + f.getPrice() + ")");
        }

    }


    private void doSetTime() {
        foodList.showTime();
        System.out.println("Enter the time you want to reserve, eg( XX:XX)");

        String time = scanner.nextLine();
        foodList.setTime(time);
    }

    private void separatorLine() {
        System.out.println("------------------------------------------------");
    }
}




