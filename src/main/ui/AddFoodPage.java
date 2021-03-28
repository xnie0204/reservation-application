package ui;

import model.Food;
import model.FoodList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFoodPage extends JFrame implements ActionListener {
    private FoodListPage foodListPage;
    private FoodList foodList;
    private JLabel label;
    private int buttonPosition = 80;
    private int buttonWidth = 300;
    private int buttonHeight = 20;
    private String addBeefBurgerButton = "BeefBurger($9)";
    private String addColaButton = "Cola($1)";
    private String addSuperBurgerButton = "SuperBurger($10)";


    public AddFoodPage(FoodListPage foodListPage, FoodList foodList) {
        super("Add Food Page");
        this.foodList = foodList;
        this.foodListPage = foodListPage;

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(600, 450));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        label = new JLabel("Please select the food you want", JLabel.CENTER);
        label.setBounds(80, 10, 300, 20);
        add(label);
        label.setForeground(Color.darkGray);

        addButton();

        setDefaultCloseOperation(HIDE_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(addBeefBurgerButton)) {
            foodList.addFood(new Food("Beef Burger", 9));

        } else if (e.getActionCommand().equals(addColaButton)) {
            foodList.addFood(new Food("Cola", 1));
        } else if (e.getActionCommand().equals(addSuperBurgerButton)) {
            foodList.addFood(new Food("Super Burger", 10));
        }
        JOptionPane.showMessageDialog(null, "add successfully");
        foodList.save(FoodList.myFile);

        //REFRESH
        foodListPage.dispose();
        new FoodListPage(foodList);
        dispose();
    }





    private void addButton() {
        creatButton(addBeefBurgerButton, 40);
        creatButton(addColaButton, 80);
        creatButton(addSuperBurgerButton, 120);
    }

    private void creatButton(String string, int yposition) {
        JButton button = new JButton(string);
        button.setBounds(buttonPosition, yposition, buttonWidth, buttonHeight);
        add(button);
        button.setActionCommand(string);
        button.addActionListener(this);
        button.setForeground(Color.black);

    }
}