package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IObiettiviDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * @author TawaHabib
 *
 */
public class DBObiettiviDOAFactory {
	
	private static IObiettiviDAO obiettiviDiPunteggioDAO;
	private static final String OBIETTIVI_DEFAULT="obiettiviDiPunteggioDAO.class.name";
	
	private static final String propertieDBObiettiviDOAFactory="resources/config/persistence/dataBase/obiettiviFactoryConfig";
	
	private static final String connectionFilePth="resources/config/persistence/dataBase/connWith_sd_sys";
	
	
	/**
	 * @return
	 */
	public static IObiettiviDAO getIObiettiviDAO() {
		if(obiettiviDiPunteggioDAO==null) {
			try {
				String className=PropertiesFile.getPropertieFromFile(OBIETTIVI_DEFAULT, propertieDBObiettiviDOAFactory);
				
				@SuppressWarnings("rawtypes")
				Constructor c = Class.forName(className).getConstructor(className.getClass());
				
				obiettiviDiPunteggioDAO=(IObiettiviDAO)c.newInstance(connectionFilePth);
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return obiettiviDiPunteggioDAO;
	}
	
	/**
	 * @param ob
	 * @return
	 */
	public static IObiettiviDAO getIObiettiviDAO(Obiettivi ob) {
		return getIObiettiviDAO(ob, connectionFilePth);
	}

	/**
	 * @param ob
	 * @param fileConnPath
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static IObiettiviDAO getIObiettiviDAO(Obiettivi ob, String fileConnPath){
		IObiettiviDAO obb;
		try {
			String className=PropertiesFile.getPropertieFromFile(ob.getClass().getName(), propertieDBObiettiviDOAFactory);
			
			Constructor c = Class.forName(className).getConstructor(className.getClass());
			
			 obb=(IObiettiviDAO)c.newInstance(fileConnPath);
			
			return obb;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

}
