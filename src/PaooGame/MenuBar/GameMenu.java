package PaooGame.MenuBar;

import PaooGame.RefLinks;

import javax.swing.*;

/*! \class public class GameMenu extends JMenu
    \brief Implementeaza butoanele meniului Game Menu
 */
public class GameMenu extends JMenu {

    public GameMenu(RefLinks refLinks){
        super("Game");

        JMenuItem pause = new JMenuItem("Pause");
        pause.addActionListener(e -> {
            if(refLinks.GetGame().GetState() == refLinks.GetGame().GetPlayState())
                refLinks.GetGame().SetState(refLinks.GetGame().GetPauseState());
        });
        add(pause);

        JMenuItem resume = new JMenuItem("Resume");
        resume.addActionListener(e -> {
            if(refLinks.GetGame().GetState() == refLinks.GetGame().GetPauseState())
                refLinks.GetGame().SetState(refLinks.GetGame().GetPlayState());
        });
        add(resume);

        JMenuItem topScores = new JMenuItem("Top Scores");
        topScores.addActionListener(e -> {
            refLinks.GetGame().SetState(refLinks.GetGame().GetScoreState());
        });
        add(topScores);    }
}
