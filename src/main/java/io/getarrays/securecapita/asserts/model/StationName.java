package io.getarrays.securecapita.asserts.model;

import java.util.HashMap;
import java.util.Map;

public enum StationName {
HARARE_HIGH_COURT(0,"HARARE_HIGH_COURT"),

    BULAWAYO_HIGH_COURT(1,"BULAWAYO_HIGH_COURT"),

    MUTARE_HIGH_COURT(2,"MUTARE_HIGH_COURT");


    private int id;
    private String name;

    StationName(int id, String name){
        this.id=id;
        this.name=name;
    }

    public static Map<Integer,StationName> getAll() {
        return names;
    }

    public static boolean existById(Integer stationId) {
        return names.containsKey(stationId);
    }

    public int getId(){
       return id;
    }

    public String getName(){
        return name;
    }

    private static final Map<Integer,StationName> names=new HashMap<>();

    static{
        for(StationName name:values()){
            names.put(name.id,name);
        }
    }

    public static StationName getById(int id){
        return names.get(id);
    }


}
