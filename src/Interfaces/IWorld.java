/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import java.util.Properties;

/**
 *
 * @author ISMAIL
 */
public interface IWorld {
     
    void initializeWorld (Properties p)  ; 
    
    ICell getCell (IPosition pos) ;
    
    void InsertPatch (List<ICell> cells, IPosition relativePosition) ;

    List<ICell> getNeigbors (ICell cell) ;    

    void evolveWorld () ;     
    
    boolean isPopulated();
    public  void play();
    public  void pause();
    public  void insertPatch();
    public  void clear();
    public  void reinitialize();
    
    public void subscribeEvent(EvolveListner listner);
    public void startEvent();
    
    public static final String WIDTH = "WIDTH" ;
    public static final String HEIGHT = "HEIGHT" ;
    public static final String THRESHOLD = "THRESHOLD" ;
    
}
