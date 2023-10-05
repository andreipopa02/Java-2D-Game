package PaooGame;

public class Main {
    public static void main(String[] args) {
        Game paooGame = Game.GetInstance("PaooGame - BomberMan", 18*64, 11*64);
        paooGame.StartGame();
    }
}
