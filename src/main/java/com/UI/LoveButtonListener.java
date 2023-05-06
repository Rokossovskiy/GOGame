package com.UI;

import javax.swing.*;

public class LoveButtonListener implements GameButtonsControlPanel.LoveButtonListener {

    LoveButtonListener() {
    }

    @Override
    public void onLoveButtonClick() {
        JOptionPane.showMessageDialog(null, "В скором времени тут появятся мои реквизиты )", "Благодарю!", JOptionPane.INFORMATION_MESSAGE);
    }
}
