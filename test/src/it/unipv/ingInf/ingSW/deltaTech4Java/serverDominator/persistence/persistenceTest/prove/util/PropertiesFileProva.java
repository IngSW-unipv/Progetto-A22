package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.persistenceTest.prove.util;


import java.io.IOException;
import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class PropertiesFileProva {

	public static void main(String[] args) {
		Properties p=new Properties();
		p.setProperty("p1", "np1");
		p.setProperty("p2", "np2");
		p.setProperty("first.config", "50");
		try {

			PropertiesFile.savePropertyInFile(p, "resources/config/persistence/dataBase/connWith_root");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
