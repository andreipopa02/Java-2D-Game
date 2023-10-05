package PaooGame.Levels;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Items.DinamicItems.Enemy_Silver;
import PaooGame.Items.DinamicItems.Enemy_Gold;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;

import java.awt.*;
/*! \ public class Level1 extends Level
    \brief Implementeaza al doilea nivel al jocului.
 */
public class Level2 extends Level {
    private RefLinks        refLinks;       /*!< Referinta catre obiectul shortcut.*/
    private Map             map;            /*!< Referinta catre harta curenta.*/


    public Level2(RefLinks refLink, String path, int totalTime) throws LoadLevelException {
        super(refLink, path, totalTime);
        this.refLinks = refLink;
        LoadLevel(path);
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
        items.AddItem(new Enemy_Silver(refLink, 64    + 58, 64*17 + 58));
        items.AddItem(new Enemy_Silver(refLink, 64*27 + 58, 64*17 + 58));
        items.AddItem(new Enemy_Silver(refLink, 64*20 + 58, 64*13 + 58));
        items.AddItem(new Enemy_Silver(refLink, 64*3  + 58, 64*6  + 58));
        items.AddItem(new Enemy_Gold(refLink, 64*10 + 58, 64*14 + 58));
        items.AddItem(new Enemy_Gold(refLink, 64*27 + 58,         58));
        items.AddItem(new Enemy_Gold(refLink, 64*17 + 58, 64*14 + 58));

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
