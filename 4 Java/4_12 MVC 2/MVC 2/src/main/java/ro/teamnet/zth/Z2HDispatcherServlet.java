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
import java.util.HashMap;
import java.util.Map;

public class Z2HDispatcherServlet extends HttpServlet {

    Map<String,MethodAttributes> allMyAttributes = new HashMap<>();
    ControllerScanner cscanner;

    @Override
    public void init() throws ServletException {
        cscanner = new ControllerScanner("ro.teamnet.zth.appl.controllers");
        cscanner.scan();
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

    private void reply(HttpServletResponse resp, Object resultToDisplay) {
        ObjectMapper om = new ObjectMapper();
        final String responseAsString;
        try {
            responseAsString = om.writeValueAsString(resultToDisplay);
            resp.getWriter().write(responseAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Object dispatch(HttpServletRequest req, HttpMethod methodType) {

        String URL = req.getPathInfo();
        MethodAttributes methodAttribute;

        if(!URL.startsWith("/employees") && !URL.startsWith("/departments") && !URL.startsWith("/locations") && !URL.startsWith("/jobs")){
            throw new RuntimeException("URL GRESIT!");
        }

        methodAttribute = cscanner.getMetaData(URL,methodType);

        try {

            if(methodAttribute != null) {

                Class myClass = methodAttribute.getControllerClass();
                Method myMethod = methodAttribute.getMethod();
                return myMethod.invoke(myClass.newInstance(),BeanDeserializator.getMethodParams(myMethod,req).toArray());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
