package io.github.shut_de_box.Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import io.github.shut_de_box.util.Util;

public class Box {
    private ArrayList<Tile> tiles;
    private ArrayList<Die> dice;
    private int currentlyThrown = 0;
    private boolean isDone = false;
    private boolean canRollDice = true;
    private ArrayList<Integer> flippedTileIndices = new ArrayList<>();

    public Box() {
        List<String> tileFiles = List.of("tiles/tile_1.png","tiles/tile_2.png","tiles/tile_3.png","tiles/tile_4.png","tiles/tile_5.png","tiles/tile_6.png","tiles/tile_7.png","tiles/tile_8.png","tiles/tile_9.png");
        tiles = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            tiles.add(new Tile(tileFiles.get(i), (float)(120 + 60 * i)));
        }
        dice = new ArrayList<>();
        dice.add(new Die(250f, 100f));
        dice.add(new Die(350f, 100f));
    }

    public boolean rollDice(){
        if (!canRollDice) return false;
        else {
            lockFlipped();
            flippedTileIndices.clear();
            currentlyThrown = 0;
            for (Die die : dice) {
                currentlyThrown += die.roll();
            }
            checkGameStatus();
            canRollDice = false;
            System.out.println("Rolled: " + currentlyThrown);
            return true;
        }
    }

    public void checkGameStatus(){
        ArrayList<Integer> nonLockedTileValues = new ArrayList<>();
        int remainingValue = getRemainingValue();
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
            isDone = true;
            System.out.println("Klaar");
            if (remainingValue == 0) {
                System.out.println("Gewonnen");
            }
            for (Tile tile : tiles) {tile.lock();}
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
        checkGameStatus();
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

    public int getRemainingValue() {
        int res = 0; 
        for (int i = 0; i < 9; i++) {
            if (!tiles.get(i).isClosed()) {
                res += i + 1;
            }
        }
        return res;
    }

    public boolean isDone() {return isDone;}

    public ArrayList<Integer> getFlippedIndices(){
        return flippedTileIndices;
    }

    public int getCurrentlyThrown() {
        return currentlyThrown;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public ArrayList<Die> getDice() {
        return dice;
    }

    public void setDice(ArrayList<Die> dice) {
        this.dice = dice;
    }

    public void setCurrentlyThrown(int currentlyThrown) {
        this.currentlyThrown = currentlyThrown;
    }

    public void setCanRollDice(boolean canRollDice) {
        this.canRollDice = canRollDice;
    }
    
}
