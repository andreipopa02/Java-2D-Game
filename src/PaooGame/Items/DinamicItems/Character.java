package PaooGame.Items.DinamicItems;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Items.Collision;
import PaooGame.Items.Item;
import PaooGame.Items.ItemManager;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \ public abstract class Character extends Item
    \brief Obiectul de tip caracter.
 */
public abstract class Character extends Item {
    public static final int DEFAULT_LIVES           = 3;    /*!< Valoarea implicita a vietii unui caracter.*/
    public static final float DEFAULT_SPEED         = 2.0f; /*!< Viteza implicita a unu caracter.*/
    public static final int DEFAULT_CREATURE_WIDTH  = 80;   /*!< Latimea implicita a imaginii caracterului.*/
    public static final int DEFAULT_CREATURE_HEIGHT = 80;   /*!< Inaltimea implicita a imaginii caracterului.*/

    protected int       lives;                               /*!< Retine viata caracterului.*/
    protected float     speed;                               /*!< Retine viteza de deplasare caracterului.*/
    protected float     xMove;                               /*!< Retine noua pozitie a caracterului pe axa X.*/
    protected float     yMove;                               /*!< Retine noua pozitie a caracterului pe axa Y.*/

    protected Collision col;
    protected String    lastDirection       = "right";
    protected int       characterInterval   = 0;
    protected int       characterCounter    = 0;
    protected boolean   isDead;


    /*! \fn public Character(RefLinks refLink, float x, float y, int width, int height)
         \brief Constructoul clasei.
   */
    public Character(RefLinks refLink, float x, float y, int width, int height) {
        ///Apel constructor la clasei de baza
        super(refLink, x,y, width, height);
        //Seteaza pe valorile implicite pentru viata, viteza si distantele de deplasare
        lives   = DEFAULT_LIVES;
        speed   = DEFAULT_SPEED;
        xMove   = 0;
        yMove = 0;
        col = new Collision(refLink);
        ///Se seteaza itemul ca fiind destructibil
        isDead = false;
    }

    /*! \fn public void Move(), MoveX(), MoveY()
          \brief Functi care se ocupa de deplasarea caracterului pe ecran si mentinerea acestuia in ecran.
    */
    public void Move() {
        if (!col.CheckTileCollision(this)) {
            MoveX();
            MoveY();
        }
    }

    public void MoveX() {
        x += xMove;
        if (x < 0) {
            x = 0;
        } else if (x + normalBounds.width > refLink.GetMap().getWidth() * Tile.TILE_WIDTH) {
            x = refLink.GetMap().getWidth() * Tile.TILE_WIDTH - normalBounds.width;
        }
    }

    public void MoveY() {
        y += yMove;
        if (y < 0) {
            y = 0;
        } else if (y + normalBounds.height > refLink.GetMap().getHeight() * Tile.TILE_HEIGHT) {
            y = refLink.GetMap().getHeight() * Tile.TILE_HEIGHT - normalBounds.height;
        }
    }

    /*! \fn public abstract void HitByFlame()
            \brief Functie abstracta care se ocupa de interactiunea caracterului cu obiectele care ii provoaca damage.
         */
    public abstract void HitByFlame() throws LoadLevelException;

    /*! \fn public void ReduceLife(int amount)
        \brief Functie care se ocupa de reducerea vietii caracterului
     */
    public void ReduceLife(int amount)  {
        lives -= amount;
        if (lives <= 0) {
            isDead = true;
           // refLink.GetGame().SetState(refLink.GetGame().GetLoseState());
            lives = DEFAULT_LIVES;
            ItemManager.RemoveItemManager();
            Hero.RemoveHero();
        }

    }


    /*!
        \getters + setters
    */
    public boolean isDead() { return isDead; }
    public int GetLives()
    {
        return lives;
    }
    public void SetLives(int lives)
    {
        this.lives = lives;
    }

    public float GetSpeed()
    {
        return speed;
    }
    public void SetSpeed(float speed) {
        this.speed = speed;
    }

    public float GetXMove()
    {
        return xMove;
    }
    public void SetXMove(float xMove) {
        this.xMove = xMove;
    }

    public float GetYMove() {
    return yMove;
}
    public void SetYMove(float yMove) {
        this.yMove = yMove;
    }

    public String GetLastDirection() { return lastDirection; }
    public void SetLastDirection(String lastDirection) { this.lastDirection = lastDirection; }

    public Collision GetCol() {return col; }
    public Rectangle normalBounds() { return normalBounds; }
    public void SetRectangle(Rectangle rectangle) {this.normalBounds = rectangle;}

}

