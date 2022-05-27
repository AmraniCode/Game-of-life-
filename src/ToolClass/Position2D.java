/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolClass;

import Interfaces.IPosition;
import javax.swing.text.Position;

/**
 *
 * @author chaki
 */
public class Position2D implements IPosition {
 private int x;
  
  private int y;
  
  public Position2D(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public int getX() {
    return this.x;
  }
  
  public int getY() {
    return this.y;
  }
  
  public int getZ() {
    return 0;
  }

}
