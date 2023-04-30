package com.UI;

import com.go.Board;
import com.go.Stone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import static com.go.Game.BOARD_SIZE;

public class GameDisplay extends JPanel{

    private final Board board;

    //Конструктор
    public GameDisplay() {
        setPreferredSize(new Dimension(600, 600)); //Размер окна
        setBackground(new Color(226, 193, 144)); //Цвет фона
        enableEvents(AWTEvent.MOUSE_EVENT_MASK); //Считывает мышь
        board = new Board(BOARD_SIZE); // Создает игровое поле
    }

    // Создает окно по заданным параметрам, добавляет панель меню сверху
    public static void main(String[] args) {
        JFrame frame = new JFrame("GO-Strategy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int width = 600;
        int height = 600;
        Dimension size = new Dimension(width, height);
        frame.getContentPane().setPreferredSize(size);
        frame.setMinimumSize(new Dimension(500, 500));

        GameDisplay gameDisplay = new GameDisplay();
        frame.add(gameDisplay);

        JMenuBar mb = new JMenuBar();
        JMenu menu1 = new JMenu("Open");
        JMenu menu2 = new JMenu("Save");
        JMenu menu3 = new JMenu("New game");
        mb.add(menu1);
        mb.add(menu2);
        mb.add(menu3);
        frame.setJMenuBar(mb);
        frame.pack();
        frame.setVisible(true);
        gameDisplay.repaint();
    }

    //Рисует доску и потом камни
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int boardSize = BOARD_SIZE - 1;
        int cellSize = Math.min((getWidth() - 30 * 2) / boardSize, (getHeight() - 30 * 2) / boardSize);
        int xIndent = 30 + (getWidth() - 30 * 2 - cellSize * boardSize) / 2;
        int yIndent = 30 + (getHeight() - 30 * 2 - cellSize * boardSize) / 2;
        g.setColor(new Color(67, 74, 86));
        for (int i = 0; i <= boardSize; i++) {
            g.drawLine(xIndent + i * cellSize, yIndent, xIndent + i * cellSize, getHeight() - yIndent);
            g.drawLine(xIndent, yIndent + i * cellSize, getWidth() - xIndent, yIndent + i * cellSize);
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Stone stone = board.getPosition(i, j);
                if (stone != null) {
                    g.setColor(stone.getColor());
                    g.fillOval(xIndent + i * cellSize - cellSize / 2, yIndent + j * cellSize - cellSize / 2, cellSize, cellSize);
                }
            }
        }
    }

    //Считывает клики мыши и добавляет камень в массив
    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        int boardSize = BOARD_SIZE - 1;
        int cellSize = Math.min((getWidth() - 30 * 2) / boardSize, (getHeight() - 30 * 2) / boardSize);
        int xIndent = 30 + (getWidth() - 30 * 2 - cellSize * boardSize) / 2;
        int yIndent = 30 + (getHeight() - 30 * 2 - cellSize * boardSize) / 2;
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            int i = (int) ((float) (x - xIndent + cellSize / 2) / cellSize);
            int j = (int) ((float) (y - yIndent + cellSize / 2) / cellSize);
            if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE) {
                if (board.getPosition(i, j) == null) {
                    Stone stone = new Stone(Color.BLACK, i, j);
                    board.addStone(stone);
                    repaint();
                }
            }
        }
    }
}