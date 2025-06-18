package io.github.shut_de_box.Objects;

import org.w3c.dom.Text;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tile {
    private boolean isClosed;
    private boolean isLocked;
    private Sprite openedSprite;
    private Sprite closedSprite = new Sprite(new Texture("closed_tile.png"));
    private Sprite currentSprite;

    /**
     * Creates a new Tile with a file name and position.
     * @param filepath name of the png file 
     * @param x x-position of the sprite
     */
    public Tile(String filepath, int x){        
        Texture texture = new Texture(filepath);
        openedSprite = new Sprite(texture);
        openedSprite.setSize(50, 80);
        openedSprite.setPosition(x,370);
        
        currentSprite = openedSprite;

        closedSprite.setSize(50, 80);
        closedSprite.setPosition(x, 290);

        isClosed = false;
        isLocked = false;
    }

    /**
     * Flips the tile from down to up or vise-versa
     */
    public void flip(){
        if (!isLocked) {
            if (!isClosed) {
                System.out.println("Opened tile was clicked");
                this.setCurrentSprite(closedSprite);
            } else {
                System.out.println("Closed tile was clicked");
                this.setCurrentSprite(openedSprite);
            }
            isClosed = !isClosed;
        }
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

    @Override
    public String toString() {
        return "Tile [isFlipped=" + isClosed + ", sprite=" + currentSprite + "]";
    }
}

 