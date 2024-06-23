package org.example.Client.Views;

import org.example.Client.Models.Paddle;

import java.awt.*;

public class PaddleView {
    private Paddle paddle;

    public PaddleView(Paddle paddle) {
        this.paddle = paddle;
    }

    public void draw(Graphics g) {
        g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
    }
}
