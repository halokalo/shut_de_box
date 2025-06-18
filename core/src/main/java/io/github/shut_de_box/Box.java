package io.github.shut_de_box;

import java.util.ArrayList;
import java.util.Arrays;

public class Box {
    private ArrayList<Tile> tiles;
    private Die[] dice;
    private int currentlyThrown;

    public Box() {
        tiles = new ArrayList<>();
        Tile tile1 = new Tile("tiles/tile_1.png", 120);
        tiles.add(tile1);
        // TODO create all tiles
        // TODO create all dice
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Die[] getDice() {
        return dice;
    }    

    @Override
    public String toString() {
        return "Box [tiles=" + tiles + ", dice=" + Arrays.toString(dice) + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tiles == null) ? 0 : tiles.hashCode());
        result = prime * result + Arrays.hashCode(dice);
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
        Box other = (Box) obj;
        if (tiles == null) {
            if (other.tiles != null)
                return false;
        } else if (!tiles.equals(other.tiles))
            return false;
        if (!Arrays.equals(dice, other.dice))
            return false;
        return true;
    }
    
}
