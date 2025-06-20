package io.github.shut_de_box.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import io.github.shut_de_box.util.*;

public class ThrowDiceButton extends AbstractButton{
    public ThrowDiceButton(String name, String normalTextureFile, String pressedTextureFile, int xPos, int yPos, int width, int height, Box box) {
        super(name, normalTextureFile, pressedTextureFile, xPos, yPos, width, height, box);
    }

    private Sound throwDiceSound = Gdx.audio.newSound(Gdx.files.internal("sounds/throw_dice_distorted.mp3"));
    private Sound aaaahSound = Gdx.audio.newSound(Gdx.files.internal("sounds/aaaah.mp3"));

    @Override
    public void press() {
        System.out.println("dice function executed");
        throwDiceSound.play();
        Util.doWithChance(0.05f, aaaahSound::play);

        setToPressed();
        box.rollDice();
    }
    
}
