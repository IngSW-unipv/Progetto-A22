package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.IDrawable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrebattagliaView extends Stage implements IDrawable {
	private UserAccount userAccount;
	private int[] selectedDifecolta=new int[2];
	private Button attacca;
	
	public PrebattagliaView(UserAccount userAccount) {
		super();
		this.userAccount = userAccount;
	}
	
	
	
	
	public UserAccount getUserAccount() {
		return userAccount;
	}




	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}




	public int[] getSelectedDifecolta() {
		return selectedDifecolta;
	}




	public void setSelectedDifecolta(int[] selectedDifecolta) {
		this.selectedDifecolta = selectedDifecolta;
	}




	public Button getAttacca() {
		return attacca;
	}




	public void setAttacca(Button attacca) {
		this.attacca = attacca;
	}




	@Override
	public void drow() {
		// TODO Auto-generated method stub
		
	}
	
}