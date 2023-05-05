package com.UI;

import com.go.Board;
import com.go.Game;

import javax.swing.*;
import java.awt.*;

public class GameDisplay {

    private final static int BOARD_SIZE = 13;

    public static void main(String[] args) {
        Board board = new Board(BOARD_SIZE); // Создает игровое поле
        Game game = new Game(board); // Создает игру

        // Создает фрейм
        JFrame frame = new JFrame("StrateGO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(DisplayConfig.WINDOW_SIZE);
        frame.setMinimumSize(DisplayConfig.MIN_WINDOW_SIZE);

        // Создает панель с кнопками
        GamePanel gamePanel = new GamePanel(board, game);
        frame.getContentPane().add(gamePanel);
        GameButtonsControlPanel buttons = new GameButtonsControlPanel(new GridLayout(2, 3, 3, 0));
        buttons.addNewGameListener(gamePanel);
        buttons.addRemoveStoneListener(new RemoveStoneListener(game, board, gamePanel));
        buttons.addSaveGameListener(new SaveGameListener(game));

        GameButtonsControlContainerPanel buttonsContainer = new GameButtonsControlContainerPanel(new FlowLayout(FlowLayout.CENTER), buttons);
        frame.getContentPane().add(buttonsContainer, BorderLayout.NORTH);

        frame.pack(); // Устанавливает размеры фрейма в соответствии с размером содержимого
        frame.setVisible(true); // Делает фрейм видимым
        gamePanel.repaint(); // Рисует игровую панель
    }
}
