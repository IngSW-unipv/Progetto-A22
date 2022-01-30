package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util;

import java.io.FileInputStream;
import java.util.Properties;
/**
 * Acesso a un prop file  
 * @author ME
 * @version 1.0
 * @see Properties
 * @see FileInputStream
 */
public class FetchPropertyFromFile {
	/**
	 * Da le prorieta' salvate in un file (fatto da coppie chiare value)
	 * @param fls
	 * Percorco file delle Prop
	 * @return Properties
	 * Proprita' scritte nel file
	 */
	public static Properties loadPropertiesFile(String fls){
		try {
			Properties prop= new Properties();
			FileInputStream in = new FileInputStream(fls);
			prop.load(in);
			in.close();
			return prop;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
