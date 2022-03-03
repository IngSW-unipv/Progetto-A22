package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Salvataggio informazioni dello user account nel file system
 * @author TawaHabib
 */
public class UserAccountInfo {
	public static final String PROP_USERNAME="UserAccount.username";
	public static final String PROP_PASSWORD="UserAccount.passw";
	public static final String PROP_MNY="UserAccount.mon";
	public static final String PROP_PUNTEGGIO="UserAccount.punteggio";
	public static final String FILE_NAME = "resources/config/persistence/UserAccount";
	
	/**
	 * salva Obiettivi user nel file predefinito
	 * @param us
	 * userAccount to save
	 * @return Boolean
	 */
	public static boolean saveUserAccountData(UserAccount us) {
		return saveUserAccountData(us,FILE_NAME);
	}
	/**
	 * prendi userAccount data da�l file predefinito
	 * @return
	 * userAccount Data
	 */
	public static UserAccount getUserAccountData() {
		return getUserAccountData(FILE_NAME);
	}
	
	/**
	 * save userAccount data into a file
	 * @param us
	 * userAccount to save
	 * @param fls
	 * file path
	 * @return
	 */
	public static boolean saveUserAccountData(UserAccount us, String fls) {
		UserAccount ussAccount=getUserAccountData(fls);
		/*
		 * si deve eliminare tutti i file riguardanti lo user account quando
		 * si salvano le informazioni di un'altro user accout
		 */
		if(!us.getUsername().equals(ussAccount.getUsername())) {
			File assetFile=new File(AssetOwnFileSystemDAO.FILE_NAME);
			File obiettiviFile=new File(ObiettiviUserFileSistemDAO.FILE_NAME);
			FileWriter fileWriter=null ;
			/*
			 *elimino il file degli asset 
			 */
			if(assetFile.exists()) {
				try {
					fileWriter =new FileWriter(AssetOwnFileSystemDAO.FILE_NAME);
					fileWriter.append("");
					
				} catch (Exception e) {
					System.err.println("Error while opening/writeing "+AssetOwnFileSystemDAO.FILE_NAME);
					e.printStackTrace();
				}finally {
					try {
						
						fileWriter.flush();
						fileWriter.close();
						
					} catch (IOException e) {
						System.err.println("Error while flushing/closing fileWriter !!!");
						e.printStackTrace();
					}     
				}
				
			}
			/*
			 *elimino i file degli obiettivi 
			 */
			if(obiettiviFile.exists()) {
					try {
						fileWriter =new FileWriter(ObiettiviUserFileSistemDAO.FILE_NAME);
						fileWriter.append("");
						
					} catch (Exception e) {
						System.err.println("Error while opening/writeing "+ObiettiviUserFileSistemDAO.FILE_NAME);
						e.printStackTrace();
					}finally {
						try {
							
							fileWriter.flush();
							fileWriter.close();
							
						} catch (IOException e) {
							System.err.println("Error while flushing/closing fileWriter "+ObiettiviUserFileSistemDAO.FILE_NAME);
							e.printStackTrace();
						}     
					}
			}
		}
				
		try {
			PropertiesFile.addPropertieInFile(PROP_USERNAME, us.getUsername(), fls);
			PropertiesFile.addPropertieInFile(PROP_PASSWORD, us.getPassw(), fls);
			PropertiesFile.addPropertieInFile(PROP_MNY, String.valueOf(us.getMny()!=null?us.getMny().intValue():0), fls);
			PropertiesFile.addPropertieInFile(PROP_PUNTEGGIO, String.valueOf((us.getPunteggio()!=null ? us.getPunteggio().intValue() :0)), fls);
			AssetOwnFileSystemDAO.saveInCsvFile(us.getAsetOwns());
			ObiettiviUserFileSistemDAO.saveObUserInCsvFile(us.getObiettiviUsers());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * prendi UserData da un property file
	 * @param fls
	 * file dal duale recupero i data
	 * @return user account data
	 */
	public static UserAccount getUserAccountData(String fls) {
		try {
			UserAccount uss=new UserAccount(PropertiesFile.getPropertieFromFile(PROP_USERNAME, fls));
			uss.setPassw(PropertiesFile.getPropertieFromFile(PROP_PASSWORD, fls));
			String punteggio=PropertiesFile.getPropertieFromFile(PROP_PUNTEGGIO, fls);
			uss.setPunteggio(punteggio==null?0:Integer.valueOf(punteggio));
			int a=PropertiesFile.getPropertieFromFile(PROP_MNY, fls)==null?0:Integer.valueOf(PropertiesFile.getPropertieFromFile(PROP_MNY, fls));
			uss.setMny(a);
			
			return uss;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		UserAccount us=getUserAccountData();
		System.out.println(us.toString());
		System.out.println("%d"+ Integer.valueOf("5412"));
	}
	
}
