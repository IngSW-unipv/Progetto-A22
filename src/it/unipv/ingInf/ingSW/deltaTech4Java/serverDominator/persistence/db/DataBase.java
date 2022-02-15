package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db;

import java.sql.Connection;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.DbConnection;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.ExecuteSQLfiel;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Classe per creare il database MySQL
 * @author TawaHabib
 *
 */
public class DataBase {
	

	private static final String CONFIGURATION_FILE_NAME ="resources/config/persistence/dataBase/connWith_root";
	
	private static final String FIRST_CONFIGURATION_PROPERTIE_NAME ="primaConfigurazione";
		
	private static final String CREATE_SCHEMA_FILE_NAME ="resources/databaseDefineSchema/dataBaseSchema.sql";
	
	private static final String POPOLA_SCHEMA_FILE_NAME ="resources/databaseDefineSchema/popolaSchema.sql";
	
	private static final String FIRST_CONFIGURATION_URL_PROPERTIE ="url";
	
	private static final String FIRST_CONFIGURATION_USERNAME_PROPERTIE ="username";
	
	private static final String FIRST_CONFIGURATION_PASSWORD_PROPERTIE ="password";
	
	/**
	 * Metodo per capire se � la prima volta che l'utente accede al gioco  oppure no;
	 * </br>
	 * @return
	 * Vero--> se � il primo acesso quindi se si deve creare il dataBase
	 * </br>Flaso--> se non � il primo acesso quindi se l'utente ha gia creato ilk database tramite l'appliativo
	 */
	@SuppressWarnings("unused")
	public static boolean isItTheFirstTime() {
		Connection conn=null;
		DbConnection.startConnection(conn, "resources/config/persistence/dataBase/connWith_sd_sys");
		Integer a ;
		try {
			a =Integer.valueOf(PropertiesFile.getPropertieFromFile(FIRST_CONFIGURATION_PROPERTIE_NAME, CONFIGURATION_FILE_NAME));
			if(a!=1)
				return false;
		}catch (Exception e) {
			if(conn!=null) 
				return false;
		}		
		return true;
	}
	
	/* 
	 * Permessi che lo user deve avere per creare il db:
	 * ALTER
	 * CREATE
	 * CREATE USER
	 * CREATE VIEW
	 * DELETE
	 * DROP
	 * GRANT OPTION
	 * INSERT
	 * REFERENCES
	 * SELECT
	 * TRIGGER
	 * UPDATE
	 */
	/**
	 * Metodo per creare il database
	 * @param ip 
	 * </br>Indirizzo ip dell'stanze in cui si deve creare il data base
	 * @param port 
	 * </br>Numero di porta 
	 * @param Username
	 * </br>Username con cui accedere per creare il db
	 * @param password
	 * </br>password associata allo username con cui accedere per creare il db
	 * @return
	 * Vero se il db � stato creato o se il db esiste gi�
	 * </br>Falso se il db non � stato reato
	 */
	public static boolean createDataBase(String ip, String port,String Username,String password) {
		if(!setConfigParameter(ip, port, Username, password)) {
			System.err.println("Impossibile accettare i dati inseriti");
			return false;
		}
		return createDataBase();
	}
	
	
	
	private static boolean setConfigParameter(String ip, String port,String Username,String password) {
		if(!setMySqlUrl(ip, port)||!setPassword(password)||!setUsername(Username))
			return false;
		return true;
	}
	
	
	private static boolean createDataBase() {
		try {
			String us=PropertiesFile.getPropertieFromFile(FIRST_CONFIGURATION_USERNAME_PROPERTIE, CONFIGURATION_FILE_NAME);
			if(us.length()<2) {
				System.err.println("username sembra troppo corto");
			}
		} catch (Exception e) {
			System.err.println("problem with configuration file");
		}
		try {
			String url=PropertiesFile.getPropertieFromFile(FIRST_CONFIGURATION_URL_PROPERTIE, CONFIGURATION_FILE_NAME);
			if(url.length()<16) {
				System.err.println("Url sembra scoretta");
			}
		} catch (Exception e) {
			System.err.println("problem with configuration file");
		}
		try {
			String pass=PropertiesFile.getPropertieFromFile(FIRST_CONFIGURATION_PASSWORD_PROPERTIE, CONFIGURATION_FILE_NAME);
			if(pass.length()<1) {
				System.err.println("password non impostata");
			}
		} catch (Exception e) {
			System.err.println("problem with configuration file");
		}
	
		if (isItTheFirstTime())
		{
			try {
				ExecuteSQLfiel.executeSqlFile(CREATE_SCHEMA_FILE_NAME, CONFIGURATION_FILE_NAME);
			} catch (Exception e) {
				System.err.println("Problemi quando tento di creare lo schema.CONTROLLA CORRETTAZZA DI USERNAME E PASSWORD"
						+ "\nAssicurati che lo user che utilizzi abbia i seguenti privilegi:"
						+ "\n\tALTER\n\tCREATE\n\tCREATE USER\n\tCREATE VIEW\n\tDELETE\n \tDROP\n\tGRANT OPTION\n\tINSERT\n\tREFERENCES\n\tSELECT\n\tTRIGGER\n\tUPDATE");
				return false;
			}
			try {
				ExecuteSQLfiel.executeSqlFile(POPOLA_SCHEMA_FILE_NAME, CONFIGURATION_FILE_NAME);
				PropertiesFile.addPropertieInFile(FIRST_CONFIGURATION_PROPERTIE_NAME, "55", CONFIGURATION_FILE_NAME);
			} catch (Exception e) {
				System.err.println("Problemi Quando si cerca di popolare lo schema");
			}		
		}
		return true;
	}
	
	private static boolean setMySqlUrl(String ip, String port) {
		String url="jdbc:mysql://"+ip+":"+port;
		try {
			PropertiesFile.addPropertieInFile(FIRST_CONFIGURATION_URL_PROPERTIE, url, CONFIGURATION_FILE_NAME);
			return true;
		} catch (Exception e) {
			System.err.println("Non posso salvare URL");
			return false;
		}
	}
	
	private static boolean setUsername(String Username) {
		try {
			PropertiesFile.addPropertieInFile(FIRST_CONFIGURATION_USERNAME_PROPERTIE, Username, CONFIGURATION_FILE_NAME);
			return true;
		} catch (Exception e) {
			System.err.println("Non posso salvare Username");
			return false;
		}	
	}
	
	private static boolean setPassword(String password) {
		try {
			PropertiesFile.addPropertieInFile(FIRST_CONFIGURATION_PASSWORD_PROPERTIE, password, CONFIGURATION_FILE_NAME);
			return true;
		} catch (Exception e) {
			System.err.println("Non posso salvare Password");
			return false;
		}
	}
}
