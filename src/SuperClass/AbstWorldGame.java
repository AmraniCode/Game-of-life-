/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuperClass;

import Interfaces.EvolveListner;
import Interfaces.ICell;
import Interfaces.IPosition;
import Interfaces.IWorld;
import ToolClass.Position2D;
import Worlds.MyWorldSquare;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author chaki
 */
public abstract class AbstWorldGame implements IWorld {  
    protected boolean active =true;
    protected ICell[][] cells; 
    protected int width ;
    protected int height;
    protected int squareSize;
    //initialise le nombre des celule a affiche
    protected int nbrH =0;
    protected int nbrV =0;   
    protected double threshold ;
    protected EvolveListner listner;
    protected long threadId = -1;
    protected boolean existWorld =true;
    protected int refreshRate =300;


    @Override
    public void initializeWorld(Properties p) {
        this.refreshRate =refreshRate;
      //initialisation des parametre width heigth
        if (p == null) {
          this.width = 120;
          this.height = 120;
          this.squareSize = 20;
          threshold = 0.7D;
        } else {
          
          this.width = Integer.parseInt(p.getProperty("WIDTH"));
          this.height = Integer.parseInt(p.getProperty("HEIGHT"));
          this.squareSize=(int) Math.sqrt(width*height/100);
          
          threshold = Double.parseDouble(p.getProperty("THRESHOLD"));
        }
         
        this.nbrH = this.width /this.squareSize;
        this.nbrV = this.height / this.squareSize;
    }
    
    public boolean getState(){
        return active ;
    }
    
    public ICell[][]getCells(){
        return cells; 
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    @Override
    public void subscribeEvent (EvolveListner listner){
        this.listner = listner;
    }
    
    @Override
    public void startEvent(){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                while (existWorld){
                    try {
                        Thread.sleep(refreshRate);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MyWorldSquare.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (listner !=null && active){
                        System.out.println("evolve in world");
                        listner.OnEvolve(cells);
                        evolveWorld();
                    }
                }
            }
        });
        threadId = t.getId();
        t.start();
    }

    public void stopWorld(){
        this.existWorld = false;
    }
    @Override
    public ICell getCell(IPosition pos) {
        if (pos == null)
           return null; 
         if (pos.getX() < 0 || pos.getX() >= this.nbrH || pos
           .getY() < 0 || pos.getY() >= this.nbrV)
           return null; 
         return this.cells[pos.getX()][pos.getY()];
    }
    
    
      @Override
    public void InsertPatch(List<ICell> cells, IPosition relativePosition) {
        //TODO 
    }

    @Override
    public List<ICell> getNeigbors(ICell cell) {
       // System.out.println(cell.getState());
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

    @Override
    public void evolveWorld() {
        beforeEvolve();
        doEvolve();
    }
    

    @Override
    public boolean isPopulated() {
        for (int i = 0; i < this.nbrH; i++) {
            for (int j = 0; j < this.nbrV; j++) {
              if (this.cells[i][j].getState() == ICell.State.ALIVE)
                return true; 
            } 
          } 
        return false;
    }

  

      
 
    @Override
    public void reinitialize() {
         //initialisation des celule possible et leur state 
        for (int i = 0; i < this.nbrH; i++) {
          for (int j = 0; j < this.nbrV; j++)
            this.cells[i][j].setState((Math.random() < threshold) ? ICell.State.ALIVE : ICell.State.DEAD); 
        } 
    }
//----------------fonction utill--------------------------------------------------------------------------
        
  protected void beforeEvolve() {
    for (int i = 0; i < this.nbrH; i++) {
      for (int j = 0; j < this.nbrV; j++)
        this.cells[i][j].beforeEvolve(); 
    } 
  }
  
  protected void doEvolve() {
    for (int i = 0; i < this.nbrH; i++) {
      for (int j = 0; j < this.nbrV; j++)
        this.cells[i][j].evolve(); 
    } 
  }

 
    
    @Override
    public void play() {
        this.active=true ;
    }

    @Override
    public void pause() {
      this.active=false ;   
    }

    @Override
    public void insertPatch() {
      this.pause();
      this.clear();
    }

    @Override
    public void clear() {
     for (int i=0;i<nbrH;i++)
            for (int j=0;j<nbrV;j++){
                Position2D p=new Position2D(i, j);
                if (getCell(p).getState() != ICell.State.WALL)
                getCell(p).setState(ICell.State.DEAD);
            }
         evolveWorld();
    }
    
     public void changeState(int x,int y){
        Position2D p=new Position2D(x, y);
        ICell c=getCell(p);
        ICell.State s=c.getState();

        if(s == ICell.State.WALL) return;

        if (s == ICell.State.ALIVE) 
            c.setState(ICell.State.DEAD);
        else 
            c.setState(ICell.State.ALIVE);
         System.out.println(c.getState());
    }

}
