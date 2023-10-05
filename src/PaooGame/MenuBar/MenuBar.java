package PaooGame.MenuBar;

import PaooGame.RefLinks;

import javax.swing.*;

/*! \ public class MenuBar extends JMenuBar
    \brief Implementeaza Bar meniul jocului
 */
public class MenuBar extends JMenuBar {

    public MenuBar(RefLinks refLinks) {
        GameMenu gameMenu       = new GameMenu(refLinks);
        HelpMenu helpMenu       = new HelpMenu();

        this.add(gameMenu);
        this.add(helpMenu);
    }


}
