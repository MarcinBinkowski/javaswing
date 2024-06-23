package org.example.Client.Views;

import javax.swing.*;

public class GameView extends JFrame {
    private GamePanel gamePanel;

    public GameView(GamePanel gamePanel) {
        super("Pong");
        int height = 400;
        int width = 600;
        this.gamePanel = gamePanel;
        setSize(width, height);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);
        setVisible(true);
    }
    public void refreshUI() {
        getGamePanel().repaint();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}

