package PaooGame.Levels;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Items.Collision;
import PaooGame.Items.ItemManager;
import PaooGame.RefLinks;

import java.awt.*;

/*! \ public abstract class Level
    \brief Implementeaza notiunea de nivel al jocului..
 */
public abstract class Level {

    protected ItemManager       items;
    private Collision           col;
    protected final int         totalTime;
    protected int               timeRemaining;

    /*! \fn public Level(RefLinks refLinks, String path, int totalTime)
        \brief Constructorul de initializare al clasei
            \param path reprezinta codul mapei care va fi incarcata
            \param totalTime reprezinta numarul total de secunde pe care il are utilizatorul la dispozitie pentru nivelul respectiv
        */
    public Level(RefLinks refLinks, String path, int totalTime) throws LoadLevelException {
        LoadLevel(path);
        this.items = refLinks.GetItemManager();
        this.col = new Collision(refLinks);
        this.totalTime = totalTime;
        this.timeRemaining = totalTime;
    }


    /*! \fn public abstract void LoadLevel(String path)
    \brief Metoda carre incarca nivelul jocului.
    */
    public abstract void LoadLevel(String path) throws LoadLevelException;

    /*! \fn public abstract void Update()
    \brief Metoda care se ocupa de actualizarea nivelului.
    */
    public abstract void Update() throws LoadLevelException;

    /*! \fn public abstract void Draw(Graphics g)
    \brief Metoda care se ocupa de deseneaza nivelului.
    */
    public abstract void Draw(Graphics g);

    /*!
        \getter
    */
    public int GetTimeRemaining() { return timeRemaining; }

    /*!
        \fn public void DecreaseTimeRemaining(float value)
        \ decrementeaza timpul ramas
    */
    public void DecreseTimeRemaining(int value) { timeRemaining -= value; }

    /*!
        \fn public void IncreaseTimeRemaining(float value)
        \ incrementeaza timpul ramas
    */
    public void IncreaseTimeRemaining(float value) { timeRemaining += value; }


}
