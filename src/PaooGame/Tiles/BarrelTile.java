package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class BarrelTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip butoi.
 */
public class BarrelTile extends Tile {
    /*! \fn public BarrelTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public BarrelTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.barrelTile, id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
    @Override
    public boolean IsDestructible() { return true; }

}
