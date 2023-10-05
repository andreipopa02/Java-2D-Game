package PaooGame.States;

import PaooGame.DataBase;
import PaooGame.Graphics.Assets;
import PaooGame.RefLinks;
import PaooGame.GUI.UserInterfaceButton;

import java.awt.*;
import java.util.ArrayList;

/*! \class public class AboutState extends State
    \brief Implementeaza notiunea de credentiale (about)
 */
public class ScoreState extends State {
    private final ArrayList<UserInterfaceButton> scoreButtons;

    /*! \fn public ScoreState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.
        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public ScoreState(RefLinks refLink) {
        ///Apel al constructorului clasei de baza.
        super(refLink);
        ///Initializare/adaugare lista de butoane din state.
        scoreButtons = new ArrayList<>();
        UserInterfaceButton backButton   = new UserInterfaceButton(990, 570, 100, 50, "Back", font, refLink);
        scoreButtons.add(backButton);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a scorului.
     */
    @Override
    public void Update() {
        ///Verificare apasare buton
        for (UserInterfaceButton button : scoreButtons) {
            /// Verifica intersectia dintre buton si mouse.
            if (button.getBounds().contains(refLink.GetMouseHandler().GetXmouse(), refLink.GetMouseHandler().GetYmouse())) {
                button.SetPressed(refLink.GetMouseHandler().IsLeft());
            } else {
                button.SetPressed(false);
            }
        }

        if(scoreButtons.get(0).isPressed())
            ///daca starea anterioara a fost PlayState -> du-te in PauseState
            if(refLink.GetGame().GetPreviousState() == refLink.GetGame().GetPlayState())
                refLink.GetGame().SetState(refLink.GetGame().GetPauseState());
            ///daca starea anterioara a fost LoseState -> du-te in LoseState
            else if(refLink.GetGame().GetPreviousState() == refLink.GetGame().GetLoseState())
                refLink.GetGame().SetState(refLink.GetGame().GetLoseState());
            ///daca starea anterioara a fost oricare alta -> du-te in MenuState
            else
                refLink.GetGame().SetState(refLink.GetGame().GetMenuState());

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a scorului.
        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.scoreBackground, 0,0,18*64, 11*64, null);
        for (UserInterfaceButton button : scoreButtons) {
            button.draw(g);
        }
        DataBase.GetInstance().Draw(g);
    }
}
