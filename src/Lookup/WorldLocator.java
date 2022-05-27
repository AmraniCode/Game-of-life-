package Lookup;

import Interfaces.IWorld;
import Interfaces.WORLD_TYPE;
import SuperClass.AbstWorldGame;

import java.util.HashMap;

public class WorldLocator {
    private static HashMap <WORLD_TYPE, AbstWorldGame> serviceList = new HashMap<>();


    public static AbstWorldGame getService(WORLD_TYPE type){
        return serviceList.get(type);
    }
    public static void subscribe(AbstWorldGame world,WORLD_TYPE type){
        serviceList.put(type,world);
    }
    public static void unsubscribe(WORLD_TYPE type){
        serviceList.remove(type);
    }
}
