import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel {

    public static final int PSIZE = 800;

    private Gameboard board;

    public Panel() {
        super();
        setSize(WIDTH, HEIGHT);

        board = new Gameboard();

        setUpInput();
    }

    public void setUpInput() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)
                    board.shiftLeft();
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
                    board.shiftRight();
                if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
                    board.shiftUp();
                if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
                    board.shiftDown();
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        board.draw(g2);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("2048");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.setBounds(0, 0, PSIZE, PSIZE + 28);
        JPanel panel = new Panel();

        panel.setFocusable(true);
        panel.grabFocus();
        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}