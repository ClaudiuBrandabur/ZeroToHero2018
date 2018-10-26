package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.domain.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Raluca.Russindilar on 13.07.2016.
 */
public class EmployeeDao {
    EntityManager entityManager = new EntityManagerImpl();

    /**
     * @param employee
     * @return employee object
     */
    public Employee insertEmployee(Employee employee) {
        return (Employee) entityManager.insert(employee);
    }

    /**
     * @param employee
     * @return employee object
     */
    public Employee updateEmployee(Employee employee) {
        return entityManager.update(employee);
    }
    public List<Employee> findByManagerId(Long managerId){
        Map<String, Object> params = new HashMap<>();
        params.put("MANAGER_ID", managerId);
        return entityManager.findByParams(Employee.class, params);
    }

    /**
     * @param employee
     */
    public void deleteEmployee(Employee employee) {
        entityManager.delete(employee);
    }

    /**
     * @return a list of employees
     */
    public List<Employee> getAllEmployees() {
        EntityManager entityManager = new EntityManagerImpl();
        return entityManager.findAll(Employee.class);
    }

    /**
     * @param id
     * @return employee object
     */
    public Employee getEmployeeById(Long id) {
        EntityManager entityManager = new EntityManagerImpl();
        return entityManager.findById(Employee.class, id);
    }
}
