package ro.teamnet.zth.api.em;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

public interface EntityManager {

    <T> T findById(Class<T> entityClass, Long id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;
    Long getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException;
    <T> Object insert(T entity) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InstantiationException;
    <T> ArrayList<T> findAll(Class<T> entityClass) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException;
    <T> T update(T entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException;
    void delete(Object entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException;
    <T> List<T> findByParams(Class<T> entityClass, Map<String,Object> params) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException;
}