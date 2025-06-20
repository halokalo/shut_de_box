package io.github.shut_de_box.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Popup {
    private String name;
    private boolean isVisible;
    private Sprite sprite;

    public Popup(String name, boolean isVisible, String fileName, float xPos, float yPos, float width, float height) {
        this.name = name;
        this.isVisible = isVisible;

        Texture texture = new Texture(fileName);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
    }

    
}
