package org.example.Server.Models;

import java.util.Objects;
import java.util.Random;

public class GameState {
    public static final int PLAYER1_PADDLE_X = 0;
    public static final int PLAYER2_PADDLE_X = 590;
    public static final int PADDLE_WIDTH = 10;
    public static final int PADDLE_HEIGHT = 70;
    public static final int STARTING_BALL_X = 300;
    public static final int STARTING_BALL_Y = 195;

    private int player1PaddleY;
    private int player2PaddleY;
    private int player1Score;
    private int player2Score;
    private int ballX;
    private int ballY;
    private int ballDX;
    private int ballDY;

    public GameState(){
        this(165, 165, 0, 0);
    }



    public GameState(int player1PaddleY, int player2PaddleY, int player1Score, int player2Score) {
        this.player1PaddleY = player1PaddleY;
        this.player2PaddleY = player2PaddleY;
        this.player1Score = player1Score;
        this.player2Score = player2Score;
        resetBall();

    }

    private void resetBall() {
        Random random = new Random();
        ballX = STARTING_BALL_X;
        ballY = STARTING_BALL_Y;
        setBallDX(random.nextBoolean() ? 5 : -5);
        setBallDY(random.nextInt(11) - 5);
    }

    public void updateBallPosition() {
        int nextX = getBallX() + getBallDX();
        int nextY = getBallY() + getBallDY();
        if (nextX <= PLAYER1_PADDLE_X + PADDLE_WIDTH && nextY >= getPlayer1PaddleY() && nextY <= getPlayer1PaddleY() + PADDLE_HEIGHT) {
            setBallDX(-ballDX);
            int hitFactor = (int) Math.round(((nextY - getPlayer1PaddleY()) / (double) PADDLE_HEIGHT - 0.5) * 6);
            setBallDY(getBallDY() + hitFactor);
            if (Math.abs(getBallDY()) > 10) {
                setBallDY(getBallDY() > 0 ? 10 : -10);
            }
        } else if (nextX >= PLAYER2_PADDLE_X - PADDLE_WIDTH && nextY >= getPlayer2PaddleY() && nextY <= getPlayer2PaddleY() + PADDLE_HEIGHT) {
            setBallDX(-ballDX);
            int hitFactor = (int) Math.round(((nextY - getPlayer2PaddleY()) / (double) PADDLE_HEIGHT - 0.5) * 6);
            setBallDY(getBallDY() + hitFactor);
            if (Math.abs(getBallDY()) > 10) {
                setBallDY(getBallDY() > 0 ? 10 : -10);
            }
        } else if (nextX < 0) {
            setPlayer2Score(getPlayer2Score() + 1);
            resetBall();
        } else if (nextX >= 600) {
            setPlayer1Score(getPlayer1Score() + 1);
            resetBall();
        }
        if (nextY <= 0 || nextY >= 365) {
            ballDY = -ballDY;
        }
        ballX += ballDX;
        ballY += ballDY;
    }

    public String serialize() {
        return getPlayer1PaddleY() + "," + getPlayer2PaddleY() + "," + getBallX() + "," + getBallY() + ',' + getPlayer1Score() + "," + getPlayer2Score();
    }

    public void updateGameState(String serializedPlayerPosition) {
        String[] parts = serializedPlayerPosition.split(",");
        String playerSide = parts[0];
        if (Objects.equals(playerSide, "left")) {
            this.player1PaddleY += Integer.parseInt(parts[1]);
        } else {
            this.player2PaddleY += Integer.parseInt(parts[1]);
        }
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public int getPlayer1PaddleY() {
        return player1PaddleY;
    }

    public void setPlayer1PaddleY(int player1PaddleY) {
        this.player1PaddleY = player1PaddleY;
    }

    public int getPlayer2PaddleY() {
        return player2PaddleY;
    }

    public void setPlayer2PaddleY(int player2PaddleY) {
        this.player2PaddleY = player2PaddleY;
    }

    public int getBallX() {
        return ballX;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public int getBallY() {
        return ballY;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
    }

    public void setBallDX(int ballDX) {
        this.ballDX = ballDX;
    }

    public int getBallDX() {
        return ballDX;
    }

    public int getBallDY() {
        return ballDY;
    }

    public void setBallDY(int ballDY) {
        this.ballDY = ballDY;
    }
}