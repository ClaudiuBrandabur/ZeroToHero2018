package ro.teamnet.zth.web;

import ro.teamnet.zth.appl.controllers.DepartmentController;
import ro.teamnet.zth.appl.controllers.EmployeeController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyDispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        dispatchReply(req, resp, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        dispatchReply(req, resp, "POST");
    }

    void dispatchReply(HttpServletRequest request, HttpServletResponse response, String type ){

        try{
            Object result = dispatch(request,type);
            reply(response,result,type);

        }catch (Exception e){
            sendExceptionError(e);
        }
    }

    Object dispatch(HttpServletRequest request, String type){

        StringBuffer URL = request.getRequestURL();
        String result = "";

        if(URL.toString().contains("/employees")){
            EmployeeController employeeController = new EmployeeController();
            result = employeeController.getAllEmployees();
        }

        if(URL.toString().contains("/departments")){
            DepartmentController departmentController = new DepartmentController();
            result = departmentController.getAllDepartments();
        }
        return result;
    }

    void reply(HttpServletResponse response,Object result, String type){
        try {
            response.getWriter().write((String) result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void sendExceptionError(Exception e) {
        e.printStackTrace();
    }
}
