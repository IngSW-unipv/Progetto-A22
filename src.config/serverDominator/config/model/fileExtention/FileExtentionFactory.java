package serverDominator.config.model.fileExtention;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * classe respnsabile della creazione di IFileExtensionStrategy
 * @author TawaHabib
 * @version 1.0
 */
public class FileExtentionFactory {
	
	private static String STRATEGY_PROP="classe.name.for.fileExtension.";
	
	private static final String PROPERTIE_FACTORY="resources/config/persistence/persistenceFactoryConfig";
	
	/**
	 * Crea IFileExtensionStrategy in base al sistema operativo passato per argomento 
	 * @param os
	 * Sistema operativo
	 * @return
	 * IFileExtensionStrategy
	 */
	public static IFileExtensionStrategy getIFileExtensionStrategy(String os) {
		IFileExtensionStrategy result=null;
		os=os.toLowerCase();
		os=os.replaceAll("[^a-z]", "");
		try {
			String className=PropertiesFile.getPropertieFromFile(STRATEGY_PROP+(os), PROPERTIE_FACTORY);
			System.out.println(className);
			@SuppressWarnings("rawtypes")
			Constructor c = Class.forName(className).getConstructor();
			
			result=(IFileExtensionStrategy)c.newInstance();

		} catch (Exception e) {
		}
		return result;
	}
	
	/**
	 * Crea IFileExtensionStrategy in base al sistema operativo corrente
	 * @return
	 * IFileExtensionStrategy
	 */
	public static IFileExtensionStrategy getIFileExtensionStrategy() {
		return getIFileExtensionStrategy(System.getProperty("os.name"));
	}
	
	/*
	 * prova
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(FileExtentionFactory.getIFileExtensionStrategy().getFileExtension()); 
	}
}
