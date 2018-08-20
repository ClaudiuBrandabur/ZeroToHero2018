package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {
    private EntityUtils() throws UnsupportedOperationException {

    }
    public static String getTableName(Class entity) {
        return ((Table)entity.getAnnotation(Table.class)).name();
    }

    public static List<ColumnInfo> getColumns(Class entity) {
        List<ColumnInfo> list = new ArrayList<>();
        Field[] fields = new Field[0];
        fields = Column.class.getDeclaredFields();
        for (Field field: fields)
        {
            ColumnInfo obj = new ColumnInfo();
            if (field.isAnnotationPresent(Column.class)) {
                obj.setColumnName(Column.class.getName());
                obj.setColumnType(Column.class.getComponentType());
                obj.setDbColumnType(Column.class.getComponentType().getName());
            }
            if (field.isAnnotationPresent(Id.class)) {
                obj.setColumnName(Id.class.getName());
                obj.setColumnType(Id.class.getComponentType());
                obj.setDbColumnType(Id.class.getComponentType().getName());
            }
            list.add(obj);
        }
        return list;
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if (value instanceof BigDecimal) {
            if (Integer.class.equals(wantedType))
                return Integer.valueOf((String) value);
            if (Long.class.equals(wantedType))
                return Long.valueOf((String) value);
            if (Float.class.equals(wantedType))
                return Float.valueOf((String) value);
            if (Double.class.equals(wantedType))
                return Double.valueOf((String) value);
        }
        return value;
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        List<Field> lsit = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field: fields) {
            if (field.getAnnotation(clazz).equals(annotation))
                lsit.add(field);
        }
        return lsit;
    }

    public static Object getSqlValue(Object object) throws NoSuchFieldException {
        if (object.getClass().equals(Table.class)) {
            Field field = object.getClass().getField(Id.class.getName());
            field.setAccessible(true);
            return field.getAnnotation(Id.class).name();
        }
        return object;
    }
}
