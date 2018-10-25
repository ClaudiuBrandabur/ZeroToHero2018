package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.dao.JobDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDao();



    @Override
    public List<Employee> findAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee findOneEmployee(Long employeeId) {
       Employee employee = employeeDao.getEmployeeById(employeeId);
        DepartmentDao dep = new DepartmentDao();
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

        JobDao jobDao = new JobDao();
        Department department = dep.findDepartmentById(employee.getDepartmentId());
        Job job = jobDao.getJobById(employee.getJobId());
        employee.setManager(employeeService.findOneManager(employee.getManagerId()));
        employee.setDepartment(department);
        employee.setJob(job);
        return employee;
    }

    public Employee findOneManager(long employeeId){
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public Boolean delete(Long employeeId) {
        Employee employeeToDelete = employeeDao.getEmployeeById(employeeId);
        if (employeeToDelete == null) {
            return false;
        }
        employeeDao.deleteEmployee(employeeToDelete);
        return true;
    }

    @Override
    public Employee save(Employee employee) {
        return employeeDao.insertEmployee(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }
}
