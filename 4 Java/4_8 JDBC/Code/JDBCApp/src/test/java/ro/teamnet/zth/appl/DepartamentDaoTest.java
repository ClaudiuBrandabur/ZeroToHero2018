package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DepartamentDaoTest {

    EntityManager entityManager = new EntityManagerImpl();
    Department department = new Department();
    DepartmentDao departmentDao = new DepartmentDao();

    @Test
    public void testCallFindById() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {


        assertEquals(departmentDao.callFindById(200L),(entityManager.findById(Department.class, 200L)));

    }

    @Test
    public void testCallInsert(){
        department.setId(290L);
        department.setLocation("loc");

    }
}
