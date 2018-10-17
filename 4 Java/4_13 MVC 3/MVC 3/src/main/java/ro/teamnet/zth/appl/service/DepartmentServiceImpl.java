package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao = new DepartmentDao();

    @Override
    public List<Department> findAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Override
    public Department findOne(Long departmentId) {
        return departmentDao.findDepartmentById(departmentId);
    }

    @Override
    public Department save(Department department) {
        return null;
    }

    @Override
    public Department update(Department department) {
        return null;
    }

    @Override
    public Department delete(Department department) {
        return null;
    }
}
