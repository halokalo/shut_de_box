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
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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

    @Override
    public void create() {
        batch = new SpriteBatch();
        backgroundTexture = new Texture("achtergrond.png");

        box = new Box();
        
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
        if (Gdx.input.isTouched() && System.currentTimeMillis() - lastClick > 200) {
            lastClick = System.currentTimeMillis();
            clickPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(clickPos);

            for (Tile tile : box.getTiles()) {
                if (tile.getCurrentSprite().getBoundingRectangle().contains(clickPos)){
                    tile.flip();
                    System.out.println("tile was clicked");
                }
            }
        }
    }

    private void logic(){}

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
        // tiles.get(0).getSprite().draw(batch);
        batch.end();
    }
}
