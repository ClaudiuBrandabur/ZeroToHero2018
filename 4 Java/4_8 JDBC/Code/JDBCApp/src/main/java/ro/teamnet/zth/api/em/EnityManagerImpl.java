package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnityManagerImpl {

    <T> T findById(Class<T> entityClass, Long id) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        Connection connection = DBManager.getConnection();

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);

        Condition condition = new Condition();
        for (ColumnInfo column : columns) {
            if (column.isId()) {
                condition.setColumnName(column.getColumnName());
                condition.setValue(column.getValue());
                break;
            }
        }

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addCondition(condition);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.setQueryType(QueryType.SELECT);

        String query = queryBuilder.createQuery();
        ResultSet result = connection.createStatement().executeQuery(query);

        if (result.next()) {
            T t = entityClass.newInstance();
            for (ColumnInfo column : columns) {
                Field field = t.getClass().getDeclaredField(column.getColumnName());
                t.getClass().getField(column.getColumnName()).setAccessible(true);
                field.set(t, result);
            }
            return t;
        }
        return null;
    }


    Long getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException {

        Connection connection = DBManager.getConnection();

        ResultSet rezSet = null;
        try (Statement statement = connection.createStatement()) {

            String select = "SELECT MAX(" + columnIdName + ") FROM " + tableName + ";";
            rezSet = statement.executeQuery(select);

            return ((Number) rezSet).longValue() + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    <T> Object insert(T entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

        Connection connection = DBManager.getConnection();

        String tableName = EntityUtils.getTableName((Class) entity);
        List<ColumnInfo> columns = EntityUtils.getColumns((Class) entity);

        for (ColumnInfo column : columns) {
            if (column.isId())
                column.setValue(getNextIdVal(tableName, column.getDbColumnName()));
            else {
                Field field = ((Class) entity).getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                column.setValue(field.get(entity));
            }
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.setQueryType(QueryType.SELECT);

        String query = queryBuilder.createQuery();
        ResultSet rezSet = null;
        try (Statement statement = connection.createStatement()) {
            rezSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findById(rezSet.getClass(),((Number) rezSet).longValue());
    }


    <T>List<T>findAll(Class<T> entityClass) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<T> list = new ArrayList<>();
        if (rezSet != null) {
            while (rezSet.next()) {
                T t = entityClass.newInstance();
                for (ColumnInfo column : columns) {
                    Field field = t.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(t, rezSet);
                    list.add(t);
                }
            }
        }
        return list;
    }

}
