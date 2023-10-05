package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class ConcreteTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip beton.
 */
public class ConcreteTile extends Tile {
    /*! \fn public ConcreteTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public ConcreteTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.concreteTile, id);
    }
    @Override
    public boolean IsSolid() {
        return true;
    }
    @Override
    public boolean IsDestructible() { return false; }

}
