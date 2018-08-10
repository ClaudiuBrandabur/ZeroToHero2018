package ro.teamnet.zth.appl;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class DepartmentDaoTest {

    EntityManagerImpl manager = new EntityManagerImpl();
    DepartmentDao dao = new DepartmentDao();

    @Test
    public void findByIdTest(){
        assertTrue(manager.findById(Department.class, (long) 40).equals(dao.findById((long) 40)));
    }

    @Test
    public void findAllTest(){
//        assertTrue(manager.findAll());
    }

    @Test
    public void insertTest(){

    }

    @Test
    public void updateTest(){

    }

    @Test
    public void deleteTest(){

    }

}
