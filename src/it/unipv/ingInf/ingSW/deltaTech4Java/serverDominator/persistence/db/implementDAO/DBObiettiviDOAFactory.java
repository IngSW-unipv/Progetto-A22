package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO;

import java.lang.reflect.Constructor;


import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IObiettiviDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivi;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class DBObiettiviDOAFactory {
	
	private static final String propertieDBObiettiviDOAFactory="resources/config/persistence/dataBase/obiettiviFactoryConfig";
	private static final String connectionFilePth="resources/config/persistence/dataBase/connWith_sd_sys";
	
	
	public IObiettiviDAO getIObiettiviDAO(Obiettivi ob) {
		return getIObiettiviDAO(ob, connectionFilePth);
	}

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
