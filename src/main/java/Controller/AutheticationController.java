package main.java.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutheticationController {
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Boolean LogIn(@RequestParam("username") String username,
			@RequestParam("password") String password) {		
		if (username.equals("admin") && password.equals("sphic"))
			return true;

		return false;

	}
}
