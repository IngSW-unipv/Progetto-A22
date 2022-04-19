package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util;


import java.io.File;
import java.util.Properties;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.CryptoUtil;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * Salvataggio informazioni dello user account nel file system
 * @author TawaHabib
 * @version 1.0
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
	 * prendi userAccount data daòl file predefinito
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
		File file=new File(fls);
		UserAccount ussAccount=us;
		if(file.exists())
			ussAccount=getUserAccountData(fls);
		/*
		 * si deve eliminare tutti i file riguardanti lo user account quando
		 * si salvano le informazioni di un'altro user accout
		 */
		if(!us.getUsername().equals(ussAccount.getUsername())) {
			File assetFile=new File(AssetOwnFileSystemDAO.FILE_NAME);
			File obiettiviFile=new File(ObiettiviUserFileSistemDAO.FILE_NAME);
			File usFile=new File(UserAccountInfo.FILE_NAME) ;
			/*
			 *elimino il file degli asset 
			 */
			assetFile.delete();
			/*
			 *elimino i file degli obiettivi 
			 */
			obiettiviFile.delete();
			
			usFile.delete();
		}
		
		try {
			Properties propAccount=new Properties();
			propAccount.put(PROP_USERNAME, us.getUsername());
			propAccount.put(PROP_PASSWORD, CryptoUtil.encrypt(us.getPassw()));
			propAccount.put(PROP_MNY, String.valueOf(us.getMny()!=null?us.getMny().intValue():0));
			propAccount.put(PROP_PUNTEGGIO, String.valueOf((us.getPunteggio()!=null ? us.getPunteggio().intValue() :0)));
			PropertiesFile.savePropertyInFile(propAccount, fls);
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
			String pssw=PropertiesFile.getPropertieFromFile(PROP_PASSWORD, fls);
			if(pssw!=null&&!pssw.isEmpty()&&pssw.getBytes().length %4==0)
				uss.setPassw(CryptoUtil.decrypt(pssw));
			else
				uss.setPassw(pssw);
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
		//System.out.println(getUserAccountData().getPassw());
		saveUserAccountData(new UserAccount("Twaasda","12sfbshndgjmdjhsgjhaj34"));
		try {
			System.out.println(PropertiesFile.getPropertieFromFile(PROP_PASSWORD, FILE_NAME).getBytes().length%8+ " "+3%2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//UserAccount us=getUserAccountData();
		//System.out.println(us.toString());
		//System.out.println("%d"+ Integer.valueOf("5412"));
	}
	
}
