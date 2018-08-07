package ro.teamnet.zth.autowired;

import java.util.HashMap;
import java.util.Map;

public class AutowiredUtils {

    private HashMap byClass;
    private HashMap byQualifier;

    public void updateByType(Object obj){
        if(obj instanceof HashMap) {
            HashMap.Entry h = (HashMap.Entry) obj;
            byClass.put(h.getKey(),h.getValue());
        }
    }

    public void updatebyQualifier(Object obj){
        if(obj instanceof HashMap) {
            HashMap.Entry h = (HashMap.Entry) obj;
            byQualifier.put(h.getKey(),h.getValue());
        }
    }

    public HashMap.Entry getByTypeClass(Object key){
        return byClass.get(key);
    }



}
