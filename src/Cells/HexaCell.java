package Cells;

import Interfaces.ICell;
import Interfaces.IPosition;
import Interfaces.IWorld;
import SuperClass.AbsCellClass;
import ToolClass.Position2D;

import java.util.List;

public class HexaCell extends AbsCellClass {


    public HexaCell(IWorld w,Position2D p,ICell.State state){
        this.pos = p;
        this.world = w;
        this.currentState = state;
    }



    @Override
    public void beforeEvolve() {
        int livingNeighbors = 0;
        List<ICell> cells = this.world.getNeigbors(this);
        livingNeighbors = (int)cells.stream().filter(c -> (c.getState() == ICell.State.ALIVE)).count();
        switch (this.currentState) {
            case ALIVE:
                if (livingNeighbors == 2 || livingNeighbors == 3) {
                    this.futureState = this.currentState;
                    //  System.out.println(ICell.State.ALIVE);
                    break;
                }
                this.futureState = ICell.State.DEAD;
                // System.out.println(ICell.State.DEAD);
                break;
            case DEAD:
                if (livingNeighbors == 3) {
                    this.futureState = ICell.State.ALIVE;
                    //System.out.println(ICell.State.ALIVE);

                    break;
                }
                this.futureState = this.currentState;
                //    System.out.println("=>"+ICell.State.DEAD);
                break;
            case WALL: this.futureState = this.currentState;
        }
    }


}
