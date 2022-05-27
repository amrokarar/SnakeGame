// import java.awt.Color;
// import java.awt.Font;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// import javax.swing.JButton;
import javax.swing.JFrame;

public class HomeFrame extends JFrame {
    // JButton button;

    HomeFrame() {

        this.add(new HomePanel());
        // button = new JButton();
        // button.setBounds(200, 100, 100, 50);
        // button.addActionListener(this);
        // // button.addActionListener(e -> new GameFrame) //no actionPerformed required
        // // or ActionListener implementation
        // button.setText("Start Game");
        // button.setFocusable(false);
        // button.setFont(new Font("Ink Free", Font.BOLD, 10));
        // button.setBackground(Color.PINK);

        this.setTitle("HOME");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setSize(500, 500);
        this.setVisible(true);
        // this.add(button);
        // this.setBackground(Color.BLACK);
        // this.setT

        this.setResizable(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    // @Override
    // public void actionPerformed(ActionEvent e) {
    // if (e.getSource() == button) {
    // new GameFrame();
    // }
    // }
}
