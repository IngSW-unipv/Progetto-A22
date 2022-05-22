package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp;

import java.math.BigDecimal;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PopUpFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.NumberSpinner;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Market {
	private int cpuAdd, cpuFinal, fwAdd, fwFinal, ramAdd, ramFinal, eAdd, eFinal, xS, yS;
	private int  cpuMax=10, fwMax=9, ramMax=10, eMax=10;
	
	private int prAv;
	private Button buttonPay;
	private NumberSpinner quantitaRootCrash;
	private NumberSpinner quantitaVirus;
	private NumberSpinner quantitaAntivirus ;
	private Base baseUtente;
	private Mercato mercato=new Mercato();
	private int total;
	private Stage stage;
	
	public Market(Base baseUtente) {
		istance(new Mercato(), baseUtente);
	}
	
	public Market(Mercato mercato, Base baseUtente) {
		istance(mercato, baseUtente);
	}
	
	public Market() {
		super();
	}
	private void istance(Mercato mercato, Base baseUtente) {
		this.baseUtente=baseUtente;
		this.cpuMax=baseUtente.getLvl_max_cpu();
		fwMax=baseUtente.getLvl_max_firewall();
		ramMax=baseUtente.getLvl_max_ram();
		eMax=ramMax;
		this.mercato=mercato;
		total=0;
		cpuAdd = 0;
		cpuFinal = baseUtente.getLvl_cpu(); 
		fwAdd =0;
		fwFinal = baseUtente.getLvl_firewall() ;
		ramAdd = 0; 
		ramFinal = baseUtente.getLvl_ram(); 
		eAdd = 0; 
		eFinal = baseUtente.getE_lvl();
		xS = 20; yS = 10;
		
	}
	
	public void market(Base baseUtente) {
		istance(new Mercato(), baseUtente);
	}
	
	/**
	 * Interfaccia popup dove si sceglie la risorsa, software da acquistare
	 */
	
	public void market() {
		Label finalBillL =ComponentCreator.getIstance().lableCreator(Pos.BASELINE_LEFT);
		setLableText(finalBillL, "Totale carrello" );
		Label totaleCarrello=ComponentCreator.getIstance().lableCreator(Pos.BASELINE_RIGHT);
		setLableText(totaleCarrello, String.valueOf(total) );
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setX(PopUpFacade.sX); stage.setY(PopUpFacade.sY);
		

		VBox vM = new VBox();
		vM.setPrefWidth(800);
		HBox initBill = new HBox();
		initBill.setPadding(PopUpFacade.STANDARD_PADDING);
		
		Label saldoAttuale=new Label();
		saldoAttuale.setFont(Font.font("Cambria", 22));
		setLableText(saldoAttuale, "Il Tuo saldo\t\t" + baseUtente.getPossessore().getValuta());
		initBill.getChildren().add(saldoAttuale);
		
		HBox hMktP = new HBox();
		GridPane mktP = new GridPane();
		mktP.setPadding(PopUpFacade.STANDARD_PADDING);
		mktP.setVgap(5);
		mktP.setHgap(5);
		
		Label rcQty = new Label("Own " + String.valueOf(baseUtente.getQnt_rootcrash()) + " rootcrash, buy ");
		Label vrQty = new Label("Own " + String.valueOf(baseUtente.getQnt_virus()) + " virus, buy ");
		Label avQty = new Label("Own " + String.valueOf(baseUtente.getQnt_antivirus()) + " antivirus, buy ");
		
		
		/*NumberSpinner*/
		int max=baseUtente.getSpazio_Ram();
		
		quantitaRootCrash = new NumberSpinner();
		quantitaRootCrash.setMax(max);
		quantitaRootCrash.setMin(0);
		quantitaVirus = new NumberSpinner();
		quantitaVirus.setMax(max);
		quantitaVirus.setMin(0);
		quantitaAntivirus = new NumberSpinner();
		quantitaAntivirus.setMax(max);
		quantitaAntivirus.setMin(0);
		quantitaRootCrash.getNumberField().setDisable(true);
		quantitaVirus.getNumberField().setDisable(true);
		quantitaAntivirus.getNumberField().setDisable(true);
		quantitaRootCrash.getIncrementButton().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	total-=mercato.getCostoRootcrash(getQuantitaRootCrash());
            	aumentoLogica(max, quantitaRootCrash, quantitaAntivirus, quantitaVirus);
            	total+=mercato.getCostoRootcrash(getQuantitaRootCrash());
            	setLableText(totaleCarrello,String.valueOf(total));
            	ae.consume();
            }
        });
		quantitaRootCrash.getDecrementButton().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	total-=mercato.getCostoRootcrash(getQuantitaRootCrash());
            	diminuzioneLogica(quantitaRootCrash);
            	total+=mercato.getCostoRootcrash(getQuantitaRootCrash());
            	setLableText(totaleCarrello,String.valueOf(total));
            	ae.consume();
            }
        });
        
		quantitaVirus.getIncrementButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
            	total-=mercato.getCostoVirus(getQuantitaVirus());
            	aumentoLogica(max, quantitaVirus, quantitaAntivirus, quantitaRootCrash);
            	total+=mercato.getCostoVirus(getQuantitaVirus());
            	setLableText(totaleCarrello,String.valueOf(total) );
            	ae.consume();
            	}
            
        });
		quantitaVirus.getDecrementButton().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	total-=mercato.getCostoVirus(getQuantitaVirus());
            	diminuzioneLogica(quantitaVirus);
            	total+=mercato.getCostoVirus(getQuantitaVirus());
            	setLableText(totaleCarrello,String.valueOf(total) );
            	ae.consume();
            }
        });
		
		quantitaAntivirus.getIncrementButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
            	total-=mercato.getCostoAntivirus(getQuantitaAntivirus());
            	aumentoLogica(max, quantitaAntivirus, quantitaVirus,quantitaRootCrash);
            	total+=mercato.getCostoAntivirus(getQuantitaAntivirus());
            	setLableText(totaleCarrello, String.valueOf(total));
            	ae.consume();
            }
        });
		quantitaAntivirus.getDecrementButton().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	total-=mercato.getCostoAntivirus(getQuantitaAntivirus());
            	diminuzioneLogica(quantitaAntivirus);
            	total+=mercato.getCostoAntivirus(getQuantitaAntivirus());
            	setLableText(totaleCarrello,String.valueOf(total) );
            	ae.consume();
            }
        });
		
		HBox cpuAdjust = new HBox();
			Button cpuInc = new Button("CPU+1 ");
			Button cpuDec = new Button("CPU-1 ");
			Label cpuResult = new Label();
		Label cpuPlus = new Label("Actual CPU level: " + baseUtente.getLvl_cpu());
		
		HBox fwAdjust = new HBox();
			Button fwInc = new Button("FWL+1 ");
			Button fwDec = new Button("FWL-1 ");
			Label fwResult = new Label();
		Label fwPlus= new Label("Actual FWL level: " + baseUtente.getLvl_firewall());
		
		HBox ramAdjust = new HBox();
			Button ramInc = new Button("RAM+1"); //ramInc.setPrefSize(xS, yS);
			Button ramDec = new Button("RAM-1"); //ramDec.setPrefSize(xS, yS);
			Label ramResult = new Label();		
		Label ramPlus = new Label("Actual RAM level: " + baseUtente.getLvl_ram());
		
		HBox eAdjust = new HBox();
			Button eInc = new Button("EGY+1  "); //eInc.setPrefSize(xS, yS);
			Button eDec = new Button("EGY-1 "); //eDec.setPrefSize(xS, yS);
			Label eResult = new Label();
		Label ePlus= new Label("Available energy: " + baseUtente.getE_disponibile());
		
		cpuInc.setOnAction(e -> {
			if(++cpuAdd==1&&cpuAdd+baseUtente.getLvl_cpu()<=cpuMax) {
				cpuFinal = baseUtente.getLvl_cpu() + cpuAdd;
				total+=mercato.getCostoCpu(1);
			}else {
				--cpuAdd;
			}
			setLableText(totaleCarrello,String.valueOf(total));
			cpuResult.setText(" add: " + cpuAdd + " Up to: " + cpuFinal);

		});
		
		cpuDec.setOnAction(e -> {
			if(--cpuAdd>=0){
				cpuFinal = baseUtente.getLvl_cpu() + cpuAdd;
				total=total-mercato.getCostoCpu(1);
			}else {
				++cpuAdd;
			}
			setLableText(totaleCarrello,String.valueOf(total));
			cpuResult.setText(" add: " + cpuAdd + " Up to: " + cpuFinal);

		});
		
		cpuAdjust.getChildren().addAll(cpuInc, cpuDec, cpuResult);
		cpuAdjust.setSpacing(8.0);
		
		fwInc.setOnAction(e -> {
			if(++fwAdd==1&&fwAdd+baseUtente.getLvl_firewall()<=fwMax) {
				fwFinal = baseUtente.getLvl_firewall() + fwAdd;
				total+=mercato.getPrezzoFirewall();
			}else {
				fwAdd--;
			}
			setLableText(totaleCarrello,String.valueOf(total));
			fwResult.setText(" add: " + fwAdd + " Up to: " + fwFinal);
		});
		
		fwDec.setOnAction(e -> {
			if(--fwAdd>=0){
				fwFinal = baseUtente.getLvl_firewall() + fwAdd;
				total-=mercato.getPrezzoFirewall();
			}else
				fwAdd++;
			setLableText(totaleCarrello,String.valueOf(total));
			fwResult.setText(" add: " + fwAdd + " Up to: " + fwFinal);
		});
		
		fwAdjust.getChildren().addAll(fwInc, fwDec, fwResult);
		fwAdjust.setSpacing(8.0);
		
		ramInc.setOnAction(e -> {
			if(ramAdd==0) {
				total+=mercato.getCostoRam(1);
			}
			ramAdd=1;
			ramFinal = baseUtente.getLvl_ram() + ramAdd; 
			if(ramFinal>=baseUtente.getLvl_max_ram()) {
				ramFinal--;
			}
			setLableText(totaleCarrello,String.valueOf(total));
			ramResult.setText(" add: " + ramAdd + " Up to: " + ramFinal);
		
		});
		
		ramDec.setOnAction(e -> {
			if(--ramAdd>=0 ){
				ramFinal = baseUtente.getLvl_ram() + ramAdd; 
				total-=mercato.getCostoRam(1);
			}
			else 
				ramAdd++;
			setLableText(totaleCarrello,String.valueOf(total));
			ramResult.setText(" add: " + ramAdd + " Up to: " + ramFinal);

		});
		
		ramAdjust.getChildren().addAll(ramInc, ramDec, ramResult);
		ramAdjust.setSpacing(8.0);
		
		eInc.setOnAction(e -> {
			int i=baseUtente.getE_lvl();
			if(++eAdd==1&&i+eAdd<eMax) {
				eFinal = baseUtente.getE_lvl() + eAdd; 
				total+=mercato.getCostoEnergia(eAdd);

			}else {
				eAdd--;
			}
			setLableText(totaleCarrello,String.valueOf(total));
			eResult.setText(" add: " + eAdd + " Up to: " + eFinal);
		});
		
		eDec.setOnAction(e -> {
			
			if(--eAdd>=0) {
				eFinal = baseUtente.getE_lvl() + eAdd;
				total-=mercato.getCostoEnergia(1);
			}else {
				++eAdd;
			}	
			eResult.setText(" add: " + eAdd + " Up to: " + eFinal);
			setLableText(totaleCarrello, String.valueOf(total));
		});
		
		eAdjust.getChildren().addAll(eInc, eDec, eResult);
		eAdjust.setSpacing(8.0);
		
		mktP.add(rcQty, 0, 0);
		mktP.add(quantitaRootCrash, 1, 0);
		mktP.add(vrQty, 0, 1);
		mktP.add(quantitaVirus, 1, 1);
		mktP.add(avQty, 0, 2);
		mktP.add(quantitaAntivirus, 1, 2);
		mktP.add(cpuPlus, 0, 3); mktP.add(cpuAdjust, 1, 3);
		mktP.add(fwPlus, 0, 4); mktP.add(fwAdjust, 1, 4);
		mktP.add(ramPlus, 0, 5); mktP.add(ramAdjust, 1, 5);
		mktP.add(ePlus, 0, 6); mktP.add(eAdjust, 1, 6);
		
		
		hMktP.getChildren().add(mktP);
		buttonPay = ComponentCreator.getIstance().createButton("buy!", Pos.BASELINE_CENTER);
		buttonPay.setOnAction(e -> {
			// username = text1.getText();
			// password = text2.getText();
			stage.close();
		});
		buttonPay.setPrefWidth(200);
		buttonPay.setPrefHeight(20);
		HBox finalBill = new HBox();
		finalBill.setPadding(PopUpFacade.STANDARD_PADDING);
		finalBill.setSpacing(30);
		finalBill.getChildren().addAll(finalBillL,totaleCarrello,buttonPay);
		HBox pay = ComponentCreator.getIstance().createHbox(Pos.CENTER_RIGHT);
		pay.prefWidth(500);
		pay.autosize();
		pay.setAlignment(Pos.TOP_CENTER);
		pay.getChildren().add(buttonPay);
		vM.setAlignment(Pos.BASELINE_CENTER);
		vM.getChildren().addAll(initBill, hMktP, finalBill,pay);
		
		Scene scene = new Scene(vM, PopUpFacade.sX, PopUpFacade.sY);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Market");
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	/**
	 * Metodo abbinato al pulsante aumento del number spinner
	 * @param sumMax
	 * @param target
	 * @param controllers
	 */
	private void aumentoLogica(int sumMax,NumberSpinner target,NumberSpinner... controllers) {
		{
        	int aumento=target.getStepWitdhProperty().get().intValue();
        	int totalControll=0;
        	for (NumberSpinner c:controllers) {
        		totalControll+=c.getNumber().intValue();
			}
        	if((target.getNumber().intValue()+totalControll+aumento)<=sumMax) {
        		target.increment();
        	}else {
        		int maxSelection=sumMax-totalControll;
        		maxSelection=maxSelection>0?maxSelection:0;
        		target.setNumber(BigDecimal.valueOf(maxSelection));
        	}
        }
	}
	private void setLableText(Label l,String stringTxt) {
		l.setText(stringTxt);
		
	}
	private void diminuzioneLogica(NumberSpinner target) {
		int diminuzione=target.getStepWitdhProperty().get().intValue();
    	if((target.getNumber().intValue()-diminuzione)>=target.getMin()) {
    		target.decrement();
    	}else {
    		target.setNumber(BigDecimal.valueOf(target.getMin()));
    	}
    	
	}
	public int getQuantitaRootCrash() {
		return quantitaRootCrash.getNumberField().getNumber().intValue();
	}
	public int getQuantitaAntivirus() {
		return quantitaAntivirus.getNumberField().getNumber().intValue();
	}
	public int getQuantitaVirus() {
		return quantitaVirus.getNumberField().getNumber().intValue();
	}
	public int getLivelloCPU() {
		return cpuAdd;
	}
	public int getLivelloFirewall() {
		return fwAdd;
	}
	public int getLivelloRam() {
		return ramAdd;
	}
	public int getLivelloEnergia() {
		return eAdd;
	}
	public Button getButtonPay() {
		return buttonPay;
	}
	
	public void setQuantitaVirus(NumberSpinner quantitaVirus) {
		this.quantitaVirus = quantitaVirus;
	}
	
	public void setCpuAdd(int cpuAdd) {
		this.cpuAdd = cpuAdd;
	}

	public void setFwAdd(int fwAdd) {
		this.fwAdd = fwAdd;
	}

	public void setRamAdd(int ramAdd) {
		this.ramAdd = ramAdd;
	}

	public void seteAdd(int eAdd) {
		this.eAdd = eAdd;
	}
	public int getCpuFinal() {
		return cpuFinal;
	}
	public void setCpuFinal(int cpuFinal) {
		this.cpuFinal = cpuFinal;
	}
	public int getFwFinal() {
		return fwFinal;
	}
	public void setFwFinal(int fwFinal) {
		this.fwFinal = fwFinal;
	}
	public int getRamFinal() {
		return ramFinal;
	}
	public void setRamFinal(int ramFinal) {
		this.ramFinal = ramFinal;
	}
	public int geteFinal() {
		return eFinal;
	}
	public void seteFinal(int eFinal) {
		this.eFinal = eFinal;
	}
	public int getxS() {
		return xS;
	}
	public void setxS(int xS) {
		this.xS = xS;
	}
	public int getyS() {
		return yS;
	}
	public void setyS(int yS) {
		this.yS = yS;
	}
	public int getCpuMax() {
		return cpuMax;
	}
	public void setCpuMax(int cpuMax) {
		this.cpuMax = cpuMax;
	}
	public int getFwMax() {
		return fwMax;
	}
	public void setFwMax(int fwMax) {
		this.fwMax = fwMax;
	}
	public int getRamMax() {
		return ramMax;
	}
	public void setRamMax(int ramMax) {
		this.ramMax = ramMax;
	}
	public int geteMax() {
		return eMax;
	}
	public void seteMax(int eMax) {
		this.eMax = eMax;
	}
	public int getPrAv() {
		return prAv;
	}
	public void setPrAv(int prAv) {
		this.prAv = prAv;
	}

	public void setButtonPay(Button button) {
		this.buttonPay = button;
	}

	public void setQuantitaRootCrash(NumberSpinner quantitaRootCrash) {
		this.quantitaRootCrash = quantitaRootCrash;
	}

	public void setQuantitaAntivirus(NumberSpinner quantitaAntivirus) {
		this.quantitaAntivirus = quantitaAntivirus;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Base getBaseUtente() {
		return baseUtente;
	}

	public void setBaseUtente(Base baseUtente) {
		this.baseUtente = baseUtente;
	}

	public Mercato getMercato() {
		return mercato;
	}

	public void setMercato(Mercato mercato) {
		this.mercato = mercato;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCpuAdd() {
		return cpuAdd;
	}

	public int getFwAdd() {
		return fwAdd;
	}

	public int getRamAdd() {
		return ramAdd;
	}

	public int geteAdd() {
		return eAdd;
	}
	

}
