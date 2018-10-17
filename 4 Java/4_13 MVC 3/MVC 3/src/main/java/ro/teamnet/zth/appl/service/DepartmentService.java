package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.domain.Department;

import java.util.List;

/**
 * Created by Oana.Mihai on 7/15/2016.
 */
public interface DepartmentService {
    List<Department> findAllDepartments();

    Department findOne(Long departmentId);

    Department save (Department department);

    Department update (Department department);

    Department delete(Department department);
}
