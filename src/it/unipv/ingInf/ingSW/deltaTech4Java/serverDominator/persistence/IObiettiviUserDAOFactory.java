package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;


public class IObiettiviUserDAOFactory {
	private static IObiettiviUserDAO Own=null;
	
	private static final String OBIETTIVI_USER_DAO_DEFAULT="IOibiettiviUserDAO.class.name";
		
	private static final String PROPERTIE_FACTORY="resources/config/persistence/persistenceFactoryConfig";
	
	private static final String DEFAULT_CONN_PROPERTY_FILE="resources/config/persistence/dataBase/connWith_sd_sys";
	
	/**
	 * @return IAssetDAO
	 */
	public static IObiettiviUserDAO getIObiettiviUserDAO() {
		if(Own==null) {
			try {
				String className=PropertiesFile.getPropertieFromFile(OBIETTIVI_USER_DAO_DEFAULT, PROPERTIE_FACTORY);
				
				@SuppressWarnings("rawtypes")
				Constructor c = Class.forName(className).getConstructor(className.getClass());
				
				Own=(IObiettiviUserDAO)c.newInstance(DEFAULT_CONN_PROPERTY_FILE);
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return Own;
	}
}
