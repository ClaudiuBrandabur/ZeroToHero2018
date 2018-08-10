package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EntityManagerImpl implements EntityManager {


    @Override
    public <T> T findById(Class<T> entityClass, Long id) {

        Connection con = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns;
        columns = EntityUtils.getColumns(entityClass);
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);

        Condition condition = new Condition();

        for (ColumnInfo c : columns) {
            if (c.isId()) {
                condition.setColumnName(c.getDbColumnName());
                condition.setValue(id);
            }
        }

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.setQueryType(QueryType.SELECT);
        queryBuilder.addCondition(condition);

        String queryResult = queryBuilder.createQuery();
        T myInstance = null;
        Field myField;

        Statement stm;
        ResultSet resultSet;

        try {

            stm = con.createStatement();
            resultSet = stm.executeQuery(queryResult);

            if (resultSet.next()) {
                myInstance = entityClass.newInstance();
                for (ColumnInfo col : columns) {
                    myField = myInstance.getClass().getDeclaredField(col.getColumnName());
                    myField.setAccessible(true);
                    if(col.getValue() instanceof Timestamp){
                        col.setValue(new Date(((Timestamp) col.getValue()).getTime()));
                    }
                    myField.set(myInstance, EntityUtils.castFromSqlType(resultSet.getObject(col.getDbColumnName()),col.getColumnType()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


        return myInstance;
    }

    @Override
    public long getNextIdVal(String tableName, String columnIdName) {

        Connection con = DBManager.getConnection();
        ResultSet resultSet = null;
        try {
            Statement stm = con.createStatement();
            resultSet = stm.executeQuery("select max( " + columnIdName + ") from " + tableName);
            resultSet.next();
            return resultSet.getLong(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public <T> Object insert(T entity) {

        try {
            Connection con = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
            long id = 0;
            for (ColumnInfo c : columns) {
                if (c.isId()) {
                    id = getNextIdVal(tableName, c.getDbColumnName());
                    c.setValue(id);
                } else {
                    
                    Field field = entity.getClass().getDeclaredField(c.getColumnName());
                    field.setAccessible(true);
                    c.setValue(field.get(entity));
                }
            }
            
            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setTableName(tableName);
            queryBuilder.addQueryColumns(columns);
            queryBuilder.setQueryType(QueryType.INSERT);
            String queryResult = queryBuilder.createQuery();
            
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(queryResult);
            
            return findById(entity.getClass(),id);
            
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {

        Connection con = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.setQueryType(QueryType.SELECT);
        
        String queryResult = queryBuilder.createQuery();
        try {
            
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(queryResult);
            
            ArrayList<T> myArray = new ArrayList<>();
            T instance;
            
            while (resultSet.next()){
                instance = entityClass.newInstance();
                for(ColumnInfo c : columns){
                    Field field = instance.getClass().getDeclaredField(c.getColumnName());
                    field.setAccessible(true);
                    if(c.getValue() instanceof Timestamp){
                        c.setValue(new Date(((Timestamp) c.getValue()).getTime()));
                    }
                    field.set(instance,EntityUtils.castFromSqlType(c.getValue(),c.getColumnType()));
                }
                myArray.add(instance);
            }
           
            return myArray;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> T update(T entity) {

        Connection con = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List <ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

        try {
            for (ColumnInfo c : columns) {
                Field field = entity.getClass().getDeclaredField(c.getColumnName());
                field.setAccessible(true);
                c.setValue(field.get(entity));
            }

            Condition cond = new Condition();
            cond.setColumnName(columns.get(0).getDbColumnName());
            cond.setValue(columns.get(0).getValue());

            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setTableName(tableName);
            queryBuilder.addQueryColumns(columns);
            queryBuilder.setQueryType(QueryType.UPDATE);
            queryBuilder.addCondition(cond);

            String queryResult = queryBuilder.createQuery();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(queryResult);

            return (T) findById(entity.getClass(), (Long) columns.get(0).getValue());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Object entity) {

        Connection con = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List <ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

        try {

            for(ColumnInfo c : columns){
                Field field = entity.getClass().getDeclaredField(c.getColumnName());
                field.setAccessible(true);
                c.setValue(field.get(entity));
            }

            Condition condition = new Condition();
            condition.setColumnName(columns.get(0).getDbColumnName());
            condition.setValue(columns.get(0).getValue());

            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setTableName(tableName);
            queryBuilder.addQueryColumns(columns);
            queryBuilder.setQueryType(QueryType.DELETE);
            queryBuilder.addCondition(condition);

            String resultQuery = queryBuilder.createQuery();
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery(resultQuery);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {

        Connection con = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List <ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        try {

            Condition condition = new Condition();
            List<T> result = new ArrayList<>();

            QueryBuilder queryBuilder = new QueryBuilder();
            queryBuilder.setTableName(tableName);
            queryBuilder.addQueryColumns(columns);
            queryBuilder.setQueryType(QueryType.SELECT);

            for(String key : params.keySet()){
                condition.setColumnName(key);
                condition.setValue(params.get(key));
                queryBuilder.addCondition(condition);
            }

            Statement stm = con.createStatement();
            String resultQuery = queryBuilder.createQuery();
            ResultSet resultSet = stm.executeQuery(resultQuery);

            while(resultSet.next()){

                T instance = entityClass.newInstance();
                for(ColumnInfo c : columns){
                    Field field = instance.getClass().getDeclaredField(c.getColumnName());
                    field.setAccessible(true);
                    if(c.getValue() instanceof Timestamp){
                        c.setValue(new Date(((Timestamp) c.getValue()).getTime()));
                    }
                    field.set(instance,EntityUtils.castFromSqlType(resultSet.getObject(c.getDbColumnName()),c.getColumnType()));
                }

                result.add(instance);
            }

            return result;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}

