package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IObiettiviDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Classe responsabile della creazione deli obiettivi dao relativi a salvare le info nel db
 * @author TawaHabib
 *
 */
public class DBObiettiviDOAFactory {
	
	private static IObiettiviDAO obiettiviDiPunteggioDAO;
	private static final String OBIETTIVI_DEFAULT="obiettiviDiPunteggioDAO.class.name";
	
	private static final String propertieDBObiettiviDOAFactory="resources/config/persistence/persistenceFactoryConfig";
	
	private static final String connectionFilePth="resources/config/persistence/dataBase/connWith_sd_sys";
	
	
	/**
	 * Crea  dataBase Obiettivi Dao generico ()
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
	 * Creao Obiettivi Dao specifico all'obeittivo passato per argomento </br>con unn file di connessione predefinito
	 * @param ob
	 * obiettivo di cui si vogliono salcare il info nel db
	 * @return IObiettiviDAO pecifico per salvare correttamento l'obiettivo passato per argomento
	 */
	public static IObiettiviDAO getIObiettiviDAO(Obiettivi ob) {
		return getIObiettiviDAO(ob, connectionFilePth);
	}

	/**
	 * Crea IObiettioviDAO specifico del obiettivo argomento </br> Con file di configurazione a scielta
	 * @param ob
	 * obiettivo di cui si vogliono salcare il info nel db
	 * @param fileConnPath
	 * file di configurazione della connessione
	 * @return IObiettiviDAO pecifico per salvare correttamento l'obiettivo passato per argomento
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
