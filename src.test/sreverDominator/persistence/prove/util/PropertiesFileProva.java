package sreverDominator.persistence.prove.util;

import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class PropertiesFileProva {

	public static void main(String[] args) throws Exception {
		Properties p=new Properties();
		p.setProperty("p1", "np1");
		p.setProperty("p2", "np2");
		p.setProperty("first.config", "50");
		try {

			PropertiesFile.savePropertyInFile(p, "resources/config/persistence/dataBase/connWith_root");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println( PropertiesFile.loadPropertiesFromCriptedFile("resources/config/persistence/dataBase/connWith_sd_sys"));
	}
}
