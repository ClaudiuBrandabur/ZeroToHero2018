package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

public class LocationDao {

    EntityManagerImpl entityManager = new EntityManagerImpl();

    public Location findById(Long id){
        return entityManager.findById(Location.class,id);
    }

    public long getNextIdVal(String columnIdName){
        return entityManager.getNextIdVal(EntityUtils.getTableName(Location.class),columnIdName);
    }

    public Location insert(Location location){
        return (Location) entityManager.insert(location);
    }

    public List<Location> findAll(){
        return entityManager.findAll(Location.class);
    }

    public Location update(Location location){
        return entityManager.update(location);
    }

    public void delete(Location location){
        entityManager.delete(location);
    }
}
