package de.fraunhofer.iem.springbench.handlerinterceptorprehandle.Interceptor;

import de.fraunhofer.iem.springbench.handlerinterceptorprehandle.LOGGER.MyLogger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String user = request.getParameter("user");

        MyLogger.writeLog(user + " is trying to login.");

        return true;
    }
}
