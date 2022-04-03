package serverDominator.config;

import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DataBase;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.CryptoUtil;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.FileToString;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;
import serverDominator.config.controllers.Controller;
import serverDominator.config.viw.ConfigFrame;

public class Main {
	public static void main(String[] args) throws Exception {
		Properties p=new Properties();
		p.put(DataBase.FIRST_CONFIGURATION_PROPERTIE_NAME, "1");
		PropertiesFile.savePropertyInCriptedFile(p, "resources/config/persistence/dataBase/connWith_root");
		System.out.println(CryptoUtil.decrypt(FileToString.transformFileToString("resources/config/persistence/dataBase/connWith_root"))); 
		ConfigFrame f=new ConfigFrame();
		@SuppressWarnings("unused")
		Controller conn=new Controller( f);
		
	}
	
}

