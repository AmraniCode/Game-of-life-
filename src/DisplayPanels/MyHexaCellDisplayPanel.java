package DisplayPanels;

import Cells.HexaCell;
import Interfaces.CELL_TYPE;
import Lookup.PanelLocator;
import SuperClass.AbstDisplayPanel;
import SuperClass.AbstWorldGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class MyHexaCellDisplayPanel extends AbstDisplayPanel {
    static {
        PanelLocator.subscribe(new MyHexaCellDisplayPanel(), CELL_TYPE.HEXAGONAL);
    }
    @Override
    public void initPanel(AbstWorldGame world, int DIM_WIDTH, int DIM_HEIGHT, int SQ_SIZE) {
        super.initPanel(world, DIM_WIDTH, DIM_HEIGHT, SQ_SIZE);
            this.addMouseListener(new MouseAdapter() {
                @Override
                public synchronized void mouseClicked(MouseEvent e) {
                    int decalage = 0 ;
                    int y=(e.getY())/SQ_SIZE;
                    if(y%2 != 0){
                        decalage = SQ_SIZE/2 ;
                    }
                    int x=(e.getX()- decalage)/SQ_SIZE;

                    System.out.println("mouse in x: "+x+" y : "+y+" ");
                    if(cells[x][y]!= null){
                        //System.out.println("mouse in x: "+x+" y : "+y+" "+cells[x][y].getState());
                    }else{
                        // System.out.println("this cell is NULL");
                    }
                    world.changeState(x,y);
                    validate();
                    repaint();
                }
            });

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int segment =6;
        double angle = 9.40;
        int  distance_centre = SQ_SIZE/2 ;
        boolean pair = true ;
        int decalage = 0 ;


        for (int i = SQ_SIZE/2 ; i < DIM_WIDTH; i += SQ_SIZE) {
            for (int j = SQ_SIZE/2; j < DIM_HEIGHT; j += SQ_SIZE) {
                int posx = (i-SQ_SIZE/2)/SQ_SIZE;
                int posy = (j-SQ_SIZE/2)/SQ_SIZE;
                Polygon p = new Polygon();
                for (int k = 0; k < segment; k++){
                    p.addPoint((int) (i+decalage + distance_centre * Math.cos((angle + (k * 2 * Math.PI)) / segment)),
                            (int) (j + distance_centre * Math.sin((angle + (k * 2 * Math.PI))/ segment)));
                }

//                    for (int k = 0; k < segment; k++){
//                        p.addPoint((int) (decalage+distance_centre*coefx*posx + distance_centre * Math.cos((angle + (k * 2 * Math.PI)) / segment)),
//                             (int) (distance_centre*coefy*posy + distance_centre * Math.sin((angle + (k * 2 * Math.PI))/ segment)));
//                    }

                System.out.println(cells);
                switch(this.cells[posx][posy].getState()){
                    case ALIVE:
                        g.setColor(Color.RED);
                        break;
                    case DEAD:
                        g.setColor(Color.GRAY);
                        break;
                    case WALL:
                        g.setColor(Color.GREEN);
                        break;
                }
                g.fillPolygon(p);
                g.setColor(Color.BLACK);
                // g.drawPolygon(p);
                if(pair){
                    decalage = (distance_centre) ;
                    pair = false ;
                }else{
                    decalage = 0 ;
                    pair = true;
                }
            }

        }


    }



}
