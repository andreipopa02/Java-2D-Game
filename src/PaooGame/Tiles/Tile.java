package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile {

    protected final int id;                                                             /*!< Id-ul unic aferent tipului de dala.*/
    private static final int NO_TILES   = 10;
    public static Tile[] tiles          = new Tile[NO_TILES];                           /*!< Vector de referinte de tipuri de dale.*/

    public static final int TILE_WIDTH  = 64;                                           /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 64;                                           /*!< Inaltimea unei dale.*/
    protected BufferedImage img;                                                        /*!< Imaginea aferenta tipului de dala.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie
    public static Tile concreteTile             = new ConcreteTile(0);               /*!< Dala de tip beton*/
    public static Tile destructibleConcreteTile = new DestructibleConcreteTile(1);   /*!< Dala de tip beton destructibil*/
    public static Tile stoneTile                = new StoneTile(2);                  /*!< Dala de tip piatra*/
    public static Tile destructibleStoneTile    = new DestructibleStoneTile(3);      /*!< Dala de tip piatra destructibila*/
    public static Tile brickTile                = new BrickTile(4);                  /*!< Dala de tip caramida*/
    public static Tile destructibleBrickTile    = new DestructibleBrickTile(5);     /*!< Dala de tip caramida destructibila*/
    public static Tile boxTile                  = new BoxTile(6);                   /*!< Dala de tip cutie*/
    public static Tile barrelTile               = new BarrelTile(7);                /*!< Dala de tip butoi*/
    public static Tile goldBarrelTile           = new GoldBarrelTile(8);            /*!< Dala transparenta*/
    public static Tile floorTile                = new FloorTile(9);                 /*!< Dala de tip podea*/



    public Tile(BufferedImage image, int idd) {
        img = image;
        id  = idd;
        tiles[id] = this;
    }

    /*! \fun public void Draw(Graphics g, int x, int y)
        \brief Face posibila desenarea obiectului de tip tile.
     *
    public void Draw(Graphics g, int x, int y) {
       g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
       //g.setColor(Color.black);
       //g.drawRect(x , y, TILE_WIDTH, TILE_HEIGHT);
    }

    /*! \fun public boolean IsSolid()
        \brief Functie care ne spune daca dala este solida sau nu.
        \      implicit consideram ca dalele nu sunt solide
     */
    public boolean IsSolid() { return false; }

    /*! \fun public boolean IsDestructible()
        \brief Functie care ne spune daca dala este destructibila sau nu.
        \      implicit consideram ca dalele nu sunt destructibile
     */
    public boolean IsDestructible() { return false; }

    public int GetId() {
        return id;
    }


    public void Draw(Graphics g, int x, int y) {
        //Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
}
