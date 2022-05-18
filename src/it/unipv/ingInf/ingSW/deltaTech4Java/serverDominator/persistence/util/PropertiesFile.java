package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

/**
 * Acesso a un prop file  
 * @author TawaHabib
 * @version 1.0
 * @see Properties
 * @see FileReader
 * @see FileWriter
 */
public class PropertiesFile {
	
	/**
	 * Da le prorieta' salvate in un file (fatto da coppie chiave value) salvate in un file 
	 * </br>Cifrato secondo la cifratura di defaoult offerta ad {@link CryptoUtil}
	 * @param fls
	 * @return
	 * @throws Exception
	 */
	public static Properties loadPropertiesFromCriptedFile(String fls)throws Exception{
		String s="";
		s=CryptoUtil.decrypt(FileToString.transformFileToString(fls));
	    Properties p = new Properties();
	    p.load(new StringReader(s));
	    return p;
	}
	
	/**
	 * Salva le proprietà in un file cifrato secondo 
	 * </br>la cifratura di default messa a disposizione da {@link CryptoUtil}
	 * Scenari possibibli (che vengono gestiti):
	 * </BR>1: file esiste vuoto --> carico le prop passate
	 * </BR>2: file esiste e dentro ha delle prop(crittate) --> carico lo prop passate e se ci sono conflitti valgono le nuove prop
	 * </BR>3: file non esiste --> creao il file com percorso passato e carico le prop 
	 * </br> E' responsabilità di chi usa questo metodo assicurarsi che se il file esiste deve essere
	 * </br>cifrato secondo la cifratura di defaoult messa a disposizione 
	 * dalla classe {@link CryptoUtil} 
	 * @param propertie
	 * le proprietà
	 * @param perco
	 * persorco del file
	 * @return
	 * @throws Exception 
	 */
	public static boolean  savePropertyInCriptedFile(Properties propertie, String perco)throws Exception{	
		boolean ris=true;
		Properties p=null;
		File f=new File(perco);
		if(f.exists()){
			p=loadPropertiesFromCriptedFile(perco);
		}
		p.putAll(propertie);
		StringWriter writer = new StringWriter();
		p.store(writer,"");
		String propInChiaro=writer.getBuffer().toString(); 
		String propCifrate=CryptoUtil.encrypt(propInChiaro);
		PrintWriter filewriter = new PrintWriter(perco);
		filewriter.print(propCifrate);
		filewriter.close();
		return ris;
	}
	
	/**
	 * Da le prorieta' salvate in un file (fatto da coppie chiare value)
	 * @param fls
	 * Percorco file delle Prop
	 * @return Properties
	 * Proprita' scritte nel file
	 */
	public static Properties loadPropertiesFromFile(String fls)throws Exception{
		FileReader in = new FileReader(fls);
		Properties prop=new Properties();
		prop.load(in);
		in.close();
		return prop;
	}
	
	/**
	 * @param keyString
	 * chiave della proprità (può esistere o non): se esista si cambia il valore a cui è 
	 * associata, se non esiste la si crea
	 * @param valueString
	 * valore associato alla chiave.
	 * @param perFile
	 * percorso del file. se esiste lo si apre e lo si aggiorna, se non esiste lo si crea;
	 * @return
	 */
	public static boolean addPropertieInFile(String keyString, String valueString, String perFile) throws Exception{
		Properties p=new Properties();
		p.setProperty(keyString,valueString);
		return savePropertyInFile(p, perFile);		
	}

	/**
	 * Salva le proprità passate in un file. 
	 * Scenari possibibli (che vengono gestiti):
	 * </BR>1: file esiste vuoto	--> carico le prop passate
	 * </BR>2: file esiste e dentro ha delle prop --> carico lo prop passate e se ci sono conflitti valgono le nuove prop
	 * </BR>3: file non esiste--> creao il file com percorso passato e carico le prop 
	 * @param propertie
	 * le proprietà
	 * @param perco
	 * persorco del file
	 * @return
	 */
	public static boolean  savePropertyInFile(Properties propertie, String perco)throws Exception{	
		boolean ris=true;
		FileWriter  ou=null;
		try {
			Properties p1=loadPropertiesFromFile(perco);
			p1.putAll(propertie);
			ou= new FileWriter(perco);
			p1.store(ou,null);
			ou.close();
		} catch (Exception e) {
			System.err.println("error when i try save propertie into the File: "+perco);
		    System.err.println("traying to create a new file: "+perco);
		    File nFile = new File(perco);
		    if(!nFile.exists())	{
		    	nFile.createNewFile();
		    }
		    ou= new FileWriter(perco);
		    propertie.store(ou,null);	
		    ou.close();
		    System.err.println("tt'apposto "+perco);
		}
		return ris;
	}
	
	/**
	 * fetch a propertie located into a file
	 * @param KeyProp
	 * chiave della propertie
	 * @param percFile
	 * property file pat 
	 * @return valore propertie
	 * @throws IOException
	 */
	public static String getPropertieFromFile(String KeyProp, String percFile) throws Exception{
		Properties p=loadPropertiesFromFile(percFile);
		return p.getProperty(KeyProp);
	}
	
	/**
	 * Prende le proprità contenute in un xml file
	 * @param filePath
	 * @return {@link Properties} salvate  nel file il cui percorso relativo </br> quello passato nell'argometo
	 * @throws Exception
	 * @throws FileNotFoundException
	 */
	public static Properties  getPropertiesFromXMLFile(String filePath) throws Exception, FileNotFoundException{
		Properties p=new Properties();
		InputStream in = new FileInputStream(filePath);
		p.loadFromXML(in);
		return p;
	}
	
	/**
	 * @param filePath
	 * @param prop
	 * @param comment
	 * @throws IOException
	 */
	public static void savePropertiesInXMLFile(String filePath,Properties prop, String comment) throws Exception  {
		Properties p=new Properties();
		OutputStream out = new FileOutputStream(filePath);
		p.storeToXML(out,comment);
	}
	/* prova
	public static void main(String[] args) {
		Properties prop=null;
		try {
			prop= new Properties();
			//file non cifrato
			FileInputStream in = new FileInputStream("resources/config/persistence/dataBase/connWith_root");
			prop.load(in);
			if(prop!=null) {
				System.out.println("file apposto");
				System.out.println(
						"driver " + prop.getProperty("driver")+ "\n "
						+ "url "+ prop.getProperty("url")+ "\n "
						+ "username "+ prop.getProperty("username")+ "\n "
						+ "password "+ prop.getProperty("password")+ "\n ");
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringWriter writer = new StringWriter();
		try {
			prop.store(writer,"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s=null;
		try {
				//fie non cifrato
				s=FileToString.transformFileToString("resources/config/persistence/dataBase/connWith_root");
				
				System.out.println("Contenuto in chiaro del file resources/config/persistence/dataBase/connWith_root\n"+s);
				s=CryptoUtil.encrypt(s);
				System.out.println("Contenuto in cifrato del file resources/config/persistence/dataBase/connWith_root\n"+s);
				System.out.println(s);
				//file cifrato
				PrintWriter filewriter = new PrintWriter("resources/config/persistence/dataBase/connWith_root");
				filewriter.print(s);
				filewriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(writer.getBuffer().toString()); 
		Properties p=new Properties();
		p.setProperty("prop1", "ProvaProp1");
		p.setProperty("prop2", "ProvaProp2");
		p.setProperty("prop3", "ProvaProp3");
		p.setProperty("prop4", "ProvaProp4");
		p.setProperty("prop5", "ProvaProp5");
		p.setProperty("prop6", "ProvaProp6");
		
		String prova="";
		try {
			//file cifrato
			PropertiesFile.savePropertyInCriptedFile(p, "resources/config/persistence/dataBase/connWith_root");
			Properties properties= PropertiesFile.loadPropertiesFromCriptedFile("resources/config/persistence/dataBase/connWith_root");
			StringWriter write = new StringWriter();
			properties.store(write, "");
			prova=write.getBuffer().toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("\ncontenuto del file cifrato dopo aver aggiunto le proprit� di p\n"+prova);
		
		
	}
	//*/
}
