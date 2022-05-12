package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class ILanguageManagerFactory {
	private static ILanguageManager linguaMan=null;
	
	private static final String ASSET_DAO_DEFAULT="ILanguageManager.class.name";
		
	private static final String PROPERTIE_FACTORY="resources/config/persistence/persistenceFactoryConfig";
	
	/**
	 * Metodo che crea un ILanguageManager di defaoult,
	 * secondo il contenuto del file di config di persistence 
	 * @return ILanguageManager
	 */
	public static ILanguageManager getILanguageManager() {
		if(linguaMan==null) {
			try {
				String className=PropertiesFile.getPropertieFromFile(ASSET_DAO_DEFAULT, PROPERTIE_FACTORY);
				
				@SuppressWarnings("rawtypes")
				Constructor c = Class.forName(className).getConstructor(String.class);
				
				linguaMan=(ILanguageManager)c.newInstance(ILanguageManager.getCurrentLanguage());
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return linguaMan;
	}
}
