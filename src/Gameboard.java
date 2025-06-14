
import java.awt.*;

public class Gameboard {

    public Cell[][] thecells;
    private final int rows = 4, cols = 4;
    public int score = 0;
    SoundPlayer sp = new SoundPlayer();
    private boolean Lmade = false, Rmade = false, Umade = false, Dmade = false;

    public Gameboard() {
        sp.addSound("merge", "./audios/dbButtonClickFlag.wav");
        sp.addSound("start", "./audios/firstPaint.wav");
        sp.addSound("win", "./audios/win.wav");
        thecells = new Cell[4][4];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                thecells[i][j] = new Cell(i, j);
            }
        //sp.playSound("start");
        startSpawn();

    }

    public void startSpawn() {
        int twoC = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (Math.random() < .1875) {
                    thecells[i][j].setValue(2);
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
                thecells[r][c].draw(g2);
    }

    public void shiftLeft(Cell[][] cells) {
        for (int r = 0; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                Cell intruder = cells[r][c];
                Cell target = cells[r][c - 1];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    Lmade = true;
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
                    Lmade = true;
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
                    Lmade = true;
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
    }

    public void shiftRight(Cell[][] cells) {
        for (int r = 0; r < rows; r++) {
            for (int c = cols - 2; c >= 0; c--) {
                Cell intruder = cells[r][c];
                Cell target = cells[r][c + 1];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    Rmade = true;
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
                    Rmade = true;
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
                    Rmade = true;
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
    }

    public void shiftUp (Cell[][] cells) {
        for (int c = 0; c < cols; c++) {
            for (int r = 1; r < rows; r++) {
                Cell intruder = cells[r][c];
                Cell target = cells[r - 1][c];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    Umade = true;
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
                    Umade = true;
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
                    Umade = true;
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
    }

    public void shiftDown(Cell[][] cells) {
        for (int c = 0; c < cols; c++) {
            for (int r = rows - 2; r >= 0; r--) {
                Cell intruder = cells[r][c];
                Cell target = cells[r + 1][c];
                Cell peeker;
                while (target.getValue() == 0 && intruder.getValue() > 0) {
                    Dmade = true;
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
                    Dmade = true;
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
                    Dmade = true;
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
    }

    public void spawn() {
        for (int r = 0; r < thecells.length; r++)
            for (int c = 0; c < thecells[0].length; c++)
                if (thecells[r][c].getValue() == 0) {
                    if (Math.random() < .125) {
                        thecells[r][c].setValue(2);
                        thecells[r][c].setNewGuy();
                        return;
                    }
                    if (Math.random() < .025) {
                        thecells[r][c].setValue(4);
                        thecells[r][c].setNewGuy();
                        return;
                    }
                }
        spawn();
    }

    public boolean checkMoved() {
        boolean buffoon = Lmade || Rmade || Umade || Dmade;
        Lmade = false;
        Rmade = false;
        Umade = false;
        Dmade = false;
        return buffoon;
    }

    public boolean deadCheck() {
        Cell[][] deadCells = thecells.clone();
        shiftLeft(deadCells);
        shiftRight(deadCells);
        shiftUp(deadCells);
        shiftDown(deadCells);
        return thecells == deadCells;
    }
}
