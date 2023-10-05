package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class BoxTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip cutie.
 */
public class BoxTile extends Tile {
    /*! \fn public BoxTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public BoxTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.boxTile, id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
    @Override
    public boolean IsDestructible() { return true; }

}
