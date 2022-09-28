
/**
 * @author Amro Karar
 * @version 1.0
 * 
 * Sets the home panel's dimensions and its mechanics
 */

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class HomePanel extends JPanel implements ActionListener {

    JButton button;
    // JButton button50;
    // JButton button75;
    // JButton button100;
    static final int SCREEN_WIDTH = 600; // width of the screen
    static final int SCREEN_HEIGHT = 600; // height of the screen
    Random random;

    HomePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);
        setButton();
    }

    /**
     * Displays and sets the mechanics of the start button
     * 
     */
    private void setButton() {
        // Start Game Button
        button = new JButton();
        this.add(button);
        this.revalidate();
        this.repaint();
        button.setFont(new Font("Ink Free", Font.BOLD, 15));
        button.addActionListener(this);
        button.setText("Start Game");
        FontMetrics metrics = getFontMetrics(button.getFont());
        button.setBounds((SCREEN_WIDTH / 2 - metrics.stringWidth("Start Game") / 2),
                (int) (SCREEN_HEIGHT * .75), 100, 50);

        // button50 = new JButton();
        // this.add(button50);
        // this.revalidate();
        // this.repaint();
        // button.setFont(new Font("Ink Free", Font.BOLD, 15));
        // button.addActionListener(this);
        // button.setText("50");
        // FontMetrics metrics2 = getFontMetrics(button.getFont());
        // button.setBounds((SCREEN_WIDTH - metrics2.stringWidth("75")) / 2 - 2 *
        // metrics2.stringWidth("75"),
        // (int) (SCREEN_HEIGHT / 2) + 50, 100, 50);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * 
     * @param g Graphics class
     * 
     *          Displays the game title
     */
    private void draw(Graphics g) {

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Snake Game",
                (SCREEN_HEIGHT - metrics.stringWidth("Snake Game")) / 2,
                SCREEN_HEIGHT / 4);

        // g.setColor(Color.red);
        // g.setFont(new Font("Ink Free", Font.BOLD, 25));
        // FontMetrics metrics2 = getFontMetrics(g.getFont());
        // g.drawString("Set CC:",
        // (SCREEN_HEIGHT - metrics2.stringWidth("Snake Game")) / 2,
        // SCREEN_HEIGHT / 2);
    }

    @Override
    /**
     * Sends the user to the game screen
     * 
     * @param e ActionEvent object
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            new GameFrame();
        }
    }
}
