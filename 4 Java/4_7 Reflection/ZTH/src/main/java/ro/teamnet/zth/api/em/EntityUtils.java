package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {

    private EntityUtils(){
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity){
        return ((Table) entity.getAnnotation(Table.class)).name();
    }

    public static List<String> getColumns(Class entity){

        List columnList = new ArrayList<>();

        Field[] fields = entity.getDeclaredFields();

        for(int i = 0; i < fields.length; i++){
            ColumnInfo co = new ColumnInfo();
            if(fields[i].isAnnotationPresent(Column.class)){
                    co.setColumnName(fields[i].getName());
                    co.setColumnType(fields[i].getType());
                    co.setDbColumnName(fields[i].getAnnotation(Column.class).name());
                    co.setId(false);
                    columnList.add(co);
            }

            if(fields[i].isAnnotationPresent(Id.class)){
                co.setColumnName(fields[i].getName());
                co.setColumnType(fields[i].getType());
                co.setDbColumnName(fields[i].getAnnotation(Id.class).name());
                co.setId(true);
                columnList.add(co);
            }
        }

        return columnList;
    }

    public static Object castFromSqlType(Object value, Class wantedType){

        if(value instanceof BigDecimal){
           if(wantedType.equals(Integer.TYPE)) return (Integer)value;
           if(wantedType.equals(Long.TYPE)) return (Long)value;
           if(wantedType.equals(Float.TYPE)) return (Float)value;
           if(wantedType.equals(Double.TYPE)) return (Double)value;

        }

        return value;
    }

    public static List<Field> getFieldsByAdnnotations(Class clazz, Class annotation){

        Field []fields = clazz.getDeclaredFields();
        List<Field> newFields = new ArrayList<>();

        for(int i = 0; i < fields.length; i++){
            if(fields[i].isAnnotationPresent(annotation))
                newFields.add(fields[i]);
        }

        return newFields;
    }

    public static Object getSqlValue(Object object){

        if(object.getClass().isAnnotationPresent(Table.class)){
            Field[] fields = object.getClass().getDeclaredFields();
            for(int i = 0; i < fields.length; i++)
                if(fields[i].isAnnotationPresent(Id.class)) {
                    fields[i].setAccessible(true);
                    return fields[i];
                }

        }

        return object;
    }

    public static void main(String [ ]args){

        ArrayList a = (ArrayList) EntityUtils.getColumns(Department.class);
        for( int i = 0; i < a.size(); i++)
            System.out.println(a.get(i));

    }
}
