package PaooGame.Items;

import PaooGame.Exceptions.LoadLevelException;
import PaooGame.Items.DinamicItems.*;
import PaooGame.Items.DinamicItems.Character;
import PaooGame.Items.StaticItems.Bomb;
import PaooGame.Items.StaticItems.Flames;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.Graphics;
import java.util.ArrayList;

/*! \class ItemManager
    clasa care se ocupa de interactiunea dintre item uri si de activitatea acestora
 */
public class ItemManager  {
    private static ItemManager      instanceItemManager;
    private final RefLinks          refLinks;
    private final Collision         col;
    private Hero                    hero;

    public ArrayList<Item>          items;
    private final int               maxNoOfBombs;
    private  int                     noOfBombsPlaced;
    private boolean                 collidedWithFlame = false;

    //private boolean                 isOnBomb = false;


    /*! \fn public ItemManager(RefLinks refLinks)
            \ constructorul clasei.
     */
    private ItemManager(RefLinks refLinks) {
        this.refLinks = refLinks;
        this.col = new Collision(refLinks);
        this.hero = Hero.GetInstance(refLinks, 58, 58);
        this.items = new ArrayList<>();
        this.noOfBombsPlaced = 0;
        this.maxNoOfBombs = 1;
    }

    public static ItemManager GetInstance(RefLinks refLinks) {
        if(instanceItemManager == null)
            instanceItemManager = new ItemManager(refLinks);
        return instanceItemManager;
    }
    public static void RemoveItemManager (){
        instanceItemManager = null;
    }

    /*! \fn public void  AddItem() / RemoveItem()
        \brief Adauga / sterge un item din lista
     */
    public void AddItem(Item item) { items.add(item); }
    public void RemoveItem(Item item) { items.remove(item); }

    /*! \fn public void  Update()
        \brief DActualizeaza starea item urilor
     */
    public void Update() throws LoadLevelException {

        for (Item item : items) {
            item.Update();
            ///daca item ul este bomba atunci poate exploda, deci voi scadea numarul curent de bombe
            if (item instanceof Bomb) {
                if (((Bomb) item).GetExploded()) {
                    noOfBombsPlaced--;
                }
            }
            ///daca item ul este caracter atunci acesta este afectat de flama
            else if(item instanceof Character){
                ((Character) item).HitByFlame();
            }
        }

        ///daca eroul este in viata atunci il actualizam, poate pune bombe si poate interactiona cu alte item uri
        if (hero != null) {
            hero.Update();
            if (refLinks.GetKeyHandler().spacePressed && noOfBombsPlaced < maxNoOfBombs)
                PlaceBomb(hero);
            CheckItemsCollisions();
        }

        ///daca eroul a pus o bomba atunci trebuie adaugate flames si distruse dalele explodate
        DestroyTile();
        AddFlames();

        ///pentru fiecare enemy distrus primesc 20 score
        for(Item item : items){
            if(item instanceof Enemy){
                if(item.isDead() && (!refLinks.GetGame().getPlayState().GetLevel1())){
                    refLinks.GetGame().getPlayState().AddExtraTime(20);
                }
            }
        }

        ///pentru fiecare enemy boss distrus primesc 30 extra time; deci 50 :)
        for(Item item : items){
            if(item instanceof Enemy_Boss){
                if(item.isDead() && (!refLinks.GetGame().getPlayState().GetLevel1())){
                    refLinks.GetGame().getPlayState().AddExtraTime(30);
                }
            }
        }

        ///pentru fiecare item verific daca mai este in viata , in caz contrar il sterg din lista de itemuri
        for (int i = 0; i < items.size(); i++){
            Item item = items.get(i);
            if (item.isDead()) {
                RemoveItem(item);
                i--;
            }
        }
    }

    /*! \fn public void Draw(Graphics g)
       \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

       \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
    */
    public void Draw(Graphics g) {
        //System.out.println(items.size());
        for (Item item : items) {
            item.Draw(g);
        }
        if (hero!= null)
            hero.Draw(g);
    }

    /*! \fn public void CheckCollisions()
       \brief Se ocupa de coliziunile dintre diferite itemuri.
    */
    public void CheckItemsCollisions()  {
        ///Verifica coliziunea dintre erou si enemyes
        for (Item item : items) {
            if (item instanceof Enemy) {
                if (col.CheckEnemyCollision(hero, (Character) item)) {
                    hero.HitByEnemy();
                }
            }
            ///Verifica coliziunea dintre erou si bomb
//            if (item instanceof Bomb) {
//                if (col.CheckBombCollision(hero, (Bomb) item)){
//                    isOnBomb = true;
//                    // System.out.println("coliziune cu bomba");
//                }
//            }

            ///Verifica coliziunea dintre erou si flames
            if (item instanceof Flames) {
                if (col.CheckFlameCollision(hero, (Flames) item) && !collidedWithFlame){
                    collidedWithFlame = true;
                    System.out.println(hero.GetLives());
                    hero.HitByFlame();
                }
            }
        }
    }

    /*! \fn public void DestroyTile()
      \brief Se ocupa de distrugerea dalelor dupa explozie.
   */
    public void DestroyTile() {
        for (Item item : items) {
            if (item instanceof Bomb) {
                if (((Bomb) item).GetExploded()) {
                    int x = (int) (item.x / Tile.TILE_WIDTH);
                    int y = (int) (item.y / Tile.TILE_HEIGHT);
                    if (refLinks.GetMap().GetTile(x + 1, y).IsDestructible()) {
                        refLinks.GetMap().SetTile(x + 1, y, Tile.floorTile);
                        refLinks.GetGame().SetScore(20);
                    }
                    if (refLinks.GetMap().GetTile(x - 1, y).IsDestructible()) {
                        refLinks.GetMap().SetTile(x - 1, y, Tile.floorTile);
                        refLinks.GetGame().SetScore(20);
                    }
                    if (refLinks.GetMap().GetTile(x, y - 1).IsDestructible()) {
                        refLinks.GetMap().SetTile(x, y - 1, Tile.floorTile);
                        refLinks.GetGame().SetScore(20);
                    }
                    if (refLinks.GetMap().GetTile(x, y + 1).IsDestructible()) {
                        refLinks.GetMap().SetTile(x, y + 1, Tile.floorTile);
                        refLinks.GetGame().SetScore(20);
                    }
                }
            }
        }
    }

    /*! \fn public void  AddFlames()
      \brief Se ocupa de adaugarea flamelor acolo unde am o dala nesolida sau destructibila.
   */
    public void AddFlames() {
        ArrayList<Item> newItems = new ArrayList<>();

        for (Item bomb : items) {
            if (bomb instanceof Bomb && ((Bomb) bomb).GetExploded()) {
                int flameX = (int) (bomb.x / Tile.TILE_WIDTH);
                int flameY = (int) (bomb.y / Tile.TILE_HEIGHT);

                newItems.add(new Flames(refLinks, flameX * Tile.TILE_WIDTH, flameY * Tile.TILE_HEIGHT));
                collidedWithFlame = false;
                if (!refLinks.GetMap().GetTile(flameX + 1, flameY).IsSolid()) {
                    newItems.add(new Flames(refLinks, (flameX + 1) * Tile.TILE_WIDTH, flameY * Tile.TILE_HEIGHT));
                }
                if (!refLinks.GetMap().GetTile(flameX - 1, flameY).IsSolid()) {
                    newItems.add(new Flames(refLinks, (flameX - 1) * Tile.TILE_WIDTH, flameY * Tile.TILE_HEIGHT));
                }
                if (!refLinks.GetMap().GetTile(flameX, flameY - 1).IsSolid()) {
                    newItems.add(new Flames(refLinks, flameX * Tile.TILE_WIDTH, (flameY - 1) * Tile.TILE_HEIGHT));
                }
                if (!refLinks.GetMap().GetTile(flameX, flameY + 1).IsSolid()) {
                    newItems.add(new Flames(refLinks, flameX * Tile.TILE_WIDTH, (flameY + 1) * Tile.TILE_HEIGHT));
                }
                ((Bomb) bomb).SetExploded(false);
            }
        }
        items.addAll(newItems);
    }

    /*! \fn public void PlaceBomb(Character parent)
      \brief Se ocupa de plasarea bombelor pe harta de catre patern...inca nu am reusit sa implementez ca si enemy sa poata pune bombe :(
   */
    public void PlaceBomb(Character parent) {
        // Cenrez bomba plasata pe mijlocul dalei
        int tileX = (int) ((parent.x + parent.normalBounds.x + parent.normalBounds.width / 2) / Tile.TILE_WIDTH);
        int tileY = (int) ((parent.y + parent.normalBounds.y + parent.normalBounds.height / 2) / Tile.TILE_HEIGHT);

        // calculez coordonatele absolute ale centrului dalei
        float bombX = tileX * Tile.TILE_WIDTH + Tile.TILE_WIDTH / 2 - Bomb.DEFAULT_BOMB_WIDTH / 2;
        float bombY = tileY * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT / 2 - Bomb.DEFAULT_BOMB_HEIGHT / 2;

        // ajustez coordonatele in functie de dimensiunile dalelor
        if (parent.normalBounds.width > Tile.TILE_WIDTH) {
            bombX += (parent.normalBounds.width - Tile.TILE_WIDTH) / 2;
        }
        if (parent.normalBounds.height > Tile.TILE_HEIGHT) {
            bombY += (parent.normalBounds.height - Tile.TILE_HEIGHT) / 2;
        }
        AddItem(new Bomb(refLinks, bombX, bombY));
        noOfBombsPlaced++;
    }


    /*! \fn public void countItemsOfType(Class<? extends Item> itemType)
      \brief Se ocupa de numararea obiectelor de un anumit tip
   */
    public int countItemsOfType(Class<? extends Item> itemType) {
        int count = 0;
        for (Item item : items)
            if (itemType.isInstance(item))
                count++;
        return count;
    }

    public Hero GetHero() { return hero; }
    public void SetHero() {
        this.hero = null;
        Hero.RemoveHero();
        this.hero = Hero.GetInstance(refLinks, 58, 58);
    }
}
