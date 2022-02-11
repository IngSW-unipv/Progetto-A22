package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.ExecuteSQLfiel;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class DataBase {
	
	private static final String FIRST_CONFIGURATION_PROPERTIE_NAME ="primaConfigurazione";
	
	private static final String FIRST_CONFIGURATION_PROPERTIE_FILE_NAME ="resources/config/persistence/dataBase/connWith_root";
	
	private static final String CREATE_SCHEMA_FILE_NAME ="resources/databaseDefineSchema/dataBaseSchema.sql";
	
	private static final String POPOLA_SCHEMA_FILE_NAME ="resources/databaseDefineSchema/popolaSchema.sql";
	
	private static final String FIRST_CONFIGURATION_URL_PROPERTIE ="url";
	
	private static final String FIRST_CONFIGURATION_USERNAME_PROPERTIE ="username";
	
	private static final String FIRST_CONFIGURATION_PASSWORD_PROPERTIE ="password";
	
	public static boolean createDataBase() {
		try {
			String us=PropertiesFile.getPropertieFromFile(FIRST_CONFIGURATION_USERNAME_PROPERTIE, FIRST_CONFIGURATION_PROPERTIE_FILE_NAME);
			if(us.length()<2) {
				System.err.println("controlla username");
			}
		} catch (Exception e) {
			System.err.println("problem with configuration file");
		}
		try {
			String url=PropertiesFile.getPropertieFromFile(FIRST_CONFIGURATION_USERNAME_PROPERTIE, FIRST_CONFIGURATION_PROPERTIE_FILE_NAME);
			if(url.length()<18) {
				System.err.println("Url sembra scoretta");
			}
		} catch (Exception e) {
			System.err.println("problem with configuration file");
		}
		try {
			String pass=PropertiesFile.getPropertieFromFile(FIRST_CONFIGURATION_USERNAME_PROPERTIE, FIRST_CONFIGURATION_PROPERTIE_FILE_NAME);
			if(pass.length()<1) {
				System.err.println("password non impostata");
			}
		} catch (Exception e) {
			System.err.println("problem with configuration file");
		}
		Integer a=0;
		try {
			a=Integer.getInteger(PropertiesFile.getPropertieFromFile(FIRST_CONFIGURATION_PROPERTIE_NAME, FIRST_CONFIGURATION_PROPERTIE_FILE_NAME));
		} catch (Exception e) {
			System.err.println("problem with configuration file");
		}
		switch (a) {
		case 1: {
			try {
				ExecuteSQLfiel.executeSqlFile(CREATE_SCHEMA_FILE_NAME, FIRST_CONFIGURATION_PROPERTIE_FILE_NAME);
			} catch (Exception e) {
				System.err.println("problemi when i tray to create schema");
				return false;
			}
			try {
				ExecuteSQLfiel.executeSqlFile(POPOLA_SCHEMA_FILE_NAME, FIRST_CONFIGURATION_PROPERTIE_FILE_NAME);
				PropertiesFile.addPropertieInFile(FIRST_CONFIGURATION_PROPERTIE_NAME, "55", FIRST_CONFIGURATION_PROPERTIE_FILE_NAME);
			} catch (Exception e) {
				System.err.println("problem when i try to populates schema");
			}		
		}
		default:
			System.err.println("database already exists");
		}
		return true;
	}
	
	public static boolean setMySqlUrl(String ip, String port) {
		String url="jdbc:mysql://"+ip+":"+port;
		try {
			PropertiesFile.addPropertieInFile(FIRST_CONFIGURATION_URL_PROPERTIE, url, FIRST_CONFIGURATION_PROPERTIE_FILE_NAME);
			return true;
		} catch (Exception e) {
			System.err.println("CANNOT SAVE");
			return false;
		}
	}
	
	public static boolean setUsername(String Username) {
		try {
			PropertiesFile.addPropertieInFile(FIRST_CONFIGURATION_USERNAME_PROPERTIE, Username, FIRST_CONFIGURATION_PROPERTIE_FILE_NAME);
			return true;
		} catch (Exception e) {
			System.err.println("CANNOT SAVE");
			return false;
		}	
	}
	
	public static boolean setPassword(String password) {
		try {
			PropertiesFile.addPropertieInFile(FIRST_CONFIGURATION_PASSWORD_PROPERTIE, password, FIRST_CONFIGURATION_PROPERTIE_FILE_NAME);
			return true;
		} catch (Exception e) {
			System.err.println("CANNOT SAVE");
			return false;
		}
	}
}
