package io.github.shut_de_box.Objects;

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
        if (resetRunnable != null) resetRunnable.run();
    }
}
