package org.sphic.tps.util;

import java.security.MessageDigest;

public class Hash {	
	public static String SHA256(String stringToHash) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(stringToHash.getBytes("UTF-8"));
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
