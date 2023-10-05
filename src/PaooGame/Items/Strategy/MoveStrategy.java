package PaooGame.Items.Strategy;

import PaooGame.Items.DinamicItems.Character;

/*! \interface  MoveStrategy
    interfata sablonului Starategy , implementeaza functia move ca strategie de miscare a inamicilor
 */

public interface  MoveStrategy {
    void Move(Character enemy);
}
