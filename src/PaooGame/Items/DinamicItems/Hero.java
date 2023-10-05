package PaooGame.Items.DinamicItems;

import java.awt.*;
import java.awt.image.BufferedImage;

import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;

public class Hero extends Character {
    private static Hero             instance;
    private BufferedImage           image;                /*!< Referinta catre imaginea curenta a eroului.*/
    private boolean                 moving;

    private Hero(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        ///Seteaza imaginea de start a eroului
        image = Assets.heroRight[0];
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 25;
        normalBounds.y = 30;
        normalBounds.width = 30;
        normalBounds.height = 36;
    }

    public static Hero GetInstance(RefLinks refLink, float x, float y){
        if(instance == null)
            instance = new Hero(refLink, x, y);
        return instance;
    }

    public static void RemoveHero(){
        instance = null;
    }

    @Override
    public void Update() {
        ///Centrez camera pe erou
        refLink.GetGame().GetCamera().followHero(this);

        ///Verifica daca a fost apasata o tasta
        GetInput();

        ///Actualizeaza pozitia
        if (moving) {
            Move();
        }
        moving = false;

        ///Actualizeaza imaginea
        Animation();

    }


    @Override
    public void Draw (Graphics g){
        g.drawImage(image, (int)(x - refLink.GetGame().GetCamera().GetxOffset()),
                (int)(y - refLink.GetGame().GetCamera().GetyOffset()),
                width, height, null);
    /*
        g.setColor(Color.black);
        g.drawRect((int)(x + normalBounds.x - refLink.GetGame().GetCamera().GetxOffset()),
                (int)(y + normalBounds.y - refLink.GetGame().GetCamera().GetyOffset()),
                normalBounds.width, + normalBounds.height);
     */
    }

    private void Animation() {
        lastDirection = "right";
        if (refLink.GetKeyHandler().leftPressed || refLink.GetKeyHandler().rightPressed
                || refLink.GetKeyHandler().upPressed || refLink.GetKeyHandler().downPressed) {
            if (refLink.GetKeyHandler().leftPressed) {
                image = Assets.heroLeft[characterInterval];
                lastDirection = "left";
            } else if (refLink.GetKeyHandler().rightPressed) {
                image = Assets.heroRight[characterInterval];
                lastDirection = "right";
            } else if (refLink.GetKeyHandler().upPressed && lastDirection.equals("left")) {
                image = Assets.heroLeft[characterInterval];
                lastDirection = "up";
            } else if (refLink.GetKeyHandler().upPressed && lastDirection.equals("right")) {
                image = Assets.heroRight[characterInterval];
                lastDirection = "up";
            } else if (refLink.GetKeyHandler().downPressed && lastDirection.equals("left")) {
                image = Assets.heroLeft[characterInterval];
                lastDirection = "down";
            } else if (refLink.GetKeyHandler().downPressed && lastDirection.equals("right")) {
                image = Assets.heroRight[characterInterval];
                lastDirection = "down";
            }
        } else
            image = Assets.heroIdle[characterInterval];

        characterCounter++;
        if (characterCounter > 6) {
            characterInterval++;
            if (characterInterval == 11)
                characterInterval = 0;
            characterCounter = 0;
        }
    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput() {
        // Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;

        // Verificare apasare tasta "up"
        if (refLink.GetKeyHandler().upPressed) {
            yMove = -speed;
            moving = true;
        }
        // Verificare apasare tasta "down"
        if (refLink.GetKeyHandler().downPressed) {
            yMove = speed;
            moving = true;
        }
        // Verificare apasare tasta "left"
        if (refLink.GetKeyHandler().leftPressed) {
            xMove = -speed;
            moving = true;
        }
        // Verificare apasare tasta "right"
        if (refLink.GetKeyHandler().rightPressed) {
            xMove = speed;
            moving = true;
        }

    }

    @Override
    public void HitByFlame()  {
        ReduceLife(1);
    }

    public void HitByEnemy() {
        ReduceLife(3);
    }
}


