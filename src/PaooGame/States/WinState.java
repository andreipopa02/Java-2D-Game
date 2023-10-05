package PaooGame.States;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Graphics.Assets;
import PaooGame.Items.ItemManager;

import PaooGame.RefLinks;
import PaooGame.GUI.UserInterfaceButton;


import java.awt.*;
import java.util.ArrayList;

/*! \class public class WinState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class WinState extends State {
    private final ArrayList<UserInterfaceButton>  winButtons;

    /*! \fn public WinState(RefLinks refLink)
      \brief Constructorul de initializare al clasei.
      \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
   */
    public WinState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        ///Initializare/adaugare lista de butoane din state.
        winButtons = new ArrayList<>();
        UserInterfaceButton replayButton = new UserInterfaceButton(430, 375, 100, 50, "Replay", font, refLink);
        UserInterfaceButton nextButton   = new UserInterfaceButton(635, 320, 100, 50, "Next Level", font, refLink);
        winButtons.add(replayButton);
        winButtons.add(nextButton);
    }

    /*! \fn public void Update()
       \brief Actualizeaza starea curenta a scorului.
    */
    @Override
    public void Update() throws LoadLevelException {
        ///Verificare apasare buton
        for (UserInterfaceButton button : winButtons) {
            /// Verifica intersectia dintre buton si mouse.
            if (button.getBounds().contains(refLink.GetMouseHandler().GetXmouse(), refLink.GetMouseHandler().GetYmouse())) {
                button.SetPressed(refLink.GetMouseHandler().IsLeft());
            } else {
                button.SetPressed(false);
            }
        }
        if (winButtons.get(0).isPressed()) {
            refLink.GetGame().RestartGame();
            refLink.GetGame().SetState(refLink.GetGame().GetPlayState());
        }
        if (winButtons.get(1).isPressed()) {
            refLink.GetGame().SetState(refLink.GetGame().GetPlayState());
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a scorului.
        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.winBackground, 0,0,18*64, 11*64, null);

        for (UserInterfaceButton button : winButtons) {
            button.draw(g);
        }
    }
}
