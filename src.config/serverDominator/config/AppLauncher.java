package serverDominator.config;

import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DataBase;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;
import serverDominator.config.controllers.Controller;
import serverDominator.config.model.ScriptsFacade;
import serverDominator.config.viw.ConfigFrame;

public class AppLauncher {

	private static final String CONTEXT="src.cofg.context";
	private static final String PROP_FILE="resources/config/persistence/persistenceFactoryConfig";
	
	public static void run() throws Exception {
		Properties p=PropertiesFile.loadPropertiesFromFile(PROP_FILE);
		String s=p.getProperty(CONTEXT);
		Integer ctx= s!=null? Integer.valueOf(s):1;
		//System.out.println(ctx);
		p.clear();
		if(ctx!=1) {
			ScriptsFacade.getIstance().runShellScript();
		}else {
			p.put(CONTEXT, "5");
			PropertiesFile.savePropertyInFile(p, PROP_FILE);
			p.put(DataBase.FIRST_CONFIGURATION_PROPERTIE_NAME, "1");
			PropertiesFile.savePropertyInCriptedFile(p, "resources/config/persistence/dataBase/connWith_root");
			//System.out.println(CryptoUtil.decrypt(FileToString.transformFileToString("resources/config/persistence/dataBase/connWith_root"))); 
			ConfigFrame f=new ConfigFrame();
			@SuppressWarnings("unused")
			Controller conn=new Controller( f);
		}	
	}
}
