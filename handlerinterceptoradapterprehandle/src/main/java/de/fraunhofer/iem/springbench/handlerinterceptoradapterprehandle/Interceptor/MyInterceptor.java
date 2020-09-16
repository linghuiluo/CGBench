package de.fraunhofer.iem.springbench.handlerinterceptoradapterprehandle.Interceptor;

import de.fraunhofer.iem.springbench.handlerinterceptoradapterprehandle.LOGGER.MyLogger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String user = request.getParameter("user");

        MyLogger.writeLog(user + " is trying to login.");

        return true;
    }
}
