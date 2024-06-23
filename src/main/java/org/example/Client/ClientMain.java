package org.example.Client;

import org.example.Client.Controllers.GameController;
import org.example.Client.Models.Ball;
import org.example.Client.Models.GameState;
import org.example.Client.Models.Paddle;
import org.example.Client.Models.Score;
import org.example.Client.Views.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
    public static void main(String[] args) {
        try {
            Paddle paddleLeft = new Paddle(0, 200-35, 10, 70);
            Paddle paddleRight = new Paddle(600 - 10, 200-35, 10, 70);
            Ball ball = new Ball(300, 200-5, 10);
            Score score = new Score(0, 0);
            GameState gameState = new GameState(ball, paddleLeft, paddleRight, score);
            Socket socket = new Socket("20.238.8.11", 2002);
//            Socket socket = new Socket("localhost", 2002);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            socket.isConnected();
            GamePanel gamePanel = new GamePanel(
                    600,
                    400,
                    new PaddleView(paddleLeft),
                    new PaddleView(paddleRight),
                    new BallView(ball),
                    new ScoreView(score)
            );
            GameView gameView = new GameView(gamePanel);
            GameController gameController = new GameController(
                    socket,
                    writer,
                    reader,
                    gameState,
                    gameView
            );
            gamePanel.addKeyListener(gameController);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IO Exception");
            throw new RuntimeException(e);
        }
    }

}