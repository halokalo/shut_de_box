package io.github.shut_de_box.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
    private boolean isClosed;
    private boolean isLocked;
    private Sprite openedSprite;
    private Sprite closedSprite = new Sprite(new Texture("closed_tile.png"));
    private Sprite currentSprite;

    /**
     * Creates a new Tile with a file name and position.
     * @param filepath name of the png file 
     * @param xPos x-position of the sprite
     */
    public Tile(String filepath, float xPos){        
        Texture texture = new Texture(filepath);
        openedSprite = new Sprite(texture);
        openedSprite.setSize(50f, 80f);
        openedSprite.setPosition(xPos,370f);
        
        currentSprite = openedSprite;

        closedSprite.setSize(50f, 80f);
        closedSprite.setPosition(xPos, 290f);

        isClosed = false;
        isLocked = false;
    }

    /**
     * Flips the tile from down to up or vise-versa
     */
    public void flip(){
        if (!isLocked) {
            if (!isClosed) {
                this.setCurrentSprite(closedSprite);
            } else {
                this.setCurrentSprite(openedSprite);
            }
            isClosed = !isClosed;
        }
    }

    public void draw(SpriteBatch batch) {
        getCurrentSprite().draw(batch);
    }

    /**
     * Locks a tile to keep it from being flipped again
     */
    public void lock(){
        isLocked = true;
    }

    /**
     * returns whether the tile is flipped
     * @return boolean
     */
    public boolean isClosed() {
        return this.isClosed;
    }

    public boolean isLocked(){
        return this.isLocked;
    }

    /**
     * returns the tile's sprite
     * @return Sprite
     */
    public Sprite getCurrentSprite() {
        return this.currentSprite;
    }

    /**
     * sets the sprite of a tile
     * @param sprite
     */
    public void setCurrentSprite(Sprite sprite) {
        this.currentSprite = sprite;
    }

    public void close() {
        isClosed = true;
    }

    @Override
    public String toString() {
        return "Tile [isFlipped=" + isClosed + ", sprite=" + currentSprite + "]";
    }
}

 