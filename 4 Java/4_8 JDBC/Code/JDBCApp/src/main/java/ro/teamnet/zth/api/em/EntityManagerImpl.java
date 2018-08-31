package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.api.database.DBProperties;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EntityManagerImpl implements EntityManager{

    @Override
    public <T> T findById(Class<T> entityClass, Long id) {//throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        ArrayList<ColumnInfo> columns;
        columns = EntityUtils.getColumns(entityClass);
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);

        Condition cond = new Condition();

        for(ColumnInfo column : columns){
            if(column.isId()){
                cond.setColumnName(column.getDbColumnName());
                cond.setValue(id);
            }
        }

        QueryBuilder2 qb = new QueryBuilder2();
        qb.setTableName(tableName);
        qb.addQueryColumns(columns);
        qb.setQueryType(QueryType.SELECT);
        qb.addCondition(cond);

        String query = qb.createQuery();
        Statement statement;
        ResultSet resultSet;
        Field field;
        T instance = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                instance = entityClass.newInstance();
                for (ColumnInfo column : columns) {

                    field = instance.getClass().getDeclaredField(column.getColumnName());

                    field.setAccessible(true);
                    if (column.getValue() instanceof Timestamp) {
                        column.setValue(new Date(((Timestamp) column.getValue()).getTime()));
                    }

                    field.set(instance, EntityUtils.castFromSqlType(resultSet.getObject(column.getDbColumnName()), column.getColumnType()));
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    return instance;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException {
        Connection connection = DBManager.getConnection();
        ResultSet resultSet;
        Long nextIdVal = new Long(0);
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT MAX(" + columnIdName + ") FROM " + tableName);
            resultSet.next();
            nextIdVal = resultSet.getLong(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return nextIdVal;
    }

    @Override
    public <T> Object insert(T entity) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        try {
            Connection connection = DBManager.getConnection();
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
            columns = EntityUtils.getColumns(entity.getClass());
            Field field;
            Long id = 0L;

            for (ColumnInfo column : columns) {
                if (column.isId()) {
                    id = getNextIdVal(tableName, column.getDbColumnName());
                    column.setValue(id);
                } else {
                    field = entity.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    column.setValue(field.get(entity));
                }
                QueryBuilder qb = new QueryBuilder();
                qb.setTableName(tableName);
                qb.addQueryColumns((ArrayList<ColumnInfo>) columns);
                qb.setQueryType(QueryType.INSERT);
                String res = qb.createQuery();

                Statement stm = connection.createStatement();
                ResultSet resultSet = stm.executeQuery(res);
            }


            return findById(entity.getClass(), id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> ArrayList<T> findAll(Class<T> entityClass) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        ArrayList<ColumnInfo> columns;
        columns = (ArrayList<ColumnInfo>) EntityUtils.getColumns(entityClass);

        QueryBuilder qb = new QueryBuilder();
        qb.setQueryType(QueryType.SELECT);
        qb.setTableName(tableName);
        qb.addQueryColumns(columns);
        String query = qb.createQuery();

        Statement statement = null;
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        ArrayList<T> arrayList = new ArrayList<>();
        T instance = null;
        Field field;

        while(resultSet.next()){
            instance = entityClass.newInstance();
            for(ColumnInfo column : columns){
                field = instance.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                if(column.getValue() instanceof Timestamp){
                    column.setValue(new Date(((Timestamp) column.getValue()).getTime()));
                }
                field.set(instance,EntityUtils.castFromSqlType(column.getValue(),column.getColumnType()));

            }
            arrayList.add(instance);

        }
        return arrayList;
    }

    @Override
    public <T> T update(T entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
        columns = EntityUtils.getColumns(entity.getClass());
        Field field = null;
        for(ColumnInfo column : columns){
            field = entity.getClass().getDeclaredField(column.getColumnName());
            field.setAccessible(true);
            column.setValue(field.get(entity));
        }
        Condition condition = new Condition();
        condition.setColumnName(columns.get(0).getDbColumnName());
        condition.setValue(columns.get(0).getValue());

        QueryBuilder qb = new QueryBuilder();
        qb.setTableName(tableName);
        qb.setQueryType(QueryType.UPDATE);
        qb.addQueryColumns((ArrayList<ColumnInfo>) columns);
        qb.addCondition(condition);
        String result = qb.createQuery();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(result);

        return entity;
    }

    @Override
    public void delete(Object entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
        columns = EntityUtils.getColumns(entity.getClass());
        Field field;
        for(ColumnInfo column : columns){
            field = entity.getClass().getDeclaredField(column.getColumnName());
            field.setAccessible(true);
            column.setValue(field.get(entity));
        }
        Condition condition = new Condition();
        condition.setColumnName(columns.get(0).getDbColumnName());
        condition.setValue(columns.get(0).getValue());
        QueryBuilder qb = new QueryBuilder();
        qb.setTableName(tableName);
        qb.setQueryType(QueryType.DELETE);
        qb.addCondition(condition);
        String result = qb.createQuery();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(result);

    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
        columns = EntityUtils.getColumns(entityClass);

        Condition condition = new Condition();

        QueryBuilder qb = new QueryBuilder();
        qb.setTableName(tableName);
        qb.setQueryType(QueryType.SELECT);
        qb.addQueryColumns((ArrayList<ColumnInfo>) columns);
        for(String key : params.keySet()){
            condition.setColumnName(key);
            condition.setValue(params.get(key));
            qb.addCondition(condition);
        }


        String result = qb.createQuery();
        Field field;
        List<T> resultList = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(result);
        while(resultSet.next()){

            T instance = entityClass.newInstance();
            for(ColumnInfo column : columns) {

                field = instance.getClass().getDeclaredField(column.getColumnName());

                field.setAccessible(true);
                if (column.getValue() instanceof Timestamp) {
                    column.setValue(new Date(((Timestamp) column.getValue()).getTime()));
                }

                field.set(instance, EntityUtils.castFromSqlType(resultSet.getObject(column.getDbColumnName()), column.getColumnType()));
            }

            resultList.add(instance);
        }
        return resultList;
    }
}
