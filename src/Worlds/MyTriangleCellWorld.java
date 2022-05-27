package Worlds;

import Cells.CellSquare;
import Interfaces.ICell;
import Interfaces.IPosition;
import SuperClass.AbstWorldGame;
import ToolClass.Position2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyTriangleCellWorld extends AbstWorldGame {
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

    @Override
    public List<ICell> getNeigbors(ICell cell) {
        System.out.println(cell.getState());
        IPosition pos = cell.getPosition();
        List<ICell> l = new ArrayList<>();

        for (int i = Math.max(0, pos.getX() - 1); i <= Math.min(this.nbrH - 1, pos.getX() + 1); i++) {
            for (int j = Math.max(0, pos.getY() - 1); j <= Math.min(this.nbrV - 1, pos.getY() + 1); j++) {
                if (pos.getX() != i || pos.getY() != j)
                    l.add(this.cells[i][j]);
            }
        }
        return l;
    }

}
