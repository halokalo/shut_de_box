package io.github.shut_de_box.Objects;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Die {
    private int value = 3;
    private List<Sprite> sides;

    public Die(float xPos, float yPos) {
        List<String> sideFileNames = List.of("die/side_1.png","die/side_2.png","die/side_3.png"
            ,"die/side_4.png","die/side_5.png","die/side_6.png");
        
        sides = new ArrayList<>();

        for (String fileName : sideFileNames) {
            Texture texture = new Texture(fileName);
            Sprite sprite = new Sprite(texture);
            sprite.setSize(50, 50);
            sprite.setPosition(xPos, yPos);
            sides.add(sprite);
        }
    }

    /**
     * Rolls the dice: returns a value between 1 and 6 and updates the Die's value.
     * @return The new value of the dice
     */
    public int roll(){
        value =  (int) (1 + Math.random() * 6);
        return value;
    }

    public Sprite getCurrentSide(){
        return sides.get(value - 1);
    }


    
}
