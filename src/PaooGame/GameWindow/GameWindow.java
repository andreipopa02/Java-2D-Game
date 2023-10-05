package PaooGame.GameWindow;

import PaooGame.MenuBar.InfoPanel;
import PaooGame.MenuBar.MenuBar;
import PaooGame.RefLinks;

import javax.swing.*;
import java.awt.*;

/*! \class GameWindow
    \brief Implementeaza notiunea de fereastra a jocului.

    Membrul wndFrame este un obiect de tip JFrame care va avea utilitatea unei
    ferestre grafice si totodata si cea a unui container (toate elementele
    grafice vor fi continute de fereastra).
 */
public class GameWindow {
    private static GameWindow   instance = null;    /*!< instanta a ferestrei de joc pentru a simula singletonul*/
    private JFrame              wndFrame;           /*!< fereastra principala a jocului*/
    private MenuBar             wndMenuBar;         /*!< bara de meniu a jocului*/
    private InfoPanel           wndInfoBar;         /*!< bara de info a jocului*/
    private final String        wndTitle;           /*!< titlul ferestrei*/
    private final int           wndWidth;           /*!< latimea ferestrei in pixeli*/
    private final int           wndHeight;          /*!< inaltimea ferestrei in pixeli*/
    private Canvas              canvas;             /*!< "panza/tablou" in care se poate desena*/
    private final RefLinks      refLinks;           /*!< referinta catre obiectul shortcut care retine map si game*/

    /*! \fn GameWindow(String title, int width, int height)
            \brief Constructorul cu parametri al clasei GameWindow

            Retine proprietatile ferestrei, proprietatile (titlu, latime, inaltime)
            in variabilele membre deoarece vor fi necesare pe parcursul jocului.
            Crearea obiectului va trebui urmata de crearea ferestrei propriuzise
            prin apelul metodei BuildGameWindow()

            \param title Titlul ferestrei.
            \param width Latimea ferestrei in pixeli.
            \param height Inaltimea ferestrei in pixeli.
         */
    private GameWindow(String title, int width, int height, RefLinks refLinks) {
        wndTitle    = title;    /*!< Retine titlul ferestrei.*/
        wndWidth    = width;    /*!< Retine latimea ferestrei.*/
        wndHeight   = height;   /*!< Retine inaltimea ferestrei.*/
        wndFrame    = null;     /*!< Fereastra nu este construita.*/
        this.refLinks = refLinks;
    }

    /*! \fn public void GetInstance(String title, int width, int height)
        \brief Returneaza instanta ferestrei, una singura pentru intreg jocul
     */
    public static GameWindow GetInstance(String title, int width, int height, RefLinks refLinks) {
        if (instance == null) {
            instance = new GameWindow(title, width, height, refLinks);
        }
        return instance;
    }

    /*! \fn private void BuildGameWindow()
        \brief Construieste/creaza fereastra si seteaza toate proprietatile
        necesare: dimensiuni, pozitionare in centrul ecranului, operatia de
        inchidere, invalideaza redimensionarea ferestrei, afiseaza fereastra.
     */
    public void BuildGameWindow() {
            /// Daca fereastra a mai fost construita intr-un apel anterior
            /// se renunta la apel
        if(wndFrame != null) {
            return;
        }
            /// Aloca memorie pentru obiectul de tip fereastra si seteaza denumirea
            /// ce apare in bara de titlu
        wndFrame = new JFrame(wndTitle);
            /// Seteaza dimensiunile ferestrei in pixeli
        wndFrame.setSize(wndWidth, wndHeight);
            /// Operatia de inchidere (fereastra sa poata fi inchisa atunci cand
            /// este apasat butonul x din dreapta sus al ferestrei). Totodata acest
            /// lucru garanteaza ca nu doar fereastra va fi inchisa ci intregul
            /// program
        wndFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            /// Avand in vedere ca dimensiunea ferestrei poate fi modificata
            /// si corespunzator continutul actualizat (aici ma refer la dalele
            /// randate) va recomand sa constrangeti deocamdata jucatorul
            /// sa se joace in fereastra stabilitata de voi. Puteti reveni asupra
            /// urmatorului apel ulterior.
        wndFrame.setResizable(false);
            /// Recomand ca fereastra sa apara in centrul ecranului. Pentru orice
            /// alte pozitie se va apela "wndFrame.setLocation(x, y)" etc.
        wndFrame.setLocationRelativeTo(null);
            /// Implicit o fereastra cand este creata nu este vizibila motiv pentru
            /// care trebuie setata aceasta proprietate

        wndMenuBar = new MenuBar(refLinks);
        wndFrame.setJMenuBar(wndMenuBar);

        wndFrame.setLayout(new BorderLayout());
        wndInfoBar = new InfoPanel(refLinks);
        wndInfoBar.setPreferredSize(new Dimension(0, 20));
        wndFrame.add(wndInfoBar, BorderLayout.NORTH);

        wndFrame.setVisible(true);
             /// Creaza obiectul de tip canvas (panza) pe care se poate desena.
        canvas = new Canvas();
            /// In aceeasi maniera trebuiesc setate proprietatile pentru acest obiect
            /// canvas (panza): dimensiuni preferabile, minime, maxime etc.
            /// Urmotorul apel de functie seteaza dimensiunea "preferata"/implicita
            /// a obiectului de tip canvas.
            /// Functia primeste ca parametru un obiect de tip Dimension ca incapsuleaza
            /// doua proprietati: latime si inaltime. Cum acest obiect nu exista
            /// a fost creat unul si dat ca parametru.
        canvas.setPreferredSize(new Dimension(wndWidth, wndHeight));
            /// Avand in vedere ca elementele unei ferestre pot fi scalate atunci cand
            /// fereastra este redimensionata
        canvas.setMaximumSize(new Dimension(wndWidth, wndHeight));
        canvas.setMinimumSize(new Dimension(wndWidth, wndHeight));

            /// Avand in vedere ca obiectul de tip canvas, proaspat creat, nu este automat
            /// adaugat in fereastra trebuie apelata metoda add a obiectul wndFrame
        wndFrame.add(canvas);
            /// Urmatorul apel de functie are ca scop eventuala redimensionare a ferestrei
            /// ca tot ce contine sa poate fi afisat complet
        wndFrame.pack();
    }


    public JFrame GetWndFrame()  { return wndFrame; }
    public int GetWndWidth() { return wndWidth; }
    public int GetWndHeight() { return wndHeight; }
    public Canvas GetCanvas() { return canvas; }
    public InfoPanel GetInfoPanel() { return wndInfoBar; }
    public MenuBar GetMenuBar(){ return wndMenuBar; }
}
