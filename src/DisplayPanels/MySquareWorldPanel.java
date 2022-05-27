/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DisplayPanels;

import Interfaces.CELL_TYPE;
import Lookup.PanelLocator;
import SuperClass.AbstDisplayPanel;
import Interfaces.ICell;
import Interfaces.IWorld;
import SuperClass.AbstWorldGame;
import ToolClass.Position2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author chaki
 */
public class MySquareWorldPanel extends AbstDisplayPanel{
    static {
        PanelLocator.subscribe(new MySquareWorldPanel(), CELL_TYPE.SQUARE);
    }
    
    /**
     *
     * @param world
     * @param DIM_WIDTH
     * @param DIM_HEIGHT
     * @param SQ_SIZE
     * @throws InterruptedException
     */
    @Override
    public void initPanel(AbstWorldGame world,int DIM_WIDTH,int DIM_HEIGHT,int SQ_SIZE){
        super.initPanel(world,DIM_WIDTH,DIM_HEIGHT,SQ_SIZE);
         this.addMouseListener(new MouseAdapter() {
            @Override
            public synchronized void mouseClicked(MouseEvent e) {                
               int x=e.getX()/SQ_SIZE;
               int y=e.getY()/SQ_SIZE;
               System.out.println("mouse in x: "+x+" y : "+y);
               world.changeState(x,y);
               validate();
               repaint();
            }
        });

       
    }
    
  
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
       
        //paint in to the panel the new update 
        for (int i = 0; i < nbrV; i += 1) {
            for (int j = 0; j < nbrH; j += 1) {
                    int posx = i/SQ_SIZE;
                    int posy = j/SQ_SIZE;
                    switch(this.cells[i][j].getState()){
                        case ALIVE :
                            g.setColor(Color.red);
                            g.fillRect( i*SQ_SIZE, j*SQ_SIZE, SQ_SIZE, SQ_SIZE );
                            g.setColor(Color.BLACK);
                            g.fillRect( i*SQ_SIZE + (SQ_SIZE-SQ_SIZE/2)/2, j*SQ_SIZE + (SQ_SIZE-SQ_SIZE/2)/2, SQ_SIZE/2,SQ_SIZE/2);
                            break;
                        case DEAD : 
                            g.setColor(Color.WHITE);
                            g.fillRect( i*SQ_SIZE, j*SQ_SIZE, SQ_SIZE, SQ_SIZE );
                            g.setColor(Color.BLACK);
                            break ;
                        case CONTAMINATED: 
                            g.setColor(Color.blue);
                            g.fillRect( i*SQ_SIZE, j*SQ_SIZE, SQ_SIZE, SQ_SIZE );
                            g.setColor(Color.MAGENTA);
                            g.fillRect( i*SQ_SIZE + (SQ_SIZE-SQ_SIZE/2)/2, j*SQ_SIZE + (SQ_SIZE-SQ_SIZE/2)/2, SQ_SIZE/2,SQ_SIZE/2);
                            break;
                        case WALL : 
                            g.setColor(Color.BLACK);
                            g.fillRect( i*SQ_SIZE, j*SQ_SIZE, SQ_SIZE, SQ_SIZE );
                            break;
                    
                    }
                    g.drawString("state",10,10);
                    
            }
        }
         
    }

    
}
