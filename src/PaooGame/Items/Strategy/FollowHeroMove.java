package PaooGame.Items.Strategy;

import PaooGame.Items.DinamicItems.Character;
import PaooGame.RefLinks;

/*! \class FollowHeroMove implements MoveStrategy
    clasa care implementeaza MoveStategy si suprascrie metoda Move.
    Aceasta face enemy ul sa se miste urmarind eroul si cautand o distanta cat mai scurta pentru a ajunge la acesta.
 */
public class FollowHeroMove implements MoveStrategy {
    private RefLinks refLinks;

    public FollowHeroMove(RefLinks refLinks) {
        this.refLinks = refLinks;
    }

    @Override
    public void Move(Character enemy) {
        ///coordonatele eroului
        float heroX = refLinks.GetItemManager().GetHero().GetX();
        float heroY = refLinks.GetItemManager().GetHero().GetY();

        ///distanta de la erou la inamic
        float dx = heroX - enemy.x;
        float dy = heroY - enemy.y;


        if (Math.abs(dx) > Math.abs(dy)) {
            enemy.SetLastDirection(dx > 0 ? "right" : "left");
        } else {
            enemy.SetLastDirection(dy > 0 ? "down" : "up");
        }

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
        // Verifica si evita coliziunea cu alte obiecte
        if (enemy.GetCol().CheckTileCollision(enemy)) {
            enemy.SetXMove(0);
            enemy.SetYMove(0);
        }

        enemy.x += enemy.GetXMove();
        enemy.y += enemy.GetYMove();
        enemy.SetXMove(0);
        enemy.SetYMove(0);
    }

}
