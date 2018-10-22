package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationServiceImpl implements LocationService {

    private final LocationDao locationDao = new LocationDao();

    @Override
    public List<Location> findAllLocations() {
        return locationDao.getAllLocations();
    }

    @Override
    public Location findOne(Long locationId) {
        return locationDao.getLocationById(locationId);
    }

    @Override
    public List<Location> findAllById(List<Long> locationIds) {
         List<Location> allLocations = locationDao.getAllLocations();
         List<Location> filteredLocations = new ArrayList<>();
         for(Location location : allLocations){
             if (locationIds.contains(location.getLocationId())){
                 filteredLocations.add(location);
             }
         }
         return filteredLocations;
    }

    @Override
    public Location add(Location location) {
        return locationDao.insertLocation(location);
    }

    @Override
    public Location update(Location location) {
        return locationDao.updateLocation(location);
    }
}
