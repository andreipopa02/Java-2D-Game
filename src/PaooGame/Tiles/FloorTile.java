package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class FloorTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip butoi.
 */
public class FloorTile extends Tile {
    /*! \fn public FloorTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public FloorTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.floorTile, id);
    }
    @Override
    public boolean IsSolid() {
        return false;
    }
    @Override
    public boolean IsDestructible() { return false; }
}
