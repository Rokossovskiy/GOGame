package com.go;

import javax.swing.*;

interface IBoard {
    void addStone(Stone stone);
    void removeStone(Stone stone);
    void clear();
}

public class Board implements IBoard {

    private final Stone[][] positions;

    public Board(int boardSize) {
        positions = new Stone[boardSize][boardSize];
    }

    public void addStone(Stone stone) {
        int x = stone.getX();
        int y = stone.getY();
        if (positions[x][y] != null)
            JOptionPane.showMessageDialog(null, "Недопустимый ход!", "Опа !", JOptionPane.ERROR_MESSAGE);
        else positions[x][y] = stone;
    }

    @Override
    public void removeStone(Stone stone) {
        int x = stone.getX();
        int y = stone.getY();
        if (positions[x][y] == stone) {
            positions[x][y] = null;
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions.length; j++) {
                positions[i][j] = null;
            }
        }
    }

    public Stone getPosition(int x, int y) {
        return positions[x][y];
    }


}
