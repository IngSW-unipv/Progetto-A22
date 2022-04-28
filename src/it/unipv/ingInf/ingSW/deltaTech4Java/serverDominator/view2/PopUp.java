package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;



import java.math.BigDecimal;
import java.math.RoundingMode;
import javafx.application.Application;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Nodo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2.MapData.Layout;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2.Main;
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
import javafx.scene.layout.Priority;
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
	static int sX = 400;
	static int sY = 400;	
	NumberSpinner useRcNs = new NumberSpinner();
	NumberSpinner useVrNs = new NumberSpinner();
	final Insets STANDARD_PADDING = new Insets(10, 10, 10, 10);
	
	
	public void selectMalware(Base baseUtente) {

		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setX(sX); stage.setY(sY);
		
		VBox atck = new VBox();
		
		Label rc = new Label("Rootcrash: ");
		Label rcQty = new Label(String.valueOf(baseUtente.getQnt_rootcrash()));
		Label vr = new Label("Virus:     ");
		 
		Label vrQty = new Label(String.valueOf(baseUtente.getQnt_virus())); 
		Label use1 = new Label("Use: ");
		Label use2 = new Label("Use: ");
		TextField useRcTf = new TextField(); useRcTf.setMaxWidth(50);
		TextField useVrTf = new TextField(); useVrTf.setMaxWidth(50);
		NumberSpinner useRcNs = new NumberSpinner();
		NumberSpinner useVrNs = new NumberSpinner();
		
		HBox gB = new HBox();
		
		GridPane layout = new GridPane();
		layout.setMinSize(400, 80);

		layout.setPadding(STANDARD_PADDING);
		layout.setVgap(5);
		layout.setHgap(5);
		
		layout.add(rc, 0, 0);
		layout.add(rcQty, 1, 0);
		layout.add(use1, 2, 0);
		layout.add(useRcNs, 3, 0);
		layout.add(vr, 0, 1);
		layout.add(vrQty, 1, 1);
		layout.add(use2, 2, 1);
		layout.add(useVrNs, 3, 1);
				
		gB.getChildren().add(layout);
		
		HBox bA = new HBox();
		bA.setPadding(new Insets(0,10,10,10));
		bA.setAlignment(Pos.BASELINE_CENTER);
				
		Button button1 = new Button("Fight");
		button1.setPrefSize(200, 20);
		button1.setAlignment(Pos.CENTER);
		button1.getStyleClass().add("redbutton");
				
		button1.setOnAction(e -> {
			int useRc, useVr ;
			useRc = useRcNs.getNumber().setScale(0, RoundingMode.DOWN).intValueExact();
			useVr = useVrNs.getNumber().setScale(0, RoundingMode.DOWN).intValueExact();
			System.out.println(useRc + "  " + useVr);
			stage.close();
		});
	
		bA.getChildren().add(button1);
		bA.getStyleClass().add("redbutton");		
		atck.getChildren().addAll(gB, bA);		
		
		Scene scene = new Scene(atck, sX, sY);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Seleziona Malware");
		stage.setScene(scene);						
		stage.showAndWait();

	}

	public void development(Base baseUtente) {
				
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setX(sX); stage.setY(sY);

		VBox dev = new VBox();
		dev.setAlignment(Pos.TOP_CENTER);
		dev.setPadding(STANDARD_PADDING);
		
		HBox devTitle = new HBox();
		devTitle.setPadding(STANDARD_PADDING);
		Label devTitleL = new Label("Develop your Base!");
		devTitle.getChildren().add(devTitleL);
		
		
		HBox devG = new HBox();
		devG.setPadding(STANDARD_PADDING);
		devG.setAlignment(Pos.BASELINE_LEFT);
		
		GridPane layout = new GridPane();
		layout.setPadding(STANDARD_PADDING);
		layout.setVgap(5);
		layout.setHgap(5);

		Label rcQty = new Label("Own " + String.valueOf(baseUtente.getQnt_rootcrash()) + " rootcrash, dev ");
		Label vrQty = new Label("Own " + String.valueOf(baseUtente.getQnt_virus()) + " virus, dev ");
		Label avQty = new Label("Own " + String.valueOf(baseUtente.getQnt_antivirus()) + " antivirus, dev ");
		NumberSpinner buyRcNs = new NumberSpinner();
		NumberSpinner buyVrNs = new NumberSpinner();
		NumberSpinner buyAvNs = new NumberSpinner();
		
		HBox devB = new HBox();
		Button button = new Button("Develop!");
		button.setPrefSize(200, 20);
		button.setAlignment(Pos.BASELINE_CENTER);
		button.setPadding(STANDARD_PADDING);
		button.getStyleClass().add("redbutton");
		button.setOnAction(e -> {
			// username = text1.getText();
			// password = text2.getText();
			stage.close();
		});
		
		layout.add(avQty, 0	, 0);
		layout.add(buyAvNs, 1, 0);
		layout.add(vrQty, 0, 1);
		layout.add(buyVrNs, 1, 1);
		layout.add(rcQty, 0, 2);
		layout.add(buyRcNs, 1, 2);
		
		devG.getChildren().add(layout);
		devB.getChildren().add(button);
		dev.getChildren().addAll(devTitle, devG, devB);
		
		Scene scene = new Scene(dev, sX, sY);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Development");
		stage.setScene(scene);
		stage.showAndWait();

		// -> inserire le variabili di ritorno
		// TODO
	}
	
	public void powerUp(Base baseUtente) {
				
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setX(sX); stage.setY(sY);
		
		VBox powerUp = new VBox();
		powerUp.setAlignment(Pos.TOP_CENTER);
		
		
		HBox powerUpTitle = new HBox();
		powerUpTitle.setPadding(STANDARD_PADDING);
		powerUpTitle.setAlignment(Pos.CENTER);
		powerUpTitle.setBackground(
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		Label powTitleL = new Label("Empower your BASE !");
		powTitleL.getStylesheets().add("redbutton");
		powTitleL.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		powTitleL.setTextFill(Color.DARKGREEN);
		powerUpTitle.getChildren().add(powTitleL);
		
		HBox availableE = new HBox();
		availableE.setAlignment(Pos.BASELINE_LEFT);
		availableE.setPadding(new Insets(10, 0, 10, 10));
		Label eDisp = new Label("Available Energy: " + String.valueOf(baseUtente.getE_disponibile()));
		availableE.getChildren().add(eDisp);
		
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(5);
		layout.setHgap(5);
		
		Label cpuPlus = new Label("Actual CPU level: " + baseUtente.getLvl_cpu());
		
		HBox cpuAdjust = new HBox();
		Button cpuInc = new Button("CPU+1 ");
		Button cpuDec = new Button("CPU-1 ");
		Label cpuResult = new Label();
		
		Label fwPlus= new Label("Actual FWL level: " + baseUtente.getLvl_firewall());
		
		HBox fwAdjust = new HBox();
			Button fwInc = new Button("FWL+1 ");
			Button fwDec = new Button("FWL-1 ");
			Label fwResult = new Label();
		
		Label ramPlus = new Label("Actual RAM level: " + baseUtente.getLvl_ram());
		
		HBox ramAdjust = new HBox();
			Button ramInc = new Button("RAM+1"); //ramInc.setPrefSize(xS, yS);
			Button ramDec = new Button("RAM-1"); //ramDec.setPrefSize(xS, yS);
			Label ramResult = new Label();		
		
		
		cpuInc.setOnAction(e -> {
			++cpuAdd ;
			cpuFinal = baseUtente.getLvl_cpu() + cpuAdd;
			cpuResult.setText(" add: " + cpuAdd + " Up to: " + cpuFinal);
		});
		
		cpuDec.setOnAction(e -> {
			--cpuAdd ;
			cpuFinal = baseUtente.getLvl_cpu() + cpuAdd;
			cpuResult.setText(" add: " + cpuAdd + " Up to: " + cpuFinal);
		});
		
		cpuAdjust.getChildren().addAll(cpuInc, cpuDec, cpuResult);
		cpuAdjust.setSpacing(8.0);
		
		fwInc.setOnAction(e -> {
		++fwAdd ;
			cpuFinal = baseUtente.getLvl_firewall() + fwAdd;
			fwResult.setText(" add: " + fwAdd + " Up to: " + fwFinal);
		});
		
		fwDec.setOnAction(e -> {
		--fwAdd ;
			cpuFinal = baseUtente.getLvl_firewall() + fwAdd;
			fwResult.setText(" add: " + fwAdd + " Up to: " + fwFinal);
		});
		
		fwAdjust.getChildren().addAll(fwInc, fwDec, fwResult);
		fwAdjust.setSpacing(8.0);
		
		ramInc.setOnAction(e -> {
			++ramAdd ;
			ramFinal = baseUtente.getLvl_ram() + ramAdd; 
			ramResult.setText(" add: " + ramAdd + " Up to: " + ramFinal);
		});
		
		ramDec.setOnAction(e -> {
			--ramAdd ;
			ramFinal = baseUtente.getLvl_ram() + ramAdd; 
			ramResult.setText(" add: " + ramAdd + " Up to: " + ramFinal);
		});
		
		ramAdjust.getChildren().addAll(ramInc, ramDec, ramResult);
		ramAdjust.setSpacing(8.0);

		HBox pUButton = new HBox();
		Button pUB = new Button("powerUp!");
		pUB.setPrefSize(200, 20);
		pUB.setAlignment(Pos.BASELINE_CENTER);
		pUB.getStyleClass().add("redbutton");
		
		pUB.setOnAction(e -> {
			// username = text1.getText();
			// password = text2.getText();
			stage.close();
		});
		pUButton.getChildren().add(pUB);
		pUButton.setPadding(new Insets(10, 10, 10, 10));
		
		layout.add(cpuPlus, 0, 0);
		layout.add(cpuAdjust, 1, 0);
		layout.add(ramPlus, 0, 1);
		layout.add(ramAdjust, 1, 1);
		layout.add(fwPlus, 0, 2);
		layout.add(fwAdjust, 1, 2);
		
		powerUp.getChildren().addAll(powerUpTitle, availableE, layout, pUButton);
		
		Scene scene = new Scene(powerUp, sX, sY);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Power up");
		stage.setScene(scene);
		stage.showAndWait();

	}
	
	int cpuAdd, cpuFinal, fwAdd, fwFinal, ramAdd, ramFinal, eAdd, eFinal, xS, yS;
	
	public void market(Base baseUtente) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setX(sX); stage.setY(sY);
		
		cpuAdd = 0; cpuFinal = 0; fwAdd = 0; fwFinal = 0; ramAdd = 0; ramFinal = 0; eAdd = 0; eFinal = 0;
		xS = 20; yS = 10;
		
		VBox vM = new VBox();
		
		HBox hMktP = new HBox();
		
		GridPane mktP = new GridPane();
		mktP.setPadding(STANDARD_PADDING);
		mktP.setVgap(5);
		mktP.setHgap(5);
		
		Label rcQty = new Label("Own " + String.valueOf(baseUtente.getQnt_rootcrash()) + " rootcrash, buy ");
		Label vrQty = new Label("Own " + String.valueOf(baseUtente.getQnt_virus()) + " virus, buy ");
		Label avQty = new Label("Own " + String.valueOf(baseUtente.getQnt_antivirus()) + " antivirus, buy ");
		NumberSpinner buyRcNs = new NumberSpinner();
		NumberSpinner buyVrNs = new NumberSpinner();
		NumberSpinner buyAvNs = new NumberSpinner();
				
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
			++cpuAdd ;
			cpuFinal = baseUtente.getLvl_cpu() + cpuAdd;
			cpuResult.setText(" add: " + cpuAdd + " Up to: " + cpuFinal);
		});
		
		cpuDec.setOnAction(e -> {
			--cpuAdd ;
			cpuFinal = baseUtente.getLvl_cpu() + cpuAdd;
			cpuResult.setText(" add: " + cpuAdd + " Up to: " + cpuFinal);
		});
		
		cpuAdjust.getChildren().addAll(cpuInc, cpuDec, cpuResult);
		cpuAdjust.setSpacing(8.0);
		
		fwInc.setOnAction(e -> {
		++fwAdd ;
			cpuFinal = baseUtente.getLvl_firewall() + fwAdd;
			fwResult.setText(" add: " + fwAdd + " Up to: " + fwFinal);
		});
		
		fwDec.setOnAction(e -> {
		--fwAdd ;
			cpuFinal = baseUtente.getLvl_firewall() + fwAdd;
			fwResult.setText(" add: " + fwAdd + " Up to: " + fwFinal);
		});
		
		fwAdjust.getChildren().addAll(fwInc, fwDec, fwResult);
		fwAdjust.setSpacing(8.0);
		
		ramInc.setOnAction(e -> {
			++ramAdd ;
			ramFinal = baseUtente.getLvl_ram() + ramAdd; 
			ramResult.setText(" add: " + ramAdd + " Up to: " + ramFinal);
		});
		
		ramDec.setOnAction(e -> {
			--ramAdd ;
			ramFinal = baseUtente.getLvl_ram() + ramAdd; 
			ramResult.setText(" add: " + ramAdd + " Up to: " + ramFinal);
		});
		
		ramAdjust.getChildren().addAll(ramInc, ramDec, ramResult);
		ramAdjust.setSpacing(8.0);
		
		eInc.setOnAction(e -> {
			++eAdd ;
			eFinal = baseUtente.getE_disponibile() + eAdd; 
			eResult.setText(" add: " + eAdd + " Up to: " + eFinal);
		});
		
		eDec.setOnAction(e -> {
			--eAdd ;
			eFinal = baseUtente.getE_disponibile() + eAdd;
			eResult.setText(" add: " + eAdd + " Up to: " + eFinal);
		});
		
		eAdjust.getChildren().addAll(eInc, eDec, eResult);
		eAdjust.setSpacing(8.0);
		
		mktP.add(rcQty, 0, 0);
		mktP.add(buyRcNs, 1, 0);
		mktP.add(vrQty, 0, 1);
		mktP.add(buyVrNs, 1, 1);
		mktP.add(avQty, 0, 2);
		mktP.add(buyAvNs, 1, 2);
		mktP.add(cpuPlus, 0, 3); mktP.add(cpuAdjust, 1, 3);
		mktP.add(fwPlus, 0, 4); mktP.add(fwAdjust, 1, 4);
		mktP.add(ramPlus, 0, 5); mktP.add(ramAdjust, 1, 5);
		mktP.add(ePlus, 0, 6); mktP.add(eAdjust, 1, 6);
		
		
		hMktP.getChildren().add(mktP);
		
		HBox mB = new HBox();
		mB.setAlignment(Pos.BASELINE_CENTER);
		Button button = new Button("buy!");
		button.setPrefSize(200, 20);
		button.setAlignment(Pos.BASELINE_CENTER);
		button.getStyleClass().add("redbutton");

		button.setOnAction(e -> {
			// username = text1.getText();
			// password = text2.getText();
			stage.close();
		});
		
		mB.getChildren().add(button);
		vM.getChildren().addAll(hMktP, mB);
	
		Scene scene = new Scene(vM, sX, sY);
		scene.getStylesheets().add("application.css");
		stage.setTitle("Market");
		stage.setScene(scene);
		stage.showAndWait();

		// -> inserire le variabili di ritorno
		// TODO
	}
}
