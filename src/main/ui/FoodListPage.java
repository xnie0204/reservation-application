package ui;

import model.Food;
import model.FoodList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodListPage extends JFrame implements ActionListener {
    private FoodList foodList;
    private DefaultTableModel tableColumn;
    private JTable table;
    private String addButton = "Add Food";
    private JLabel background;


    public FoodListPage(FoodList foodList) {
        this.foodList = foodList;

        final String[] columnLabels = new String[]{
                "Index",
                "Name",
                "Price",
        };


        //create empty list
        tableColumn = new DefaultTableModel(null, columnLabels) {
        };
        table = new JTable(tableColumn);

        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.getFood(i);
            Object[] row = new Object[]{
                    i + 1, // index
                    food.getName(), // name
                    food.getPrice() //price
            };
            tableColumn.addRow(row);
        }

        add(new JScrollPane(table));

        creatButton(addButton);

        //setBackground();

        setTitle("Current Food List");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }


    private void creatButton(String string) {
        JButton button = new JButton(string);
        //     button.setBounds(buttonPosition, yposition, buttonWidth, buttonHeight);
        add(button);
        button.setActionCommand(string);
        button.addActionListener(this);
        button.setForeground(Color.black);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(addButton)) {
            new AddFoodPage(this, foodList);
        }

        foodList.save(FoodList.myFile);
        new FoodListPage(foodList);
        dispose();
    }
}
