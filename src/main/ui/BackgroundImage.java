package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BackgroundImage extends JComponent {
    private Image image;

    public BackgroundImage(BufferedImage backgroundImage) {
        this.image = backgroundImage;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(image, 0, 0, this);
    }
}
