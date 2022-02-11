package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class IAssetOwnDAOFactory {
	
	private static IAssetOwnDAO Own=null;
	
	private static final String ASSET_DAO_DEFAULT="IAssetOwnDAO.class.name";
		
	private static final String PROPERTIE_FACTORY="resources/config/persistence/persistenceFactoryConfig";
	
	private static final String DEFAULT_CONN_PROPERTY_FILE="resources/config/persistence/dataBase/connWith_sd_sys";
	
	/**
	 * @return IAssetDAO
	 */
	public static IAssetOwnDAO getIAssetDAO() {
		if(Own==null) {
			try {
				String className=PropertiesFile.getPropertieFromFile(ASSET_DAO_DEFAULT, PROPERTIE_FACTORY);
				
				@SuppressWarnings("rawtypes")
				Constructor c = Class.forName(className).getConstructor(className.getClass());
				
				Own=(IAssetOwnDAO)c.newInstance(DEFAULT_CONN_PROPERTY_FILE);
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return Own;
	}
}
