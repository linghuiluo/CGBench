package de.fraunhofer.iem.springbench.handlerinterceptorposthandle.Interceptor;

import de.fraunhofer.iem.springbench.handlerinterceptorposthandle.LOGGER.MyLogger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //remove all the information of this user from the cookie and session
        String user = request.getParameter("user");

        MyLogger.writeLog(user + " is trying to logout.");
    }
}
