package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class DestructibleStoneTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip piatra care poate fi distrusa.
 */
public class DestructibleStoneTile extends Tile {
    /*! \fn public DestructibleStoneTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public DestructibleStoneTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.destructibleStoneTile, id);
    }
    @Override
    public boolean IsSolid() {
        return true;
    }
    @Override
    public boolean IsDestructible() { return true; }

}
