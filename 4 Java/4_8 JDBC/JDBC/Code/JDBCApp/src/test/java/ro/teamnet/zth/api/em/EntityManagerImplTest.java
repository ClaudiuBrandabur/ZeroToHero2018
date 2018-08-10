package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EntityManagerImplTest {

    EntityManagerImpl manager = new EntityManagerImpl();
    Department department = new Department();

    @Test
    public void testFindAll(){
        List dep = manager.findAll(department.getClass());
        assertEquals(31,dep.size());
    }

    @Test
    public void findById(){
        department = manager.findById(department.getClass(),10L);
        assertTrue(department.getDepartmentName().equals("Administration"));
        assertTrue(department.getId() == 10L);
    }

    @Test
    public void nextId(){ //insert
        department.setDepartmentName("sales");
        department.setLocation(1000L);
        department = (Department) manager.insert(department);
        assertEquals(manager.findById(department.getClass(),department.getId()),department);
    }

    @Test
    public void updateTest(){
        department.setLocation(1000L);
        department.setDepartmentName("myUpdate");
        department.setId(272L);
        department = manager.update(department);
        assertTrue(department.getDepartmentName().equals("myUpdate"));
    }

    @Test
    public void deleteTest(){
        department.setId(271L);
        manager.delete(department);
        assertTrue(manager.findById(department.getClass(),department.getId())==null);
    }

    @Test
    public void findByParamsTest(){
        Map <String,Object> myMap = new HashMap<>();
        myMap.put("department_id",30L);
        List myList = manager.findByParams(Department.class,myMap);
        assertEquals(1,myList.size());

    }

}
