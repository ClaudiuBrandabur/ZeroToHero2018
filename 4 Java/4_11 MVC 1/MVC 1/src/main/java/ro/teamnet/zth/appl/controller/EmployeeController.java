package ro.teamnet.zth.appl.controller;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

@MyController(urlPath = "/employees")
public class EmployeeController {
    @MyRequestMethod(urlPath = "/all", methodType = "/get")
    String getAllEmployees(){
        return "allEmployees";
    }

    @MyRequestMethod(urlPath = "/one", methodType = "/get")
    String getOneEmployee(){
        return "oneRandomEmployee";
    }
}
