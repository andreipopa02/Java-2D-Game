package PaooGame.Items.DinamicItems;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Strategy.FollowHeroMove;
import PaooGame.RefLinks;

import java.util.Objects;

public class Enemy_Boss extends Enemy {

    public Enemy_Boss(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        this.SetSpeed(DEFAULT_SPEED);
        moveStrategy = new FollowHeroMove(refLink);
        this.image = Assets.superEnemyLeft[0];
    }

    public void Animation() {
        if (Objects.equals(lastDirection, "up")
                || Objects.equals(lastDirection, "down")
                || Objects.equals(lastDirection, "left")) {
            image = Assets.superEnemyLeft[characterInterval];
        }
        else if (Objects.equals(lastDirection, "right")) {
            image = Assets.superEnemyRight[characterInterval];
        }
        else {
            image = Assets.superEnemyIdle[characterInterval];
        }
        if(col.CheckTileCollision(this)){
            image = Assets.superEnemyIdle[characterInterval];
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
