package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Location;

import java.util.List;

/**
 * Created by Oana.Mihai on 7/15/2016.
 */
public interface LocationService {
    List<Location> findAllLocations();

    Location findOne(Long locationId);
    List<Location> findAllById(List<Long> locationIds);
    Location add(Location location);

    Location update(Location location);
}
