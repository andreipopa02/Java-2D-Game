package PaooGame;

import PaooGame.Input.MouseHandler;
import PaooGame.Items.ItemManager;
import PaooGame.Maps.Map;

import PaooGame.Input.KeyHandler;

/*! \class public class RefLinks
    \brief Clasa ce retine o serie de referinte ale unor elemente pentru a fi usor accesibile.

    Altfel ar trebui ca functiile respective sa aiba o serie intreaga de parametri si ar ingreuna programarea.
 */

public class RefLinks {
    private Game            game;           /*!< Referinta catre obiectul Game.*/
    private Map             map;            /*!< Referinta catre harta curenta.*/
    private ItemManager     itemManager;    /*!< Referinta catre obiectul ItemManager*/


    public RefLinks(Game game)
    {
        this.game = game;
    }

    public KeyHandler GetKeyHandler() { return game.GetKeyHandler(); }
    public MouseHandler GetMouseHandler() { return game.GetMouseHandler(); }

    public int GetWidth()
    {
        return game.GetWidth();
    }
    public int GetHeight()
    {
        return game.GetHeight();
    }


    public Game GetGame() { return game; }
    public Map GetMap() { return map; }
    public ItemManager GetItemManager() { return game.GetItemManager(); }

    public void SetGame(Game game)
    {
        this.game = game;
    }
    public void SetMap(Map map) { this.map = map; }
    public void SetItemManager(ItemManager itemManager) { this.itemManager = itemManager; }
}
