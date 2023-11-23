package gamelogic;

public class EntityObserver {
    private int score;
    public EntityObserver() {
        this.score = 0;
    }

    public void addScore(int score){
        this.score += score;
    }
    public int getScore(){
        return score;
    }


}
