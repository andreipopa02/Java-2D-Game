package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class DestructibleConcreteTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip beton care poate fi distrus de bomba.
 */
public class DestructibleConcreteTile extends Tile {

    /*! \fn public DestructibleConcreteTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public DestructibleConcreteTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.destructibleConcreteTile, id);
    }
    @Override
    public boolean IsSolid() { return true; }
    @Override
    public boolean IsDestructible() { return true; }

}
