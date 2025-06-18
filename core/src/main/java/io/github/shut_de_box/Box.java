package io.github.shut_de_box;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box {
    private ArrayList<Tile> tiles;
    private Die[] dice;
    private int currentlyThrown;

    public Box() {
        List<String> tileFiles = List.of("tiles/tile_1.png","tiles/tile_2.png","tiles/tile_3.png","tiles/tile_4.png","tiles/tile_5.png","tiles/tile_6.png","tiles/tile_7.png","tiles/tile_8.png","tiles/tile_9.png");
        tiles = new ArrayList<>();
        int initialTileLoc = 120;
        for (int i = 0; i < 9; i++) {
            tiles.add(new Tile(tileFiles.get(i), 120 + 60 * i));
        }
        // TODO create dice
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
