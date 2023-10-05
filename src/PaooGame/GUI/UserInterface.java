package PaooGame.GUI;

import PaooGame.RefLinks;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class UserInterface {
    private boolean menuS, pauseS, scoreS;
    private RefLinks refLinks;
    private ArrayList<UserInterfaceButton> menuButtons;
    private ArrayList<UserInterfaceButton> pauseButtons;
    private ArrayList<UserInterfaceButton> scoreButtons;

    public UserInterface(RefLinks refLinks) {
        menuButtons = new ArrayList<>();
        pauseButtons = new ArrayList<>();

        this.refLinks = refLinks;

        // Adaug butoanele Play, Score si Exit
        Font font = new Font("Times New Roman", Font.BOLD, 30);
        UserInterfaceButton playButton   = new UserInterfaceButton(360, 320, 100, 50, "Play", font, refLinks);
        UserInterfaceButton scoreButton  = new UserInterfaceButton(563, 410, 100, 50, "Score", font, refLinks);
        UserInterfaceButton exitButton   = new UserInterfaceButton(738, 330, 100, 50, "Exit", font, refLinks);
        UserInterfaceButton resumeButton = new UserInterfaceButton(360, 320, 100, 50, "Resume", font, refLinks);
        UserInterfaceButton backButton   = new UserInterfaceButton(360, 320, 100, 50, "Back", font, refLinks);

        menuButtons.add(playButton);
        menuButtons.add(scoreButton);
        menuButtons.add(exitButton);

        pauseButtons.add(resumeButton);
        pauseButtons.add(scoreButton);
        pauseButtons.add(exitButton);

        scoreButtons.add(backButton);
        scoreButtons.add(exitButton);
    }

    public void Update() {
        if(menuS) {
            for (UserInterfaceButton button : menuButtons) {
                if (button.getBounds().contains(refLinks.GetMouseHandler().GetXmouse(), refLinks.GetMouseHandler().GetYmouse())) {
                    button.SetPressed(refLinks.GetMouseHandler().IsLeft());
                } else {
                    button.SetPressed(false);
                }
            }
        }
        if(pauseS){
            for (UserInterfaceButton button : pauseButtons) {
                if (button.getBounds().contains(refLinks.GetMouseHandler().GetXmouse(), refLinks.GetMouseHandler().GetYmouse())) {
                    button.SetPressed(refLinks.GetMouseHandler().IsLeft());
                } else {
                    button.SetPressed(false);
                }
            }
        }
        if(scoreS){
            for (UserInterfaceButton button : scoreButtons) {
                if (button.getBounds().contains(refLinks.GetMouseHandler().GetXmouse(), refLinks.GetMouseHandler().GetYmouse())) {
                    button.SetPressed(refLinks.GetMouseHandler().IsLeft());
                } else {
                    button.SetPressed(false);
                }
            }
        }
    }

    public void Draw(Graphics g) {
        // Desenam toate butoanele
        if(menuS)
            for (UserInterfaceButton button : menuButtons) {
                button.draw(g);
            }
        if(pauseS)
            for (UserInterfaceButton button : pauseButtons) {
                button.draw(g);
            }
        if(scoreS)
            for (UserInterfaceButton button : scoreButtons) {
                button.draw(g);
            }
    }

    public boolean PlayButtonClicked() {
        for (UserInterfaceButton button : menuButtons) {
            if (button.GetText().equals("Play")
                    && button.getBounds().contains(refLinks.GetMouseHandler().GetXmouse(), refLinks.GetMouseHandler().GetYmouse())
                    && refLinks.GetMouseHandler().IsLeft()) {
                return true;
            }
        }
        return false;
    }

    public boolean ScoreButtonClicked() {
        for (UserInterfaceButton button : menuButtons) {
            if (button.GetText().equals("Score")
                    && button.getBounds().contains(refLinks.GetMouseHandler().GetXmouse(), refLinks.GetMouseHandler().GetYmouse())
                    && refLinks.GetMouseHandler().IsLeft()) {
                return true;
            }
        }
        return false;
    }

    public boolean ExitButtonClicked() {
        for (UserInterfaceButton button : menuButtons) {
            if (button.GetText().equals("Exit")
                    && button.getBounds().contains(refLinks.GetMouseHandler().GetXmouse(), refLinks.GetMouseHandler().GetYmouse())
                    && refLinks.GetMouseHandler().IsLeft()) {
                return true;
            }
        }
        return false;
    }

    public UserInterfaceButton getPlayButton() {
        return menuButtons.get(0);
    }
    public UserInterfaceButton getScoreButton() {
        return menuButtons.get(1);
    }
    public UserInterfaceButton getExitButton() {
        return menuButtons.get(2);
    }


    public ArrayList getButtonsList(){ return menuButtons; }

}
