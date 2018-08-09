package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EntityManagerImplTest {

    EntityManagerImpl manager = new EntityManagerImpl();
    Department department = new Department();

    @Test
    public void testFindAll(){
        List dep = manager.findAll(department.getClass());
        assertEquals(27,dep.size());
    }

    @Test
    public void findById(){
        department = manager.findById(department.getClass(),10L);
        assertTrue(department.getDepartmentName().equals("Administration"));
        assertTrue(department.getId() == 10L);
    }

    @Test
    public void nextId(){
        //department = manager.getNextIdVal(department.getDepartmentName(),)
        department.setDepartmentName("sales");
        department.setLocation(1000L);
        department = (Department) manager.insert(department);
        assertEquals(manager.findById(department.getClass(),department.getId()),department);
    }
}
