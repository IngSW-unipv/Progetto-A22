package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.proxy;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.IUserAccountDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO.UserAccountDAO;

public class ProxyUserAccountDAO implements IUserAccountDAO {
	private UserAccountDAO us;
	
	public ProxyUserAccountDAO(String fileFath) {
		us=new UserAccountDAO(fileFath);
	}
	
	
	
	@Override
	public boolean insetUserAccount(UserAccount us) {
		if(!this.us.insetUserAccount(us)) {
			return false;
		}
		else{
			return true;
		}
	}

	@Override
	public boolean updateUserAccount(UserAccount us) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserAccountUsername(UserAccount us, String newUsername) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserAccount getUserAccountById(UserAccount us) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean chengeUserAccountPassword(UserAccount us, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<AsetOwn> getAssetOwndByUserAccount(UserAccount us) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ObiettiviUser> getObiettiviUserByUserAccount(UserAccount us) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUserAccountPunteggio(UserAccount us, int newPunteggio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserAccountMny(UserAccount us, int newmny) {
		// TODO Auto-generated method stub
		return false;
	}

}
