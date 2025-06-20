package io.github.shut_de_box.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class AbstractButton {
    protected String name;
    protected Sprite normalSprite;
    protected Sprite pressedSprite;
    protected Box box;

    protected boolean isPressed;
    protected float pressedTime = 0f;
    protected static final float PRESSED_DURATION = 0.05f; // 0.15f = 150 ms

    public AbstractButton(String name, String normalTextureFile, String pressedTextureFile, int xPos, int yPos, int width, int height, Box box) {
        this.name = name;
        this.box = box;
        this.isPressed = false;
        Texture normalTexture = new Texture(normalTextureFile);
        Texture pressedTexture = new Texture(pressedTextureFile);

        normalSprite = new Sprite(normalTexture);
        normalSprite.setSize(width, height);
        normalSprite.setPosition(xPos, yPos);

        pressedSprite = new Sprite(pressedTexture);
        pressedSprite.setSize(width, height);
        pressedSprite.setPosition(xPos, yPos);

    }

    public void onPress() {
        isPressed = true;
        System.out.println("on press now " + isPressed + " for " + name);
    }

    /**
     * Performs the action that should happen upon press of button
     */
    public void press() {
        pressedTime = PRESSED_DURATION;
        System.out.println(name + " was clicked");
    }

    public void onRelease() {
        System.out.println("inside on release in " + name + " with ispressed: " + isPressed);
        if (isPressed) {
            press();
            System.out.println("pressed function executed");
        }
        isPressed = false;
        System.out.println("on press now false");
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

    public Sprite getNormalSprite() {
        return normalSprite;
    }

    public void setNormalSprite(Sprite normalSprite) {
        this.normalSprite = normalSprite;
    }

    public Sprite getPressedSprite() {
        return pressedSprite;
    }

    public void setPressedSprite(Sprite pressedSprite) {
        this.pressedSprite = pressedSprite;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public float getPressedTime() {
        return pressedTime;
    }

    public void setPressedTime(float pressedTime) {
        this.pressedTime = pressedTime;
    }

    public static float getPressedDuration() {
        return PRESSED_DURATION;
    }   

    
}
