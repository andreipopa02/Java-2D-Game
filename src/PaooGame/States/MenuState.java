package PaooGame.States;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.GUI.UserInterfaceButton;

import java.awt.*;
import java.util.ArrayList;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State {
    private final ArrayList<UserInterfaceButton> menuButtons;

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        ///Initializare/adaugare lista de butoane din state.
        menuButtons = new ArrayList<>();
        UserInterfaceButton playButton   = new UserInterfaceButton(360, 320, 100, 50, "Play", font, refLink);
        UserInterfaceButton scoreButton  = new UserInterfaceButton(563, 410, 100, 50, "Score", font, refLink);
        UserInterfaceButton exitButton   = new UserInterfaceButton(738, 330, 100, 50, "Exit", font, refLink);
        menuButtons.add(playButton);
        menuButtons.add(scoreButton);
        menuButtons.add(exitButton);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update() throws LoadLevelException {
        ///Verificare apasare buton
        for (UserInterfaceButton button : menuButtons) {
            /// Verifica intersectia dintre buton si mouse.
            if (button.getBounds().contains(refLink.GetMouseHandler().GetXmouse(), refLink.GetMouseHandler().GetYmouse())) {
                button.SetPressed(refLink.GetMouseHandler().IsLeft());
            } else {
                button.SetPressed(false);
            }
        }
        if(menuButtons.get(0).isPressed()) {

            refLink.GetGame().SetState(refLink.GetGame().GetPlayState());
        }
        if(menuButtons.get(1).isPressed()) {
            refLink.GetGame().SetState(refLink.GetGame().GetScoreState());
        }
        if(menuButtons.get(2).isPressed()) {
            System.exit(0);
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        ///Desenarea imaginii de background
        g.drawImage(Assets.menuBackground, 0,0,18*64, 11*64, null);
        for (UserInterfaceButton button : menuButtons) {
            button.draw(g);
        }
    }

}
