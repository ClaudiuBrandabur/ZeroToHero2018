package ro.teamnet.zth.web;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyDispatcherServlet extends HttpServlet {

    Map<String,MethodAttributes> myMap = new HashMap();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, "get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, "post");
    }

    public void init(){


        try {
            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for(Class cls : classes){
                MyController myController = (MyController) cls.getDeclaredAnnotation(MyController.class);
                Method[] methods = cls.getDeclaredMethods();
                System.out.println(myController.urlPath());
                for(Method method : methods){
                    String key;
                    System.out.println(method.getAnnotation(MyRequestMethod.class).urlPath());
                    System.out.println(method.getAnnotation(MyRequestMethod.class).methodType());
                    key = myController.urlPath() + method.getAnnotation(MyRequestMethod.class).urlPath() + method.getAnnotation(MyRequestMethod.class).methodType();
                    MethodAttributes value = new MethodAttributes();
                    value.setMethodName(method.getName());
                    value.setMethodType(method.getAnnotation(MyRequestMethod.class).methodType());
                    value.setControllerClass(cls.getName());
                    myMap.put(key, value);

                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void dispatchReply(HttpServletRequest req, HttpServletResponse resp, String method){
        try {
            Object object = dispatch(req, method);
            reply(resp, object);
        }
        catch (Exception e){
            sendExceptionError(e);
        }
    }

    Object dispatch(HttpServletRequest req, String method){
        String path = req.getPathInfo();
        path += "/" + method;
        if(!(path.startsWith("/employees") || path.startsWith("/departments") || path.startsWith("/jobs") || path.startsWith("/locations"))){
            throw new RuntimeException("Url gresit");
        }
        MethodAttributes methodAttributes = myMap.get(path);

        return methodAttributes;
    }

    void reply(HttpServletResponse resp, Object object){
        PrintWriter printWriter = null;
        try {
            printWriter = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        printWriter.write((String) object);
    }

    void sendExceptionError(Exception e){
        e.printStackTrace();
    }
}
