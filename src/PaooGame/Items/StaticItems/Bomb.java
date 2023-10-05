package PaooGame.Items.StaticItems;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*! \ public class Bomb extends Item
    \brief Obiectul de tip bomba.
 */
public class Bomb extends Item {
    private BufferedImage   image;                      /*!< Referinta catre imaginea curenta a bombei.*/
    public static final int DEFAULT_BOMB_WIDTH  = 48;   /*!< Latimea implicita a imaginii BOMBEI.*/
    public static final int DEFAULT_BOMB_HEIGHT = 48;   /*!< Inaltimea implicita a imaginii BOMBEI.*/
    private boolean         exploded       = false;
    private int             bombCounter  = 0;

    public Bomb(RefLinks refLink, float x, float y) {
        super(refLink, x, y, DEFAULT_BOMB_WIDTH, DEFAULT_BOMB_HEIGHT);
        image = Assets.bombExplosion[0];
        isDead = false;
        normalBounds.x = -8;
        normalBounds.y = -8;
        normalBounds.width = 64;
        normalBounds.height = 64;
    }

    private void Animation() {
        int explosionImage = bombCounter / 15;                         // schimba imaginea la fiecare 15 cicli
        if (explosionImage == Assets.bombExplosion.length) {           // seteaza exploded la true cand a trecut timpul de afisare a tuturor imaginilor de explozie
            exploded = true;
            image = null;
            isDead = true;
        }
        else {
            image = Assets.bombExplosion[explosionImage];
            bombCounter++;
        }
    }

    @Override
    public void Update() {
        Animation();
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int) (x - refLink.GetGame().GetCamera().GetxOffset()),
                           (int) (y - refLink.GetGame().GetCamera().GetyOffset()),
                           width, height, null);
    /*
        g.setColor(Color.blue);
        g.drawRect((int)(x - refLink.GetGame().GetCamera().GetxOffset() + normalBounds.x),
                (int)(y - refLink.GetGame().GetCamera().GetyOffset() + normalBounds.y),
                normalBounds.width, normalBounds.height);
    */
    }

    public boolean GetExploded() { return exploded; }
    public void SetExploded(boolean exploded) { this.exploded = exploded; }

}
