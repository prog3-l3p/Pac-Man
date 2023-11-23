package entities.moving.ghosts;

import entities.moving.PacMan;

import java.io.Serializable;

public class PacManObserver implements Serializable {
    PacMan pacMan;

    public void setPacMan(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    public int getPacManX(){
        return pacMan.getX();
    }

    public int getPacManY(){
        return pacMan.getY();
    }
}
