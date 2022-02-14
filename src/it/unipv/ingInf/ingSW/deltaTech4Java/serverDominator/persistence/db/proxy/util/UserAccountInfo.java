package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util;


import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * @author TawaHabib
 *
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
		try {
			PropertiesFile.addPropertieInFile(PROP_USERNAME, us.getUsername(), fls);
			PropertiesFile.addPropertieInFile(PROP_PASSWORD, us.getPassw(), fls);
			PropertiesFile.addPropertieInFile(PROP_MNY, String.valueOf(us.getMny()!=null?us.getMny().intValue():0), fls);
			PropertiesFile.addPropertieInFile(PROP_PUNTEGGIO, String.valueOf((us.getPunteggio()!=null ? us.getPunteggio().intValue() :0)), fls);
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
			
			uss.setPassw(PropertiesFile.getPropertieFromFile(PROP_PASSWORD, fls));
			String punteggio=PropertiesFile.getPropertieFromFile(PROP_PUNTEGGIO, fls);
			uss.setPunteggio(Integer.valueOf(punteggio));
			Integer a=Integer.valueOf(PropertiesFile.getPropertieFromFile(PROP_MNY, fls));
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
