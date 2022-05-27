/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cells;

import Interfaces.ICell;
import Interfaces.IPosition;
import Interfaces.IWorld;
import SuperClass.AbsCellClass;
import ToolClass.Position2D;
import java.util.List;

/**
 *
 * @author ISMAIL
 */
public class CellSquare extends AbsCellClass {

    public CellSquare(IWorld w, Position2D p, State state){
       this.pos = p;
       this.world = w;
       this.currentState = state;
    }
  


    @Override
    public void beforeEvolve() {
        int livingNeighbors = 0;
        List<ICell> cells = this.world.getNeigbors(this);
        livingNeighbors = (int)cells.stream().filter(c -> (c.getState() == State.ALIVE)).count();
        switch (this.currentState) {
          case ALIVE:
            if (livingNeighbors == 2 || livingNeighbors == 3) {
              this.futureState = this.currentState;
              break;
            } 
            this.futureState = State.DEAD;
            break;
          case DEAD:
            if (livingNeighbors == 3) {
              this.futureState = State.ALIVE;
              break;
            } 
            this.futureState = this.currentState;
            break;
            case WALL : this.futureState =this.currentState;
        } 
    }


    
}
