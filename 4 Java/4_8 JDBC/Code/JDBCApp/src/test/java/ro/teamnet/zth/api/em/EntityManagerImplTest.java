package java.ro.teamnet.zth.api.em;

import org.testng.annotations.Test;

import java.ro.teamnet.zth.appl.domain.Department;
import java.ro.teamnet.zth.appl.domain.Location;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EntityManagerImplTest {


    Department department = new Department();
    Location location = new Location();


    @Test
    public void testGetNextIdVal() throws SQLException, ClassNotFoundException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Long nextId = entityManager.getNextIdVal("EMPLOYEES", "EMPLOYEE_ID");
        assertEquals(207, nextId.intValue());
    }

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        EntityManagerImpl entityManager = new EntityManagerImpl();

        location.setCity("city");
        location.setId(2000L);
        location.setPostalCode("2143");
        location.setStateProvince("state");
        location.setStreetAddress("street");
        assertTrue(location.getCity() == "city");


    }

    @Test
    public void testFindAll() throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        ArrayList<Department> arrayList = new ArrayList<>();
        arrayList = entityManager.findAll(Department.class);
        assertEquals(arrayList.size(), 25);

    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        department.setLocation(1000L);
        department.setDepartmentName("myUpdate");
        department.setId(272L);
        department = entityManager.update(department);
        assertTrue(department.getDepartmentName().equals("myUpdate"));

    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        EntityManager entityManager = new EntityManagerImpl();
        Department dep = new Department();
        dep.setId(230L);
        entityManager.delete(dep);
        ArrayList arrayList = entityManager.findAll(Department.class);
        assertEquals(arrayList.size(), 25);
    }

    @Test
    public void testFindByParams() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        EntityManager entityManager = new EntityManagerImpl();
        HashMap map = new HashMap<>();
        map.put("LOCATION_ID", 1700L);
        List arrayList = entityManager.findByParams(Location.class, map);
        assertEquals(arrayList.size(), 1);
    }
}