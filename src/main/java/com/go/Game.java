package com.go;

import java.awt.*;
import java.io.*;
import java.util.Objects;

interface IGame {
    void newGame();
    void saveGame();
    void move();
    void returnGame();
}

interface IBoardController {
    void initBoard();
}

public class Game implements IGame {
    //private static final double KOMI = 6.5; // Фора, то бишь количество компенсации белым
    private final Board board; // Состояние доски
    private Color currentPlayer;
    public Stone previousPlayer;
    private int numberOfGames = 0;

    // Конструктор
    public Game(Board board) {
        this.board = board;
        currentPlayer = Color.BLACK;
    }

    // Метод, который создает новую игру
    public void newGame() {
        board.clearBoard();
        currentPlayer = Color.BLACK;
    }

    @Override
    public void saveGame() {
        saveArrayToFile(board.positions);
    }

    @Override
    public void returnGame() {
        board.positions = loadArrayFromFile(numberOfGames);
    }

    @Override
    public void move() {
        if (currentPlayer == Color.BLACK)
            currentPlayer = Color.WHITE;
        else
            currentPlayer = Color.BLACK;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public Stone getPreviousPlayer () {
        return previousPlayer;
    }

    private void saveArrayToFile(Stone[][] arr) {
        try {
            numberOfGames++;
            BufferedWriter bw = new BufferedWriter(new FileWriter("SaveGame " + numberOfGames));
            bw.write(String.valueOf(arr.length));
            bw.newLine();
            bw.write(String.valueOf(arr[0].length));
            bw.newLine();
            for (Stone[] stones : arr) {
                for (int j = 0; j < arr[0].length; j++) {
                    if (!Objects.equals(String.valueOf(stones[j]), "null"))
                        bw.write(String.valueOf(stones[j].color()));
                    else
                        bw.write(String.valueOf(stones[j]));
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Stone[][] loadArrayFromFile(int numberOfGames) {
        Stone[][] savePositions;
        String fileName = "SaveGame" + numberOfGames;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // в первых 2-х строках файла цифры задают размерность массива
            int rows = Integer.parseInt(br.readLine());
            int cols = Integer.parseInt(br.readLine());
            savePositions = new Stone[rows][cols];
            for (int i = 0; i < savePositions.length; i++) {
                for (int j = 0; j < savePositions[i].length; j++) {
                    String line = br.readLine();
                    switch (line) {
                        case "null" -> savePositions[i][j] = null;
                        case "java.awt.Color[r=0,g=0,b=0]" -> {
                            Stone stone = new Stone(Color.BLACK, i, j);
                            savePositions[i][j] = stone;
                        }
                        case "java.awt.Color[r=255,g=255,b=255]" -> {
                            Stone stone = new Stone(Color.WHITE, i, j);
                            savePositions[i][j] = stone;
                        }
                    }
                }
            }
            return savePositions;
        } catch (IOException e) {
            e.printStackTrace();
            savePositions = new Stone[13][13];
            return savePositions;
        }
    }
}
