package ro.teamnet.zth.api.em;

import java.util.List;

public class EntityManagerImpl implements EntityManager{


    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        return null;
    }

    @Override
    public int getNextIdVal(String tableName, String columnIdName) {
        return 0;
    }

    @Override
    public <T> Object insert(T entity) {
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        return null;
    }
}

