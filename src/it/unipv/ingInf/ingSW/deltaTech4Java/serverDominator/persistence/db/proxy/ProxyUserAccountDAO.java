package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IUserAccountDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AssetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO.UserAccountDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util.AssetOwnFileSystemDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util.ObiettiviUserFileSistemDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util.UserAccountInfo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * @author TawaHabib
 *
 */
public class ProxyUserAccountDAO implements IUserAccountDAO {
	private UserAccountDAO us;
	
	private static final String FILE_NAME="resources/config/persistence/dataBase/connWith_sd_sys";
	
	
	/**
	 * Crea proxy con connection file path quello passato come argomento
	 * @param fileFath
	 * Connection file path
	 */
	public ProxyUserAccountDAO(String fileFath) {
		us=new UserAccountDAO(fileFath);
	}
	
	/**
	 * Crea proxy con connection file path di default
	 */
	public ProxyUserAccountDAO() {
		us=new UserAccountDAO(FILE_NAME);
	}
	
	
	@Override
	public boolean insertUserAccount(UserAccount us) {
		if (this.us.insertUserAccount(us)) {
			UserAccountInfo.saveUserAccountData(us);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateUserAccount(UserAccount us) {
		return this.us.updateUserAccount(us);
	}

	@Override
	public boolean updateUserAccountUsername(UserAccount us, String newUsername) {
		boolean ris=true;
		if( !this.us.updateUserAccountUsername(us,newUsername)) {
			ris=false;
		}
		else {
			UserAccount usa=UserAccountInfo.getUserAccountData();
			usa.setUsername(newUsername);
			UserAccountInfo.saveUserAccountData(usa);
		}
		return ris;
	}

	@Override
	public UserAccount getUserAccountById(UserAccount us) {
		UserAccount uss=this.us.getUserAccountById(us);
		
		if(uss==null&&us.getUsername().equals(UserAccountInfo.getUserAccountData().getUsername())&&us.getPassw().equals(UserAccountInfo.getUserAccountData().getPassw())) {
			uss=UserAccountInfo.getUserAccountData();
			uss.setAsetOwns(AssetOwnFileSystemDAO.readAssetOwnFromCsvFile());
			uss.setObiettiviUsers(ObiettiviUserFileSistemDAO.readObUserFromCsvFile());
			return uss;
		}else {
			if(uss!=null)
			UserAccountInfo.saveUserAccountData(uss);
		}
		return uss;			
	}

	@Override
	public boolean chengeUserAccountPassword(UserAccount us, String newPassword) {
		boolean ris=true;
		if(!this.us.chengeUserAccountPassword(us, newPassword)) {
			ris=false;
		}
		else {
			UserAccount usa=new UserAccount();
			usa=UserAccountInfo.getUserAccountData();
			usa.setPassw(newPassword);
			UserAccountInfo.saveUserAccountData(usa);
		}
		return ris;
	}

	@Override
	public ArrayList<AssetOwn> getAssetOwndByUserAccount(UserAccount us) {
		UserAccount uss=UserAccountInfo.getUserAccountData();
		if(this.us.getAssetOwndByUserAccount(us)==null&&us.getUsername()==uss.getUsername()&&us.getPassw()==uss.getPassw()) {
			ArrayList<AssetOwn> aWs=AssetOwnFileSystemDAO.readAssetOwnFromCsvFile();
			for(AssetOwn a: aWs) {
				a.setUserAccount(uss);
			}
			return aWs;
		}
		return this.us.getAssetOwndByUserAccount(us);
	}

	@Override
	public ArrayList<ObiettiviUser> getObiettiviUserByUserAccount(UserAccount us) {
		UserAccount uss=UserAccountInfo.getUserAccountData();
		ArrayList<ObiettiviUser> ObUs;
		ObUs=this.us.getObiettiviUserByUserAccount(us);
		if(ObUs==null&&us.getUsername()==uss.getUsername()&&us.getPassw()==uss.getPassw()) {
			ObUs=ObiettiviUserFileSistemDAO.readObUserFromCsvFile();
			for(ObiettiviUser obs: ObUs) {
				obs.setUserAccount(uss);
			}
		}
		else {
			ObiettiviUserFileSistemDAO.saveObUserInCsvFile(ObUs);
		}
		return ObUs;
	}

	@Override
	public boolean updateUserAccountPunteggio(UserAccount us, int newPunteggio) {
		boolean ris=true;
		try {
			ris=PropertiesFile.addPropertieInFile(UserAccountInfo.PROP_PUNTEGGIO, String.valueOf(newPunteggio),UserAccountInfo.FILE_NAME);
		} catch (Exception e) {
			ris=false;
		}
		return (this.us.updateUserAccountPunteggio(us, newPunteggio)||ris);
		
	}

	@Override
	public boolean updateUserAccountMny(UserAccount us, int newmny) {
		boolean ris=true;
		if(!this.us.updateUserAccountMny(us, newmny)){
			try {
				ris=PropertiesFile.addPropertieInFile(UserAccountInfo.PROP_MNY, String.valueOf(newmny),UserAccountInfo.FILE_NAME);
			} 
			catch (Exception e) {
				ris=false;
				System.err.println("ProxyUserAccountDAO.updateUserAccountMny: impossibile aggiornare filesystem");
			}
		}
		try {
			PropertiesFile.addPropertieInFile(UserAccountInfo.PROP_MNY, String.valueOf(newmny),UserAccountInfo.FILE_NAME);
		} 
		catch (Exception e) {
			System.err.println("ProxyUserAccountDAO.updateUserAccountMny: impossibile aggiornare filesystem");
		}
		return ris;
	}


}
