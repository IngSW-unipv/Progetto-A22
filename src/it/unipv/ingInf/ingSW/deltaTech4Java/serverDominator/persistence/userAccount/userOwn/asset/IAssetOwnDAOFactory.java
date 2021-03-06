package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.userAccount.userOwn.asset;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.obiettivi.IObiettiviDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Classe responsabile della creazione di {@link IObiettiviDAO}
 * @author TawaHabib
 * @version 1.0
 */
public class IAssetOwnDAOFactory {
	
	private static IAssetOwnDAO Own=null;
	
	private static final String ASSET_DAO_DEFAULT="IAssetOwnDAO.class.name";
		
	private static final String PROPERTIE_FACTORY="resources/config/persistence/persistenceFactoryConfig";
	
	private static final String DEFAULT_CONN_PROPERTY_FILE="resources/config/persistence/dataBase/connWith_sd_sys";
	
	/**
	 * Metodo che crea un IAssetOwnDAO di default (secondo il contenuto del file di configurazione di persistence
	 * @return IAssetOwnDAO
	 */
	public static IAssetOwnDAO getIAssetDAO() {
		if(Own==null) {
			try {
				String className=PropertiesFile.getPropertieFromFile(ASSET_DAO_DEFAULT, PROPERTIE_FACTORY);
				
				@SuppressWarnings("rawtypes")
				Constructor c = Class.forName(className).getConstructor(String.class);
				
				Own=(IAssetOwnDAO)c.newInstance(DEFAULT_CONN_PROPERTY_FILE);
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return Own;
	}
}
