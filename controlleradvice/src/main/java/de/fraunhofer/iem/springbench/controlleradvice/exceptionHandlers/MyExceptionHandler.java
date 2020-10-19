package de.fraunhofer.iem.springbench.controlleradvice.exceptionHandlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebMvc
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = {NumberFormatException.class})
    public void invalidNumberExceptionHandler(NumberFormatException ex, HttpServletResponse response) throws IOException {

        String uid = ex.getMessage();

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(uid);
    }
}
