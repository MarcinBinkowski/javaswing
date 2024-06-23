package org.example.Client.Views;

import org.example.Client.Models.Ball;

import java.awt.*;

public class BallView {
    private Ball ball;

    public BallView(Ball ball) {
        this.ball = ball;
    }

    public void draw(Graphics g) {
        g.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
    }
}
