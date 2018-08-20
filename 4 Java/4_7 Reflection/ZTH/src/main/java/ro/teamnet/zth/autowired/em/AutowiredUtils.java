package ro.teamnet.zth.autowired.em;

import ro.teamnet.zth.autowired.annotations.MyAutowired;
import ro.teamnet.zth.autowired.annotations.MyQualifier;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AutowiredUtils {
    public static Map<Class, Object> map1 = new HashMap<>();
    public static Map<String, Object> map2 = new HashMap<>();

    public static void updateType(Object object) {
        map1.put(object.getClass(), object);
    }

    public static void updateQualifiers(Object object) {
        map2.put(((MyQualifier)object.getClass().getAnnotation(MyQualifier.class)).value(), object);
    }

    public static Object getType(Class type) {
        return map1.get(type);
    }

    public static Object getQualifier(String value) {
        return map2.get(value);
    }

    public static void handleAutowiring(Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(MyAutowired.class) != null) {
                if (!field.getAnnotation(MyAutowired.class).value().equals("")) {
                    field.setAccessible(true);
                    field.set(object, getQualifier(field.getAnnotation(MyAutowired.class).value()));
                }
                else {
                    field.setAccessible(true);
                    field.set(object, getType(field.getType()));
                }
            }
        }
    }
}
