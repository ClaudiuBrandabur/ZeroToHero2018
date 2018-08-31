package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void updateTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        department.setLocation(1800L);
        department.setDepartmentName("Design_update");
        department.setId(40L);
        department = manager.update(department);
        assertEquals("Design_update", department.getDepartmentName());
    }

    @Test
    public void deleteTest() throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        department.setId(200L);
        manager.delete(department);
        assertTrue(manager.findById(department.getClass(), department.getId()) == null);
    }

    @Test
    public void findByParamsTest() throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        Map<String, Object> map = new HashMap<>();
        map.put("DEPARTMENT_ID", 90L);
        List list = manager.findByParams(Department.class, map);
        assertEquals(1, list.size());
    }
}
