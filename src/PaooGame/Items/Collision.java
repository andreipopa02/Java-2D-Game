package PaooGame.Items;

import PaooGame.Items.DinamicItems.Character;
import PaooGame.Items.StaticItems.Bomb;
import PaooGame.Items.StaticItems.Flames;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \ public class Collision
    \brief Clasa care se ocupa de coliziunile dintre item urile jocului
 */
public class Collision {
    private final RefLinks refLinks;


    public Collision(RefLinks ref) {
        this.refLinks = ref;
    }

    public boolean CheckTileCollision(Character character) {
        float nextX = character.GetX();
        float nextY = character.GetY();

        switch(character.GetLastDirection()) {
            case "up":
                nextY -= character.GetSpeed();
                break;
            case "down":
                nextY += character.GetSpeed();
                break;
            case "left":
                nextX -= character.GetSpeed();
                break;
            case "right":
                nextX += character.GetSpeed();
                break;
            default:
                break;
        }
        float nextLeftX = character.normalBounds.x + nextX;
        float nextRightX = character.normalBounds.x + nextX + character.normalBounds.width;
        float nextTopY = character.normalBounds.y + nextY;
        float nextBottomY = character.normalBounds.y + nextY + character.normalBounds.height;

        float nextLeftCol = nextLeftX / Tile.TILE_WIDTH;
        float nextRightCol = nextRightX / Tile.TILE_WIDTH;
        float nextTopRow = nextTopY / Tile.TILE_HEIGHT;
        float nextBottomRow = nextBottomY / Tile.TILE_HEIGHT;

        // Verifică dacă există o dala solidă la următoarea poziție și evită mutarea dacă da
        return Tile.tiles[refLinks.GetMap().GetTile((int) nextLeftCol, (int) nextTopRow).GetId()].IsSolid() ||
                Tile.tiles[refLinks.GetMap().GetTile((int) nextRightCol, (int) nextTopRow).GetId()].IsSolid() ||
                Tile.tiles[refLinks.GetMap().GetTile((int) nextLeftCol, (int) nextBottomRow).GetId()].IsSolid() ||
                Tile.tiles[refLinks.GetMap().GetTile((int) nextRightCol, (int) nextBottomRow).GetId()].IsSolid();
    }

    public boolean CheckEnemyCollision(Character hero, Character enemy) {
        float nextX1 = hero.GetX();
        float nextY1 = hero.GetY();

        float nextLeftX1 = hero.normalBounds().x + nextX1;
        float nextRightX1 = hero.normalBounds().x + nextX1 + hero.normalBounds().width;
        float nextTopY1 = hero.normalBounds().y + nextY1;
        float nextBottomY1 = hero.normalBounds().y + nextY1 + hero.normalBounds().height;

        float nextX2 = enemy.GetX();
        float nextY2 = enemy.GetY();

        float nextLeftX2 = enemy.normalBounds().x + nextX2;
        float nextRightX2 = enemy.normalBounds().x + nextX2 + enemy.normalBounds().width;
        float nextTopY2 = enemy.normalBounds().y + nextY2;
        float nextBottomY2 = enemy.normalBounds().y + nextY2 + enemy.normalBounds().height;

        Rectangle character1CollisionBox = new Rectangle((int) nextLeftX1, (int) nextTopY1,
                (int) (nextRightX1 - nextLeftX1), (int) (nextBottomY1 - nextTopY1));
        Rectangle character2CollisionBox = new Rectangle((int) nextLeftX2, (int) nextTopY2,
                (int) (nextRightX2 - nextLeftX2), (int) (nextBottomY2 - nextTopY2));

        return character1CollisionBox.intersects(character2CollisionBox);
    }

    public boolean CheckBombCollision(Character character, Bomb bomb) {
        float nextX = character.GetX();
        float nextY = character.GetY();

        float nextLeftX = character.normalBounds().x + nextX;
        float nextRightX = character.normalBounds().x + nextX + character.normalBounds().width;
        float nextTopY = character.normalBounds().y + nextY;
        float nextBottomY = character.normalBounds().y + nextY + character.normalBounds().height;

        Rectangle characterCollisionBox = new Rectangle((int) nextLeftX, (int)nextTopY,
                (int) (nextRightX - nextLeftX), (int)(nextBottomY - nextTopY));
        Rectangle bombCollisionBox = new Rectangle((int) bomb.GetX(), (int) bomb.GetY(),
                Bomb.DEFAULT_BOMB_WIDTH, Bomb.DEFAULT_BOMB_HEIGHT);
        return bombCollisionBox.intersects(characterCollisionBox);
    }

    public boolean CheckFlameCollision(Character character, Flames flame) {
        float nextX = character.GetX();
        float nextY = character.GetY();

        float nextLeftX = character.normalBounds().x + nextX;
        float nextRightX = character.normalBounds().x + nextX + character.normalBounds().width;
        float nextTopY = character.normalBounds().y + nextY;
        float nextBottomY = character.normalBounds().y + nextY + character.normalBounds().height;

        Rectangle characterCollisionBox = new Rectangle((int) nextLeftX, (int) nextTopY,
                (int) (nextRightX - nextLeftX), (int) (nextBottomY - nextTopY));

        Rectangle flameCollisionBox = new Rectangle((int) flame.GetX(), (int) flame.GetY(),
                Flames.DEFAULT_FLAME_WIDTH, Flames.DEFAULT_FLAME_HEIGHT);
        return flameCollisionBox.intersects(characterCollisionBox);
    }

}