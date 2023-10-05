package PaooGame.States;

import PaooGame.DataBase;
import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Items.DinamicItems.Enemy;
import PaooGame.Items.ItemManager;
import PaooGame.Levels.*;
import PaooGame.RefLinks;

import java.awt.*;


/*! \ public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State {
    private static PlayState        instance;                       /*!< Instanta clasei singleton.*/
    private Level                   curentLevel;                    /*!< referinta catre nivelul curent.*/
    private Level                   nextLevel;                      /*!< referinta catre nivelul urmator.*/
    private ItemManager             itemManager;                    /*!< referinta catre obiectul itemManager, responsabil de interactiunea item-urilor.*/
    private int                     remainingEnemies;               /*!< variabila responsabila de numararea inamicilor ramasi in viata.*/
    private final float             timeFrame = 1000000000 ;        /*!< Durata unui frame in nanosecunde.*/
    private long                    curentTime;                     /*!< variabila care retine timpul curent.*/
    private long                    oldTime;                        /*!< variabila care retine timpul trecut.*/
                                                                    /*!< variabile care ma ajuta sa folosesc timer-ul jocului.*/

    /*! \fn public PlayState(RefLinks refLink)
    \brief Constructorul de initializare al clasei

    \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
    */
    private PlayState(RefLinks refLink) throws LoadLevelException {
        ///Apel al constructorului clasei de baza
        super(refLink);
        this.curentLevel        = new Level1(refLink, "1", 120);
        this.nextLevel          = curentLevel;
        this.itemManager        = refLink.GetItemManager();
        this.remainingEnemies   = itemManager.countItemsOfType(Enemy.class);   // numararea inamicilor initial in viata
       // DataBase.GetInstance();
    }

    /*! \fn public static PlayState GetInstancePlayState(RefLinks refLink)
   \brief Metoda statica responsabila de intoarcerea referintei catre instanta obiectului.
   */
    public static PlayState GetInstancePlayState(RefLinks refLink) throws LoadLevelException {
        if (instance == null)
            instance = new PlayState(refLink);
        return instance;
    }

    /*! \fn public static void RemovePlayState()
   \brief Metoda statica responsabila de stergerea referintei catre instanta obiectului.
   */
    public static void RemovePlayState() {
        instance = null;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update() throws LoadLevelException {
        ///Timer-ul jocului : se va decrementa timpul curentTime(timpul ramas) odata la un frame.
        curentTime = System.nanoTime();
        if((curentTime - oldTime) > timeFrame) {
            curentLevel.DecreseTimeRemaining(1);
            oldTime = curentTime;
        }

        ///Conditia de sfarsit a jocului : daca jucatorul ramane fara timp sau daca eroul moare.
        if(curentLevel.GetTimeRemaining() <= 0 || refLink.GetItemManager().GetHero().isDead()) {
            refLink.GetGame().SetState(refLink.GetGame().GetLoseState());
            ///Inregistrare rezultate in baza de date.
            DataBase.GetInstance().UpdateDatabase();
        }

        ///Actualizare nivel curent.
        curentLevel.Update();

        ///Actualizare valori in Info Panel : Time     Score    Lives
        refLink.GetGame().GetWnd().GetInfoPanel().setTime(curentLevel.GetTimeRemaining());
        refLink.GetGame().GetWnd().GetInfoPanel().setLives(refLink.GetItemManager().GetHero().GetLives());
        refLink.GetGame().GetWnd().GetInfoPanel().setScore(refLink.GetGame().GetScore());

        ///Actulizarea numarului de inamici ramasi in viata.
        remainingEnemies = itemManager.countItemsOfType(Enemy.class);
        if (remainingEnemies == 0) {
            ///Actualizare variabila pentru nivelul urmator
            if (curentLevel instanceof Level1 && itemManager.items.size() == 0) {
                itemManager.SetHero();
                nextLevel = new Level2(refLink, "2", 180);
                refLink.GetGame().SetState(refLink.GetGame().GetWinState());
            }
            else if (curentLevel instanceof Level2 && itemManager.items.size() == 0) {
                itemManager.SetHero();
                nextLevel = new Level3(refLink, "3", 240);
                refLink.GetGame().SetState(refLink.GetGame().GetWinState());
            }
            else if (curentLevel instanceof Level3 && itemManager.items.size() == 0){
                refLink.GetGame().SetState(refLink.GetGame().GetWinState());
                ///Inregistrare rezultate in baza de date.
                DataBase.GetInstance().UpdateDatabase();
                return;
            }
            ///Schimbare nivel, daca este cazul.
            curentLevel = nextLevel;
        }

    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.
        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        curentLevel.Draw(g);
    }


    public void AddExtraTime(int time) { curentLevel.IncreaseTimeRemaining(time); }

    public boolean GetLevel1() { return curentLevel instanceof Level1; }
}



