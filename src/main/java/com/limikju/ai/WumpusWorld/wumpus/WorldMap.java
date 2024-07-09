package com.limikju.ai.WumpusWorld.wumpus;

import java.awt.*;
import java.util.Vector;

public class WorldMap {
    private int size;
    private Point wumpus;
    private Point gold;
    private Vector<Point> pits;

    public WorldMap(int size) {
        this.size = size;
        pits = new Vector<Point>();
    }

    public void addWumpus(int x, int y)
    {
        wumpus = new Point(x,y);
    }

    public void addGold(int x, int y)
    {
        gold = new Point(x,y);
    }

    public void addPit(int x, int y)
    {
        pits.add(new Point(x,y));
    }

    public boolean hasPit(int x, int y) {
        for (Point p:pits) {
            if (p.x == x && p.y == y) {
                return true;
            }
        }
        return false;
    }

    public boolean hasGold(int x, int y) {
        if (gold != null) {
            if (gold.x == x && gold.y == y) {
                return true;
            }
        }
        return false;
    }

    public boolean hasWumpus(int x, int y) {
        if (wumpus != null) {
            if (wumpus.x == x && wumpus.y == y) {
                return true;
            }
        }
        return false;
    }

    public World generateWorld() {
        World w = new World(size);
        w.addWumpus(wumpus.x, wumpus.y);
        w.addGold(gold.x, gold.y);
        for (int i = 0; i < pits.size(); i++)
            w.addPit(pits.get(i).x, pits.get(i).y);
        return w;
    }
}
