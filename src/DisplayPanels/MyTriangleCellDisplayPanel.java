package DisplayPanels;

import Interfaces.CELL_TYPE;
import Lookup.PanelLocator;
import SuperClass.AbstDisplayPanel;
import SuperClass.AbstWorldGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.Random;

public class MyTriangleCellDisplayPanel extends AbstDisplayPanel {
    static {
        PanelLocator.subscribe(new MyTriangleCellDisplayPanel(), CELL_TYPE.TRIANGLE);
    }

    public void initPanel(AbstWorldGame world, int DIM_WIDTH, int DIM_HEIGHT, int SQ_SIZE) {
        super.initPanel(world, DIM_WIDTH, DIM_HEIGHT, SQ_SIZE);
        this.addMouseListener(new MouseAdapter() {

        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int points = 3 ;
        Random rand = new Random();
        boolean pair_y =true;        //paint in to the panel the new update*
        for (int j = 0 ; j < this.nbrV*SQ_SIZE; j=j+SQ_SIZE)
        {

            if(pair_y)
            {
                boolean pair_x = true;
                for (int i =0 ; i < this.nbrH*SQ_SIZE; i=i+SQ_SIZE)
                {
                    int x = i/SQ_SIZE;
                    int y = j/SQ_SIZE;

                    switch (this.cells[x][y].getState()){
                        case ALIVE:
                            g.setColor(Color.BLUE);
                            break;
                        case DEAD:
                            g.setColor(Color.GRAY);
                            break;
                        case WALL:break;
                    }

                    if(pair_x)
                    {

                        int[] px_up = { i +1  , SQ_SIZE + i , i + 2*SQ_SIZE -1 };
                        int[] py_up = {j + SQ_SIZE -1   , j +1  , j + SQ_SIZE  -1 };
                        Polygon p_up = new Polygon(px_up,py_up,points);
                        g.fillPolygon(p_up);
                    }
                    else{
                        int[] px_down = { i +1 , i + SQ_SIZE ,i +  2*SQ_SIZE -1 };
                        int[] py_down = {j +1 , j + SQ_SIZE-1 , j +1 };
                        Polygon p_down = new Polygon(px_down,py_down,points);
                        g.fillPolygon(p_down);


                    }
                    pair_x = !pair_x;
                }


            }else{
                boolean pair_x = true;
                for (int i =0 ; i < this.nbrH*SQ_SIZE; i=i+SQ_SIZE)
                {
                    int x = i/SQ_SIZE;
                    int y = j/SQ_SIZE;

                    if(pair_x)
                    {

                        int[] px_down = { i +1 , SQ_SIZE + i , i + 2*SQ_SIZE -1};
                        int[] py_down = {j +1  , j + SQ_SIZE-1, j +1 };
                        Polygon p_down = new Polygon(px_down,py_down,points);
                        switch (this.cells[x][y].getState()){
                            case ALIVE:
                                g.setColor(Color.BLUE);
                                break;
                            case DEAD:
                                g.setColor(Color.GRAY);
                                break;
                            case WALL:break;
                        }
                        g.fillPolygon(p_down);


                    }
                    else{
                        int[] px_up = { i + 1, i + SQ_SIZE ,i +  2*SQ_SIZE -1};
                        int[] py_up = {j + SQ_SIZE-1, j +1 , j + SQ_SIZE-1 };
                        Polygon p_up = new Polygon(px_up,py_up,points);
                        switch (this.cells[x][y].getState()){
                            case ALIVE:
                                g.setColor(Color.BLUE);
                                break;
                            case DEAD:
                                g.setColor(Color.GRAY);
                                break;
                            case WALL:break;
                        }
                        g.fillPolygon(p_up);


                    }
                    pair_x = !pair_x;
                }
            }

            pair_y = !pair_y;
        }

    }


}
