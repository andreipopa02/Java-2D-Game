package PaooGame.MenuBar;

import PaooGame.RefLinks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/*! \class public class InfoPanel extends JPanel
    \brief Implementeaza panoul de informatii care contine
                Time            Score           Lives
 */
public class InfoPanel extends JPanel {

    private final JLabel timeLabel;
    private final JLabel scoreLabel;
    private final JLabel livesLabel;

    public InfoPanel(RefLinks refLinks) {
        ///setez layout-ul grid pentru estetica (le aseaza grila)
        setLayout(new GridLayout());

        timeLabel = new JLabel();
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        scoreLabel = new JLabel();
        scoreLabel.setForeground(Color.BLACK);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);

        livesLabel = new JLabel();
        livesLabel.setForeground(Color.BLACK);
        livesLabel.setHorizontalAlignment(JLabel.CENTER);

        add(timeLabel);
        add(scoreLabel);
        add(livesLabel);

        setBackground(Color.orange);
        setPreferredSize(new Dimension(0, 20));
    }

    /*!
        \fn setters :)
     */
    public void setTime(int t) {
        timeLabel.setText("Time: " + t);
    }
    public void setLives(int t) { livesLabel.setText("Lives: " + t ); }
    public void setScore(int t) { scoreLabel.setText("Score: " + t); }

}
