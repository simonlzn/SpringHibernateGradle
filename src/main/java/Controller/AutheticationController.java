package main.java.Controller;

import java.security.MessageDigest;
import main.java.HibernateConfig.HibernateUtil;
import main.java.Model.Account;
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
		Account account = (Account) cr.list().get(0);		
		String hashedPassowrd = hash(password);
		
		if (username.equals("admin") && hashedPassowrd.equals(account.getPassword()))
			return true;

		return false;

	}

	private String hash(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password.getBytes("UTF-8"));
		} catch (Exception e) { 
			e.printStackTrace();
		} 
		byte[] digest = md.digest();
		StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < digest.length; i++) {
            String hex = Integer.toHexString(0xff & digest[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
		return hexString.toString();
	}
}
