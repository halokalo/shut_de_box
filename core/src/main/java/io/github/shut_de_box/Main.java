package io.github.shut_de_box;

import java.util.ArrayList;
import java.util.HashSet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.shut_de_box.Objects.AbstractButton;
import io.github.shut_de_box.Objects.Box;
import io.github.shut_de_box.Objects.Die;
import io.github.shut_de_box.Objects.ThrowDiceButton;
import io.github.shut_de_box.Objects.Tile;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.time.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    
    private Texture backgroundTexture;
    private Box box;

    private Viewport viewport;

    private Vector2 clickPos;

    private ArrayList<AbstractButton> buttons;

    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("achtergrond.png");

        box = new Box();

        buttons = new ArrayList<>();
        ThrowDiceButton throwDiceButton = new ThrowDiceButton("Dice button", "buttons/throw_dice_button/normal.png", 
            "buttons/throw_dice_button/pressed.png", 800, 240, 102, 63, box);
        buttons.add(throwDiceButton);
        
        viewport = new FitViewport(1028f, 480f);
        clickPos = new Vector2();
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
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

    private long lastClick = System.currentTimeMillis();

    private void input(){
        if (Gdx.input.isTouched() && System.currentTimeMillis() - lastClick > 50) {
            lastClick = System.currentTimeMillis();
            clickPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(clickPos);

            for (int i = 0; i < box.getTiles().size(); i++) {
                Tile tile = box.getTiles().get(i);
                if (tile.getCurrentSprite().getBoundingRectangle().contains(clickPos)){
                    box.flipTile(i);
                    System.out.println("tile was clicked");
                }
            }

            for (AbstractButton button : buttons) {
                if (button.getCurrentSprite().getBoundingRectangle().contains(clickPos)) {
                        System.out.println("button was pressed");
                        button.press();
                }
            }
        }
    }

    private void logic(){
        float delta = Gdx.graphics.getDeltaTime();
        for (AbstractButton button : buttons) {
            button.update(delta);
        }
    }

    private void draw(){
        ArrayList<Tile> tiles = box.getTiles();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);
        for (Tile curTile : tiles) {
            Sprite sprite = curTile.getCurrentSprite();
            sprite.draw(batch);
        }

        for (AbstractButton button : buttons) {
            Sprite sprite = button.getCurrentSprite();
            sprite.draw(batch);
        }

        Die[] dice = box.getDice();
        dice[0].getCurrentSide().draw(batch);
        dice[1].getCurrentSide().draw(batch);

        batch.end();
    }
}
