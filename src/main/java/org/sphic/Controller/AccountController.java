package org.sphic.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/account")
public class AccountController {
	@RequestMapping(value = "/login",  method = RequestMethod.GET)
    public ModelAndView Login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
    }	
}
