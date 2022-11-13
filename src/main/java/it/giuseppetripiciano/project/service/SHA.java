package it.giuseppetripiciano.project.service;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHA {
	
	public static String encrypt(String plain) throws Exception {
		String sha256 = "";
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		
	    digest.reset();
	    
	    digest.update(plain.getBytes("utf8"));
	    
	    sha256 = String.format("%040x", new BigInteger(1, digest.digest()));
		
		return sha256;
	}
}
