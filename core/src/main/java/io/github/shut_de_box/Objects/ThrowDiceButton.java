package io.github.shut_de_box.Objects;


public class ThrowDiceButton extends AbstractButton{
    public ThrowDiceButton(String name, String normalTextureFile, String pressedTextureFile, int xPos, int yPos, int width, int height, Box box) {
        super(name, normalTextureFile, pressedTextureFile, xPos, yPos, width, height, box);
    }

    @Override
    public void press() {
        setToPressed();
        pressedTime = PRESSED_DURATION;
        box.rollDice();
    }
    
}
