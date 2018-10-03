package java.ro.teamnet.zth.appl.dao;

import java.ro.teamnet.zth.api.em.EntityManager;
import java.ro.teamnet.zth.api.em.EntityManagerImpl;
import java.ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.List;

public class LocationDao{

    EntityManager entityManager = new EntityManagerImpl();
    public Location callFindById(Long id) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return entityManager.findById(Location.class,id);
    }

    public Long callGetNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException {
        return entityManager.getNextIdVal(tableName, columnIdName);
    }

    public Location callInsert(Location loc) throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return (Location) entityManager.insert(loc);
    }

    public List<Location> callFindAll() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        return entityManager.findAll(Location.class);
    }

    public Location callUpdate(Location loc) throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        return entityManager.update(loc);
    }

}