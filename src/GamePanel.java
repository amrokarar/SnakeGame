
/**
 * @author Amro Karar
 * @version 1.0
 * 
 * Sets the game panel's dimensions and the game mechanics
 */

import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600; // width of the screen
    static final int SCREEN_HEIGHT = 600; // height of the screen
    static final int UNIT_SIZE = 25;
    static final int GAME_UNTS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 150; // controls spd of the game

    // holds the coordinates for the snake
    final int x[] = new int[GAME_UNTS];
    final int y[] = new int[GAME_UNTS];

    int bodyParts = 6; // number of starting parts on the snake
    int applesEaten; // tracks number of fruit eaten

    // coordinats for the location of the apple
    int appleX;
    int appleY;

    char direction = 'R'; // current direction of snake
    boolean running = false; // state of the game at the moment
    boolean start = true;
    Timer timer; // creates a timer instance
    Random random; // creates a random instance

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // sets panel dimensions
        this.setBackground(Color.BLACK); // screen background color
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    /**
     * Starts the game by creating an apple,
     * setting runnning value to true
     * and starting the timer
     */
    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(DELAY, this); // sets how fast the snake is moving
        timer.start();
    }

    /**
     * 
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * If game is running displays the apple, snake, and current score
     * 
     * @param g Graphics class
     */
    public void draw(Graphics g) {

        if (running) {
            // grid
            // for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            // g.setColor(Color.white);
            // g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            // g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            // }

            // Apple Color
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // color of the snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    // g.setColor(new Color(45, 180, 0));
                    g.setColor(
                            new Color(
                                    random.nextInt(255),
                                    random.nextInt(255),
                                    random.nextInt(255)));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            // Current Score
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString(
                    "Score: " + applesEaten,
                    (SCREEN_HEIGHT - metrics.stringWidth("Score: " + applesEaten)) / 2,
                    g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    /**
     * creates a new apple at a random spot
     */
    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

    }

    /**
     * movement method for the snake
     */
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U': // moving up
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D': // moving down
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L': // moving left
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R': // moving right
                x[0] = x[0] + UNIT_SIZE;
                break;
            default:
                break;
        }
    }

    /**
     * checks to see if apple is eaten
     */
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    /**
     * Checks if the snake has collided with its body or the borders
     */
    public void checkCollisions() {
        // checks if head collides w/ body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // checks if head touches border:

        // left
        if (x[0] < 0) {
            running = false;
        }

        // right
        if (x[0] > SCREEN_WIDTH) {
            running = false;
        }

        // top
        if (y[0] < 0) {
            running = false;
        }

        // bottom
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }

    }

    /**
     * Displays the game over screen
     * 
     * @param g Graphics class
     */
    public void gameOver(Graphics g) {
        // Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString(
                "Score: " + applesEaten,
                (SCREEN_HEIGHT - metrics1.stringWidth("Score: " + applesEaten)) / 2,
                g.getFont().getSize());

        // Game Over Text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString(
                "Game Over",
                (SCREEN_HEIGHT - metrics2.stringWidth("Game Oover")) / 2,
                SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    }

    /**
     * 
     * @author Amro Karar
     * @version 1.0
     * 
     *          Class which extends the KeyAdapter class for the key controls
     */
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        /**
         * 
         * @param e KeyEvent class for the keys pressed
         * 
         *          Uses a switch statement to determine the key presses and direct the
         *          snake accordingly
         */
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT: // if left key is pressed
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT: // if right arrow key is pressed
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP: // if up arrow key is pressed
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN: // if down arrow key is pressed
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
