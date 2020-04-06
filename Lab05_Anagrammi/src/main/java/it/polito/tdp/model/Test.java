package it.polito.tdp.model;

import java.util.Map;
import java.util.Set;

public class Test {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		//per rimuovere l'ultimo carattere di una stringa
		String a = "ciao";
		System.out.println(a);
		a = removeCh(a,a.length()-1);
		System.out.println(a);
		*/
		
		Model model=new Model();
		/*
		Set<String> soluzione=model.cerca("casa");
		int conta=0;
		for(String s:soluzione) {
			conta++;
			System.out.println(""+conta+" "+s);
		}
		
		if(model.isParzialePresente("abac")) {
			System.out.println("PRESENTE");
		}
		else {
			System.out.println("NON PRESENTE");
		}
		
		if(model.isParzialePresente("rz")) {
			System.out.println("PRESENTE");
		}
		else {
			System.out.println("NON PRESENTE");
		}
		
		if(model.isParzialePresente("ciao")) {
			System.out.println("PRESENTE");
		}
		else {
			System.out.println("NON PRESENTE");
		}
		
		if(model.isParzialePresente("ciaoz")) {
			System.out.println("PRESENTE");
		}
		else {
			System.out.println("NON PRESENTE");
		}
		*/
		
		Map<String,Boolean> soluzione2=model.cerca("casale");
		int conta=0;
		for(String s:soluzione2.keySet()) {
			if(soluzione2.get(s)==true) {
				conta++;
				System.out.println(""+conta+" "+s);
			}
			
		}
		
		

		
	}
	
	/*
	public static String removeCh (String s , int index) {
		if ((index > s.length()-1) || (index < 0)) {
			return null;
		}
		String c = s.substring(0,index) + s.substring(index+1 , s.length());
		return c;
	}
	*/


}
