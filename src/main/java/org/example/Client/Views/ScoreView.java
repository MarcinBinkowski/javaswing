package org.example.Client.Views;

import org.example.Client.Models.Score;

import javax.swing.*;
import java.awt.*;

public class ScoreView {
    private final Score score;
    private final JLabel label;

    public ScoreView(Score score) {
        this.score = score;
        this.label = new JLabel(score.getScorePlayer1() + " : " + score.getScorePlayer2());
    }

    public void update() {
        label.setText(score.getScorePlayer1() + "         " + score.getScorePlayer2());
    }
    public JLabel getLabel() {
        return label;
    }
    public void draw(Graphics g)
    {
        update();
        g.drawString(label.getText(), 275, 30);
    }
}
