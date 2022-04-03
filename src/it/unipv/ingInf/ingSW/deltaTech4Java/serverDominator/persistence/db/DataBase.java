package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.CryptoUtil;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.DbConnection;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.ExecuteSQLfiel;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.FileToString;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Classe con utilità per il database
 * @author TawaHabib
 * @version 1.0
 */
public class DataBase {
	/**
	 * File di configuarazione in cui vengono salvati i dati per connettersi al db e crearlo
	 */
	private static final String CONFIGURATION_FILE_NAME ="resources/config/persistence/dataBase/connWith_root";
	
	/**
	 * Proprità nel file di configurazione per indicare e è la prima volta.
	 */
	public static final String FIRST_CONFIGURATION_PROPERTIE_NAME ="primaConfigurazione";
		
	/**
	 * File dello script per creare lo schema
	 */
	private static final String CREATE_SCHEMA_FILE_NAME ="resources/databaseDefineSchema/dataBaseSchema.sql";
	
	/**
	 * File dello script per popolare  lo schema
	 */
	private static final String POPOLA_SCHEMA_FILE_NAME ="resources/databaseDefineSchema/popolaSchema.sql";
	
	/**
	 * Chiave della proprità nel file di configurazione che ha la URL.
	 */
	private static final String FIRST_CONFIGURATION_URL_PROPERTIE ="url";
	
	/**
	 * Chiave della proprità nel file di configurazione che ha la username con il quale connettersi.
	 */
	private static final String FIRST_CONFIGURATION_USERNAME_PROPERTIE ="username";
	
	/**
	 * Chiave della proprità della password nel file di configurazione che ha la username con il quale connettersi
	 */
	private static final String FIRST_CONFIGURATION_PASSWORD_PROPERTIE ="password";
	
	/**
	 * Metodo per capire se è la prima volta che l'utente accede al gioco  oppure no;
	 * </br>
	 * @return
	 * Vero se è il primo acesso quindi se si deve creare il dataBase
	 * </br>Flaso se non è il primo acesso quindi se l'utente ha gia creato ilk database tramite l'appliativo
	 */
	public static boolean isItTheFirstTime() {
		Connection conn=null;
		conn=DbConnection.startConnection(conn, "resources/config/persistence/dataBase/connWith_sd_sys");
		if(conn!=null) {
			return false;
		}
		Integer a ;
		try {
			Properties p=PropertiesFile.loadPropertiesFromCriptedFile(CONFIGURATION_FILE_NAME);
			a =Integer.valueOf(p.getProperty(FIRST_CONFIGURATION_PROPERTIE_NAME));
			if(a==1)
				return true;
		}catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}		
		return false;
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
	 * Metodo per creare il database in um MySql server
	 * @param ip 
	 * </br>Indirizzo ip dell'stanze in cui si deve creare il data base
	 * @param port 
	 * </br>Numero di porta 
	 * @param Username
	 * </br>Username con cui accedere per creare il db
	 * @param password
	 * </br>password associata allo username con cui accedere per creare il db
	 * @return
	 * Vero se il db è stato creato o se il db esiste già
	 * </br>Falso se il db non è stato reato
	 */
	public static boolean createDataBase(String ip, String port,String Username,String password) {
		if(!setConfigParameter(ip, port, Username, password)) {
			System.err.println("Impossibile slvare i dati inseriti");
			return false;
		}
		return createDataBase();
	}
	/**
	 * Metodo per creare il data base secondo i parametri passati
	 * @param url 
	 * </br>&emsp;&emsp;Url del db; <b>NON</b> deve avere lo schema;
	 * </br><b> Esempio di MySQL:</b> 
	 * </br>&emsp;&emsp;jdbc:mysql://localhost:3306
	 * </br><b> Esempio di oracle:</b> 
	 * </br>&emsp;&emsp;jdbc:oracle:thin:@localhost:1521 
	 * @param Username
	 * @param password
	 * @return boolean
	 */
	public static boolean createDataBase(String url,String Username,String password) {
		if(!setConfigParameter(url, Username, password)) {
			System.err.println("DataBase.class:Impossibile slvare i dati inseriti");
			return false;
		}
		return createDataBase();
	}
	/**
	 * Recupera la posizione della colonna di interesse locata nello schema passato e nella tabella passata
	 * @param schema
	 * @param table
	 * @param colomn
	 * @param connectionFilePath
	 * @return column position
	 */
	public static int getColumnPosition(String schema, String table,String colomn, Connection conn) {
		int result=0;
		PreparedStatement st1;
		ResultSet rs1=null;
		try
		{
			
			String query="SELECT Ordinal_Position FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ? AND COLUMN_NAME=?";
			st1 = conn.prepareStatement(query);
			st1.setString(1, schema);
			st1.setString(2, table);
			st1.setString(3, colomn);
			rs1=st1.executeQuery();
			rs1.next();
			int a=rs1.getInt(1);
			result=a>0?a:0;
			
		}catch (Exception e){
			e.printStackTrace();
			}
		return result;
	}
	
	/**
	 * Metodo che setta i paramenti per la connessione al db Mysql 
	 * @param ip
	 * @param port
	 * @param Username
	 * @param password
	 * @return boolean
	 */
	public static boolean setConfigParameter(String ip, String port,String Username,String password) {
		if(!setUrl(ip, port)||!setPassword(password)||!setUsername(Username))
			return false;
		return true;
	}
	/**
	 * Metodo che setta i parametri per la connessione secondo la url passata</br>
	 * NB: la url non deve contenere uno schema e deve essere compatibile con </br>
	 * il db utilizzato; di lascia la responsabilità a chu usa questo metodo </br>
	 * di fare i controlli per la correttezza della url 
	 * @param url
	 * @param Username
	 * @param password
	 * @return boolean
	 */
	public static boolean setConfigParameter(String url,String Username,String password) {
		if(!setUrl(url)||!setPassword(password)||!setUsername(Username))
			return false;
		return true;
	}
	
	/**
	 * Metodo per creare un database secondo le proprietà presenti nel file 
	 * {@link CONFIGURATION_FILE_NAME}
	 * @return boolean
	 */
	public static boolean createDataBase() {
		boolean result=true;
		Properties p=null;
		try {
			p=PropertiesFile.loadPropertiesFromCriptedFile(CONFIGURATION_FILE_NAME);
		} catch (Exception e) {
			System.err.println("Problems With properties File Impossibile Creare il DB");
			return false;
		}
		try {
			String us=p.getProperty(FIRST_CONFIGURATION_USERNAME_PROPERTIE);
			if(us.length()<2) {
				System.err.println("username sembra troppo corto");
			}
		} catch (Exception e) {
			System.err.println("problem with configuration file");
		}
		try {
			String url=p.getProperty(FIRST_CONFIGURATION_URL_PROPERTIE);
			if(url.length()<16) {
				System.err.println("Url sembra scoretta");
			}
		} catch (Exception e) {
			System.err.println("DataBase.class: problem with configuration file");
		}
		try {
			String pass=p.getProperty(FIRST_CONFIGURATION_PASSWORD_PROPERTIE);
			if(pass.length()<1) {
				System.err.println("DataBase.class: password non impostata");
			}
		} catch (Exception e) {
			System.err.println("DataBase.class: problem with configuration file");
		}
	
		if (isItTheFirstTime())
		{
			try {
				ExecuteSQLfiel.executeSqlFile(CREATE_SCHEMA_FILE_NAME, CONFIGURATION_FILE_NAME);
			} catch (Exception e) {
				System.err.println("DataBase.class: Problemi quando tento di creare lo schema.CONTROLLA CORRETTAZZA DI USERNAME E PASSWORD"
						+ "\nAssicurati che lo user che utilizzi abbia i seguenti privilegi:"
						+ "\n\tALTER\n\tCREATE\n\tCREATE USER\n\tCREATE VIEW\n\tDELETE\n \tDROP\n\tGRANT OPTION\n\tINSERT\n\tREFERENCES\n\tSELECT\n\tTRIGGER\n\tUPDATE");
				e.getMessage();
				e.printStackTrace();
				return false;
			}
			try {
				ExecuteSQLfiel.executeSqlFile(POPOLA_SCHEMA_FILE_NAME, CONFIGURATION_FILE_NAME);
				Properties p1=new Properties();
				p1.put(FIRST_CONFIGURATION_PROPERTIE_NAME, "55");
				PropertiesFile.savePropertyInCriptedFile(p1, CONFIGURATION_FILE_NAME);
			} catch (Exception e) {
				System.err.println("DataBase.class: Problemi Quando si cerca di popolare lo schema");
				e.getMessage();
				e.printStackTrace();
				result =false;
			}		
		}
		return result;
	}
	
	private static boolean setUrl(String ip, String port) {
		String url="jdbc:mysql://"+ip+":"+port;
		boolean ris=true;
		Properties p=new Properties();
		p.put(FIRST_CONFIGURATION_URL_PROPERTIE,url);
		try {
			PropertiesFile.savePropertyInCriptedFile(p, CONFIGURATION_FILE_NAME);
			
		} catch (Exception e) {
			System.err.println("DataBase.class: Non posso salvare URL");
			ris= false;
		}
		return ris;
	}
	
	private static boolean setUrl(String url) {
		boolean ris=true;
		Properties p=new Properties();
		p.put(FIRST_CONFIGURATION_URL_PROPERTIE,url.toString());
		try {
			PropertiesFile.savePropertyInCriptedFile(p, CONFIGURATION_FILE_NAME);
			
		} catch (Exception e) {
			System.err.println("DataBase.class: Non posso salvare URL");
			ris= false;
		}
		return ris;
	}
	
	private static boolean setUsername(String Username) {
		Properties p=new Properties();
		p.put(FIRST_CONFIGURATION_USERNAME_PROPERTIE,Username);
		try {
			PropertiesFile.savePropertyInCriptedFile(p, CONFIGURATION_FILE_NAME);
			return true;
		} catch (Exception e) {
			System.err.println("DataBase.class: Non posso salvare Username");
			return false;
		}	
	}
	
	private static boolean setPassword(String password) {
		Properties p=new Properties();
		p.put(FIRST_CONFIGURATION_PASSWORD_PROPERTIE,password);
		try {
			PropertiesFile.savePropertyInCriptedFile(p, CONFIGURATION_FILE_NAME);
			return true;
		} catch (Exception e) {
			System.err.println("DataBase.class: Non posso salvare Password");
			return false;
		}
	}
	///*
	public static void main(String[] args) {
		try {
			System.out.println(CryptoUtil.decrypt(FileToString.transformFileToString(CONFIGURATION_FILE_NAME)));
		} catch (Exception e) {
			// TODO: handle exception
		};
	}
	//*/
}
