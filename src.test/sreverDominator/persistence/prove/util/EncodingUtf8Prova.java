package sreverDominator.persistence.prove.util;

import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class EncodingUtf8Prova {
	public static void main(String[] args) throws Exception {
		Properties p=null;
		p=PropertiesFile.loadPropertiesFromCriptedFile("resources/config/persistence/dataBase/connWith_root");
		PropertiesFile.savePropertyInFile(p, "connWith_root");
		p=PropertiesFile.loadPropertiesFromCriptedFile("resources/config/persistence/dataBase/connWith_sd_sys");
		PropertiesFile.savePropertyInFile(p, "connWith_sd_sys");
		
	}
}
