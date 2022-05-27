package Cells;

import Interfaces.ICell;
import SuperClass.AbsCellClass;

import java.util.List;

public class TriangleCell extends AbsCellClass {
    @Override
    public void beforeEvolve() {
        int livingNeighbors = 0;
        List<ICell> cells = this.world.getNeigbors(this);
        livingNeighbors = (int)cells.stream().filter(c -> (c.getState() == ICell.State.ALIVE)).count();
        switch (this.currentState) {
            case ALIVE:
                if (livingNeighbors == 2 || livingNeighbors == 3) {
                    this.futureState = this.currentState;
                    break;
                }
                this.futureState = ICell.State.DEAD;
                break;
            case DEAD:
                if (livingNeighbors == 3) {
                    this.futureState = ICell.State.ALIVE;
                    break;
                }
                this.futureState = this.currentState;
                break;
            case WALL: this.futureState=this.currentState;
        }
    }
}
