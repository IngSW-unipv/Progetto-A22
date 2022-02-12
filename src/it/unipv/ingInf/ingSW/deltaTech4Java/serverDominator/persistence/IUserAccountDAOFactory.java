package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * @author TawaHabib
 *
 */
public class IUserAccountDAOFactory {
	private static IUserAccountDAO usDao;
	private static final String PROPERTYNAME="userAccountDAO.class.name";
	public static final String FILE_NAME="resources/config/persistence/persistenceFactoryConfig";
	
	public static IUserAccountDAO getUserAccountDAO() {
		if(usDao==null) {
			String className;
			try {
					className=PropertiesFile.getPropertieFromFile(PROPERTYNAME, FILE_NAME);
					@SuppressWarnings("rawtypes")
					Constructor c = Class.forName(className).getConstructor();
					usDao=(IUserAccountDAO)c.newInstance();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return usDao;
	}
}
