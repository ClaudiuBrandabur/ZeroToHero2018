package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;


public class LocationDaoTest {

    EntityManagerImpl manager = new EntityManagerImpl();
    LocationDao locationDao = new LocationDao();
    Location location = new Location();

    @Test
    public void findByIdTest(){
        assertTrue(manager.findById(Location.class, (long) 1000).equals(locationDao.findById((long) 1000)));
    }

    @Test
    public void insertTest(){
        location.setCity("Bucuresti");
        location.setPostalCode("400000");
        location.setStreetAddress("Splaiul Independentei");
        location.setStateProvince("Sector6");
        location = locationDao.insert(location);
        assertEquals(locationDao.findById(location.getId()),location);
    }

    @Test
    public void updateTest(){
        insertTest();
        location.setStreetAddress("my new adress");
        location = locationDao.update(location);
        assertEquals(locationDao.findById(location.getId()),location);
    }

    @Test
    public void deleteTest(){

        insertTest();
        locationDao.delete(location);
        assertNull(locationDao.findById(location.getId()));
    }


    @Test
    public void findAllTest(){
        assertTrue(manager.findAll(Location.class).size()==(locationDao.findAll().size()));
    }

}
