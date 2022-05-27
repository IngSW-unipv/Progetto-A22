package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.userAccount.userOwn.obiettivi;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;


/**
 * Classe responsabile della creazione di {@link IObiettiviUserDAO}
 * @author TawaHabib
 * @version 1.0
 */
public class IObiettiviUserDAOFactory {
	private static IObiettiviUserDAO Own=null;
	
	private static final String OBIETTIVI_USER_DAO_DEFAULT="IOibiettiviUserDAO.class.name";
		
	private static final String PROPERTIE_FACTORY="resources/config/persistence/persistenceFactoryConfig";
	
	private static final String DEFAULT_CONN_PROPERTY_FILE="resources/config/persistence/dataBase/connWith_sd_sys";
	
	/**
	 * Metodo che crea un IObiettiviUserDAO di default,
	 * secondo il contenuto del file di configurazione di persistence
	 * @return IObiettiviUserDAO
	 */
	public static IObiettiviUserDAO getIObiettiviUserDAO() {
		if(Own==null) {
			try {
				String className=PropertiesFile.getPropertieFromFile(OBIETTIVI_USER_DAO_DEFAULT, PROPERTIE_FACTORY);
				
				@SuppressWarnings("rawtypes")
				Constructor c = Class.forName(className).getConstructor(String.class);
				
				Own=(IObiettiviUserDAO)c.newInstance(DEFAULT_CONN_PROPERTY_FILE);
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return Own;
	}
}
