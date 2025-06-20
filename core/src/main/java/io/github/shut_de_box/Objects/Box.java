package io.github.shut_de_box.Objects;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import io.github.shut_de_box.util.Util;

public class Box {
    private ArrayList<Tile> tiles;
    private Die[] dice;
    private int currentlyThrown = 0;

    private boolean canRollDice = true;
    private ArrayList<Integer> flippedTileIndices = new ArrayList<>();

    public Box() {
        List<String> tileFiles = List.of("tiles/tile_1.png","tiles/tile_2.png","tiles/tile_3.png","tiles/tile_4.png","tiles/tile_5.png","tiles/tile_6.png","tiles/tile_7.png","tiles/tile_8.png","tiles/tile_9.png");
        tiles = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            tiles.add(new Tile(tileFiles.get(i), (float)(120 + 60 * i)));
        }
        dice = new Die[2];
        dice[0] = new Die(250f, 100f);
        dice[1] = new Die(350f, 100f);
    }

    public boolean rollDice(){
        System.out.println("Flipped value: " + getValueFlipped());
        if (!canRollDice) return false;
        else {
            lockFlipped();
            flippedTileIndices.clear();
            currentlyThrown = dice[0].roll() + dice[1].roll(); // roll dice
            checkForEndOfGame();
            canRollDice = false;
            System.out.println("Rolled: " + currentlyThrown);
            return true;
        }
    }

    public void checkForEndOfGame(){
        ArrayList<Integer> nonLockedTileValues = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
           if (tiles.get(i).isLocked()) continue;
            nonLockedTileValues.add(i + 1);
        }
        int[] nonLockedTileValuesArray = new int[nonLockedTileValues.size()];
        for (int i = 0; i < nonLockedTileValues.size(); i++) {
            nonLockedTileValuesArray[i] = nonLockedTileValues.get(i);
        }

        boolean stillPossible = Util.isSubsetSum1Or2(nonLockedTileValuesArray, currentlyThrown);
        if (!stillPossible) {
            System.out.println("Klaar");
        }
    }

    public boolean flipTile(int index) {
        Tile tile = tiles.get(index);
        
        if (canRollDice) return false;
        if (tile.isLocked()) return false;

        if (tile.isClosed()) {
            tile.flip();
            flippedTileIndices.remove((Integer) index);
            updateCanRollDice();
            return true;
        }
        if (flippedTileIndices.size() == 2) return false;
        
        tile.flip();
        flippedTileIndices.add(index);
        updateCanRollDice();
        return true;
    }

    public int getValueFlipped() {
        int res = 0;
        for (Integer value : flippedTileIndices) {
            res += value + 1;
        }
        return res;
    }

    public void lockFlipped() {
        for (Integer index : flippedTileIndices) {
            tiles.get(index).lock();
        }
    }

    public boolean updateCanRollDice() {
        canRollDice = getCurrentlyThrown() == getValueFlipped() && flippedTileIndices.size() > 0;
        return canRollDice;
    }

    public ArrayList<Integer> getFlippedIndices(){
        return flippedTileIndices;
    }

    public int getCurrentlyThrown() {
        return currentlyThrown;
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

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public void setDice(Die[] dice) {
        this.dice = dice;
    }

    public void setCurrentlyThrown(int currentlyThrown) {
        this.currentlyThrown = currentlyThrown;
    }

    public void setCanRollDice(boolean canRollDice) {
        this.canRollDice = canRollDice;
    }
    
}
