package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn;

import java.lang.reflect.Constructor;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class SessionFactory {
	private static final String CLASS_SESSION_NAME="ISession.class.name";
	
	private static final String PROPERTIE_FACTORY="resources/config/persistence/persistenceFactoryConfig";
	
	private static ISession session=null;
	
	
	public static ISession getSession() {
		if(session==null) {
			return sessionMaker();
		}
		return session;
	}
	
	private static ISession sessionMaker() {
		try {
			String className=PropertiesFile.getPropertieFromFile(CLASS_SESSION_NAME, PROPERTIE_FACTORY);
			
			@SuppressWarnings("rawtypes")
			Constructor c = Class.forName(className).getConstructor();
			
			session=(ISession)c.newInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return session;
	}
	
}
