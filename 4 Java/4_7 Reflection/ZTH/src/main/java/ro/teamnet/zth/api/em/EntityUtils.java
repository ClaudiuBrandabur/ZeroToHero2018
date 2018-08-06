package ro.teamnet.zth.api.em;

import java.lang.reflect.Field;
import java.util.List;

public class EntityUtils {

    private EntityUtils(){
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity){
        return null;
    }

    public static List<String> getColumns(Class entity){
        return null;
    }

    public static Object castFromSqlType(Object value, Class wantedType){
        return null;
    }

    public static List<Field> getFieldsByAdnnotations(Class clazz, Class annotation){
        return null;
    }

    public static Object getSqlValue(Object object){
        return null;
    }
}
