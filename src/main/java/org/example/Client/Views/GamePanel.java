package org.example.Client.Views;
import javax.swing.*;
import java.awt.*;

public class GamePanel   extends  JPanel {
    private int width;
    private int height;
    private PaddleView paddleLeftView;
    private PaddleView paddleRightView;
    private BallView ballView;
    private ScoreView scoreView;

    public PaddleView getPaddleLeftView() {
        return paddleLeftView;
    }

    public PaddleView getPaddleRightView() {
        return paddleRightView;
    }

    public BallView getBallView() {
        return ballView;
    }

    public ScoreView getScoreView() {
        return scoreView;
    }


    public GamePanel(
            int widtdh,
            int height,
            PaddleView paddleLeftView,
            PaddleView paddleRightView,
            BallView ballView,
            ScoreView scoreView
    ) {
        this.width = widtdh;
        this.height = height;
        this.paddleLeftView = paddleLeftView;
        this.paddleRightView = paddleRightView;
        this.ballView = ballView;
        this.scoreView = scoreView;

        setBounds(0, 0, width, height);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(width / 2, 0, width / 2, height);
        paddleLeftView.draw(g);
        paddleRightView.draw(g);
        ballView.draw(g);
        scoreView.draw(g);
    }
}
