package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class StoneTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip piatra.
 */
public class StoneTile extends Tile {
    /*! \fn public StoneTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public StoneTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.stoneTile, id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
    @Override
    public boolean IsDestructible() { return false; }

}
