package PaooGame.Maps;

import PaooGame.Items.Item;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

/*! \ public class Camera
    \brief Implementeaza camera jocului.
 */
public class Camera {
    private float xOffset;                  /*!< variabila care retine coordonata x a camerei.*/
    private float yOffset;                   /*!< variabila care retine coordonata x a camerei.*/
    private RefLinks refLinks;              /*!< Referinta catre obiectul care retine game si map. */

    /*!
    \fn public Camera(RefLinks refLinks, float xOffset, float yOffset)
        Constructorul clasei Camera
     */
    public Camera(RefLinks refLinks, float xOffset, float yOffset) {
        this.refLinks = refLinks;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }


    /*! \fn public void followHero(Item hero)
        \Metoda care urmareste Item ul erou si centreaza imaginea pe el
        \actualizeaza
     */
    public void followHero(Item hero) {
        xOffset = hero.GetX() + hero.GetWidth()/2 - refLinks.GetWidth()/2; //jumatate din latimea ecranului + jumatate din latimea eroului pt o centrare perfecta
        yOffset = hero.GetY() + hero.GetHeight()/2 - refLinks.GetHeight()/2;
        checkCameraLimits();
    }

    /*! \fn public void checkCameraLimits(Item hero)
        \Metoda care verifica limitele camerei.
     */
    public void checkCameraLimits() {
        ///Impun limitele de deplasare a camerei pentru a nu iesi in afara "lumii"
        ///Verific extremitatile posibile ale camerei
        if(xOffset < 0)
            xOffset = 0;
        else if(xOffset > refLinks.GetMap().getWidth() * Tile.TILE_WIDTH - refLinks.GetWidth())
            xOffset = refLinks.GetMap().getWidth() * Tile.TILE_WIDTH - refLinks.GetWidth();

        if(yOffset < 0)
            yOffset = 0;
        else if(yOffset > refLinks.GetMap().getHeight() * Tile.TILE_HEIGHT  - refLinks.GetHeight())
            yOffset = refLinks.GetMap().getHeight() * Tile.TILE_HEIGHT - refLinks.GetHeight();
    }


    /*!
        \fn getters and setters
    */
    public float GetxOffset() { return xOffset; }
    public float GetyOffset() { return yOffset; }

    public void SetxOffset(float x) { xOffset = x; }
    public void SetyOffset(float y) { yOffset = y; }

}
