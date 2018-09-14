package ro.teamnet.zth.web;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.fmk.AnnotationScanUtils;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, "get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, "post");
    }

    public void init(){
        Map<String,String> map = new HashMap();

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
            dispatch(req, method);
            reply(resp, method);
        }
        catch (Exception e){
            sendExceptionError(e);
        }
    }

    void dispatch(HttpServletRequest req, String method){
        String path = req.getPathInfo();
        path += "/" + method;
        if(!(path.startsWith("/employees") || path.startsWith("/departments") || path.startsWith("/jobs") || path.startsWith("/locations"))){
            throw new RuntimeException("Url gresit");
        }
    }

    void reply(HttpServletResponse resp, String method){
        PrintWriter printWriter;
//        printWriter.write();
    }

    void sendExceptionError(Exception e){
        e.printStackTrace();
    }
}
