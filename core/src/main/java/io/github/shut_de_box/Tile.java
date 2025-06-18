package io.github.shut_de_box;

import org.w3c.dom.Text;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tile {
    private boolean isClosed;
    private boolean isLocked;
    private Sprite closedSprite = new Sprite(new Texture("closed_tile.png"));
    private Texture texture;
    private Sprite sprite;

    /**
     * Creates a new Tile with a file name and position.
     * @param filepath name of the png file 
     * @param x x-position of the sprite
     */
    public Tile(String filepath, int x){        
        texture = new Texture(filepath);
        sprite = new Sprite(texture);
        sprite.setSize(50, 80);
        sprite.setPosition(x,370);

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
                this.setSprite(closedSprite);
            } else {
                this.setSprite(sprite);
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

    /**
     * returns the tile's texture
     * @return Texture
     */
    public Texture getTexture(){
        return this.texture;
    }

    /**
     * returns the tile's sprite
     * @return Sprite
     */
    public Sprite getSprite() {
        return this.sprite;
    }

    /**
     * sets the sprite of a tile
     * @param sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public String toString() {
        return "Tile [isFlipped=" + isClosed + ", texture=" + texture + ", sprite=" + sprite + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (isClosed ? 1231 : 1237);
        result = prime * result + ((texture == null) ? 0 : texture.hashCode());
        result = prime * result + ((sprite == null) ? 0 : sprite.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (isClosed != other.isClosed)
            return false;
        if (texture == null) {
            if (other.texture != null)
                return false;
        } else if (!texture.equals(other.texture))
            return false;
        if (sprite == null) {
            if (other.sprite != null)
                return false;
        } else if (!sprite.equals(other.sprite))
            return false;
        return true;
    }

    
}
