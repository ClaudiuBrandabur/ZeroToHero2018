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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyDispatcherServlet extends HttpServlet {

    Map<String, MethodAttributes> attributes = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        dispatchReply(request, response, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        dispatchReply(request, response, "POST");
    }

    void dispatchReply(HttpServletRequest request, HttpServletResponse response, String method) {
        try {
            Object ans = dispatch(request, method);
            reply(response, ans);
        } catch (Exception e) {
            sendExceptionError(response, e);
        }
    }

    Object dispatch(HttpServletRequest request, String method) {
        String url = request.getPathInfo();
        MethodAttributes methodAttributes = attributes.get(url + "/" + method);

        if (!(url.startsWith("/employees") || url.startsWith("/departments") || url.startsWith("/locations")
                || url.startsWith("/jobs"))) {
            throw new RuntimeException("WRONG URL!!!!!!!!!!");
        }

        try {
            if (methodAttributes != null) {
                Method aMethod = Class.forName(methodAttributes.getControllerClass())
                        .getMethod(methodAttributes.getMethodName());
                return aMethod.invoke(Class.forName(methodAttributes.getControllerClass()).newInstance());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    void reply(HttpServletResponse response, Object result) {
        try {
            response.getWriter().write((String) result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendExceptionError(HttpServletResponse response, Exception e) {
        try {
            response.getWriter().write(e.getMessage());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException{

        try {
            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class clazz : classes) {

                if (clazz.getAnnotation(MyController.class) == null) {
                    return ;
                }
                MyController myController = (MyController) clazz.getAnnotation(MyController.class);

                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {

                    MethodAttributes methodAttributes = new MethodAttributes();
                    methodAttributes.setControllerClass(clazz.getName());
                    methodAttributes.setMethodName(method.getName());
                    methodAttributes.setMethodType(method.getAnnotation(MyRequestMethod.class).methodType());

                    String string = myController.urlPath() + "/" + method.getAnnotation(MyRequestMethod.class).urlPath() +
                            "/" + method.getAnnotation(MyRequestMethod.class).methodType();
                    attributes.put(string, methodAttributes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
