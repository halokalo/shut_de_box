package io.github.shut_de_box.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class AbstractButton {
    protected String name;
    protected Sprite normalSprite;
    protected Sprite pressedSprite;
    protected Box box;

    protected boolean isPressed = false;
    protected float pressedTime = 0f;
    protected static final float PRESSED_DURATION = 0.05f; // 0.15f = 150 ms

    public AbstractButton(String name, String normalTextureFile, String pressedTextureFile, int xPos, int yPos, int width, int height, Box box) {
        this.name = name;
        this.box = box;
        Texture normalTexture = new Texture(normalTextureFile);
        Texture pressedTexture = new Texture(pressedTextureFile);

        normalSprite = new Sprite(normalTexture);
        normalSprite.setSize(width, height);
        normalSprite.setPosition(xPos, yPos);

        pressedSprite = new Sprite(pressedTexture);
        pressedSprite.setSize(width, height);
        pressedSprite.setPosition(xPos, yPos);

    }

    /**
     * Performs the action that should happen upon press of button
     */
    public void press() {
        setToPressed();
        pressedTime = PRESSED_DURATION;
        System.out.println(name + " was clicked");
    }

    public void update(float delta) {
        if (isPressed) {
            pressedTime -= delta;
            if (pressedTime <= 0f) {
                isPressed = false;
        }
        }
    }

    public void setToNormal() {
        this.isPressed = false;
    }

    public void setToPressed() {
        this.isPressed = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprite getCurrentSprite(){
        return isPressed ? pressedSprite : normalSprite;
    }   
}
