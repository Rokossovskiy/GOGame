package com.UI;

import com.go.Game;

class OpenGameListener implements GameButtonsControlPanel.OpenGameListener {

    private final Game game;

    OpenGameListener(Game game) {
        this.game = game;
    }

    @Override
    public void onOpenGameClick() {
        game.returnGame();
    }
}

