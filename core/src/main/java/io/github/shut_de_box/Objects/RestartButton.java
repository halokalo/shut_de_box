package io.github.shut_de_box.Objects;

import io.github.shut_de_box.ShutGame;

public class RestartButton extends AbstractButton {
    private Runnable resetRunnable;

    public RestartButton(String name, String normalTextureFile, String pressedTextureFile, int xPos, int yPos, 
            int width, int height, Box box, Runnable resetRunnable){
        super(name, normalTextureFile, pressedTextureFile, xPos, yPos, width, height, box);
        this.resetRunnable = resetRunnable;
    }

    @Override
    public void press(){
        setToPressed();
        pressedTime = PRESSED_DURATION;
        if (resetRunnable != null) resetRunnable.run();
    }
}
