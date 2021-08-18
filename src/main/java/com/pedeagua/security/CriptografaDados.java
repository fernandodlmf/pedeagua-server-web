package com.pedeagua.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografaDados {
	
	public CriptografaDados() {		
		
	}
		
	public static String gerarHash(String frase, String algoritmo) {
		
		String m;
		 
		try {
		    MessageDigest md = MessageDigest.getInstance(algoritmo);		    
		    md.update(frase.getBytes());
		    		    
		    m = stringHexa(md.digest());
		    System.out.println("HASH processado é: " + m);
		    return m;	
		    
		} catch (NoSuchAlgorithmException e) {
			System.out.println("erro no gerarHash: "+ e.getMessage());
		    return null;
		}
		  
	}
	
	private static String stringHexa(byte[] bytes) {
		   StringBuilder s = new StringBuilder();
		   for (int i = 0; i < bytes.length; i++) {
		       int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
		       int parteBaixa = bytes[i] & 0xf;
		       if (parteAlta == 0) s.append('0');
		       s.append(Integer.toHexString(parteAlta | parteBaixa));
		   }
		   return s.toString();
		}
		
}
