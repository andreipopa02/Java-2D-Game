package PaooGame.Items.StaticItems;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Item;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \ public class Flames extends Item
    \brief Obiectul de tip flacara/foc.
 */
public class Flames extends Item {

    public BufferedImage   image;                       /*!< Referinta catre imaginea curenta a flamei.*/
    public static final int DEFAULT_FLAME_WIDTH  = 64;   /*!< Latimea implicita a imaginii flamei.*/
    public static final int DEFAULT_FLAME_HEIGHT = 64;   /*!< Inaltimea implicita a imaginii flamei.*/
    public int              flameCounter;


    public Flames(RefLinks refLink, float x, float y) {
        super(refLink, x, y, DEFAULT_FLAME_WIDTH, DEFAULT_FLAME_HEIGHT);
        image = Assets.explosion[0];
        normalBounds.x = 0;
        normalBounds.y = 0;
        normalBounds.width = 64;
        normalBounds.height = 64;
        flameCounter = 0;
    }

    private void Animation() {
        //System.out.println(this);
        int explosionImage = flameCounter / 4;      // schimba imaginea la fiecare 15 cicli
        if (explosionImage >= Assets.explosion.length) {
            image = null;
            isDead = true;
        }
        else {
            image = Assets.explosion[explosionImage];
            flameCounter++;
        }
       // System.out.println(explosionImage);
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
    }

    public int GetWidth()
    {
        return DEFAULT_FLAME_WIDTH;
    }
    public int GetHeight()
    {
        return DEFAULT_FLAME_HEIGHT;
    }
}
