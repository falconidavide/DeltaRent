package gui;

import java.awt.*;

import javax.swing.JPanel;

public class GradientPanel extends JPanel {
    private Color color1;
    private Color color2;

    public GradientPanel(Color color1, Color color2) {
        this.color1 = color1;
        this.color2 = color2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        GradientPaint gradientPaint = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, width, height);
    }
}