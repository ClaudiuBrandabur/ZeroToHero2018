package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class LocationDaoTest {
    EntityManager entityManager = new EntityManagerImpl();
    Location location = new Location();
    LocationDao locationDao = new LocationDao();


    @Test
    public void testCallFindById() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {


//        assertTrue(locationDao.callFindById(1700L).equals(en tityManager.findById(Location.class, 1700L)));
        assertTrue(entityManager.findById(Location.class, (long) 1400).equals(locationDao.callFindById((long) 1400)));

    }
    @Test
    public void testCallInsert() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        location.setStreetAddress("street");
        location.setStateProvince("state");
        location.setPostalCode("1233456");
        location.setCity("Bucuresti");
        location = locationDao.callInsert(location);
        assertTrue(location.getCity().equals("Bucuresti"));
    }

    @Test
    public void testCallFindAll() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        assertEquals(entityManager.findAll(Location.class).size(), locationDao.callFindAll().size());
    }

    @Test
    public void testCallUpdate() throws ClassNotFoundException, SQLException, NoSuchFieldException, IllegalAccessException {
        location.setStreetAddress("street");
        location.setStateProvince("state");
        location.setPostalCode("1233456");
        location.setCity("Bucuresti");
        location = locationDao.callUpdate(location);
        assertTrue(location.getCity().equals("Bucuresti"));
    }
}
