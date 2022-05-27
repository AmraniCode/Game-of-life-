package GUI;

import javax.swing.*;

public class Buttons{
    public JPanel mainPanel;
    private JButton pauseButton;
    private JButton playButton;
    private JButton resetButton;
    private JButton insertPatchManuelyButton;
    private JButton selectPatchButton;
    private JButton settingButton;
    private JButton loadWorldButton;

    public Buttons(int w , int h){
        this.mainPanel.setSize(w,h);
    }

    public JButton getPauseButton() {
        return pauseButton;
    }

    public JButton getLoadWorldButton(){
        return loadWorldButton;
    }
    public JButton getPlayButton() {
        return playButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public JButton getInsertPatchManuelyButton() {
        return insertPatchManuelyButton;
    }

    public JButton getSelectPatchButton() {
        return selectPatchButton;
    }

    public JButton getSettingButton() {
        return settingButton;
    }
}
