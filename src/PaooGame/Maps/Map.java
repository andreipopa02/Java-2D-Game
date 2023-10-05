package PaooGame.Maps;

import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map {
    private RefLinks refLink;   /*!< Referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/


    /*! \fn public Map(RefLinks refLink, String code)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
        \param code reprezinta codul hartii care va fi incarcata -> nivelul
     */
    public Map(RefLinks refLink, String code) {
        this.refLink = refLink;
        String filePath = "res/Levels/Level" + code + ".txt";
        LoadWorld(filePath);
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public  void Update() {

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.
        \Funtia calculeaza care sunt marginile hartii care trebuie desenate
        \in functie de pozitia camerei jocului.

        \param g Contextul grafic in care se realizeaza desenarea.
     */
    public void Draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(0,0, 50*64, 50*64);

        ///Se calculeaza marginile hartii in functie de pozitia camerei
        //xStart va fi max dintre 0 si xOffset/Width pentru a preveni iesirea camerei din fereastra de joc...samd
        int xStart = (int)Math.max(0, refLink.GetGame().GetCamera().GetxOffset()/Tile.TILE_WIDTH);
        int xEnd   = (int)Math.min(width, (refLink.GetGame().GetCamera().GetxOffset()+ refLink.GetGame().GetWidth())/Tile.TILE_WIDTH +1);
        int yStart = (int)Math.max(0, refLink.GetGame().GetCamera().GetyOffset()/Tile.TILE_HEIGHT);
        int yEnd   = (int)Math.min(height, (refLink.GetGame().GetCamera().GetyOffset()+ refLink.GetGame().GetWidth())/Tile.TILE_HEIGHT +1);
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = yStart; y < yEnd; y++) {
            for(int x = xStart; x < xEnd; x++) {
                GetTile(x, y).Draw(g,
                        (int)(x * Tile.TILE_WIDTH  - refLink.GetGame().GetCamera().GetxOffset()),
                        (int)(y * Tile.TILE_HEIGHT - refLink.GetGame().GetCamera().GetyOffset()));
            }
        }
    }

    /*! \fn private void LoadWorld(String filePath)
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta.
     */
    private void LoadWorld(String filePath) {
       try {
           BufferedReader br = new BufferedReader(new FileReader(filePath));
           String line;
           // Initializare latime harta
           if ((line = br.readLine()) != null)
               width = Integer.parseInt(line);

           // Initializare inaltime harta
           if ((line = br.readLine()) != null)
               height = Integer.parseInt(line);

           // Initializare matrice de coduri de dale
           tiles = new int[width][height];

           // Citeste matricea de coduri din fisier
           for (int y = 0; y < height; y++) {
               if ((line = br.readLine()) != null) {
                   String[] values = line.trim().split("\\s+");
                   for (int x = 0; x < width; x++) {
                       tiles[x][y] = Integer.parseInt(values[x]);
                   }
               }
           }
           br.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   /*! \fn public Tile GetTile(int x, int y)
            \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

            In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
            intoarce o dala predefinita (ex. grassTile, mountainTile)
         */
    public Tile GetTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.concreteTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null) {
            return Tile.floorTile;
        }
        return t;
    }

    public void SetTile(int x, int y, Tile tile){
        this.tiles[x][y] = tile.GetId();            // GetId() returnează id.ul dalei (un int)
    }
    public int getWidth()  {return width; }
    public int getHeight() {return height;}

}

