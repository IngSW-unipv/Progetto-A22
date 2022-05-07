package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp;



import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.NumberSpinner;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class PopUpFacade {

	public static int sX = 400;
	public static int sY = 400;	
	public static final Insets STANDARD_PADDING = new Insets(10, 10, 10, 10);
	
	Development developmentPopUp;
	Market marketPopUp;
	Powerup powerUpPopUp;
	Selectmalware selectMalwarePopUp;
	private Mercato mkt = new Mercato();
	
	public PopUpFacade(Mercato mercato, Base baseUtente ) {
		this.mkt=mercato;
		this.developmentPopUp=new Development(baseUtente);
		this.marketPopUp=new Market(baseUtente);
		this.powerUpPopUp=new Powerup(baseUtente);
		this.selectMalwarePopUp=new Selectmalware(baseUtente);
	}
	
	public PopUpFacade(Base baseUtente) {
		this.developmentPopUp=new Development(baseUtente);
		this.marketPopUp=new Market();
		this.powerUpPopUp=new Powerup(baseUtente);
		this.selectMalwarePopUp=new Selectmalware(baseUtente);
	}
	public PopUpFacade() {
		super();
	}
	public void selectMalware(Base baseUtente) {
		Selectmalware sm= new Selectmalware(baseUtente);
		
		sm.selectMalware();
		
	}

	public void development(Base baseUtente) {
		
		Development dvl= new Development(baseUtente);
		dvl.development();
		// -> inserire le variabili di ritorno
		// TODO
		
	}
	
	public void powerUp(Base baseUtente) {
		
		Powerup pu=new Powerup(baseUtente);
		
		pu.powerUp();
				
	}
	
	
	public void market(Base baseUtente) {
		
		// -> inserire le variabili di ritorno
		// TODO
		
		Market m=new Market(new Mercato(2,0,2,0,2,0,2,0,2,0,2,0),baseUtente);
		m.market();
	}
}
