package PaooGame.Levels;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Items.DinamicItems.Enemy_Silver;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;

import java.awt.*;

/*! \ public class Level1 extends Level
    \brief Implementeaza primul nivel al jocului.
 */
public class Level1 extends Level {
    private RefLinks        refLinks;       /*!< Referinta catre obiectul shortcut.*/
    private Map             map;            /*!< Referinta catre harta curenta.*/


    public Level1(RefLinks refLink, String path, int totalTime) throws LoadLevelException {
        super(refLink, path, totalTime);
        this.refLinks = refLink;
        ///Incarcare nivel
        LoadLevel(path);
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);

        ///Incarcare item-uri
        items.AddItem(new Enemy_Silver(refLink, 64*8  + 58,        58));
        items.AddItem(new Enemy_Silver(refLink, 64*12 + 58, 64*2 + 58));
        items.AddItem(new Enemy_Silver(refLink, 64*22 + 58,        58));
    }


    @Override
    public void LoadLevel(String path) {
        map = new Map(refLinks, path);
    }


    @Override
    public void Update() throws LoadLevelException {
        map.Update();
        items.Update();
    }

    @Override
    public void Draw(Graphics g) {
        map.Draw(g);
        items.Draw(g);
    }
}
