package ro.teamnet.zth;

import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.fmk.MethodAttributes;
import ro.teamnet.zth.fmk.domain.HttpMethod;
import ro.teamnet.zth.utils.BeanDeserializator;
import ro.teamnet.zth.utils.ControllerScanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Z2HDispatcherServlet extends HttpServlet {
    ControllerScanner controllerScanner;

    @Override
    public void init() throws ServletException {
        controllerScanner = new ControllerScanner("ro.teamnet.zth.appl.controller");
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
                ioe.printStackTrace();
            }
        }
    }

    private void sendExceptionError(Exception e, HttpServletResponse resp) throws IOException {
        resp.getWriter().print(e.getMessage());
    }

    private void reply(HttpServletResponse resp, Object resultToDisplay) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(resultToDisplay);
        resp.getWriter().write(str);
    }

    private Object dispatch(HttpServletRequest req, HttpMethod methodType) {
        String path = req.getPathInfo();
        path += "/" + methodType;
        if(!(path.startsWith("/employees") || path.startsWith("/departments") || path.startsWith("/jobs") || path.startsWith("/locations"))){
            throw new RuntimeException("Url gresit");
        }
        MethodAttributes methodAttributes = controllerScanner.getMetaData(path, methodType);
        if(methodAttributes != null){
            Class cls;
            try {
                cls = Class.forName(methodAttributes.getControllerClass().getName());
                Method method = cls.getMethod(methodAttributes.getMethod().getName());
                return method.invoke(cls.newInstance(), BeanDeserializator.getMethodParams(method, req).toArray());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
        } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
