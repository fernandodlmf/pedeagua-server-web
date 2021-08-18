package com.pedeagua.security;

import java.util.*;

public class SecuritySingleton {
	private static Map<String, String> map;
	
	private static SecuritySingleton sing;  
	  
	   private SecuritySingleton() {}  
	  
	   public static SecuritySingleton getInstance() {  
	      if (sing == null) {  
	    	  map = new HashMap<String, String>();
	          sing = new SecuritySingleton(); 	          
	      }  
	      return sing;  
	   }
	   
	   
	public Map<String, String> armazenaChaves(String chave, String valor){
		
		if(map.containsKey(chave) == false) {
			map.put(chave, valor);
			System.out.println("A chave não existia");
		} else {
			System.out.println("A chave existia geral");	
		}
		
		return map;
		
	}
	
	public boolean retiraChave(String chave, String valor){		
		
		return map.remove(chave, valor);
					
	}
	
	public boolean existeChave(String chave){		
		
		return map.containsKey(chave);
								
	}
	
	public String recuperaValor(String chave){		
		
		return map.get(chave);
								
	}

}
