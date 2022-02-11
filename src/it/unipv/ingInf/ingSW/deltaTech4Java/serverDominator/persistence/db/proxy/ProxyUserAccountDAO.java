package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IUserAccountDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO.UserAccountDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util.AssetOwnFileSystemDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util.ObiettiviUserFileSistemDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy.util.UserAccountInfo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

public class ProxyUserAccountDAO implements IUserAccountDAO {
	private UserAccountDAO us;
	
	private static final String FILE_NAME="resources/config/persistence/dataBase/connWith_sd_sys";
	
	
	public ProxyUserAccountDAO(String fileFath) {
		us=new UserAccountDAO(fileFath);
	}
	
	public ProxyUserAccountDAO() {
		us=new UserAccountDAO(FILE_NAME);
	}
	
	
	@Override
	public boolean insetUserAccount(UserAccount us) {
		if (	
			(
				UserAccountInfo.saveUserAccountData(us)
				&&
				ObiettiviUserFileSistemDAO.saveObUserInCsvFile(us.getObiettiviUsers())
				&&
				AssetOwnFileSystemDAO.saveInCsvFile(us.getAsetOwns())
			)
				||
				this.us.insetUserAccount(us)
		) return true;
		
		return false;
	}

	@Override
	public boolean updateUserAccount(UserAccount us) {
		if (	
				(
					UserAccountInfo.saveUserAccountData(us)
					&&
					ObiettiviUserFileSistemDAO.saveObUserInCsvFile(us.getObiettiviUsers())
					&&
					AssetOwnFileSystemDAO.saveInCsvFile(us.getAsetOwns())
				)
					||
					this.us.updateUserAccount(us)
			) return true;
			
			return false;
	}

	@Override
	public boolean updateUserAccountUsername(UserAccount us, String newUsername) {
		return this.us.updateUserAccountUsername(us,newUsername);
			
	}

	@Override
	public UserAccount getUserAccountById(UserAccount us) {
		UserAccount uss=this.us.getUserAccountById(us);
		if(uss==null) {
			us=UserAccountInfo.getUserAccountData();
			us.setAsetOwns(AssetOwnFileSystemDAO.readAssetOwnFromCsvFile());
			us.setObiettiviUsers(ObiettiviUserFileSistemDAO.readObUserFromCsvFile());
			return us;
		}
		UserAccountInfo.saveUserAccountData(uss);
		ObiettiviUserFileSistemDAO.saveObUserInCsvFile(uss.getObiettiviUsers());
		AssetOwnFileSystemDAO.saveInCsvFile(uss.getAsetOwns());
		return uss;
			
			
	}

	@Override
	public boolean chengeUserAccountPassword(UserAccount us, String newPassword) {
		return this.us.chengeUserAccountPassword(us, newPassword);
	}

	@Override
	public ArrayList<AsetOwn> getAssetOwndByUserAccount(UserAccount us) {
		UserAccount uss=UserAccountInfo.getUserAccountData();
		if(this.us.getAssetOwndByUserAccount(us)==null&&us.getUsername()==uss.getUsername()&&us.getPassw()==uss.getPassw()) {
			ArrayList<AsetOwn> aWs=AssetOwnFileSystemDAO.readAssetOwnFromCsvFile();
			for(AsetOwn a: aWs) {
				a.setUserAccount(uss);
			}
			return aWs;
		}
		return this.us.getAssetOwndByUserAccount(us);
	}

	@Override
	public ArrayList<ObiettiviUser> getObiettiviUserByUserAccount(UserAccount us) {
		UserAccount uss=UserAccountInfo.getUserAccountData();
		if(this.us.getObiettiviUserByUserAccount(us)==null&&us.getUsername()==uss.getUsername()&&us.getPassw()==uss.getPassw()) {
			ArrayList<ObiettiviUser> ObUs=ObiettiviUserFileSistemDAO.readObUserFromCsvFile();
			for(ObiettiviUser obs: ObUs) {
				obs.setUserAccount(uss);
			}
			return ObUs;
		}
		return this.us.getObiettiviUserByUserAccount(us);
	}

	@SuppressWarnings("finally")
	@Override
	public boolean updateUserAccountPunteggio(UserAccount us, int newPunteggio) {
		
		try {
			PropertiesFile.addPropertieInFile(UserAccountInfo.PROP_PUNTEGGIO, String.valueOf(newPunteggio),UserAccountInfo.FILE_NAME);
		} 
		finally {
			return this.us.updateUserAccountPunteggio(us, newPunteggio);
		}
		
	}

	@SuppressWarnings("finally")
	@Override
	public boolean updateUserAccountMny(UserAccount us, int newmny) {
		try {
			PropertiesFile.addPropertieInFile(UserAccountInfo.PROP_MNY, String.valueOf(newmny),UserAccountInfo.FILE_NAME);
		} 
		finally {
			return this.us.updateUserAccountMny(us, newmny);
		}
	}

}
