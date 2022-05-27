package Lookup;

import Interfaces.CELL_TYPE;
import Interfaces.WORLD_TYPE;
import SuperClass.AbstDisplayPanel;
import SuperClass.AbstWorldGame;

import java.util.HashMap;

public class PanelLocator {

    private static HashMap<CELL_TYPE, AbstDisplayPanel> serviceList = new HashMap<>();


    public static AbstDisplayPanel getService(CELL_TYPE type){
        return serviceList.get(type);
    }
    public static void subscribe(AbstDisplayPanel world,CELL_TYPE type){
        serviceList.put(type,world);
    }
    public static void unsubscribe(CELL_TYPE type){
        serviceList.remove(type);
    }

}
