package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class BaseStatsPane extends Pane{
	
	private Base nodeBase = new Base();
	private Button buttonTitle;
	private Button buttonBack;
	private Button buttonNext;
	public final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	
	
	public  BaseStatsPane(Base bU) {
		super();
		super.setBackground(
				new Background(new BackgroundFill(Color.web("#f8cecc"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		super.setPadding(STANDARD_PADDING);
		this.nodeBase = bU;
		buttonBack=ComponentCreator.getIstance().createButton("<<", Pos.TOP_CENTER);
		buttonNext=ComponentCreator.getIstance().createButton(">>", Pos.TOP_CENTER);
	}
		
	public Pane getBsPane(Base bU) {
		nodeBase=bU;
		make();
		return this;
	}
	
	private void make() {

		VBox yb = new VBox();
		yb.setPadding(STANDARD_PADDING);
		yb.setTranslateX(10);
		
		HBox ybTitle = ComponentCreator.getIstance().createHbox(Pos.BASELINE_CENTER);
		buttonTitle = ComponentCreator.getIstance().createButton("Your Base Stats", Pos.BASELINE_CENTER);
		buttonTitle.setStyle("-fx-background-color:  #FAF9F6; -fx-background-radius: 15px; -fx-text-fill: #006400 ;");
		ybTitle.getChildren().add(buttonTitle);
//Color.DARKGREEN.get;
		HBox hBg = new HBox();
		hBg.setPadding(STANDARD_PADDING);
		GridPane ybG = new GridPane();
		ybG.setVgap(5);
		ybG.setHgap(20);
		ybG.setAlignment(Pos.BASELINE_LEFT);

		Label ybEnergy = new Label();
		Label ybFwLvl = new Label();
		Label ybRamLvl = new Label();
		Label ybCpuLvl = new Label();
		Label ybAv = new Label();
		Label ybVr = new Label();
		Label ybRc = new Label();
		Label avQy = new Label();
		Label vrQy = new Label();
		Label rcQy = new Label();

		ybEnergy.setText("Energy: ");
		ybFwLvl.setText("Firewall Lvl: ");
		ybRamLvl.setText("Ram Lvl: ");
		ybCpuLvl.setText("CPU Lvl: ");
		ybAv.setText("Antivirus disp: ");
		ybVr.setText("Virus disp: ");
		ybRc.setText("Rootcrash disp: ");
		avQy.setText("" + nodeBase.getSoftware_disponibile());
		vrQy.setText("" + nodeBase.getSoftware_disponibile());
		rcQy.setText("" + nodeBase.getSoftware_disponibile());

		ybG.add(ybEnergy, 0, 0);
		ybG.add(new Label("" + nodeBase.getE_disponibile()), 1, 0);
		ybG.add(ybFwLvl, 0, 1);
		ybG.add(new Label("" + nodeBase.getLvl_firewall()), 1, 1);
		ybG.add(ybRamLvl, 0, 2);
		ybG.add(new Label("" + nodeBase.getLvl_ram()), 1, 2);
		ybG.add(ybCpuLvl, 0, 3);
		ybG.add(new Label("" + nodeBase.getLvl_cpu()), 1, 3);
		ybG.add(ybAv, 0, 4);
		ybG.add(new Label("" + nodeBase.getQnt_antivirus()), 1, 4);
		ybG.add(ybVr, 0, 5);
		ybG.add(new Label("" + nodeBase.getQnt_virus()), 1, 5);
		ybG.add(ybRc, 0, 6);
		ybG.add(new Label("" + nodeBase.getQnt_rootcrash()), 1, 6);

		hBg.getChildren().add(ybG);
		hBg.setAlignment(Pos.CENTER);
		HBox hb=new HBox();
		hb.setPadding(STANDARD_PADDING);
		hb.setSpacing(20);
		hb.setAlignment(Pos.BASELINE_CENTER);
		buttonBack.setPrefWidth(50);
		buttonNext.setPrefWidth(50);
		hb.getChildren().addAll(buttonBack,buttonNext);
		yb.getChildren().addAll(ybTitle, hBg,hb);
		super.getChildren().add(yb);
	}
	public Base getNodeBase() {
		return nodeBase;
	}

	public void setNodeBase(Base bU) {
		this.nodeBase = bU;
	}

	public Button getButtonTitle() {
		return buttonTitle;
	}

	public void setButtonTitle(Button buttonTitle) {
		this.buttonTitle = buttonTitle;
	}
	

}
