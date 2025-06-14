
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel {

    public static final int PSIZE = 600;

    private Gameboard board;

    private boolean stupid = false, dumb = false;
    public boolean doneFor = false;

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
                    board.shiftLeft(board.thecells);
                if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)
                    board.shiftRight(board.thecells);
                if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W)
                    board.shiftUp(board.thecells);
                if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S)
                    board.shiftDown(board.thecells);
                if (key == KeyEvent.VK_R)
                    board = new Gameboard();
                if (key == KeyEvent.VK_SPACE) {
                    board.thecells[0][0].setValue(2048);
                    board.thecells[0][1].setValue(512);
                }
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (board.checkMoved())
            board.spawn();

        board.draw(g2);
        g2.setColor(Color.black);
        g2.drawString(board.score + "", 650, 100);

        if (board.deadCheck())
            doneFor = true;

        for (Cell[] help : board.thecells) {
            for (Cell me : help) {
                if (me.getValue() >= 2048 && !stupid) {
                    JFrame winFrame = new JFrame("GGs you win!");
                    winFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                    winFrame.setBounds(200, 386, 300, 1);
                    JPanel winPanel = new The();

                    winPanel.setFocusable(true);
                    winPanel.grabFocus();
                    winFrame.add(winPanel);
                    winFrame.setVisible(true);
                    winFrame.setResizable(false);
                    stupid = true;
                }
            }
        }

        if (doneFor && !dumb) {
            JFrame loseFrame = new JFrame("You suck at the game!");
            loseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            loseFrame.setBounds(200, 386, 300, 1);
            JPanel losePanel = new The();

            losePanel.setFocusable(true);
            losePanel.grabFocus();
            loseFrame.add(losePanel);
            loseFrame.setVisible(true);
            loseFrame.setResizable(false);
            dumb = true;
        }
    }

    public static void main(String[] args) {
        JFrame window = new JFrame("2048");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        window.setBounds(0, 0, PSIZE + 300, PSIZE + 28);
        JPanel panel = new Panel();

        panel.setFocusable(true);
        panel.grabFocus();
        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
}
