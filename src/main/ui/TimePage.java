package ui;

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


    public TimePage(FoodListPage foodListPage, FoodList foodList) {
        this.foodList = foodList;
        this.foodListPage = foodListPage;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        JLabel timeLabel = new JLabel("Please change your reserve time(format : XX:XX)");
        timeLabel.setBounds(48, 40, 400, 20);
        add(timeLabel);
        timeLabel.setForeground(Color.darkGray);

        reserveTimeField = new JTextField(30);
        reserveTimeField.setBounds(50, 70, 300, 20);
        add(reserveTimeField);

        JButton finishButton = new JButton(finishButtonString);
        finishButton.setBounds(310, 210, 100, 20);
        add(finishButton);
        finishButton.setActionCommand(finishButtonString);
        finishButton.addActionListener(this);
        finishButton.setForeground(Color.darkGray);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(finishButtonString)) {
            String timeReserve = reserveTimeField.getText();
            foodList.setTime(timeReserve);
          //  JOptionPane.showMessageDialog(null, "successfully");
            foodList.save(FoodList.myFile);
            foodListPage.dispose();
            new FoodListPage(foodList);
            dispose();
        }

    }
}
