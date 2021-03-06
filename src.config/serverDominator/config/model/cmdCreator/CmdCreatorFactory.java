package serverDominator.config.model.cmdCreator;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Classe responsabile delle creazione delle strategie
 * per la creazione dei comandi 
 * @author TawaHabib
 * @version 1.0
 */
public class CmdCreatorFactory {
	
	public static final String CMD_FULL="strategy.cmdFull.class.name";
	public static final String CMD_NOT_FULL="strategy.fxCmd.class.name";
	private static final String PROPERTIE_FACTORY="resources/config/persistence/persistenceFactoryConfig";
	
	/**
	 * Creazione della strategia del creatore del comando 
	 * @param witch 
	 * strategia da adottare (deve essere una delle messe e disposizione dalla classe)
	 * @param fxPath
	 * path alla libreria lib di fx
	 * @param jarPath
	 * path al jar da eseguire 
	 * @return
	 * ICmdCreatorStrategy
	 */
	public static ICmdCreatorStrategy getCmdCreator(String witch,String fxPath, String jarPath) {
		ICmdCreatorStrategy result=null;
			try {
				String className=PropertiesFile.getPropertieFromFile(witch, PROPERTIE_FACTORY);
				
				@SuppressWarnings("rawtypes")
				Constructor c = Class.forName(className).getConstructor(String.class,String.class);
				
				result=(ICmdCreatorStrategy)c.newInstance(fxPath,jarPath);

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return result;
		}
	public static void main(String[] args) {
		System.out.println(getCmdCreator(CMD_NOT_FULL,"","").toString());
	}
}
