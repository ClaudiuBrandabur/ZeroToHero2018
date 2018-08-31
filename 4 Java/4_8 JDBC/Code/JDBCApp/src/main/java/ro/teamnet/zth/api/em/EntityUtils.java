package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {
    private EntityUtils() throws UnsupportedOperationException {

    }

    public static String getTableName(Class entity) {
        Table tableAnnotation = (Table) entity.getAnnotation(Table.class);
        return "".equals(tableAnnotation.name()) ? entity.getClass().getSimpleName() : tableAnnotation.name();
    }

//    public static List<ColumnInfo> getColumns(Class entity) {
//        List<ColumnInfo> columns = new ArrayList<>();
//
//        Field[] fields = entity.getDeclaredFields();
//        for (Field field : fields) {
//            Column column = field.getAnnotation(Column.class);
//            Id id = field.getAnnotation(Id.class);
//
//            ColumnInfo columnInfo = new ColumnInfo();
//            columnInfo.setColumnName(field.getName());
//            columnInfo.setColumnType(field.getType());
//            if (column != null) {
//                columnInfo.setDbColumnName(column.name());
//            } else {
//                columnInfo.setDbColumnName(id.name());
//                columnInfo.setId(true);
//            }
//
//            columns.add(columnInfo);
//        }
//        return columns;
//    }
public static ArrayList<ColumnInfo> getColumns(Class entity){
    Field [] fields = (entity.getDeclaredFields());
    ArrayList<ColumnInfo> columnInfoList = new ArrayList<ColumnInfo>();
    for(int i = 0; i < fields.length; i++){
        ColumnInfo column = new ColumnInfo();
        if(fields[i].isAnnotationPresent(Column.class)){
            column.setColumnName(fields[i].getName());
            column.setDbColumnName(fields[i].getAnnotation(Column.class).name());
            column.setColumnType(fields[i].getType());
            column.setId(false);
            columnInfoList.add(column);
        }
        if(fields[i].isAnnotationPresent(Id.class)){
            column.setColumnName(fields[i].getName());
            column.setDbColumnName(fields[i].getAnnotation(Id.class).name());
            column.setColumnType(fields[i].getType());
            column.setId(true);
            columnInfoList.add(column);
        }
    }
    return columnInfoList;
}

    public static Object castFromSqlType(Object value, Class wantedType) {
        if (value != null) {
            if (value instanceof BigDecimal) {
                BigDecimal bdValue = (BigDecimal) value;
                return wantedType.equals(Integer.class) ? bdValue.intValue() :
                        wantedType.equals(Long.class) ? bdValue.longValue() :
                                wantedType.equals(Float.class) ? bdValue.floatValue() :
                                        wantedType.equals(Double.class) ? bdValue.doubleValue() : value;
            } else {
                return value;
            }
        }
        return null;
    }
    //    public static Object castFromSqlType(Object value, Class wantedType){
//        if(value instanceof BigDecimal) {
//            BigDecimal val = (BigDecimal) value;
//            if (wantedType.equals(Integer.TYPE))
//                return val.intValue();
//            if (wantedType.equals(Long.TYPE))
//                return val.longValue();
//            if (wantedType.equals(Float.TYPE))
//                return val.floatValue();
//            if (wantedType.equals(Double.TYPE))
//                return val.doubleValue();
//        }
//        return value;
//    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        List<Field> fields = new ArrayList<>();

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getAnnotation(annotation) != null) {
                fields.add(declaredField);
            }
        }
        return fields;
    }

    public static Object getSqlValue(Object object) throws IllegalAccessException {
        if (object == null)
            return null;

        if (object.getClass().getAnnotation(Table.class) != null) {
            Field idField = getFieldsByAnnotations(object.getClass(), Id.class).get(0);
            idField.setAccessible(true);
            return idField.get(object);
        } else {
            return object;
        }
    }
}
