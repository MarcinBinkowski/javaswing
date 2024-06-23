package org.example.Server.Workers;


import org.example.Server.Models.Player;
import org.example.Server.Models.GameState;
import org.example.Helpers.MessageHelper;

import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameWorker extends Thread {
    private Player player1;
    private Player player2;
    private final GameState gameState;
    private final ExecutorService executorService;
    private Timer timer;


    public  GameWorker(Player player1, Player player2, GameState gameState) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameState = gameState;
        this.executorService = Executors.newFixedThreadPool(2);
        String messagePlayer1 = new MessageHelper(MessageHelper.START_GAME, "left").serialize();
        String messagePlayer2 = new MessageHelper(MessageHelper.START_GAME, "right").serialize();
        player1.sentMessage(messagePlayer1);
        player2.sentMessage(messagePlayer2);

    }

    @Override
    public void run() {
        System.out.println("Game worker started");
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sendGameState();
                gameState.updateBallPosition();
            }
        }, 0, 35);
        executorService.execute(() -> processPlayer(player1));
        executorService.execute(() -> processPlayer(player2));
    }


        private void processPlayer(Player player) {
        while (true) {
            String message;
            try {
                message = player.getClient().getReader().readLine();
            } catch (IOException e) {
                System.out.println("Error while reading message from player");
                return;
            }
            if (message != null) {
                MessageHelper messageHelper = new MessageHelper(message);
                if (Objects.equals(messageHelper.getType(), MessageHelper.GAME_STATE_PLAYER)) {
                    gameState.updateGameState(messageHelper.getMessage());
                }
            }
        }
    }

    public String getGameState() {
        return gameState.serialize();
    }

    private void sendGameState() {
        String gameStateString = getGameState();
        String message = new MessageHelper(MessageHelper.GAME_STATE, gameStateString).serialize();
        player1.sendGameState(message);
        player2.sendGameState(message);
    }
    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    
}