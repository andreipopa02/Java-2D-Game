package PaooGame.States;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.GUI.UserInterfaceButton;

import java.awt.*;
import java.util.ArrayList;

/*! \class public class PauseState extends State
    \brief Implementeaza notiunea de pauza pentru joc.
 */
public class PauseState extends State {

    private final ArrayList<UserInterfaceButton> pauseButtons;

    /*! \fn public PauseState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PauseState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        ///Initializare/adaugare lista de butoane din state.
        pauseButtons = new ArrayList<>();
        UserInterfaceButton resumeButton = new UserInterfaceButton(425, 405, 100, 50, "Resume", font, refLink);
        UserInterfaceButton menuButton = new UserInterfaceButton(610, 325, 100, 50, "Menu", font, refLink);
        pauseButtons.add(resumeButton);
        pauseButtons.add(menuButton);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update() throws LoadLevelException {
        ///Verificare apasare buton
        for (UserInterfaceButton button : pauseButtons) {
            /// Verifica intersectia dintre buton si mouse.
            if (button.getBounds().contains(refLink.GetMouseHandler().GetXmouse(), refLink.GetMouseHandler().GetYmouse())) {
                button.SetPressed(refLink.GetMouseHandler().IsLeft());
            } else {
                button.SetPressed(false);
            }
        }
        if (pauseButtons.get(0).isPressed())
            refLink.GetGame().SetState(refLink.GetGame().GetPlayState());
        if (pauseButtons.get(1).isPressed()) {
            refLink.GetGame().RestartGame();
            refLink.GetGame().SetState(refLink.GetGame().GetMenuState());
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.pauseBackground, 0, 0, 18 * 64, 11 * 64, null);
        for (UserInterfaceButton button : pauseButtons) {
            button.draw(g);
        }
    }
}
