package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.Z2HController;
import ro.teamnet.zth.api.annotations.Z2HRequestMethod;
import ro.teamnet.zth.api.annotations.Z2HRequestObject;
import ro.teamnet.zth.api.annotations.Z2HRequestParam;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.service.DepartmentService;
import ro.teamnet.zth.fmk.domain.HttpMethod;

import java.util.List;

@Z2HController(urlPath = "/departments")
public class DepartmentController {

    public DepartmentService departmentService;

    @Z2HRequestMethod(urlPath = "/all")
    public List<Department> getAllDepartments() {
        return departmentService.findAll();
    }

    @Z2HRequestMethod(urlPath = "/one")
    public Department getOneDepartment(@Z2HRequestParam(name = "departmentId") Long departmentId) {
        return departmentService.findOne(departmentId);
    }

    @Z2HRequestMethod(urlPath = "/add", methodType = HttpMethod.POST)
    public Department addDepartment(@Z2HRequestObject Department department) {
        return departmentService.add(department);
    }

    @Z2HRequestMethod(urlPath = "/edit", methodType = HttpMethod.PUT)
    public Department updateDepartment(@Z2HRequestObject Department department) {
        return departmentService.update(department);
    }
}
