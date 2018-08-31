package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnityManagerImpl {

    public static <T> T findById(Class<T> entityClass, Long id) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        Connection connection = DBManager.getConnection();

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);

        Condition condition = new Condition();
        for (ColumnInfo column : columns) {
            if (column.isId()) {
                condition.setColumnName(column.getDbColumnName());
                condition.setValue(id);
            }
        }

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addCondition(condition);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.setQueryType(QueryType.SELECT);

        String query = queryBuilder.createQuery();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(query);

        if (result.next()) {
            T t = entityClass.newInstance();
            for (ColumnInfo column : columns) {
                Field field = t.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                if (column.getValue() instanceof Timestamp) {
                    column.setValue(new Date(((Timestamp) column.getValue()).getTime()));
                }
                field.set(t, EntityUtils.castFromSqlType(result.getObject(column.getDbColumnName()),
                        column.getColumnType()));
            }
            return t;
        }
        return null;
    }


    public Long getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException {

        Connection connection = DBManager.getConnection();

        ResultSet rezSet = null;
        try (Statement statement = connection.createStatement()) {

            String select = "select max( " + columnIdName + ") from " + tableName;
            rezSet = statement.executeQuery(select);
            rezSet.next();
            return rezSet.getLong(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public <T> Object insert(T entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        Connection connection = DBManager.getConnection();

        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
        Long id = 0L;
        for (ColumnInfo column : columns) {
            if (column.isId()) {
                id = getNextIdVal(tableName, column.getDbColumnName());
                column.setValue(id);
            }
            else {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));
            }
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.setQueryType(QueryType.INSERT);

        String query = queryBuilder.createQuery();
        ResultSet rezSet = connection.createStatement().executeQuery(query);

        return findById(entity.getClass(), id);
    }


    public <T>List<T>findAll(Class<T> entityClass) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        Connection connection = DBManager.getConnection();

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.setQueryType(QueryType.SELECT);

        String query = queryBuilder.createQuery();
        ResultSet rezSet = null;
        try (Statement statement = connection.createStatement()) {
            rezSet = statement.executeQuery(query);
            List<T> list = new ArrayList<>();
            T t;
            while (rezSet.next()) {
                t = entityClass.newInstance();
                for (ColumnInfo column : columns) {
                    Field field = t.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    if (column.getValue() instanceof Timestamp) {
                        column.setValue(new Date(((Timestamp) column.getValue()).getTime()));
                    }
                    field.set(t, EntityUtils.castFromSqlType(column.getValue(),column.getColumnType()));
                }
                list.add(t);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T update(T entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

        for (ColumnInfo column : columns) {
            Field field = entity.getClass().getDeclaredField(column.getColumnName());
            field.setAccessible(true);
            column.setValue(field.get(entity));
        }

        Condition condition = new Condition();
        condition.setColumnName(columns.get(0).getDbColumnName());
        condition.setValue(columns.get(0).getValue());

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addCondition(condition);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.setQueryType(QueryType.UPDATE);

        try {
            String query = queryBuilder.createQuery();
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            return (T) findById(entity.getClass(), (Long) columns.get(0).getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Object entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

        for (ColumnInfo column : columns) {
            Field field = entity.getClass().getDeclaredField(column.getColumnName());
            field.setAccessible(true);
            column.setValue(field.get(entity));
        }

        Condition condition = new Condition();
        condition.setColumnName(columns.get(0).getDbColumnName());
        condition.setValue(columns.get(0).getValue());

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addCondition(condition);
        queryBuilder.setQueryType(QueryType.DELETE);

        try {
            String query = queryBuilder.createQuery();
            ResultSet resultSet = connection.createStatement().executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> List<T>findByParams(Class<T> entityClass, Map<String, Object> params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Connection connection  = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        Condition condition = new Condition();
        List<T> result = new ArrayList<>();

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.setQueryType(QueryType.SELECT);

        for (String key : params.keySet()) {
            condition.setColumnName(key);
            condition.setValue(params.get(key));
            queryBuilder.addCondition(condition);
        }

        try {
            String query = queryBuilder.createQuery();
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            T t;
            while (resultSet.next()) {
                t = entityClass.newInstance();
                for (ColumnInfo column : columns) {
                    Field field = t.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    if (column.getValue() instanceof Timestamp) {
                        column.setValue(new Date(((Timestamp) column.getValue()).getTime()));
                    }
                    field.set(t, EntityUtils.castFromSqlType(column.getValue(),column.getColumnType()));
                }
                result.add(t);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
