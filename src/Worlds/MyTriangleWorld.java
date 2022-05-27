package Worlds;

import Cells.CellSquare;
import DisplayPanels.MySquareWorldPanel;
import Interfaces.CELL_TYPE;
import Interfaces.ICell;
import Interfaces.WORLD_TYPE;
import Lookup.PanelLocator;
import Lookup.WorldLocator;
import SuperClass.AbstWorldGame;
import ToolClass.Position2D;

import java.util.Properties;

public class MyTriangleWorld extends AbstWorldGame {

    static {
        WorldLocator.subscribe(new MyTriangleWorld(), WORLD_TYPE.TRIANGLE);
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

        makeTriangle();
    }


    @Override
    public void reinitialize() {
        super.reinitialize();
        makeTriangle();
    }

    private void makeTriangle(){
        for (int i = 0;i<nbrH;i++){
            for (int j=0;j<this.nbrV;j++){
                if(j<nbrV/2-i/2 ||j >nbrV/2+i/2 )
                    this.cells[i][j].setState(ICell.State.WALL);
                else this.cells[i][j].setState((Math.random() < threshold) ? ICell.State.ALIVE : ICell.State.DEAD);
            }
        }
    }
}
