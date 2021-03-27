package ui;

import model.FoodList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Window;

import java.io.File;
import java.io.IOException;

//Show the main page of the APP.
public class MainPage extends JFrame implements ActionListener {
    private FoodList foodList = new FoodList();
    private String viewList;
    private JLabel label;
    private JTextField field;
    private int buttonPosition = 150;
    private int buttonWidth = 300;
    private int buttonHeight = 30;
    private String viewButton = "Check your current reserve list";
    private String clearButton = "Clear all food from your reserve list";
    private String quitButton = "save and quit APP";
    private FoodListPage foodListPage;


    public MainPage() {
        //set the size of main
        super("Reserve Food Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 90));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        //set the background image.
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("src/ui/image 1.jpeg"));
            setContentPane(new BackgroundImage(backgroundImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //load the persistence
        foodList.load(FoodList.myFile);

        //set the construction
        label = new JLabel("Please select the option you want", JLabel.CENTER);
        label.setBounds(26, 10, 300, 20);
        add(label);
        label.setForeground(Color.darkGray);

        addButton();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    //EFFECT: add new button in given position.
    private void addButton() {
        creatButton(viewButton, 40);
        creatButton(clearButton, 80);
        creatButton(quitButton, 120);
    }


    //EFFECT: create a button in given postion.
    private void creatButton(String string, int yposition) {
        JButton checkListButton = new JButton(string);
        checkListButton.setBounds(buttonPosition, yposition, buttonWidth, buttonHeight);
        add(checkListButton);
        checkListButton.setActionCommand(string);
        checkListButton.addActionListener(this);
        checkListButton.setForeground(Color.black);

    }

    //EFFECTS: after clicking the button, given specific operation
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(viewButton)) {
            foodListPage = new FoodListPage(foodList);
        } else if (e.getActionCommand().equals(clearButton)) {
            foodList.clearAll();
            foodListPage.dispose();
            foodListPage = new FoodListPage(foodList);
        } else {
            foodListPage.dispose();
            dispose();
        }
    }
}
