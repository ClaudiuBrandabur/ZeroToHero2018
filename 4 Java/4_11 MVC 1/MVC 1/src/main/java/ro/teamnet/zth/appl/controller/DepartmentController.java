package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

@MyController(urlPath = "departments")
public class DepartmentController {
    @MyRequestMethod(urlPath = "/all", methodType = "/get")
    String getAllDepartments(){
        return "allDepartments";
    }

    @MyRequestMethod(urlPath = "/one", methodType = "/get")
    String getOneDepartment(){
        return "oneRandomDepartment";
    }
}
