package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EnityManagerImpl;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LocationDao {

    EnityManagerImpl manager = new EnityManagerImpl();

    public Location findById(Long id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return manager.findById(Location.class, id);
    }

    public Long getNextIdVal(String columnIdName) throws SQLException, ClassNotFoundException {
        return manager.getNextIdVal("LOCATIONS", columnIdName);
    }

    public Object insert(Location entity) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return manager.insert(entity);
    }

    public List<Location> findAll() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return manager.findAll(Location.class);
    }

    public Location update(Location entity) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return manager.update(entity);
    }

    public void delete(Object entity) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        manager.delete(entity);
    }

    public void findByParams(Class<Location> entityClass, Map<String, Object> params) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        manager.findByParams(entityClass, params);
    }
}
