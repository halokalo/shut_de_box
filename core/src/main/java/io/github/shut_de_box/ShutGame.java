package io.github.shut_de_box;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.shut_de_box.Objects.*;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class ShutGame extends ApplicationAdapter implements InputProcessor{
    private SpriteBatch batch;
    BitmapFont font;

    private Music backgroundSound;
    private Texture backgroundTexture;
    private Box box;

    private Viewport viewport;

    private Vector2 clickPos;

    private ArrayList<AbstractButton> buttons = new ArrayList<>();
    private ArrayList<Popup> popups = new ArrayList<>();

    @Override
    public void create() {
        font = new BitmapFont();
        Gdx.input.setInputProcessor(this);
        backgroundSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/temple_os_hymn.mp3"));
        backgroundSound.setVolume(0.5f);
        // backgroundSound.play();
        batch = new SpriteBatch();
        backgroundTexture = new Texture("achtergrond.png");

        box = new Box();
        ThrowDiceButton throwDiceButton = new ThrowDiceButton("Dice button", "buttons/throw_dice_button/normal.png", 
            "buttons/throw_dice_button/pressed.png", 800, 240, 102, 63, box);
        RestartButton restartButton = new RestartButton("Restart button", "buttons/restart_button/normal.png", 
            "buttons/restart_button/pressed.png", 800, 167, 102, 63, box, 
            this::reset);
        buttons.add(throwDiceButton);
        buttons.add(restartButton);
        
        viewport = new FitViewport(1028f, 480f);
        clickPos = new Vector2();
    }

    @Override
    public void render() {
                ArrayList<Tile> tiles = box.getTiles();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
        
        // Draw all items
        for (Tile tile : tiles) { tile.draw(batch);}
        for (AbstractButton button : buttons) { button.draw(batch);}
        for (Die die : box.getDice()) { die.draw(batch);}
        for (Popup popup : popups) { popup.draw(batch, font);}
        // Draw all items

        int score = box.getRemainingValue();
        if (box.isDone()) {
            handleFinishedGame(score);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundTexture.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); // true centers the camera
    }

    public void reset() {
        batch = new SpriteBatch();

        box = new Box();

        for (AbstractButton button : buttons) {
            button.setBox(box);
        }
    }

    public void handleFinishedGame(int score) {
        if (score == 0) {
                font.draw(batch, "Gewonnen", 500, 200);
            } else if (score == 37) {
                font.draw(batch, "Staatslegende juttutu juttutu " + score, 500, 200);
            } else if (score < 10){
                font.draw(batch, "niet super slecht: " + score, 500, 200);
            } else if (score < 20) {
                font.draw(batch, "lol slecht: " + score, 500, 200);
            } else {
                font.draw(batch, "loser: " + score, 500, 200);
            }
    }
    
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        clickPos.set(screenX, screenY);
        viewport.unproject(clickPos);
        for (AbstractButton b : buttons) {
            if (b.getCurrentSprite().getBoundingRectangle().contains(clickPos)) {
                b.onPress();
            }
        }
        for (int i = 0; i < box.getTiles().size(); i++) {
                Tile tile = box.getTiles().get(i);
                if (tile.getCurrentSprite().getBoundingRectangle().contains(clickPos)){
                    box.flipTile(i);
                }
            }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        clickPos.set(screenX, screenY);
        viewport.unproject(clickPos);
        for (AbstractButton b : buttons) {
            b.onRelease();
        }
        return true;
    }

    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }
    @Override public boolean touchCancelled(int x, int y, int pointer, int button) { return false;}
}
    
