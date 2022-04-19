package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Classe responsabile della creazione di {@link IUserAccountDAO}
 * @author TawaHabib
 * @version 1.0
 */
public class IUserAccountDAOFactory {
	private static IUserAccountDAO usDao;
	private static final String PROPERTYNAME="IUserAccountDAO.class.name";
	public static final String FILE_NAME="resources/config/persistence/persistenceFactoryConfig";
	private static final String DEFAULT_CONN_PROPERTY_FILE="resources/config/persistence/dataBase/connWith_sd_sys";
	
	/**
	 * Metodo che crea un IUserAccountDAO di default,
	 * secondo il contenuto del file di configurazione di persistence
	 * @return IUserAccountDAO
	 */
	public static IUserAccountDAO getUserAccountDAO() {
		if(usDao==null) {
			String className;
			try {
					className=PropertiesFile.getPropertieFromFile(PROPERTYNAME, FILE_NAME);
					@SuppressWarnings("rawtypes")
					Constructor c = Class.forName(className).getConstructor(String.class);
					usDao=(IUserAccountDAO)c.newInstance(DEFAULT_CONN_PROPERTY_FILE);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return usDao;
	}
}
