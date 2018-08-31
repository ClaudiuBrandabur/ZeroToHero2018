package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.LocationDao;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;

public class LocationDaoTest {
    EntityManager entityManager = new EntityManagerImpl();
    Location location = new Location();
    LocationDao locationDao = new LocationDao();


    @Test
    public void testCallFindById() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {


//        assertTrue(locationDao.callFindById(1700L).equals(entityManager.findById(Location.class, 1700L)));
        assertTrue(entityManager.findById(Location.class, (long) 1000).equals(locationDao.callFindById((long) 1000)));

    }

}
