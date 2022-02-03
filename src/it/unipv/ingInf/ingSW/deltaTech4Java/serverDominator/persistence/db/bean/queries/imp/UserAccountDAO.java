package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.imp;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.AsetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.IUserAccountDAO;

public class UserAccountDAO implements IUserAccountDAO {

	@Override
	public boolean insetUserAccount(UserAccount us) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean updateUserAccountPunteggio(UserAccount us, String newPunteggio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserAccountMny(UserAccount us, int newmny) {
		// TODO Auto-generated method stub
		return false;
	}

}
