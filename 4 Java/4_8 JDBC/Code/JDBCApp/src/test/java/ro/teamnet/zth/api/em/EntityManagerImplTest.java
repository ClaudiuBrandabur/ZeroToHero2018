package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EntityManagerImplTest {

    EnityManagerImpl manager = new EnityManagerImpl();
    Department department = new Department();

    @Test
    public void findAllTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List departments = manager.findAll(department.getClass());
        assertEquals(34, departments.size());
    }

    @Test
    public void findById() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        department = manager.findById(department.getClass(), 20L);
        assertTrue(department.getDepartmentName().equals("Marketing"));
    }

    @Test
    public void insertTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        department.setDepartmentName("Design");
        department.setLocation(2300L);
        department = (Department) manager.insert(department);
        assertEquals(manager.findById(department.getClass(),department.getId()),department);
    }

}
