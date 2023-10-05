package PaooGame.Levels;

public class Score {
    private static Score    instanceScore;
    private int             score;


    private Score() { score = 0; }

    public static Score GetInstance() {
        if(instanceScore == null)
           instanceScore = new Score();
        return instanceScore;
    }

    public void Remove() { instanceScore = null; }

    public int GetScore() { return score; }

    public void AddScore(int score) { this.score += score; }

}
