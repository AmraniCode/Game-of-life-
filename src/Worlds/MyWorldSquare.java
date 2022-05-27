/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Worlds;

import Interfaces.ICell;

import Cells.CellSquare;
import Interfaces.WORLD_TYPE;
import Lookup.WorldLocator;
import SuperClass.AbstWorldGame;
import ToolClass.Position2D;

import java.util.Properties;


/**
 *
 * @author Chakib
 */
public class MyWorldSquare extends AbstWorldGame{

    static {
        WorldLocator.subscribe(new MyWorldSquare(), WORLD_TYPE.SQUARE);
    }
    @Override
    public void initializeWorld(Properties p) {
       super.initializeWorld(p);
        
        //initialisation des celule possible et leur state 
        this.cells = new ICell[this.nbrH][this.nbrV];
        for (int i = 0; i < this.nbrH; i++) {
          for (int j = 0; j < this.nbrV; j++)
            this.cells[i][j] = new CellSquare(this,new Position2D(i, j), 
                (Math.random() < threshold) ? ICell.State.ALIVE : ICell.State.DEAD); 
        } 
        
        
    }

    
   
    

  
}
