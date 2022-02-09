package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.prove.util;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class Prova1 {

	public static void main(String[] args) {
		int a=80;
		try {
		PropertiesFile.addPropertieInFile("b", String.valueOf(a), "asd");
		Integer c=Integer.valueOf(PropertiesFile.loadPropertiesFromFile("asd").getProperty("b"));
		int d=(int)c;
		System.out.println("b= "+c);
		d=d+c*2;
		System.out.println("d= "+d);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
