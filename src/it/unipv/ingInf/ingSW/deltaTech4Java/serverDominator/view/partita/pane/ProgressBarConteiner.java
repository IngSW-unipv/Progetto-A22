package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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

class ProgressBarElement extends ProgressBar implements Comparable<ProgressBarElement>{

	private String bTitle;
	private long durata;
	private double timeScale;
	
	public  ProgressBarElement(String bTitle, long durata,String progressStyle) {
		super(0);
		super.setStyle(progressStyle);
		super.setBackground(
				new Background(new BackgroundFill(Color.web("#000000"), new CornerRadii(10), new Insets(10, 10, 10, 10))));
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
		this.durata=durata;
	}
	
	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public double getTimeScale() {
		return timeScale;
	}

	public void setTimeScale(double timeScale) {
		this.timeScale = timeScale;
	}

	@Override
	public int compareTo(ProgressBarElement o) {
		if(super.getProgress() > o.getProgress())
			return -1;
		if(super.getProgress() < o.getProgress())
			return 1;
		return 0;
	}
	
}
	
	
public class ProgressBarConteiner extends Pane{	
	
	public static final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	private ArrayList<Drawable> disegnabili;
	private VBox battaglie;				// contiene tutte le battaglie
	private Label elementTitle;  				// titolo della VBox battaglie
	private List<ProgressBarElement> battles;
	private GridPane bGrid;				// griglia che dispone le battaglie in corso	
	private HBox battleTitle;	
	
	
	
	public ProgressBarConteiner () {
		super();
		super.setBackground(new Background(new BackgroundFill(Color.web("#000000"), new CornerRadii(10), new Insets(0, 10, 0, 0))));
		this.battles = new ArrayList<ProgressBarElement>();
		
		this.disegnabili=new ArrayList<Drawable>();
		this.disponiTestata();
		this.initScheduler();
		
		
	}

	public VBox getBattleBox() {
		
		if(battaglie == null)
			disponiTestata();
		
		else if(battles!=null)
				Collections.sort(this.battles);
		
		return battaglie;
	}

//aggiungere una battaglia (titolo, durata) dentro alla Lista 
	
	public void addElement(String battleTitle, long durata,String progressStyle) {
		if (this.battles == null)
			this.battles = new ArrayList<ProgressBarElement>(0);

		ProgressBarElement battle = new ProgressBarElement(battleTitle, durata,progressStyle);
		this.battles.add(battle);
		Collections.sort(this.battles);
		//disponiBattaglie();
	}

	public HBox createElement(String battleTitle, long durata) {
		
		HBox oneB = new HBox();
		VBox inOneB = new VBox();
		Label titleB = new Label(battleTitle);
		HBox hbT = new HBox();
		hbT.getChildren().add(titleB);
		ProgressBar progB = new ProgressBar(0);

		
		double progress = 0;
		
		for (int i = 0; i <= (int) durata/1000; i++) {
			progB.setProgress(elementProgress(progress, durata));
		}
		
		inOneB.getChildren().addAll(hbT, progB);
		oneB.getChildren().add(inOneB);
		
		return oneB;
	}
	
	public double elementProgress(double progress, double durata) {

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
		this.battaglie.setBackground(new Background(new BackgroundFill(Color.web("#000000"), new CornerRadii(10), null)));
		battaglie.setSpacing(5);
		battaglie.setPadding(STANDARD_PADDING);
		//battaglie.setMaxWidth(390);
		
		battleTitle = new HBox();
		battleTitle.setPadding(STANDARD_PADDING);
		battleTitle.setAlignment(Pos.CENTER);
		battleTitle.setBackground(
						new Background(
						new BackgroundFill(
								Color.web("#ffffff"), 
								new CornerRadii(10), 
								new Insets(5, 5, 5, 5))));
		
		elementTitle = new Label("BATTAGLIE IN CORSO");
		elementTitle.setFont(Font.font(
							"Verdana", 
							FontWeight.BOLD, 
							FontPosture.REGULAR, 12));
		
		elementTitle.setTextFill(Color.DARKGREEN);
		battleTitle.getChildren().add(elementTitle);
		
		bGrid = new GridPane();
		bGrid.setAlignment(Pos.BASELINE_CENTER);
		bGrid.setPadding(STANDARD_PADDING);
		bGrid.setVgap(5);
		
		
		
		bGrid = disponiBattaglie();
		
		battaglie.getChildren().addAll(battleTitle, bGrid);
		super.getChildren().add(battaglie);
	}
	
	public GridPane disponiBattaglie() {

		bGrid.getChildren().clear();
		for (ProgressBarElement o : this.battles) { // for each
			ProgressBar btt = o;
			btt.setProgress(btt.getProgress() + 1 / ((double) o.getDurata() / 1000));
			if(o.getProgress()>=1)
				for(Drawable d:disegnabili)
					d.drow();
		}
		
		this.battles.removeIf(o -> o.getProgress() >= 1);
		Collections.sort(this.battles);
		
		for (int i = 0; i < this.battles.size(); i++) {

			Label title = new Label();
			title.setTextFill(Color.web("FFFF00"));
			HBox hbB = new HBox();
			hbB.setPadding(new Insets(0, 0, 10, 0));
			
			title.setText(this.battles.get(i).getTitle());
			ProgressBar btt = this.battles.get(i);

			hbB.getChildren().add(btt);
			
			int posizione=i*2;
			bGrid.add(title, 0, posizione);
			bGrid.add(hbB, 0, ++posizione);
		}
		
		return bGrid;
	}
	
	private void initScheduler(){
		Timer t = new Timer();						//creato un timer
		t.scheduleAtFixedRate(new TimerTask() {		// imposto schedulazione del task 

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						disponiBattaglie();

					}
				});


			}
		}, 0, 1000);		// la schedulazione parte da 0 e arriva fino a 1000
	}
	
	
	public int getElementCard() {
		return battles.size();
	}


	public Label getElementTitle() {
		return elementTitle;
	}

	public void setElementTitle(Label bTitle) {
		this.elementTitle = bTitle;
	}

	public List<ProgressBarElement> getBattlesList() {
		return battles;
	}

	public void setBattle(List<ProgressBarElement> battles) {
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
	public void addDrawable(Drawable d) {
		disegnabili.add(d);
	}

	public ArrayList<Drawable> getDisegnabili() {
		return disegnabili;
	}

	public void setDisegnabili(ArrayList<Drawable> disegnabili) {
		this.disegnabili = disegnabili;
	}

	public List<ProgressBarElement> getBattles() {
		return battles;
	}

	public void setBattles(List<ProgressBarElement> battles) {
		this.battles = battles;
	}

	public GridPane getbGrid() {
		return bGrid;
	}

	public void setbGrid(GridPane bGrid) {
		this.bGrid = bGrid;
	}
	public void setTitle(String titolo) {
		elementTitle.setText(titolo);
	}
	
}	
	