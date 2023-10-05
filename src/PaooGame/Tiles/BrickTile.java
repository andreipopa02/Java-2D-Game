package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class BrickTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip caramida.
 */
public class BrickTile extends Tile {
    /*! \fn public BrickTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public BrickTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.brickTile, id);
    }
    @Override
    public boolean IsSolid() {
        return true;
    }
    @Override
    public boolean IsDestructible() { return false; }

}
