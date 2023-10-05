package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \class public class GoldBarrelTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip butoi.
 */
public class GoldBarrelTile extends Tile {
    /*! \fn public GoldBarrelTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public GoldBarrelTile(int id) {
        /// Apel al constructorului clasei de baza
        super(Assets.goldBarrelTile, id);
    }
    @Override
    public boolean IsSolid() {
        return true;
    }
    @Override
    public boolean IsDestructible() { return true; }

}
