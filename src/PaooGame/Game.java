package PaooGame;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.Assets;
import PaooGame.Input.KeyHandler;
import PaooGame.Input.MouseHandler;
import PaooGame.Items.DinamicItems.Hero;
import PaooGame.Items.ItemManager;
import PaooGame.Levels.Score;
import PaooGame.Maps.Camera;
import PaooGame.States.*;

import java.awt.*;
import java.awt.image.BufferStrategy;

/*! \class Game
    \brief Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)

    Implementeaza interfata Runnable:

        public interface Runnable {
            public void run();
        }

    Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.
    Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata
    in noul thread(fir de executie). Mai multe explicatii veti primi la curs.
 */
public class Game implements Runnable {
    private static Game         instance = null;    /*!< Referinta catre obiectul in sine pentru crearea unui Singleton.*/
    private final GameWindow    wnd;                /*!< Fereastra in care se va desena tabla jocului*/
    private boolean             runState;           /*!< Flag ce starea firului de executie.*/
    private Thread              gameThread;         /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy      bs;                 /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    private Graphics            g;                  /*!< Referinta catre un context grafic.*/
    private State               playState;          /*!< Referinta catre joc.*/
    private State               menuState;          /*!< Referinta catre menu.*/
    private State               pauseState;         /*!< Referinta catre pause.*/
    private State               winState;           /*!< Referinta catre endGame.*/
    private State               loseState;          /*!< Referinta catre endGame.*/
    private State               scoreState;         /*!< Referinta catre score.*/
    private KeyHandler          keyH;               /*!< Referinta catre obiectul care gestioneaza intrarile din partea utilizatorului.*/
    private MouseHandler        mouseH;             /*!< Referinta catre obiectul care gestioneaza intrarile din partea utilizatorului.*/
    private RefLinks            refLink;            /*!< Referinta catre un obiect a carui sarcina este doar de a retine diverse referinte pentru a fi usor accesibile.*/
    private Camera              camera;             /*!< Referinta catre obiectul care gestioneaza camera jocului.*/
    private  Score              score = null;       /*!< Referinta catre obiectul care gestioneaza scorul jocului.*/
    private ItemManager         itemManager;        /*!< Referinta catre obiectul care gestioneaza item-urile jocului si relatia dintre ele.*/


    /*! \fn private Game(String title, int width, int height)
                \brief Constructor de initializare al clasei Game.

                Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
                acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

                \param title Titlul ferestrei.
                \param width Latimea ferestrei in pixeli.
                \param height Inaltimea ferestrei in pixeli.
             */
    private Game(String title, int width, int height) {
        ///Se construieste obiectul de tip shortcut ce va retine o serie de referinte catre elementele importante din program.
        refLink = new RefLinks(this);
        /// Obiectul GameWindow este creat insa fereastra nu este construita
        /// Acest lucru va fi realizat in metoda init() prin apelul
        /// functiei BuildGameWindow();
        wnd = GameWindow.GetInstance(title, width, height, refLink);
        /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;
        ///Construirea obiectului de gestiune a evenimentelor de tastatura si mouse
        keyH   = new KeyHandler();
        mouseH = new MouseHandler();
        ///Construirea obiectului de gestiune a item urilor
        itemManager = ItemManager.GetInstance(refLink);
    }

    /*! \fn public GetInstance(String title, int width, int height)
                \brief Metoda de initializare al clasei Game.

                Utilizata pentru a putea crea o singura instanta a obiectului Game
     */
    public static Game GetInstance(String title, int width, int height){
        if(instance == null)
            instance = new Game(title, width, height);
        return instance;
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame() throws LoadLevelException {
        /// Este construita fereastra grafica.
        wnd.BuildGameWindow();

        ///Sa ataseaza ferestrei managerul de tastatura si mouse pentru a primi evenimentele furnizate de fereastra.
        wnd.GetWndFrame().addKeyListener(keyH);
        wnd.GetWndFrame().addFocusListener(keyH);
        wnd.GetCanvas().addMouseListener(mouseH);
        wnd.GetCanvas().addMouseMotionListener(mouseH);

        ///Se incarca toate elementele grafice (dale)
        Assets.Init();

        ///Se instantiaza scorul jocului
        score           = Score.GetInstance();

        ///Se instantiaza camera jocului
        camera          = new Camera(refLink,0,0);

        ///Definirea starilor programului
        playState       = PlayState.GetInstancePlayState(refLink);
        menuState       = new MenuState(refLink);
        pauseState      = new PauseState(refLink);
        scoreState      = new ScoreState(refLink);
        winState        = new WinState(refLink);
        loseState       = new LoseState(refLink);

        ///Seteaza starea implicita cu care va fi lansat programul in executie
        State.SetState(menuState);
    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run() {
        /// Initializeaza obiectul game
        try {
            InitGame();
        } catch (LoadLevelException e) {
            throw new RuntimeException(e);
        }
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final float timeFrame       = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState) {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame) {
                /// Actualizeaza pozitiile elementelor
                try {
                    Update();
                } catch (LoadLevelException e) {
                    throw new RuntimeException(e);
                }
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;
            }
        }

    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame() {
        if(!runState) {
            /// Se actualizeaza flagul de stare a threadului
            runState = true;
            /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
            /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
            /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
        else {
            /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StopGame() {
        if(runState) {
            /// Actualizare stare thread
            runState = false;
            /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try {
                /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex) {
                /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else {
            /// Thread-ul este oprit deja.
            return;
        }
    }

    public void Dispose() {
        if (bs != null) {                           // resetare buffer strategy
            bs.dispose();
            bs = null;
        }
        if (g != null) {                            // resetare context grafic
            g.dispose();
            g = null;
        }
    }

    /*! \fn public void Restart()
        \brief Reseteaza elementele jocului pentru inceperea unui nou joc.
    */
    public void RestartGame() throws LoadLevelException {
        Dispose();
        if(Score.GetInstance()!= null)              // resetare scor
            score.Remove();
        Hero.RemoveHero();                          // resetare erou
        itemManager = null;                         // resetare itemManager
        itemManager = ItemManager.GetInstance(refLink);
        playState = null;
        PlayState.RemovePlayState();                // resetare PlayState
        InitGame();                                 // crearea noului joc
        StartGame();
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Update() throws LoadLevelException {
        ///Determina starea tastelor si a mouse ului
        keyH.Update();
        mouseH.Update();
        ///Trebuie obtinuta starea curenta pentru care urmeaza a se actualiza starea, atentie trebuie sa fie diferita de null.
        if(State.GetState() != null) {
            ///Actualizez starea curenta a jocului daca exista.
            State.GetState().Update();}
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Draw() {
        /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
        /// Verific daca buffer strategy a fost construit sau nu
        if(bs == null) {
            /// Se executa doar la primul apel al metodei Draw()
            try {
                /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e) {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
        /// Se obtine contextul grafic curent in care se poate desena.
        g = bs.getDrawGraphics();
        /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());
        /// operatie de desenare
        ///Trebuie obtinuta starea curenta pentru care urmeaza a se actualiza starea, atentie trebuie sa fie diferita de null.
        if(State.GetState() != null) {
            ///Actualizez starea curenta a jocului daca exista.
            State.GetState().Draw(g);
        }
        /// end operatie de desenare

        /// Se afiseaza pe ecran
        bs.show();

        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }




    /*!
        \fn public Getters & Setters
    */
    public int GetWidth()
    {
        return wnd.GetWndWidth();
    }
    public int GetHeight() { return wnd.GetWndHeight(); }
    public int GetScore() { return score.GetScore(); }

    public GameWindow GetWnd() { return wnd; }
    public Camera GetCamera() { return camera; }
    public KeyHandler GetKeyHandler() { return keyH; }
    public MouseHandler GetMouseHandler(){ return mouseH; }
    public ItemManager GetItemManager() { return itemManager; }
    public State GetPlayState() { return playState; }
    public PlayState getPlayState() { return (PlayState) playState; }

    public State GetScoreState() { return scoreState; }
    public State GetMenuState() { return menuState; }
    public State GetPauseState() { return pauseState; }
    public State GetWinState() { return winState; }

    public State GetLoseState() { return loseState; }

    public State GetState() { return State.GetState(); }

    public State GetPreviousState() { return State.GetPreviousState(); }


    public void SetState(State state) { State.SetState(state); }
    public void SetScore(int value) { score.AddScore(value); }
}

