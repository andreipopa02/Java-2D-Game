package PaooGame.States;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.GUI.UserInterfaceButton;

import java.awt.*;
import java.util.ArrayList;

/*! \class public class LoseState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class LoseState extends State {
    private final ArrayList<UserInterfaceButton>  loseButtons;

    /*! \fn public LoseState(RefLinks refLink)
            \brief Constructorul de initializare al clasei.
            \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
         */
    public LoseState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        ///Initializare/adaugare lista de butoane din state.
        loseButtons  = new ArrayList<>();
        UserInterfaceButton restartButton = new UserInterfaceButton(430, 375, 100, 50, "Restart", font, refLink);
        UserInterfaceButton scoreButton    = new UserInterfaceButton(635, 320, 100, 50, "Score", font, refLink);
        loseButtons.add(restartButton);
        loseButtons.add(scoreButton);
    }

    /*! \fn public void Update()
            \brief Actualizeaza starea curenta a scorului.
         */
    @Override
    public void Update() throws LoadLevelException {
        ///Verificare apasare buton
        for (UserInterfaceButton button : loseButtons) {
            /// Verifica intersectia dintre buton si mouse.
            if (button.getBounds().contains(refLink.GetMouseHandler().GetXmouse(), refLink.GetMouseHandler().GetYmouse())) {
                button.SetPressed(refLink.GetMouseHandler().IsLeft());
            } else {
                button.SetPressed(false);
            }
        }
        if (loseButtons.get(0).isPressed()) {
            refLink.GetGame().RestartGame();
            refLink.GetGame().SetState(refLink.GetGame().GetPlayState());
        }
        if (loseButtons.get(1).isPressed()) {
            refLink.GetGame().SetState(refLink.GetGame().GetScoreState());
        }
    }

    /*! \fn public void Draw(Graphics g)
           \brief Deseneaza (randeaza) pe ecran starea curenta a scorului.
           \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
    */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.loseBackground, 0,0,18*64, 11*64, null);

        for (UserInterfaceButton button : loseButtons) {
            button.draw(g);
        }
    }




}
