package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ActionPane extends Pane implements IDrawable{

	final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	private Label actionTitleL;
	private Button actionMarketL;
	private Button powerUpL;
	private Button develop ;
	
	public ActionPane() {
		super();
	}

	public Pane getActionPane() {
		
		drow();
		return this;
	}
	
	
	public Label getActionTitleL() {
		return actionTitleL;
	}

	public void setActionTitleL(Label actionTitleL) {
		this.actionTitleL = actionTitleL;
	}

	/**
	 * Restituisce pulsante che genera il popup di Market
	 * @return
	 */
	public Button getActionMarketL() {
		return actionMarketL;
	}

	public void setActionMarketL(Button actionMarketL) {
		this.actionMarketL = actionMarketL;
	}
	
	/**
	 * Restituisce il pulsante che genera il popup di potenziamento
	 * @return
	 */

	public Button getPowerUpL() {
		return powerUpL;
	}

	public void setPowerUpL(Button powerUpL) {
		this.powerUpL = powerUpL;
	}

	/**
	 * Restituisce il pulsante che genera il popup di sviluppo
	 * @return
	 */
	public Button getDevelop() {
		return develop;
	}

	public void setDevelop(Button develop) {
		this.develop = develop;
	}

	public Insets getSTANDARD_PADDING() {
		return STANDARD_PADDING;
	}

	/**
	 * Metodo che genera la zona con i pulsanti per generare i popup di azione
	 */
	@Override
	public void drow() {

		super.setBackground(
				new Background(new BackgroundFill(Color.web("#f8cecc"), new CornerRadii(10), new Insets(0, 0, 0, 0))));

		VBox controlli = new VBox();
		controlli.setMinWidth(200.0);
		controlli.setPadding(STANDARD_PADDING);
		controlli.setAlignment(Pos.CENTER);

		HBox actionTitle = ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		actionTitle.setPadding(STANDARD_PADDING);
		actionTitle.setBackground(
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(5, 5, 5, 5))));
		actionTitleL = new Label("Action");
		actionTitleL.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		actionTitleL.setTextFill(Color.DARKGREEN);
		actionTitle.getChildren().add(actionTitleL);

		
		HBox actionMarket = ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		actionMarket.setAlignment(Pos.BASELINE_CENTER);

		actionMarketL = ComponentCreator.getIstance().createButton("Market", Pos.BASELINE_CENTER);
		actionMarket.setSpacing(10);
		actionMarket.getChildren().add(actionMarketL);
		actionMarketL.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	PopUpFacade p=new PopUpFacade();
            	Giocatore g=new Utente();
            	p.avviaMarket(new Base(g));
            }
		});
		HBox powerUp =ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		powerUp.setAlignment(Pos.BASELINE_CENTER);

		powerUpL = ComponentCreator.getIstance().createButton("PowerUp", Pos.BASELINE_CENTER);
		powerUp.getChildren().add(powerUpL);

		powerUpL.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	PopUpFacade p=new PopUpFacade();
            	p.avviaPowerUp(new Base());
            }
		});
		
		HBox dev = ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		develop = ComponentCreator.getIstance().createButton("development", Pos.BASELINE_CENTER);
		dev.getChildren().add(develop);
		develop.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	PopUpFacade p=new PopUpFacade();
            	p.avviaDevelopment(new Base());
            }
		});
		controlli.setAlignment(Pos.BASELINE_CENTER);
		controlli.setSpacing(10);
		controlli.getChildren().addAll(actionTitle, actionMarket, powerUp, dev);
		super.getChildren().add(controlli);
		
	}
	
	
}
