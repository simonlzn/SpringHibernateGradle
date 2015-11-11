package main.java.Controller;

import java.security.MessageDigest;

import main.java.HibernateConfig.HibernateUtil;
import main.java.Model.Account;
import main.java.util.Hash;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
		if(username.isEmpty())
			return false;
		
		Session session = HibernateUtil.currentSession();
		Criteria cr = session.createCriteria(Account.class).add(Restrictions.eq("username", username));
		if(cr.list().size()== 0)
			return false;
		
		Account account = (Account) cr.list().get(0);		
		String hashedPassowrd = Hash.SHA256(password);
		
		if (username.equals("admin") && hashedPassowrd.equals(account.getPassword()))
			return true;

		return false;

	}

	
}
