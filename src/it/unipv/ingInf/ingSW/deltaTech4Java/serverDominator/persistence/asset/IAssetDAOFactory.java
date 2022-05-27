package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.asset;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Classe responsabile della creazione di {@link IAssetDAO}
 * @author TawaHabib
 * @version 1.0
 */
public class IAssetDAOFactory {
	private static IAssetDAO ass=null;
	
	private static final String ASSET_DAO_DEFAULT="IAssetDAO.class.name";
		
	private static final String PROPERTIE_FACTORY="resources/config/persistence/persistenceFactoryConfig";
	
	private static final String DEFAULT_CONN_PROPERTY_FILE="resources/config/persistence/dataBase/connWith_sd_sys";
	
	/**
	 * Metodo che crea un IAssetDAO di default secondo il contenuto del file di configurazione di persistence
	 * @return IAssetDAO
	 */
	public static IAssetDAO getIAssetDAO() {
		if(ass==null) {
			try {
				String className=PropertiesFile.getPropertieFromFile(ASSET_DAO_DEFAULT, PROPERTIE_FACTORY);
				
				@SuppressWarnings("rawtypes")
				Constructor c = Class.forName(className).getConstructor(String.class);
				
				ass=(IAssetDAO)c.newInstance(DEFAULT_CONN_PROPERTY_FILE);
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return ass;
	}
}
