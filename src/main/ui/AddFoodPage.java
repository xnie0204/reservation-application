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
    private JLabel background;

   //set the add food page,
    public AddFoodPage(FoodListPage foodListPage, FoodList foodList) {
        super("Add Food Page");
        this.foodList = foodList;
        this.foodListPage = foodListPage;

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(450, 450));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        label = new JLabel("Please select the food you want", JLabel.CENTER);
        label.setBounds(80, 10, 400, 20);
        add(label);
        label.setForeground(Color.darkGray);

        addButton();
        setBackground();


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //EFFECTS: show the mouse action
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
        dispose();

        foodListPage.dispose();
        new FoodListPage(foodList);
        dispose();
    }


    //EFFECTS: add the button.
    private void addButton() {
        creatButton(addBeefBurgerButton, 40);
        creatButton(addColaButton, 80);
        creatButton(addSuperBurgerButton, 120);
    }

    //EFFECTS: creat the button
    private void creatButton(String string, int yposition) {
        JButton button = new JButton(string);
        button.setBounds(buttonPosition, yposition, buttonWidth, buttonHeight);
        add(button);
        button.setActionCommand(string);
        button.addActionListener(this);
        button.setForeground(Color.black);

    }

    //EFFECTS: set the background.
    private void setBackground() {
        ImageIcon img = new ImageIcon("src/picture/P2.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 450, 450);
        add(background);
    }


}
