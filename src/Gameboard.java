import java.awt.*;

public class Gameboard {

    private Cell[][] cells;
    private final int rows = 4, cols = 4;
    public int score = 0;
    SoundPlayer sp = new SoundPlayer();

    public Gameboard() {
        sp.addSound("merge", "./audios/dbButtonClickFlag.wav");
        sp.addSound("start", "./audios/firstPaint.wav");
        sp.addSound("win", "./audios/win.wav");
        cells = new Cell[4][4];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i, j);
            }
        sp.playSound("start");
        startSpawn();

    }

    public void startSpawn() {
        int twoC = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (Math.random() < .1875) {
                    cells[i][j].setValue(2);
                    twoC++;
                }
                if (twoC > 1)
                    return;
            }
        startSpawn();
    }

    public void draw(Graphics2D g2){
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                cells[r][c].draw(g2);
    }

    public void shiftLeft() {
        boolean madeMove = false;
        for (int r = 0; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                Cell intruder = cells[r][c];
                Cell target = cells[r][c - 1];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    madeMove = true;
                    target.setValue(intruder.getValue());
                    intruder.setValue(0);
                    if (c > 1) {
                        peeker = cells[r][c - 2];
                        if (peeker.getValue() == 0)
                            c -= 2;
                    }
                }
            }
            for (int c = 1; c < cols; c++) {
                Cell intruder = cells[r][c];
                Cell target = cells[r][c - 1];
                if (intruder.getValue() == target.getValue() && target.getValue() > 0) {
                    sp.playSound("merge");
                    madeMove = true;
                    target.setValue(target.getValue() * 2);
                    intruder.setValue(0);
                    score += target.getValue();
                }
            }
            for (int c = 1; c < cols; c++) {
                Cell intruder = cells[r][c];
                Cell target = cells[r][c - 1];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    madeMove = true;
                    target.setValue(intruder.getValue());
                    intruder.setValue(0);
                    if (c > 1) {
                        peeker = cells[r][c - 2];
                        if (peeker.getValue() == 0)
                            c -= 2;
                    }
                }
            }
        }
        if (madeMove)
            spawn();
    }

    public void shiftRight() {
        boolean madeMove = false;
        for (int r = 0; r < rows; r++) {
            for (int c = cols - 2; c >= 0; c--) {
                Cell intruder = cells[r][c];
                Cell target = cells[r][c + 1];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    madeMove = true;
                    target.setValue(intruder.getValue());
                    intruder.setValue(0);
                    if (c < cols - 2) {
                        peeker = cells[r][c + 2];
                        if (peeker.getValue() == 0)
                            c += 2;
                    }
                }
            }
            for (int c = cols - 2; c >= 0; c--) {
                Cell intruder = cells[r][c];
                Cell target = cells[r][c + 1];
                if (intruder.getValue() == target.getValue() && target.getValue() > 0) {
                    sp.playSound("merge");
                    madeMove = true;
                    target.setValue(target.getValue() * 2);
                    intruder.setValue(0);
                    score += target.getValue();
                }
            }
            for (int c = cols - 2; c >= 0; c--) {
                Cell intruder = cells[r][c];
                Cell target = cells[r][c + 1];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    madeMove = true;
                    target.setValue(intruder.getValue());
                    intruder.setValue(0);
                    if (c < cols - 2) {
                        peeker = cells[r][c + 2];
                        if (peeker.getValue() == 0)
                            c += 2;
                    }
                }
            }
        }
        if (madeMove)
            spawn();
    }

    public void shiftUp () {
        boolean madeMove = false;
        for (int c = 0; c < cols; c++) {
            for (int r = 1; r < rows; r++) {
                Cell intruder = cells[r][c];
                Cell target = cells[r - 1][c];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    madeMove = true;
                    target.setValue(intruder.getValue());
                    intruder.setValue(0);
                    if (r > 1) {
                        peeker = cells[r - 2][c];
                        if (peeker.getValue() == 0)
                            r -= 2;
                    }
                }
            }
            for (int r = 1; r < rows; r++) {
                Cell intruder = cells[r][c];
                Cell target = cells[r - 1][c];
                if (intruder.getValue() == target.getValue() && target.getValue() > 0) {
                    sp.playSound("merge");
                    madeMove = true;
                    target.setValue(target.getValue() * 2);
                    intruder.setValue(0);
                    score += target.getValue();
                }
            }
            for (int r = 1; r < rows; r++) {
                Cell intruder = cells[r][c];
                Cell target = cells[r - 1][c];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    madeMove = true;
                    target.setValue(intruder.getValue());
                    intruder.setValue(0);
                    if (r > 1) {
                        peeker = cells[r - 2][c];
                        if (peeker.getValue() == 0)
                            r -= 2;
                    }
                }
            }
        }
        if (madeMove)
            spawn();
    }

    public void shiftDown() {
        boolean madeMove = false;
        for (int c = 0; c < cols; c++) {
            for (int r = rows - 2; r >= 0; r--) {
                Cell intruder = cells[r][c];
                Cell target = cells[r + 1][c];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    madeMove = true;
                    target.setValue(intruder.getValue());
                    intruder.setValue(0);
                    if (r < rows - 2) {
                        peeker = cells[r + 2][c];
                        if (peeker.getValue() == 0)
                            r += 2;
                    }
                }
            }
            for (int r = rows - 2; r >= 0; r--) {
                Cell intruder = cells[r][c];
                Cell target = cells[r + 1][c];
                if (intruder.getValue() == target.getValue() && target.getValue() > 0) {
                    sp.playSound("merge");
                    madeMove = true;
                    target.setValue(target.getValue() * 2);
                    intruder.setValue(0);
                    score += target.getValue();
                }
            }
            for (int r = cols - 2; r >= 0; r--) {
                Cell intruder = cells[r][c];
                Cell target = cells[r + 1][c];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    madeMove = true;
                    target.setValue(intruder.getValue());
                    intruder.setValue(0);
                    if (r < rows - 2) {
                        peeker = cells[r + 2][c];
                        if (peeker.getValue() == 0)
                            r += 2;
                    }
                }
            }
        }
        if (madeMove)
            spawn();
    }

    public void spawn() {
        for (int r = 0; r < cells.length; r++)
            for (int c = 0; c < cells[0].length; c++)
                if (cells[r][c].getValue() == 0) {
                    if (Math.random() < .125) {
                        cells[r][c].setValue(2);
                        cells[r][c].setNewGuy();
                        return;
                    }
                    if (Math.random() < .025) {
                        cells[r][c].setValue(4);
                        cells[r][c].setNewGuy();
                        return;
                    }
                }
        spawn();
    }

    public boolean deadCheck() {
        Cell[][] deadCells = cells.clone();
        shiftUp(deadCells);
        shiftLeft(deadCells);
        shiftRight(deadCells);
        shiftDown(deadCells);
        return cells == deadCells;
    }
}
