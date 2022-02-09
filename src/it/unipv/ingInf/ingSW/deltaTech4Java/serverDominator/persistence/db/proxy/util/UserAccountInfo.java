package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class UserAccountInfo {
	private static final String PROP_USERNAME="UserAccount.username";
	private static final String PROP_PASSWORD="UserAccount.passw";
	private static final String PROP_MNY="UserAccount.mny";
	private static final String PROP_PUNTEGGIO="UserAccount.punteggio";
	private static final String FILE_NAME = "resources/config/persistence/UserAccount";
	
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
		try {
			PropertiesFile.addPropertieInFile(PROP_USERNAME, us.getUsername(), fls);
			PropertiesFile.addPropertieInFile(PROP_PASSWORD, us.getPassw(), fls);
			PropertiesFile.addPropertieInFile(PROP_MNY, String.valueOf(us.getMny()), fls);
			PropertiesFile.addPropertieInFile(PROP_PUNTEGGIO, String.valueOf(us.getPunteggio()), fls);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * prendi UserData da un file
	 * @param fls
	 * file dal duale recupero i data
	 * @return user account data
	 */
	public static UserAccount getUserAccountData(String fls) {
		try {
			UserAccount uss=new UserAccount(PropertiesFile.getPropertieFromFile(PROP_USERNAME, fls));
			uss.setMny(Integer.getInteger(PropertiesFile.getPropertieFromFile(PROP_MNY, fls)));
			uss.setPassw(PropertiesFile.getPropertieFromFile(PROP_PASSWORD, fls));
			uss.setPunteggio(Integer.getInteger(PropertiesFile.getPropertieFromFile(PROP_PUNTEGGIO, fls)));
			return uss;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
