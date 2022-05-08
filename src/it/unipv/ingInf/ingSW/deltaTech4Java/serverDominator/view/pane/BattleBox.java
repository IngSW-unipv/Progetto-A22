package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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

class OneBattle extends ProgressBar implements Comparable<OneBattle>{

	private String bTitle; 					// titolo di ogni battaglia
	ProgressBar pB = new ProgressBar(0);
	VBox bBox = new VBox();
	long durata;
	double timeScale;
	
	public  OneBattle(String bTitle, long durata) {
		super();
		this.bTitle = bTitle;
		this.durata = durata;
		this.timeScale = 1 / durata;
	}
	
	public String getTitle() {
		return this.bTitle;
	}
	
	public long getDurata() {
		return this.durata;
	}
	
	public void setTitle(String title) {
		this.bTitle = title;
	}
	
	public void setDurata(long durata) {
	}
	
	@Override
	public int compareTo(OneBattle o) {
		if(this.pB.getProgress() > o.pB.getProgress())
			return -1;
		if(this.pB.getProgress() < o.pB.getProgress())
			return 1;
		return 0;
	}
	
	// 
	
}
	
	
public class BattleBox {	
	
	Base bU;
	final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	
	public BattleBox (Base bU) {
		
		this.bU = bU;
	}
	
	VBox battaglie;				// contiene tutte le battaglie
	Label bTitle;  				// titolo della VBox battaglie
	List<OneBattle> battles = new ArrayList<OneBattle>();	// collezione delle battaglie
	GridPane bGrid;				// griglia che dispone le battaglie in corso	
	HBox battleTitle;			
	
	public VBox getBattleBox(Base bU) {
		
		if(battaglie == null)
			disponiTestata();
		
		else if(battles!=null)
				Collections.sort(this.battles);
		
		return battaglie;
	}

//aggiungere una battaglia (titolo, durata) dentro alla Lista 
	
	public void addbattaglia(String battleTitle, long durata) {
		if (this.battles == null)
			this.battles = new ArrayList<OneBattle>(0);

		OneBattle battle = new OneBattle(battleTitle, durata);
		this.battles.add(battle);
		Collections.sort(this.battles);
		//disponiBattaglie();
	}

	public HBox creaOneBattle(String battleTitle, long durata) {
		
		HBox oneB = new HBox();
		VBox inOneB = new VBox();
		Label titleB = new Label(battleTitle);
		HBox hbT = new HBox();
		hbT.getChildren().add(titleB);
		ProgressBar progB = new ProgressBar(0);

		
		double progress = 0;
		
		for (int i = 0; i <= (int) durata/1000; i++) {
			progB.setProgress(battleProgress(progress, durata));
		}
		
		inOneB.getChildren().addAll(hbT, progB);
		oneB.getChildren().add(inOneB);
		
		return oneB;
	}
	
	public double battleProgress(double progress, double durata) {

		double timeScale = 1 / durata;

		try {
			Thread.sleep(1000);
			progress = progress + timeScale;

		} catch (Exception e) {
			System.out.println(e);
		}

		return progress;
	}

// dispone battaglie dentro alla griglia
	
	private void disponiTestata() {
		
		battaglie = new VBox();
		battaglie.setSpacing(0);
		battaglie.setPadding(STANDARD_PADDING);
		battaglie.setMaxWidth(390);
		
		battleTitle = new HBox();
		battleTitle.setPadding(STANDARD_PADDING);
		battleTitle.setAlignment(Pos.CENTER);
		battleTitle.setBackground(
						new Background(
						new BackgroundFill(
								Color.web("#ffffff"), 
								new CornerRadii(10), 
								new Insets(5, 5, 5, 5))));
		
		bTitle = new Label("BATTAGLIE IN CORSO");
		bTitle.setFont(Font.font(
							"Verdana", 
							FontWeight.BOLD, 
							FontPosture.REGULAR, 12));
		
		bTitle.setTextFill(Color.DARKGREEN);
		battleTitle.getChildren().add(bTitle);
		
		bGrid = new GridPane();
		bGrid.setAlignment(Pos.BASELINE_CENTER);
		bGrid.setPadding(STANDARD_PADDING);
		bGrid.setVgap(5);
		
		
		
		bGrid = disponiBattaglie();
		
		battaglie.getChildren().addAll(battleTitle, bGrid);
	}
	
	public GridPane disponiBattaglie() {

		bGrid.getChildren().clear();
		
		
		for (OneBattle o : this.battles) { // for each

			ProgressBar btt = o.pB;
			btt.setProgress(btt.getProgress() + 1 / ((double) o.durata / 1000));
			
			System.out.println(o.durata);

		}
		
		this.battles.removeIf(o -> o.pB.getProgress() >= 1);
		Collections.sort(this.battles);
		

		for (int i = 0; i < this.battles.size(); i++) {

			Label title = new Label();
			title.setTextFill(Color.web("FFFF00"));
			HBox hbB = new HBox();
			hbB.setPadding(new Insets(0, 0, 10, 0));
			
			title.setText(this.battles.get(i).getTitle());
			ProgressBar btt = this.battles.get(i).pB;

			hbB.getChildren().add(btt);
			
			int posizione=i*2;
			bGrid.add(title, 0, posizione);
			bGrid.add(hbB, 0, ++posizione);
			
			
			
			System.out.println(btt.getProgress());
		}
		
		return bGrid;
	}

	
	
	public Base getbU() {
		return bU;
	}

	public void setbU(Base bU) {
		this.bU = bU;
	}

	public VBox getBattleBox() {
		return battaglie;
	}

	public Label getTitoloBattaglie() {
		return bTitle;
	}

	public void setTitoloBattaglie(Label bTitle) {
		this.bTitle = bTitle;
	}

	public List<OneBattle> getBattlesList() {
		return battles;
	}

	public void setBattle(List<OneBattle> battles) {
		this.battles = battles;
	}

	public GridPane getBattlesGrid() {
		return bGrid;
	}

	public void setBattles(GridPane bGrid) {
		this.bGrid = bGrid;
	}

	public HBox getBattleTitle() {
		return battleTitle;
	}

	public void setBattleTitle(HBox battleTitle) {
		this.battleTitle = battleTitle;
	}

	public VBox getBattaglie() {
		return battaglie;
	}

	public void setBattaglie(VBox battaglie) {
		this.battaglie = battaglie;
	}

	public Insets getSTANDARD_PADDING() {
		return STANDARD_PADDING;
	}
}	
	