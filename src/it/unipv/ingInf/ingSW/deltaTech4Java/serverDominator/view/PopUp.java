package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view;



import java.math.RoundingMode;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.Development;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.Market;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.Powerup;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp.Selectmalware;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp {

	// NOTA: le variabili di ritorno sono definite allì'interno di ogni PopUp
	int useRootcrash, useVirus;
	public static int sX = 400;
	public static int sY = 400;	
	NumberSpinner useRcNs = new NumberSpinner();
	NumberSpinner useVrNs = new NumberSpinner();
	public static final Insets STANDARD_PADDING = new Insets(10, 10, 10, 10);

	int cpuAdd, cpuFinal, fwAdd, fwFinal, ramAdd, ramFinal, eAdd, eFinal, xS, yS;
	Mercato mkt = new Mercato();
	int prAv = mkt.prezzoAntivirus;
	
	
	public void selectMalware(Base baseUtente) {
		Selectmalware sm= new Selectmalware(baseUtente);
		
		sm.selectMalware(baseUtente);
		
	}

	public void development(Base baseUtente) {
		
		Development dvl= new Development(baseUtente);
		dvl.development(baseUtente);
		// -> inserire le variabili di ritorno
		// TODO
		
	}
	
	public void powerUp(Base baseUtente) {
		
		Powerup pu=new Powerup(baseUtente);
		
		pu.powerUp(baseUtente);
				
	}
	
	
	public void market(Base baseUtente) {
		
		// -> inserire le variabili di ritorno
		// TODO
		
		Mercato m1=new Mercato();
		Market m=new Market(m1);
		m.market(baseUtente);
	}
}
