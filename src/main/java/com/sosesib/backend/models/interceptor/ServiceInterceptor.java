package com.sosesib.backend.models.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Log4j2
public class ServiceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        Integer code = response.getStatus();
        String method = request.getMethod();
        String protocol = request.getProtocol();
        String output = (" -  " + ip + " - - " + "'" + method + " " + url + " " + protocol + "' " + code.toString());
        if(code==200) {
            log.info(output);
        }
        else {
            log.error(output);
        }
        return true;
    }
    /*
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        System.out.println("Pre Handle method is Calling");
    }

     */
}
