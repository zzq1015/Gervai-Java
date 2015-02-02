/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gervai;

import java.awt.*;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import javax.swing.*;

/**
 *
 * @author Sam
 */
public class SurveyPanel extends JPanel {

    DecimalFormat df = new DecimalFormat("#,###,###.#");
    static int[] dataToDraw;
    final int PAD = 25;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("Segoe UI", 0, 12));
        int w = getWidth();
        int h = getHeight();
        // Draw abscissa.
        boolean allZero = true;
        boolean negnum = false;
        for (int i = 0; i < dataToDraw.length; i++) {
            if (dataToDraw[i] != 0) {
                allZero = false;
            }
            if (dataToDraw[i] < 0) {
                negnum = true;
            }
        }
        if (allZero || negnum) {
            g2.setFont(new Font("Segoe UI", 1, 24));
            g2.drawString("Line Chart Unavailable", w / 2 - 130, h / 2 - 35);
            g2.drawString("Check for all zero ", w / 2 - 130, h / 2 + 20);
            g2.drawString("and negative numbers.", w / 2 - 130, h / 2 + 50);
            return;
        }
        g2.draw(new Line2D.Double(PAD, h - PAD, w - (PAD / 2), h - PAD));
        // Draw ordinate.
        g2.draw(new Line2D.Double(PAD, PAD / 2, PAD, h - PAD));
        double xInc = (double) (w - 2 * PAD) / (dataToDraw.length - 1);
        double scale = (double) (h - 2 * PAD) / getMax();
        // Mark data points.
        g2.setPaint(Color.RED);
        boolean increase = false;

        if (dataToDraw.length > 1) {
            for (int i = 0; i < dataToDraw.length - 1; i++) {
                int x = (int) (PAD + i * xInc);
                int x2 = (int) (PAD + (i + 1) * xInc);
                int y = (int) (h - PAD - scale * dataToDraw[i]);
                int y2 = (int) (h - PAD - scale * dataToDraw[i + 1]);
                g2.setPaint(Color.RED);
                g2.draw(new Line2D.Double(x, y, x2, y2));
                g2.setPaint(Color.BLACK);
                if (y != y2) {
                    increase = (y < y2);
                }
                if (y < y2) {
                    g2.drawString(df.format(dataToDraw[i]), x - 5, y - 10);
                } else if (y > y2) {
                    g2.drawString(df.format(dataToDraw[i]), x - 5, y + 20);
                } else {
                    if (increase) {
                        g2.drawString(df.format(dataToDraw[i]), x - 5, y + 20);
                    } else {
                        g2.drawString(df.format(dataToDraw[i]), x - 5, y - 10);
                    }

                }
                if (i + 1 == dataToDraw.length - 1) {
                    x2 = (int) (PAD + (i + 1) * xInc);
                    y2 = (int) (h - PAD - scale * dataToDraw[i + 1]);
                    if (y != y2) {
                        increase = (y > y2);
                    }
                    if (y < y2) {
                        g2.drawString(df.format(dataToDraw[i + 1]), x2 - 5, y2 + 20);
                    } else if (y > y2) {
                        g2.drawString(df.format(dataToDraw[i + 1]), x2 - 5, y2 - 10);
                    } else {
                        if (increase) {
                            g2.drawString(df.format(dataToDraw[i + 1]), x2 - 5, y2 + 20);
                        } else {
                            g2.drawString(df.format(dataToDraw[i + 1]), x2 - 5, y2 - 10);
                        }
                    }
                }
            }
        } else {
            xInc = (int) (w / 2);
            int x = (int) (PAD + xInc);
            int y = (int) (h - PAD - scale * dataToDraw[0]);
            g2.setPaint(Color.RED);
            g2.fillOval(x - 2, y - 2, 4, 4);
            g2.setPaint(Color.BLACK);
            g2.drawString(df.format(dataToDraw[0]), x - 5, y + 20);
        }
    }

    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for (int i = 0; i < dataToDraw.length; i++) {
            if (dataToDraw[i] > max) {
                max = dataToDraw[i];
            }
        }
        return max;
    }
}
