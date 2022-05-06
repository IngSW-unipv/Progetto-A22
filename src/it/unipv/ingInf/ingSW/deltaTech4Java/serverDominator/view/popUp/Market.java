package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.popUp;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Mercato;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.NumberSpinner;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.PopUp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Market {
	int cpuAdd, cpuFinal, fwAdd, fwFinal, ramAdd, ramFinal, eAdd, eFinal, xS, yS;
	int  cpuMax=10, fwMax=9, ramMax=10, eMax=10;
	
	Mercato mkt;
	int prAv;
	Button button;
	NumberSpinner quantitaRootCrash;
	NumberSpinner quantitaVirus;
	NumberSpinner quantitaAntivirus ;
	public Market(Mercato mkt) {
		this.mkt=mkt;
		prAv=mkt.prezzoAntivirus;
	}
	
	public void market(Base baseUtente) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setX(PopUp.sX); stage.setY(PopUp.sY);
		
		cpuAdd = 0; cpuFinal = 0; fwAdd = 0; fwFinal = 0; ramAdd = 0; ramFinal = 0; eAdd = 0; eFinal = 0;
		xS = 20; yS = 10;
		
		VBox vM = new VBox();
		
		HBox initBill = new HBox();
		initBill.setPadding(PopUp.STANDARD_PADDING);
		Label saldoAttuale = new Label("Il tuo saldo iniziale è:  " + baseUtente.getPossessore().getValuta());
		initBill.getChildren().add(saldoAttuale);
		
		GridPane priceList = new GridPane();
		priceList.setPadding(PopUp.STANDARD_PADDING);
		priceList.add(new Label("Antivirus: " + prAv), cpuFinal, cpuAdd);
		
		HBox hMktP = new HBox();
		GridPane mktP = new GridPane();
		mktP.setPadding(PopUp.STANDARD_PADDING);
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
				
		/*NumberSpinner listeners per max quantita*/ 
		quantitaRootCrash.getIncrementButton().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent ae) {
            	int aumento=quantitaRootCrash.getStepWitdhProperty().get().intValue();
            	if((quantitaRootCrash.getNumber().intValue()+quantitaVirus.getNumber().intValue()+quantitaAntivirus.getNumber().intValue()+aumento)<=max) {
            		quantitaRootCrash.increment();
                    ae.consume();
            	}
            }
        });
        
		quantitaVirus.getIncrementButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
            	int aumento=quantitaVirus.getStepWitdhProperty().get().intValue();
            	if((quantitaRootCrash.getNumber().intValue()+quantitaVirus.getNumber().intValue()+quantitaAntivirus.getNumber().intValue()+aumento)<=max) {
            		quantitaVirus.increment();
                    ae.consume();
            	}
            }
        });
		
		quantitaAntivirus.getIncrementButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ae) {
            	int aumento=quantitaAntivirus.getStepWitdhProperty().get().intValue();
            	if((quantitaRootCrash.getNumber().intValue()+quantitaVirus.getNumber().intValue()+quantitaAntivirus.getNumber().intValue()+aumento)<=max) {
            		quantitaAntivirus.increment();
                    ae.consume();
            	}
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
				cpuResult.setText(" add: " + cpuAdd + " Up to: " + cpuFinal);
			}else {
				--cpuAdd;
			}
		});
		
		cpuDec.setOnAction(e -> {
			if(--cpuAdd>=0){
			cpuFinal = baseUtente.getLvl_cpu() + cpuAdd;
			}else {
				++cpuAdd;
			}
			cpuResult.setText(" add: " + cpuAdd + " Up to: " + cpuFinal);

		});
		
		cpuAdjust.getChildren().addAll(cpuInc, cpuDec, cpuResult);
		cpuAdjust.setSpacing(8.0);
		
		fwInc.setOnAction(e -> {
		if(++fwAdd==1&&fwAdd+baseUtente.getLvl_firewall()<=fwMax) {
			fwFinal = baseUtente.getLvl_firewall() + fwAdd;
		}else {
			fwAdd--;
		}
		fwResult.setText(" add: " + fwAdd + " Up to: " + fwFinal);
		});
		
		fwDec.setOnAction(e -> {
			if(--fwAdd >=0){
				cpuFinal = baseUtente.getLvl_firewall() + fwAdd;
			}else
				fwAdd++;
			fwResult.setText(" add: " + fwAdd + " Up to: " + fwFinal);
		});
		
		fwAdjust.getChildren().addAll(fwInc, fwDec, fwResult);
		fwAdjust.setSpacing(8.0);
		
		ramInc.setOnAction(e -> {
			if(++ramAdd==1&&ramAdd+baseUtente.getLvl_ram()<=ramMax) {
				ramFinal = baseUtente.getLvl_ram() + ramAdd; 
			}else {
				ramAdd--;
			}
			 ;
			ramResult.setText(" add: " + ramAdd + " Up to: " + ramFinal);
		});
		
		ramDec.setOnAction(e -> {
			if(--ramAdd>=0 ){
				ramFinal = baseUtente.getLvl_ram() + ramAdd; 
			}
			else 
				ramAdd++;
			ramResult.setText(" add: " + ramAdd + " Up to: " + ramFinal);

		});
		
		ramAdjust.getChildren().addAll(ramInc, ramDec, ramResult);
		ramAdjust.setSpacing(8.0);
		
		eInc.setOnAction(e -> {
			int i=baseUtente.getE_lvl();
			if(++eAdd==1&&i+eAdd<eMax) {
				eFinal = baseUtente.getE_lvl() + eAdd; 

			}else {
				eAdd--;
			}
			eResult.setText(" add: " + eAdd + " Up to: " + eFinal);
		});
		
		eDec.setOnAction(e -> {
			
			if(--eAdd>=0) {
				eFinal = baseUtente.getE_lvl() + eAdd;
			}else {
				++eAdd;
			}	
			eResult.setText(" add: " + eAdd + " Up to: " + eFinal);

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
		
		HBox finalBill = new HBox();
		finalBill.setPadding(PopUp.STANDARD_PADDING);
		Label finalBillL = new Label("Stai spendendo: xxx" + "Ti resterà: yyy");
		finalBill.getChildren().add(finalBillL);
		
		HBox mB = new HBox();
		mB.setAlignment(Pos.BASELINE_CENTER);
		button = new Button("buy!");
		button.setPrefSize(200, 20);
		button.setAlignment(Pos.BASELINE_CENTER);
		button.getStyleClass().add("redbutton");

		button.setOnAction(e -> {
			// username = text1.getText();
			// password = text2.getText();
			stage.close();
		});
		
		mB.getChildren().add(button);
		vM.getChildren().addAll(initBill, priceList, hMktP, finalBill, mB);
	
		Scene scene = new Scene(vM, PopUp.sX, PopUp.sY);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Market");
		stage.setScene(scene);
		stage.showAndWait();

		// -> inserire le variabili di ritorno
		// TODO
	}
	public int getCpuAdd() {
		return cpuAdd;
	}
	public void setCpuAdd(int cpuAdd) {
		this.cpuAdd = cpuAdd;
	}
	public int getFwAdd() {
		return fwAdd;
	}
	public void setFwAdd(int fwAdd) {
		this.fwAdd = fwAdd;
	}
	public int getRamAdd() {
		return ramAdd;
	}
	public void setRamAdd(int ramAdd) {
		this.ramAdd = ramAdd;
	}
	public int geteAdd() {
		return eAdd;
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
	public Mercato getMkt() {
		return mkt;
	}
	public void setMkt(Mercato mkt) {
		this.mkt = mkt;
	}
	public int getPrAv() {
		return prAv;
	}
	public void setPrAv(int prAv) {
		this.prAv = prAv;
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}

	public NumberSpinner getQuantitaRootCrash() {
		return quantitaRootCrash;
	}

	public void setQuantitaRootCrash(NumberSpinner quantitaRootCrash) {
		this.quantitaRootCrash = quantitaRootCrash;
	}

	public NumberSpinner getQuantitaVirus() {
		return quantitaVirus;
	}

	public void setQuantitaVirus(NumberSpinner quantitaVirus) {
		this.quantitaVirus = quantitaVirus;
	}

	public NumberSpinner getQuantitaAntivirus() {
		return quantitaAntivirus;
	}

	public void setQuantitaAntivirus(NumberSpinner quantitaAntivirus) {
		this.quantitaAntivirus = quantitaAntivirus;
	}
	
}
