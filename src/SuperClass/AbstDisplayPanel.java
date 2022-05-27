/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperClass;

import Interfaces.ICell;
import javax.swing.JPanel;

/**
 *
 * @author chaki
 */
public abstract  class AbstDisplayPanel extends JPanel {
    protected   int DIM_WIDTH = 16;
    protected   int DIM_HEIGHT = 64;
    protected   int SQ_SIZE = 10;
    protected   int nbrH =0;
    protected   int nbrV =0;
    protected boolean [][] states =null ;
    public ICell [][] cells;

    public void initPanel(AbstWorldGame world,int DIM_WIDTH,int DIM_HEIGHT,int SQ_SIZE){
        this.SQ_SIZE=SQ_SIZE;
        this.cells = world.getCells();
        this.DIM_HEIGHT=DIM_HEIGHT;
        this.DIM_WIDTH=DIM_WIDTH;

        this.nbrH = DIM_WIDTH / SQ_SIZE;
        this.nbrV = DIM_HEIGHT / SQ_SIZE;
    }

}
