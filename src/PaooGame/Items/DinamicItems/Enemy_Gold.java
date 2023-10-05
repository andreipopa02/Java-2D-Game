package PaooGame.Items.DinamicItems;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Strategy.FollowHeroMove;
import PaooGame.RefLinks;

import java.util.Objects;

public class Enemy_Gold extends Enemy {

    public Enemy_Gold(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        this.SetSpeed(DEFAULT_SPEED - DEFAULT_SPEED / 2);
        moveStrategy = new FollowHeroMove(refLink);
        this.image = Assets.goldGoldLeft[0];
    }

    public void Animation() {
        if (Objects.equals(lastDirection, "up")
                || Objects.equals(lastDirection, "down")
                || Objects.equals(lastDirection, "left")) {
            image = Assets.goldGoldLeft[characterInterval];
        }
        else if (Objects.equals(lastDirection, "right")) {
            image = Assets.goldEnemyRight[characterInterval];
        }
        else {
            image = Assets.goldEnemyIdle[characterInterval];
        }
        if(col.CheckTileCollision(this)){
            image = Assets.goldEnemyIdle[characterInterval];
        }
        characterCounter++;
        if (characterCounter > 6) {
            characterInterval++;
            if (characterInterval == 11)
                characterInterval = 0;
            characterCounter = 0;
        }
    }

    @Override
    public void Update() {
        super.Update();
        moveStrategy.Move(this);
        Animation();
    }

}
