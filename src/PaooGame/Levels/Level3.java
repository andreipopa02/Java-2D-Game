package PaooGame.Levels;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Items.DinamicItems.*;
import PaooGame.Maps.Map;
import PaooGame.RefLinks;

import java.awt.*;

/*! \ public class Level1 extends Level
    \brief Implementeaza al treilea nivel al jocului.
 */
public class Level3 extends Level {
    private RefLinks        refLinks;       /*!< Referinta catre obiectul shortcut.*/
    private Map             map;            /*!< Referinta catre harta curenta.*/


    public Level3(RefLinks refLink, String path, int totalTime) throws LoadLevelException {
        super(refLink, path, totalTime);
        this.refLinks = refLink;
        LoadLevel(path);

        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
        items.GetHero().SetSpeed(4.0f);

        items.AddItem(new Enemy_Silver(refLink, 64*18 + 58, 64*14 + 58));
        items.AddItem(new Enemy_Silver(refLink, 64*2  + 58, 64*6  + 58));
        items.AddItem(new Enemy_Silver(refLink, 64*12 + 58, 64*13 + 58));
        items.AddItem(new Enemy_Silver(refLink, 64*10 + 58, 64*17 + 58));
        items.AddItem(new Enemy_Silver(refLink, 64*18 + 58, 64*17 + 58));

        items.AddItem(new Enemy_Gold(refLink, 64*27 + 58,          58));
        items.AddItem(new Enemy_Gold(refLink, 64*22 + 58,  64*5  + 58));
        items.AddItem(new Enemy_Gold(refLink, 64*38 + 58,  64*2  + 58));
        items.AddItem(new Enemy_Gold(refLink, 64*38 + 58,  64*15 + 58));
        items.AddItem(new Enemy_Gold(refLink, 64*45 + 58,  64*17+  58));

        items.AddItem(new Enemy_Boss(refLink, 64*11 + 58, 64*20 + 58));
        items.AddItem(new Enemy_Boss(refLink, 64*24 + 58, 64*24 + 58));
        items.AddItem(new Enemy_Boss(refLink, 64*48 + 58, 64*37 + 58));
        items.AddItem(new Enemy_Boss(refLink, 64*7  + 58, 64*38 + 58));
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
