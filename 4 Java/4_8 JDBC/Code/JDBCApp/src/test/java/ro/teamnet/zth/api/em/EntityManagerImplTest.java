package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class EntityManagerImplTest {

    @Test
    public void testFindById() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Employee employee = new Employee();
        Long id = new Long(101);
        employee = entityManager.findById(Employee.class, id);
        assertEquals(employee.getId(), 101);
    }

    @Test
    public void testGetNextIdVal() throws SQLException, ClassNotFoundException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Long nextId = entityManager.getNextIdVal("EMPLOYEES", "EMPLOYEE_ID");
        assertEquals(207, nextId.intValue());
    }

    @Test
    public void testInsert() throws ClassNotFoundException, SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department department = new Department();
        department.setDepartmentName("IT");
        department.setLocation((long) 1700);
        department.setId((long) 280);
        department = (Department) entityManager.insert(department);

        assertEquals(entityManager.findById(Department.class, department.getId()), department);

    }

    @Test
    public void testFindAll() throws ClassNotFoundException, SQLException, IllegalAccessException, NoSuchFieldException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        ArrayList<Employee> arrayList = new ArrayList<>();
        arrayList = entityManager.findAll(Employee.class);
        assertEquals(arrayList.size(), 107);

    }

    @Test
    public void testUpdate(){
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Employee employee = new Employee();
        employee.setId
    }

    @Test
    public void testDelete(){

    }
}
