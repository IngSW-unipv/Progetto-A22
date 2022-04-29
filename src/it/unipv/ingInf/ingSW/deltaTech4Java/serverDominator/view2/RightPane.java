package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class RightPane {
	
	Base bU = new Base();
	
	public final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	
	
	public  RightPane(Base bU) {
		
		this.bU = bU;
		
	}
		
	public Pane getRightPane(Base bU) {

		Pane yourBasePane = new Pane();
		yourBasePane.setBackground(
				new Background(new BackgroundFill(Color.web("#f8cecc"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		yourBasePane.setPadding(STANDARD_PADDING);

		VBox yb = new VBox();
		yb.setPadding(STANDARD_PADDING);
		yb.setTranslateX(10);

		HBox ybTitle = new HBox();
		ybTitle.setPadding(STANDARD_PADDING);
		// ybTitle.setAlignment(Pos.CENTER);
		ybTitle.setBackground(
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(0, 0, 0, 0))));
		Label ybTitleL = new Label("Your Base Stats");
		ybTitleL.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		ybTitleL.setTextFill(Color.DARKGREEN);
		ybTitleL.setAlignment(Pos.CENTER);

		ybTitle.getChildren().add(ybTitleL);

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
		avQy.setText("" + bU.getSoftware_disponibile());
		vrQy.setText("" + bU.getSoftware_disponibile());
		rcQy.setText("" + bU.getSoftware_disponibile());

		ybG.add(ybEnergy, 0, 0);
		ybG.add(new Label("" + bU.getE_disponibile()), 1, 0);
		ybG.add(ybFwLvl, 0, 1);
		ybG.add(new Label("" + bU.getLvl_firewall()), 1, 1);
		ybG.add(ybRamLvl, 0, 2);
		ybG.add(new Label("" + bU.getLvl_ram()), 1, 2);
		ybG.add(ybCpuLvl, 0, 3);
		ybG.add(new Label("" + bU.getLvl_cpu()), 1, 3);
		ybG.add(ybAv, 0, 4);
		ybG.add(new Label("" + bU.getQnt_antivirus()), 1, 4);
		ybG.add(ybVr, 0, 5);
		ybG.add(new Label("" + bU.getQnt_virus()), 1, 5);
		ybG.add(ybRc, 0, 6);
		ybG.add(new Label("" + bU.getQnt_rootcrash()), 1, 6);

		hBg.getChildren().add(ybG);

		yb.getChildren().addAll(ybTitle, hBg);
		yourBasePane.getChildren().add(yb);

		return yourBasePane;
	}

}
