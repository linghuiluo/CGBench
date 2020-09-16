package de.fraunhofer.iem.springbench.handlerinterceptoradapteraftercompletion.Interceptor;

import de.fraunhofer.iem.springbench.handlerinterceptoradapteraftercompletion.LOGGER.MyLogger;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String user = request.getParameter("user");

        MyLogger.writeLog(user + " is logged out.");
    }
}
