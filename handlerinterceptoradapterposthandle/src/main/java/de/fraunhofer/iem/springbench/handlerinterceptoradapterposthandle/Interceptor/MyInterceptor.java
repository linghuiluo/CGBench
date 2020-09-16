package de.fraunhofer.iem.springbench.handlerinterceptoradapterposthandle.Interceptor;

import de.fraunhofer.iem.springbench.handlerinterceptoradapterposthandle.LOGGER.MyLogger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //remove all the information of this user from the cookie and session
        String user = request.getParameter("user");

        MyLogger.writeLog(user + " is trying to logout.");
    }
}
