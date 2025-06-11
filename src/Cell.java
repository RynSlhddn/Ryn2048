import java.awt.*;

public class Cell {

    private int row, col, value;
    private boolean newGuy;
    public static final int CSIZE = 150;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        value = 0;
        newGuy = false;
    }

    public void setValue(int a) {
        value = a;
    }

    public int getValue() {
        return value;
    }

    public void setNewGuy() {
        newGuy = true;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5));
        g2.drawRect(col * CSIZE, row * CSIZE, CSIZE, CSIZE);
        g2.setFont(new Font(null, Font.BOLD, 75));
        if (newGuy) {
            g2.setColor(Color.red);
            newGuy = false;
        }
        if (value == 0)
            value = 0;
        else if (value < 9)
            g2.drawString(value + "", col * CSIZE + 50, row * CSIZE + 100);
        else if (value < 65)
            g2.drawString(value + "", col * CSIZE + 25, row * CSIZE + 100);
        else if (value < 513)
            g2.drawString(value + "", col * CSIZE, row * CSIZE + 100);
    }
}