package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.pane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

class Battaglia implements Comparable<Battaglia>{

	private ProgressBar progressBar;
	private String title;
	
	public Battaglia(ProgressBar progressBar, String title) {
		super();
		this.progressBar = progressBar;
		this.title = title;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(Battaglia o) {
		if(progressBar.getProgress()>o.getProgressBar().getProgress())
			return -1;
		if(progressBar.getProgress()<o.getProgressBar().getProgress())
			return 1;
		return 0;
	}
	
}

public class BattlePane {

	
	Base bU = new Base();
	final Insets STANDARD_PADDING = new Insets(10,10,10,10);
	
	public BattlePane(Base bU) {
	
		this.bU = bU;
	
	}
	Pane battlePane;
	
	Label titoloBattaglie;
	
	List<Battaglia> battle;
	
	GridPane battles;
	
	HBox battleTitle;
	
	VBox battaglie;
	
	
	
	public Pane getBattlePain(Base bU) {
				addbattaglia("prova", 3000);
				addbattaglia("prova1", 3000);
		return battlePane;
	}
	
	//aggiungere una battaglia di durata 'millis' millisecondi
	public void addbattaglia(String titoloProgressBar, long millis) {
		if(this.battle==null) {
			this.battle=new ArrayList<Battaglia>();
		}
		/*-----Darivedere perchè non funziona la progress bar*/
		ProgressBar battle = new ProgressBar();
		Battaglia battaglia=new Battaglia(battle, titoloProgressBar);
		Timeline timeline = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(battaglia.getProgressBar().progressProperty(), 0)),
				new KeyFrame(Duration.millis(5), e-> {
					battaglia.getProgressBar().setProgress((5/millis)+battaglia.getProgressBar().getProgress());
					}, new KeyValue(battaglia.getProgressBar().progressProperty(), 1))    
			    );
			    timeline.setCycleCount(Animation.INDEFINITE);
			    timeline.play();
		
		this.battle.add(battaglia);
		Collections.sort(this.battle);
		rifai();
	}
	private void rifai() {

		battlePane = new Pane();

		battlePane.setBackground(
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(0, 10, 0, 0))));
		battlePane.setPadding(STANDARD_PADDING);
		
		battaglie = new VBox();
		battaglie.setSpacing(0);
		battaglie.setPadding(STANDARD_PADDING);
		battaglie.setMaxWidth(390);
		
		battleTitle = new HBox();
		battleTitle.setPadding(STANDARD_PADDING);
		battleTitle.setAlignment(Pos.CENTER);
		battleTitle.setBackground(
				new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(5, 5, 5, 5))));
		titoloBattaglie = new Label("BATTAGLIE IN CORSO");
		titoloBattaglie.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		titoloBattaglie.setTextFill(Color.DARKGREEN);
		
		battleTitle.getChildren().add(titoloBattaglie);
		
		
		battles = new GridPane();
		battles.setAlignment(Pos.BASELINE_CENTER);
		battles.setPadding(STANDARD_PADDING);
		battles.setVgap(5);
		
		for(int i=0;i<this.battle.size();i++) {
			Label title = new Label(this.battle.get(i).getTitle());
			title.setTextFill(Color.web("FFFF00"));
			HBox hbB3 = new HBox();
			hbB3.setPadding(new Insets(0,0,10,0));
			ProgressBar btt = new ProgressBar(0.25);
			hbB3.getChildren().add(btt);
			int posizione=i*2;
			battles.add(title, 0, posizione);
			battles.add(hbB3, 0, ++posizione);
		}
		battaglie.getChildren().addAll(battleTitle, battles);
		battlePane.getChildren().add(battaglie);
		battlePane.setBackground(new Background(new BackgroundFill(Color.web("#262626"), new CornerRadii(10), new Insets(0, 10, 0, 0))));
	}
	
}

