package PaooGame.Items.DinamicItems;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Strategy.RandomMove;
import PaooGame.RefLinks;

import java.util.Objects;

public class Enemy_Silver extends Enemy {

    public Enemy_Silver(RefLinks refLink, float x, float y) {
        super(refLink, x, y);
        this.SetSpeed(DEFAULT_SPEED - DEFAULT_SPEED / 2);
        this.moveStrategy = new RandomMove();
        this.image = Assets.enemyLeft[0];
    }

    public void Animation() {
        if (Objects.equals(lastDirection, "up")
                || Objects.equals(lastDirection, "down")
                || (Objects.equals(lastDirection, "left"))){
            image = Assets.enemyLeft[characterInterval];
        }
        else if (Objects.equals(lastDirection, "right")) {
            image = Assets.enemyRight[characterInterval];
        }
        else {
            image = Assets.enemyIdle[characterInterval];
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
