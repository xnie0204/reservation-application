package ui;

import exception.InvalidTimeException;
import model.FoodList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimePage extends JFrame implements ActionListener {
    private FoodList foodList;
    private FoodListPage foodListPage;
    private JTextField reserveTimeField;
    private String finishButtonString = "Finish";

    //change reserve time
    public TimePage(FoodListPage foodListPage, FoodList foodList) {
        super("change time");
        this.foodList = foodList;
        this.foodListPage = foodListPage;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        JLabel timeLabel = new JLabel("Please change your reserve time(format : XX:XX AM/PM)");
        timeLabel.setBounds(48, 40, 400, 20);
        add(timeLabel);
        timeLabel.setForeground(Color.darkGray);

        reserveTimeField = new JTextField(30);
        reserveTimeField.setBounds(50, 70, 300, 20);
        add(reserveTimeField);

        addButton();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //EFFECTS: add the button
    private void addButton() {
        JButton finishButton = new JButton(finishButtonString);
        finishButton.setBounds(310, 210, 100, 20);
        add(finishButton);
        finishButton.setActionCommand(finishButtonString);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.darkGray);
    }


    //EFFECTS: option respond
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(finishButtonString)) {
            String timeReserve = reserveTimeField.getText();
            try {
                foodList.setTime(timeReserve);
            } catch (InvalidTimeException invalidTimeException) {

                JOptionPane.showMessageDialog(null, invalidTimeException.getMessage());
            }
            //  JOptionPane.showMessageDialog(null, "successfully");
            foodList.save(FoodList.myFile);
            foodListPage.dispose();
            new FoodListPage(foodList);
            dispose();
        }

    }
}
