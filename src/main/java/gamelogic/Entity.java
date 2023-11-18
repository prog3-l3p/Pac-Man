package gamelogic;

import java.awt.*;


public interface Entity {

    default void draw(Graphics g){
        g.drawImage(getSprite(), getX() * getWidth(), getY() * getHeight(), null);
    }

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    Image getSprite();

}
