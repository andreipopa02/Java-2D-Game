package PaooGame.Items.Strategy;
import PaooGame.Items.DinamicItems.Character;


/*! \class RandomMove implements MoveStrategy
    clasa care implementeaza MoveStategy si suprascrie metoda Move alegand o miscare random a caracterului.
 */
public class RandomMove implements MoveStrategy {
    @Override
    public void Move(Character enemy) {
        ///in functie de ultima directie o sa se deplaseze
        switch (enemy.GetLastDirection()) {
            case "up":
                enemy.SetYMove(-enemy.GetSpeed());
                break;
            case "down":
                enemy.SetYMove(enemy.GetSpeed());
                break;
            case "left":
                enemy.SetXMove(-enemy.GetSpeed());
                break;
            case "right":
                enemy.SetXMove(enemy.GetSpeed());
                break;
        }
        /// Daca inamicul nu reuseste sa se deplaseze, facem miscarea manual
        if (enemy.GetCol().CheckTileCollision(enemy)) {
            switch (enemy.GetLastDirection()) {
                case "up":
                    enemy.SetLastDirection("down");
                    enemy.SetYMove(enemy.GetSpeed());
                    break;
                case "down":
                    enemy.SetLastDirection("up");
                    enemy.SetYMove(-enemy.GetSpeed());
                    break;
                case "left":
                    enemy.SetLastDirection("right");
                    enemy.SetXMove(enemy.GetSpeed());
                    break;
                case "right":
                    enemy.SetLastDirection("left");
                    enemy.SetXMove(-enemy.GetSpeed());
                    break;
            }
        }

        enemy.x += enemy.GetXMove();
        enemy.y += enemy.GetYMove();
        enemy.SetXMove(0);
        enemy.SetYMove(0);
    }
}