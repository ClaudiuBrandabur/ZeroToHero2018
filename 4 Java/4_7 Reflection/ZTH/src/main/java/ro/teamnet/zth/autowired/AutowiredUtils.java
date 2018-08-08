package ro.teamnet.zth.autowired;

import java.util.HashMap;
import java.util.Map;

public class AutowiredUtils {

    private HashMap byClass;
    private HashMap byQualifier;

//    @MyAutowired
    public AutowiredUtils(HashMap byClass,HashMap byQualifier) {
        this.byClass = byClass;
        this.byQualifier = byQualifier;
    }

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

    public HashMap.Entry getByTypeClass(HashMap byClass){
        return (HashMap.Entry) byClass.get(0);
    }

    public HashMap.Entry getByQualifier(Object key){
        return (HashMap.Entry) byClass.get(key);
    }

//    public void myMethod (O)


}
