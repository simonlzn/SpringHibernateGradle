package org.sphic;

import org.sphic.HibernateConfig.HibernateUtil;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
//
//        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
//            throw e;
//
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("error");
//        return mav;
//    }
}