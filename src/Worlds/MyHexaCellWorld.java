package Worlds;

import Cells.HexaCell;
import Interfaces.ICell;
import Interfaces.IPosition;
import Interfaces.WORLD_TYPE;
import Lookup.WorldLocator;
import SuperClass.AbstWorldGame;
import ToolClass.Position2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyHexaCellWorld extends AbstWorldGame {
    int Qmax =0 ;
    int Rmax =0;

    @Override
    public void initializeWorld(Properties p) {
        super.initializeWorld(p);
        this.Qmax = this.nbrH + (nbrV-1)/2; //ceci represente le nouveau width de la matrice
        this.Rmax = nbrV ;


        //initialisation des celule possible et leur state
        this.cells = new HexaCell[this.Rmax][this.Qmax];

        int countCell = this.nbrH ; //ceci represente le nombre reel de hexagon.
        int doubleLigne = 0;
        int caseNullGauche = 0;
        int caseNull = 0;
        int decalage = Qmax-nbrH;
        int ligneCaseNull = 0;
        for (int i = 0; i < this.Rmax; i++) {
            countCell = this.nbrH ;
            if(doubleLigne == 2){
                doubleLigne = 0 ;
                caseNull++;
                decalage--;
                caseNullGauche = caseNull;
                ligneCaseNull = 2 ;
            }
            for (int j = this.Qmax-1; j >=0 ; j--){
                if(countCell == 0 || caseNullGauche>0){
                    this.cells[i][j] = null ;
                    if(caseNullGauche>0){
                        caseNullGauche--;

                    }
                }else{
                    this.cells[i][j] = new HexaCell(this,new Position2D(i, j-decalage),
                            (Math.random() < threshold) ? ICell.State.ALIVE : ICell.State.DEAD);
                    countCell--;
                }
            }
            doubleLigne++;
            if(ligneCaseNull > 0){
                caseNullGauche = caseNull ;
            }
        }

    }

    @Override
    public ICell getCell(IPosition pos) {
        if (pos == null)
            return null;
        if (pos.getX() < 0 || pos.getX() >= this.Rmax || pos
                .getY() < 0 || pos.getY() >= this.Qmax)
            return null;
        return this.cells[pos.getX()][pos.getY()];
    }

    @Override
    public List<ICell> getNeigbors(ICell cell) {
        IPosition pos = cell.getPosition();
        List<ICell> l = new ArrayList<>();
        int posY=0;

        for(int k =0 ; k<this.Qmax ; k++){
            if(this.cells[pos.getX()][k] != null)
                if(this.cells[pos.getX()][k].getPosition().getY()== pos.getY()){
                    posY = k ;
                }
        }
        //    System.out.print(cell.getState()+" "+pos.getX()+" "+posY+" : ");

        for (int i = Math.max(0, pos.getX() - 1); i <= Math.min(this.Rmax - 1, pos.getX() + 1); i++) {
            for (int j = Math.max(0, posY - 1); j <= Math.min(this.Qmax - 1, posY + 1); j++) {

                if ((pos.getX() != i || posY != j)&&(this.cells[i][j] != null) //on a ajouter tous les condition car le voisinage est le meme que carre sauf le x-1/y-1 et le x+1/y+1
                        && (pos.getX()-1 != i || posY-1 != j)&&(pos.getX()+1 != i || posY+1 != j)){
                    l.add(this.cells[i][j]); //System.out.print(i+";"+j+"="+this.cells[i][j].getState()+"/");
                }

            }
        }// System.out.println();
        return l;
    }

    @Override
    public void clear() {
        for (int i=0;i<Rmax;i++)
            for (int j=0;j<Qmax;j++){
                Position2D p=new Position2D(i, j);
                if(getCell(p)!= null){
                    getCell(p).setState(ICell.State.DEAD);
                }}
        evolveWorld();
    }

    @Override
    public void reinitialize() {
        //initialisation des celule possible et leur state
        for (int i=0;i<Rmax;i++)
            for (int j=0;j<Qmax;j++){
                if(this.cells[i][j]!= null){
                    this.cells[i][j].setState((Math.random() < threshold) ? ICell.State.ALIVE : ICell.State.DEAD);
                }
            }
    }

    @Override
    protected void beforeEvolve() {
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < this.Rmax; i++) {
//                if((i%2) != 0){
//                    System.out.print("");
//                }
            for (int j = 0; j < this.Qmax; j++)
                if(this.cells[i][j]!=null){
                    this.cells[i][j].beforeEvolve();
                    //  System.out.print(this.cells[i][j].getState()+" ");
                }
            //   System.out.println();
        }
    }

    @Override
    protected void doEvolve() {
        for (int i = 0; i < this.Rmax; i++) {
            for (int j = 0; j < this.Qmax; j++)
                if(this.cells[i][j]!=null){
                    this.cells[i][j].evolve();
                }
        }
    }

    @Override
    public void changeState(int x,int y){
        Position2D p=new Position2D(x, y);
        int posY=0 ;
        for(int k =0 ; k<this.Qmax ; k++){
            if(this.cells[p.getX()][k] != null)
                if(this.cells[p.getX()][k].getPosition().getY()== p.getY()){
                    posY = k ;
                }
        }
        Position2D pNew=new Position2D(x, posY);
        ICell c=getCell(pNew);
        ICell.State s=c.getState();

        if (s == ICell.State.ALIVE)
            c.setState(ICell.State.DEAD);
        else
            c.setState(ICell.State.ALIVE);

    }

}
