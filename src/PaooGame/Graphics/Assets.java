package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \clasa public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete etc.
 */
public class  Assets {
    /// Referinte catre elementele grafice utilizate in joc.
    private static final int HERO_LENGTH = 12;
    public static BufferedImage [] heroLeft         = new BufferedImage[HERO_LENGTH];
    public static BufferedImage [] heroRight        = new BufferedImage[HERO_LENGTH];
    public static BufferedImage [] heroIdle         = new BufferedImage[HERO_LENGTH];

    private static final int ENEMY_LENGTH = 12;
    public static BufferedImage [] enemyLeft        = new BufferedImage[ENEMY_LENGTH];
    public static BufferedImage [] enemyRight       = new BufferedImage[ENEMY_LENGTH];
    public static BufferedImage [] enemyIdle        = new BufferedImage[ENEMY_LENGTH];

    private static final int GOLD_ENEMY_LENGTH = 12;
    public static BufferedImage [] goldGoldLeft     = new BufferedImage[GOLD_ENEMY_LENGTH];
    public static BufferedImage [] goldEnemyRight   = new BufferedImage[GOLD_ENEMY_LENGTH];
    public static BufferedImage [] goldEnemyIdle    = new BufferedImage[GOLD_ENEMY_LENGTH];

    private static final int SUPER_ENEMY_LENGTH = 12;
    public static BufferedImage [] superEnemyLeft   = new BufferedImage[SUPER_ENEMY_LENGTH];
    public static BufferedImage [] superEnemyRight  = new BufferedImage[SUPER_ENEMY_LENGTH];
    public static BufferedImage [] superEnemyIdle   = new BufferedImage[SUPER_ENEMY_LENGTH];

    public static BufferedImage [] bombExplosion    = new BufferedImage[10];

    public static BufferedImage [] explosion        = new BufferedImage[16];


    public static BufferedImage floorTile;
    public static BufferedImage noTile;
    public static BufferedImage concreteTile;
    public static BufferedImage destructibleConcreteTile;
    public static BufferedImage stoneTile;
    public static BufferedImage destructibleStoneTile;
    public static BufferedImage brickTile;
    public static BufferedImage destructibleBrickTile;
    public static BufferedImage boxTile;
    public static BufferedImage barrelTile;
    public static BufferedImage goldBarrelTile;


    public static BufferedImage menuBackground;
    public static BufferedImage scoreBackground;
    public static BufferedImage pauseBackground;
    public static BufferedImage winBackground;
    public static BufferedImage loseBackground;




    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.
     */
    public static void Init()  {
        ///Incarcare subimagini dale
        SpriteSheet sheet  = new SpriteSheet(ImageLoader.LoadImage("/SpriteSheets/SpriteSheetTiles.png"));
        concreteTile                = sheet.crop(0, 0);
        destructibleConcreteTile    = sheet.crop(1, 0);
        stoneTile                   = sheet.crop(2, 0);
        destructibleStoneTile       = sheet.crop(1, 1);
        brickTile                   = sheet.crop(2, 1);
        destructibleBrickTile       = sheet.crop(0, 2);
        boxTile                     = sheet.crop(1, 2);
        barrelTile                  = sheet.crop(2, 2);
        goldBarrelTile              = sheet.crop(1, 3);
        floorTile                   = sheet.crop(0, 3);
        noTile                      = sheet.crop(2, 3);

        ///Incarcare subimagini erou
        SpriteSheet sheetHero  = new SpriteSheet(ImageLoader.LoadImage("/SpriteSheets/SpriteSheetHero.png"));
        for (int i = 0; i < HERO_LENGTH; i++) {
            heroIdle[i]        = sheetHero.crop(i*900, 0, 900, 900);
            heroLeft[i]        = sheetHero.crop(i*900, 2*900, 900, 900);
            heroRight[i]       = sheetHero.crop(i*900, 900, 900, 900);
        }

        ///Incarcare subimagini enemy
        SpriteSheet sheetEnemy = new SpriteSheet(ImageLoader.LoadImage("/SpriteSheets/SpriteSheetEnemy.png"));
        for (int i = 0; i < ENEMY_LENGTH; i++) {
            enemyIdle[i]       = sheetEnemy.crop(i*900, 0, 900, 900);
            enemyLeft[i]       = sheetEnemy.crop(i*900, 2*900, 900, 900);
            enemyRight[i]      = sheetEnemy.crop(i*900, 900, 900, 900);
        }

        ///Incarcare subimagini gold enemy
        SpriteSheet sheetGoldEnemy = new SpriteSheet(ImageLoader.LoadImage("/SpriteSheets/SpriteSheetEnemyGold.png"));
        for (int i = 0; i < GOLD_ENEMY_LENGTH; i++) {
            goldEnemyIdle[i]       = sheetGoldEnemy.crop(i*900, 0, 900, 900);
            goldGoldLeft[i]       = sheetGoldEnemy.crop(i*900, 2*900, 900, 900);
            goldEnemyRight[i]      = sheetGoldEnemy.crop(i*900, 900, 900, 900);
        }

        ///Incarcare subimagini BOSS
        SpriteSheet sheetSuperEnemy = new SpriteSheet(ImageLoader.LoadImage("/SpriteSheets/SpriteSheetSuperEnemy.png"));
        for (int i = 0; i < SUPER_ENEMY_LENGTH; i++) {
            superEnemyIdle[i]   = sheetSuperEnemy.crop(i*900, 0, 900, 900);
            superEnemyLeft[i]   = sheetSuperEnemy.crop(i*900, 2*900, 900, 900);
            superEnemyRight[i]  = sheetSuperEnemy.crop(i*900, 900, 900, 900);
        }

        ///Incarcare subimagini bomba
        SpriteSheet sheetBomb  = new SpriteSheet(ImageLoader.LoadImage("/SpriteSheets/SpriteSheetBomb.png"));
        for (int i = 0; i < bombExplosion.length;  i++) {
            bombExplosion[i]   = sheetBomb.crop(i*64, 0, 64, 64);
        }

        ///Incarcare subimagini explozie
        SpriteSheet sheetExplosion  = new SpriteSheet(ImageLoader.LoadImage("/SpriteSheets/SpriteSheetExplosion.png"));
        for (int i = 0; i < explosion.length;  i++) {
            explosion[i]   = sheetExplosion.crop(i*64, 0, 64, 64);
        }

        ///Incarcare subimagine backGround meniu
        menuBackground  = ImageLoader.LoadImage("/Backgrounds/MenuBackground.png");
        scoreBackground = ImageLoader.LoadImage("/Backgrounds/ScoreBackground.png");
        pauseBackground = ImageLoader.LoadImage("/Backgrounds/PauseBackground.png");
        winBackground   = ImageLoader.LoadImage("/Backgrounds/WinBackground.png");
        loseBackground  = ImageLoader.LoadImage("/Backgrounds/LoseBackground.png");
    }
}
