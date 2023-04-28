package com.go;

public class Board {
    private Stone[][] positions;

    public Board() {
        positions = new Stone[13][13];
    }

    public void addStone(Stone stone) {
        positions[stone.getX()][stone.getY()] = stone;
    }
}
