package org.sphic.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
	@RequestMapping(value = "/main",  method = RequestMethod.GET)
    public ModelAndView Login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
    }	
}
