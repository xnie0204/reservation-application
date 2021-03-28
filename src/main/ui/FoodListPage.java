package ui;

import model.Food;
import model.FoodList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoodListPage extends JFrame implements ActionListener {
    private FoodList foodList;
    private DefaultTableModel tableColumn;
    private JTable table;
    private String addButton = "Add Food";
    private String timeButton = "change reserve time";
    private JLabel background;


    public FoodListPage(FoodList foodList) {
        this.foodList = foodList;

        setPreferredSize(new Dimension(600, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        showFoodList(foodList);

        add(new JScrollPane(table));

        creatButton(addButton);
        creatButton(timeButton);
        showTotal();


        setTitle("Current Food List");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void showFoodList(FoodList foodList) {
        final String[] columnLabels = new String[]{
                "Index", "Name", "Price",
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
        } else if (e.getActionCommand().equals(timeButton)) {
            new TimePage(this,foodList);
        }

        foodList.save(FoodList.myFile);
        new FoodListPage(foodList);
        dispose();
    }

    //EFFECTS: show the total number, total price, reserve time.
    public void showTotal() {
        int totalPrice = foodList.getTotalPrice();
        int totalNumber = foodList.getTotalOrderNum();
        String reserveTime = foodList.getTime();
        JLabel totalNumberLabel = new JLabel("Total Order Number: " + totalNumber);
        totalNumberLabel.setBounds(10, 400, 500, 20);
        add(totalNumberLabel);

        JLabel totalPriceLabel = new JLabel("Total Order Price: $" + totalPrice);
        totalPriceLabel.setBounds(80, 440, 500, 20);
        add(totalPriceLabel);

        JLabel timeLabel = new JLabel("reserve time: " + reserveTime);
        timeLabel.setBounds(80, 40, 600, 20);
        add(timeLabel);

    }
}
