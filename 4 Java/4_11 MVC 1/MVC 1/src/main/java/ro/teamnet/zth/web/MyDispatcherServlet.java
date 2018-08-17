package ro.teamnet.zth.web;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.appl.controller.JobController;
import ro.teamnet.zth.appl.controller.LocationController;
import ro.teamnet.zth.appl.domain.Location;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyDispatcherServlet extends HttpServlet {

    Map<String,MethodAttributes> allMyAttributes = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        dispatchReply(req, resp, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        dispatchReply(req, resp, "POST");
    }

    @Override
    public void init() throws ServletException {

        try {
            Iterable <Class> classes = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for(Class c : classes){

                if(c.getAnnotation(MyController.class) == null){
                    return;
                }

                MyController myController = (MyController) c.getAnnotation(MyController.class);
                Method[] methods = c.getDeclaredMethods();
                for(Method method : methods){
                    MethodAttributes methodAttributes = new MethodAttributes();
                    methodAttributes.setControllerClass(c.getName());
                    methodAttributes.setMethodName(method.getName());
                    methodAttributes.setMethodType(method.getAnnotation(MyRequestMethod.class).methodType());

                    String key = myController.urlPath() + method.getAnnotation(MyRequestMethod.class).urlPath() +"/"+
                            method.getAnnotation(MyRequestMethod.class).methodType();

                    allMyAttributes.put(key,methodAttributes);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void dispatchReply(HttpServletRequest request, HttpServletResponse response, String type ){

        try{
            Object result = dispatch(request,type);
            reply(response,result);

        }catch (Exception e){
            sendExceptionError(response,e);
        }
    }

    Object dispatch(HttpServletRequest request, String type){

        String URL = request.getPathInfo();
        MethodAttributes methodAttribute = null;
        String path;

        if(!URL.startsWith("/employees") && !URL.startsWith("/departments") && !URL.startsWith("/locations") && !URL.startsWith("/jobs")){
            throw new RuntimeException("URL GRESIT!");
        }

        path = URL + "/" + type;
        methodAttribute = allMyAttributes.get(path);

        try {

            if(methodAttribute != null) {

                Class myClass = Class.forName(methodAttribute.getControllerClass());
                Method myMethod = myClass.getMethod(methodAttribute.getMethodName());
                return myMethod.invoke(myClass.newInstance());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    void reply(HttpServletResponse response,Object result){
        try {
            response.getWriter().write((String) result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendExceptionError(HttpServletResponse response,Exception e) {
        try {
            response.getWriter().write(e.getMessage());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
