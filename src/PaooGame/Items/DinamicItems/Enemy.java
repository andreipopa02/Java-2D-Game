package PaooGame.Items.DinamicItems;

import PaooGame.Items.Strategy.MoveStrategy;
import PaooGame.Items.Item;
import PaooGame.Items.StaticItems.Bomb;
import PaooGame.Items.StaticItems.Flames;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Enemy extends Character {

    protected BufferedImage             image;                /*!< Referinta catre imaginea curenta a inamicului.*/
    protected int                       timeSinceLastDirectionChange = 0;
    protected boolean                   collideWithFlame;
    protected MoveStrategy              moveStrategy;


    public Enemy(RefLinks refLink, float x, float y) {
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        normalBounds.x = 20;
        normalBounds.y = 24;
        normalBounds.width = 40;
        normalBounds.height = 40;
        this.collideWithFlame = false;
    }

    public String generateRandomDirection() {
        int randomDirection = (int)(Math.random()*4);
        switch (randomDirection) {
            case 0:
                return "up";
            case 1:
                return "down";
            case 2:
                return "left";
            default:
                return "right";
        }
    }

    private String generateOppositeDirection(String direction) {
        switch (direction) {
            case "up":
                return "down";
            case "down":
                return "up";
            case "left":
                return "right";
            case "right":
                return "left";
            default:
                return "";
        }
    }

    @Override
    public void Update(){
        if (!col.CheckTileCollision(this)) {
            timeSinceLastDirectionChange++;
            if (timeSinceLastDirectionChange >= 100) {
                SetLastDirection(generateRandomDirection());
                timeSinceLastDirectionChange = 0;
            }
        } else SetLastDirection(generateRandomDirection());

        for (Item item : items.items)
            if (item instanceof Bomb)
                if (col.CheckBombCollision(this, (Bomb) item))
                    SetLastDirection(generateOppositeDirection(lastDirection));

    }

    @Override
    public void HitByFlame() {
        for (Item item : items.items) {
            if (item instanceof Flames) {
                if (col.CheckFlameCollision(this, (Flames) item) && !collideWithFlame) {
                    collideWithFlame = true;
                    refLink.GetGame().SetScore(200);
                    isDead = true;
                    image = null;
                }
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image, (int)(x - refLink.GetGame().GetCamera().GetxOffset()),
                (int)(y - refLink.GetGame().GetCamera().GetyOffset()),
                width, height, null);
/*
        g.setColor(Color.black);
        g.drawRect((int)(x - refLink.GetGame().GetCamera().GetxOffset() + normalBounds.x),
                   (int)(y - refLink.GetGame().GetCamera().GetyOffset() + normalBounds.y),
                   normalBounds.width, normalBounds.height);
 */
    }

    public boolean isDead() { return isDead; }

}
