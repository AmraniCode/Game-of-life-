package Worlds;

import Cells.CellSquare;
import Interfaces.ICell;
import Interfaces.WORLD_TYPE;
import Lookup.WorldLocator;
import SuperClass.AbstWorldGame;
import ToolClass.Position2D;

import java.util.Properties;

public class MyWorldHexagonal extends AbstWorldGame {
    static {
        WorldLocator.subscribe(new MyWorldHexagonal(), WORLD_TYPE.HEXAGONAL);
    }


    @Override
    public void initializeWorld(Properties p) {
        super.initializeWorld(p);
        this.cells = new ICell[this.nbrH][this.nbrV];
        for (int i = 0; i < this.nbrH; i++) {
            for (int j = 0; j < this.nbrV; j++)
                this.cells[i][j] = new CellSquare(this,new Position2D(i, j),
                        (Math.random() < threshold) ? ICell.State.ALIVE : ICell.State.DEAD);
        }
        MakeHexaWorld();
    }

    @Override
    public void reinitialize() {
        super.reinitialize();
        MakeHexaWorld();
    }



    private void MakeHexaWorld(){
        System.out.println(nbrH+" "+nbrV);
        triangleBottomleft(0,3*nbrH/10,((10*nbrV-3*nbrV)/10), nbrV);
        triangleTopleft(0,3*nbrH/10,0,3*nbrV/10);
        triangleTopRight(((10*nbrH-3*nbrH)/10),nbrH,0,3*nbrV/10);
        triangleBottomRight(((10*nbrH-3*nbrH)/10),nbrH,((10*nbrV-3*nbrV)/10),nbrV);
    }

    private void triangleBottomleft(int row_d,int row_f, int col_d, int col_f){
        int i, j;
        for (i = row_d; i < row_f; i++)
        {
            for (j = col_d; j < col_f; j++) //  (7*nbrV/10)-1 le -1 car on demare de 0 donc 7 -1 =6
            {
                if (i <= j -col_d )
                {
                    this.cells[i][j].setState(ICell.State.WALL);
                }
            }
        }
    }


    private void triangleTopleft(int row_d,int row_f, int col_d, int col_f){
        int i, j;
        for (i = row_d; i < row_f; i++)
        {
            for (j = col_d ; j < col_f-i; j++) //  (7*nbrV/10)-1 le -1 car on demare de 0 donc 7 -1 =6
            {
                this.cells[i][j].setState(ICell.State.WALL);
            }
        }
    }

    private void triangleBottomRight(int row_d,int row_f, int col_d, int col_f){
        int i, j;
        for (j = col_f-1; j >= col_d; j--)
        {
            for (i = row_d+(col_f-j-1) ; i < row_f; i++) //  (7*nbrV/10)-1 le -1 car on demare de 0 donc 7 -1 =6
            {
                this.cells[i][j].setState(ICell.State.WALL);
            }
        }
    }

    private void triangleTopRight(int row_d,int row_f, int col_d, int col_f){
        int i, j;

        for (j = col_d; j < col_f; j++)
        {
            for (i = row_d+j; i < row_f; i++)
            {
                System.out.println(i+" "+j);
                this.cells[i][j].setState(ICell.State.WALL);
            }
            //System.out.println(); 
        }
    }
    
    
}
