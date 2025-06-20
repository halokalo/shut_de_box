package io.github.shut_de_box.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Popup {
    private String text;
    private boolean isVisible;
    private Sprite sprite;

    public Popup(String text, boolean isVisible, String fileName, float xPos, float yPos, float width, float height) {
        this.text = text;
        this.isVisible = isVisible;

        Texture texture = new Texture(fileName);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        sprite.setPosition(xPos, yPos);
    }

    public void draw(SpriteBatch batch, BitmapFont font) {
        if (isVisible) {
            sprite.draw(batch);
            font.draw(batch, text, sprite.getX(), sprite.getY());
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Sprite getSprite() {
        return sprite;
    }  
}
