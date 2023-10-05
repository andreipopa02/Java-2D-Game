package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class DestructibleBrickTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip caramida care poate fi distrusa de bomba.
 */
public class DestructibleBrickTile extends Tile {
    /*! \fn public DestructibleBrickTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public DestructibleBrickTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.destructibleBrickTile, id);
    }
    @Override
    public boolean IsSolid() {
        return true;
    }
    @Override
    public boolean IsDestructible() { return true; }

}
