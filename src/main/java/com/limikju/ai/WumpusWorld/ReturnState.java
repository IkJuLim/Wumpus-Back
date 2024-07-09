package com.limikju.ai.WumpusWorld;

import com.limikju.ai.WumpusWorld.enums.Direction;
import com.limikju.ai.WumpusWorld.wumpus.World;
import lombok.Setter;

public class ReturnState {
    public Direction agentDirection;

    public String[][] cave = new String[4][4];

    @Setter
    public int agentX;
    @Setter
    public int agentY;

    public ReturnState(World world){
        String[][] w = world.getW();

        for (int i = 1; i < w.length; i++) {
            for (int j = 1; j < w[i].length; j++) {
                cave[i - 1][j - 1] = w[i][j];
            }
        }

        agentX = world.getPlayerX() - 1;
        agentY = world.getPlayerY() - 1;
        if(world.getDirection() == 0)
            agentDirection = Direction.UP;
        else if(world.getDirection() == 1)
            agentDirection = Direction.RIGHT;
        else if(world.getDirection() == 2)
            agentDirection = Direction.DOWN;
        else if(world.getDirection() == 3)
            agentDirection = Direction.LEFT;
    }
}
