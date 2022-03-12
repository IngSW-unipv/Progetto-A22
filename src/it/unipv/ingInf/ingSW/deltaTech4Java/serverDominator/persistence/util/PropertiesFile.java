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
	 * Da le prorieta' salvate in un file (fatto da coppie chiare value)
	 * @param fls
	 * Percorco file delle Prop
	 * @return Properties
	 * Proprita' scritte nel file
	 */
	public static Properties loadPropertiesFromFile(String fls)throws IOException{
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
	public static boolean addPropertieInFile(String keyString, String valueString, String perFile) throws IOException{
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
	public static boolean  savePropertyInFile(Properties propertie, String perco)throws IOException{	
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
	public static String getPropertieFromFile(String KeyProp, String percFile) throws IOException{
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
	public static void savePropertiesInXMLFile(String filePath,Properties prop, String comment) throws IOException  {
		Properties p=new Properties();
		OutputStream out = new FileOutputStream(filePath);
		p.storeToXML(out,comment);
	}
	/* prova
	public static void main(String[] args) {
		try {
			Properties prop= new Properties();
			FileInputStream in = new FileInputStream("resorces/config.txt");
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
	}*/
}
