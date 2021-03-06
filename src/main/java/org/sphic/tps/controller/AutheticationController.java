package org.sphic.tps.controller;

import org.sphic.tps.model.Account;
import org.sphic.tps.service.DAO.AccountDao;
import org.sphic.tps.util.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutheticationController {
	private AccountDao accountDao;

	@Autowired
	public AutheticationController(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Boolean LogIn(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		if(username.isEmpty())
			return false;
		
		Account account = accountDao.getByUsername(username);
		String hashedPassowrd = Hash.SHA256(password);
		
		if (username.equals("admin") && hashedPassowrd.equals(account.getPassword()))
			return true;

		return false;

	}

	
}
