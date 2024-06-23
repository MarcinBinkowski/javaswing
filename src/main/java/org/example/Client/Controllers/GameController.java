package org.example.Client.Controllers;


import org.example.Client.Models.GameState;
import org.example.Client.Views.GameView;
import org.example.Helpers.MessageHelper;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameController implements KeyListener {
    private static AtomicBoolean isGameStarted = new AtomicBoolean(false);
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private GameState gameState;
    private GameView gameView;
    private int dy;
    private String position;

    public GameController(
            Socket socket,
            PrintWriter writer,
            BufferedReader reader,
            GameState gameState,
            GameView gameView
    ) {
        this.socket = socket;
        this.writer = writer;
        this.reader = reader;
        this.gameState = gameState;
        this.gameView = gameView;
        this.dy = 0;
        gameView.addKeyListener(this);
        gameView.setFocusable(true);
        gameView.requestFocusInWindow();
        System.out.println("Game controller created");
        Timer timer = new Timer(35, e -> {
            sendPaddleMove();
        });
        timer.start();
        new Thread(this::updateGame).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            setDy(-5);
        } else if (key == KeyEvent.VK_S) {
            setDy(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
            setDy(0);
        }
    }

    public void updateGame() {
        while (true){
            MessageHelper message = null;
            try {
                message = MessageHelper.deserialize(reader.readLine());
            } catch (Exception e) {
                System.out.println("Error while connecting to server");
            }
            if (message != null) {
                if (message.getType().equals(MessageHelper.GAME_STATE)){
                    gameState.updateGameState(message.getMessage());
                    gameView.refreshUI();
                } else if (message.getType().equals(MessageHelper.START_GAME)) {
                    setPosition(message.getMessage());
                    isGameStarted.set(true);
                }
            }
        }

    }
    public void sendPaddleMove(){
//        if (isGameStarted.get()){
            MessageHelper message = new MessageHelper(MessageHelper.GAME_STATE_PLAYER, getPosition() + "," + dy);
            writer.println(message.serialize());
//        }
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}