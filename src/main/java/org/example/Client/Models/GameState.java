package org.example.Client.Models;

public class GameState {
    private Ball ball;
    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Score score;

    public GameState(Ball ball, Paddle paddleLeft, Paddle paddleRight, Score score) {
        this.ball = ball;
        this.paddleLeft = paddleLeft;
        this.paddleRight = paddleRight;
        this.score = score;
    }

    public void updateGameState(String serializedGameState) {
        String[] parts = serializedGameState.split(",");
        int paddleLeftY = Integer.parseInt(parts[0]);
        int paddleRightY = Integer.parseInt(parts[1]);
        int ballX = Integer.parseInt(parts[2]);
        int ballY = Integer.parseInt(parts[3]);
        int scorePlayer1 = Integer.parseInt(parts[4]);
        int scorePlayer2 = Integer.parseInt(parts[5]);
        paddleLeft.setY(paddleLeftY);
        paddleRight.setY(paddleRightY);
        ball.setX(ballX);
        ball.setY(ballY);
        score.setScorePlayer1(scorePlayer1);
        score.setScorePlayer2(scorePlayer2);
    }

    public Ball getBall() {
        return ball;
    }
    public Paddle getPaddleLeft() {
        return paddleLeft;
    }
    public Paddle getPaddleRight() {
        return paddleRight;
    }
    public Score getScore() {
        return score;
    }
}
