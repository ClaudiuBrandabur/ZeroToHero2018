package ro.teamnet.zth.web;

import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.fmk.MethodAttributes;
import ro.teamnet.zth.fmk.*;
import ro.teamnet.zth.fmk.domain.HttpMethod;
import ro.teamnet.zth.utils.BeanDeserializator;
import ro.teamnet.zth.utils.ComponentScanner;
import ro.teamnet.zth.utils.ControllerScanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Z2HDispatcherServlet extends HttpServlet {
    private ComponentScanner controllerScanner;


    @Override
    public void init() throws ServletException {
        controllerScanner=new ControllerScanner("ro.teamnet.zth.appl.controller");
        controllerScanner.scan();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.POST);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.DELETE);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply(req, resp, HttpMethod.PUT);
    }

    private void dispatchReply(HttpServletRequest req, HttpServletResponse resp, HttpMethod methodType) {
        try {
            Object resultToDisplay = dispatch(req, methodType);
            reply(resp, resultToDisplay);
        } catch (Exception e) {
            try {
                sendExceptionError(e, resp);
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }

    private void sendExceptionError(Exception e, HttpServletResponse resp) throws IOException {
        resp.getWriter().print(e.getMessage());
    }

    private void reply(HttpServletResponse resp, Object resultToDisplay) {
        ObjectMapper objectMapper = new ObjectMapper();
        final String responseAsString;
        try {
            responseAsString = objectMapper.writeValueAsString(resultToDisplay);
            resp.getWriter().print(responseAsString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Object dispatch(HttpServletRequest req, String method){
        String path = req.getPathInfo();
        path += "/" + method;
        if(!(path.startsWith("/employees") || path.startsWith("/departments") || path.startsWith("/jobs") || path.startsWith("/locations"))){
            throw new RuntimeException("Url gresit");
        }
        MethodAttributes methodAttributes = Employee.get(path);
        if(methodAttributes != null){
            try {
                Class cls = Class.forName(methodAttributes.getControllerClass());
                Method met = cls.getMethod(methodAttributes.getMethodName())
                return met.invoke(cls.newInstance());
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
        }

        return methodAttributes;


}