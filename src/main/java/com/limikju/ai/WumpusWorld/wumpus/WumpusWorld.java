package com.limikju.ai.WumpusWorld.wumpus;

import com.limikju.ai.WumpusWorld.Return;
import com.limikju.ai.WumpusWorld.ReturnState;

import java.util.ArrayList;
import java.util.List;

public class WumpusWorld {
    public Return get() {

        Return ret = new Return();

        WorldMap worldMap = MapGenerator.getRandomMap((int)System.currentTimeMillis());

        World world = worldMap.generateWorld();
        int actions = 0;
        Agent a = new Agent(world);
        while (!world.gameOver()) {
            if (actions > 100) {
                return null;
            }
            ReturnState returnState = new ReturnState(world);

            a.doAction();

            ret.states.add(returnState);

            actions++;
        }

        String[][] w = world.getW();

        AStarNode initialNode = new AStarNode(world.getPlayerX(), world.getPlayerY());
        AStarNode finalNode = new AStarNode(1, 1);
        int rows = 5;
        int cols = 5;
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);

        List<List<Integer>> blocksArray = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                String cell = w[i][j];
                if (cell.contains("U") || cell.contains("P") || cell.contains("Q")) {
                    ArrayList<Integer> objects = new ArrayList<>();
                    objects.add(i);
                    objects.add(j);
                    blocksArray.add(objects);
                }
            }
        }
        aStar.setBlocks(blocksArray);

        List<AStarNode> path = aStar.findPath();

        for (AStarNode node : path) {
            ReturnState returnState = new ReturnState(world);
            returnState.setAgentX(node.getRow()-1);
            returnState.setAgentY(node.getCol()-1);
            ret.states.add(returnState);
            System.out.println(node);
        }

        actions++;
        int score = world.getScore();

        System.out.println("Simulation ended after " + actions + " actions. Score " + score);
        ret.score = score;
        return ret;
    }
}
