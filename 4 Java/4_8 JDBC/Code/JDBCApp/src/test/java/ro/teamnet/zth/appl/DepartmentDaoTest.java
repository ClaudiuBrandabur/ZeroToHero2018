package ro.teamnet.zth.appl;

import junit.framework.TestCase;
import org.junit.Test;
import ro.teamnet.zth.api.em.EnityManagerImpl;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class DepartmentDaoTest {

    EnityManagerImpl manager = new EnityManagerImpl();
    DepartmentDao departmentDao = new DepartmentDao();

    @Test
    public void findByIdTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        assertTrue(manager.findById(Department.class, 90L).equals(departmentDao.findById(90L)));
    }

    @Test
    public void getNextIdValTest() throws SQLException, ClassNotFoundException {
        TestCase.assertEquals(manager.getNextIdVal("DEPARTMENT", "LOCATION_ID"), departmentDao.getNextIdVal("LOCATION_ID"));
    }

    @Test
    public void insertTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Department department = new Department();
        Department department1 = new Department();
        Department department2 = new Department();
        department1.setId(109L);
        department1.setDepartmentName("Senegal");
        department1.setLocation(3000L);
        department2.setId(109L);
        department2.setDepartmentName("Senegal");
        department2.setLocation(3000L);
        department1 =(Department) manager.insert(department1);
        department2 =(Department) departmentDao.insert(department2);
        assertTrue(manager.findById(Department.class, department1.getId()).equals(departmentDao.findById(department2.getId())));
    }



}
