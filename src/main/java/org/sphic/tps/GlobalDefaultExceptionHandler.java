package org.sphic.tps;

import org.springframework.web.bind.annotation.ControllerAdvice;

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