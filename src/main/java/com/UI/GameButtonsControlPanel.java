package com.UI;

import com.go.Stone;

import javax.swing.*;
import java.awt.*;

class GameButtonsControlPanel extends JPanel {

    interface NewGameListener {
        void onNewGameClick();
    }

    interface SaveGameListener {
        void onSaveGameClick();
    }

    //interface RemoveStoneListener {
    //    void RemoveStoneListener();
    //}

    private NewGameListener newGameListener;
    private SaveGameListener saveGameListener;
    //private RemoveStoneListener removeStoneListener;

    public GameButtonsControlPanel(LayoutManager layoutManager) {
        super(layoutManager);
        setBackground(DisplayConfig.BACKGROUND_COLOR);

        JButton newGame = new JButton("Новая игра");
        JButton saveGame = new JButton("Сохранить игру");
        JButton openGame = new JButton("Открыть игру");
        JButton passButton = new JButton("Пасс");
        JButton cancelButton = new JButton("Отменить ход");
        JButton loveButton = new JButton("Разработчик топ");


        newGame.addActionListener(e -> {
            if (newGameListener != null) {
                newGameListener.onNewGameClick();
            }
        });

        saveGame.addActionListener(e -> {
            if (saveGameListener != null) {
                saveGameListener.onSaveGameClick();
            }
        });

//        cancelButton.addActionListener( e -> {
//            if (RemoveStoneListener != null) {
//                RemoveStoneListener.onRemoveStoneClick(Stone );
//            }
//        });

        add(newGame);
        add(saveGame);
        add(openGame);
        add(passButton);
        add(cancelButton);
        add(loveButton);
    }

    public void addNewGameListener(NewGameListener newGameListener) {
        this.newGameListener = newGameListener;
    }

    public void removeNewGameListener() {
        this.newGameListener = null;
    }

    public void addSaveGameListener(SaveGameListener saveGameListener) {
        this.saveGameListener = saveGameListener;
    }

//    public void removeSaveGameListener() {
//        this.removeStoneListener = null;
//    }
}
