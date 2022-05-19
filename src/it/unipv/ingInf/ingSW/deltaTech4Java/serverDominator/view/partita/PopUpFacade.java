package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita;



import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Development;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Market;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Powerup;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Selectmalware;
import javafx.geometry.Insets;

public class PopUpFacade {

	public static int sX = 400;
	public static int sY = 400;	
	public static final Insets STANDARD_PADDING = new Insets(10, 10, 10, 10);
	private Powerup popUpPowerup;
	private Selectmalware popUpSelectmalware;
	private Development popUpDevelopment;
	private Market popUpMarket;
	private Mercato mkt = new Mercato();
	private  Base baseUtente;
	
	public PopUpFacade(Mercato mercato, Base baseUtente ) {
		this.mkt=mercato;
		this.baseUtente=baseUtente;
		this.popUpPowerup =new Powerup(baseUtente);
		this.popUpSelectmalware=new Selectmalware(baseUtente);
		this.popUpDevelopment= new Development(baseUtente);
		this.popUpMarket=new Market(mkt,baseUtente); 
		
	}
	
	public PopUpFacade() {
		super();
		this.mkt=new Mercato();
	}

	public void avviaSelectMalware(Base baseUtente) {
		popUpSelectmalware= new Selectmalware(baseUtente);
		popUpSelectmalware.selectMalware();
		
	}

	public void avviaDevelopment(Base baseUtente) {
		popUpDevelopment= new Development(baseUtente);
		popUpDevelopment.development();
	}
	
	public void avviaPowerUp(Base baseUtente) {
		popUpPowerup=new Powerup(baseUtente);
		popUpPowerup.powerUp();	
	}
	
	public void avviaMarket(Base baseUtente) {
		popUpMarket=new Market(mkt,baseUtente);
		popUpMarket.market();
	}
	
	
	public void avviaSelectMalware() {
		popUpSelectmalware.selectMalware();
		
	}

	public void avviaDevelopment() {
		popUpDevelopment.development();
	}
	
	public void avviaPowerUp() {
		popUpPowerup.powerUp();	
	}
	
	public void avviaMarket() {
		popUpMarket.market();
	}

	public Powerup getPopUpPowerup() {
		return popUpPowerup;
	}

	public void setPopUpPowerup(Powerup popUpPowerup) {
		this.popUpPowerup = popUpPowerup;
	}

	public Selectmalware getPopUpSelectmalware() {
		return popUpSelectmalware;
	}

	public void setPopUpSelectmalware(Selectmalware popUpSelectmalware) {
		this.popUpSelectmalware = popUpSelectmalware;
	}

	public Development getPopUpDevelopment() {
		return popUpDevelopment;
	}

	public void setPopUpDevelopment(Development popUpDevelopment) {
		this.popUpDevelopment = popUpDevelopment;
	}

	public Market getPopUpMarket() {
		return popUpMarket;
	}

	public void setPopUpMarket(Market popUpMarket) {
		this.popUpMarket = popUpMarket;
	}

	public Mercato getMkt() {
		return mkt;
	}

	public void setMkt(Mercato mkt) {
		this.mkt = mkt;
	}

	public Base getBaseUtente() {
		return baseUtente;
	}

	public void setBaseUtente(Base baseUtente) {
		this.baseUtente = baseUtente;
	}
	
}
