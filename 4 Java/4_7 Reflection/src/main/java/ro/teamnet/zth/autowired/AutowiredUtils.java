package ro.teamnet.zth.autowired;

import java.util.HashMap;
import java.util.Map;

public class AutowiredUtils {
    private HashMap storeByType;
    private HashMap storeByQualifier;

    public void updateByType(Object obj){
        obj = (HashMap.Entry) obj;
        storeByType.put(((Map.Entry) obj).getKey(), ((Map.Entry) obj).getValue());
    }

    public void updateByQualifier(Object obj){
        obj = (HashMap.Entry) obj;
        storeByQualifier.put(((Map.Entry) obj).getKey(), ((Map.Entry) obj).getValue());
    }
    public HashMap.Entry getByType(Object key){
        return (HashMap.Entry) storeByType.get(key);
    }

    public HashMap.Entry getByQualifier(Object key){
        return (HashMap.Entry) storeByQualifier.get(key);
    }
}
