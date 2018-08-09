package ro.teamnet.zth.api.em;

import java.util.List;

public interface EntityManager {

    <T> T findById(Class<T> entityClass, Long id);
    long getNextIdVal(String tableName, String columnIdName);
    <T> Object insert(T entity);
    <T> List<T> findAll(Class<T> entityClass);
}
