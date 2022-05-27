/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolClass;

import Interfaces.CELL_TYPE;
import Interfaces.WORLD_TYPE;

/**
 *
 * @author chaki
 */
public class Parameter {
       public int speed ;
       public int width ;
       public int height ;
       public float threshold;
       public WORLD_TYPE world ;
       public CELL_TYPE cell;

    public Parameter(WORLD_TYPE world ,CELL_TYPE cell,int speed, int width, int height, float threshold) {
        this.speed = speed;
        this.width = width;
        this.height = height;
        this.threshold = threshold;
        this.world = world;
        this.cell = cell ;
        
    }
       
      
}
