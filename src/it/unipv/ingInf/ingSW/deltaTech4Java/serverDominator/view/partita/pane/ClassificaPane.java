package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane;

import java.util.List;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Classifica;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.observers.ObserverClassifica;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class ClassificaPane extends GridPane {
	
	private Classifica classifica;
	private Label titolo;
	public ClassificaPane(Classifica classifica) {
		super();
		super.setOpacity(0.5);
		super.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.titolo=new Label("CLASSIFICA");
		this.classifica=classifica;
		this.classifica.addPropertyChangeListener(Classifica.LISTA_PROP, new ObserverClassifica(this));
		dispone();
	}
	
	public void dispone() {
		List<Giocatore> gs=classifica.getLista();
	
		super.getChildren().clear();
		
		HBox btitolo=new HBox();
		btitolo.setPadding(ProgressBarConteiner.STANDARD_PADDING);
		btitolo.setAlignment(Pos.CENTER);
		btitolo.setBackground(new Background(new BackgroundFill(Color.web("#ffffff"), new CornerRadii(10), new Insets(5, 5, 5, 5))));
		
		titolo.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
		titolo.setTextFill(Color.DARKGREEN);
		btitolo.getChildren().add(titolo);
		
		btitolo.setMinHeight(30);
		btitolo.setMinWidth(90);
		super.add(btitolo,0,0,6,1);
		int min=gs.size()<=10?gs.size():10;
		for (int i = 0; i < min; i++) {
			
			HBox b1=new HBox();
			
			HBox b2=new HBox();
			b2.setBackground(new Background(new BackgroundFill(Color.gray(i%2 * (0.9 - 0.8) + 0.8),null,null)));
			
			b2.setAlignment(Pos.BASELINE_LEFT);
			
			HBox b3=new HBox();
			b3.setBackground(new Background(new BackgroundFill(Color.gray(i%2 * (0.9 - 0.8) + 0.8),null,null)));
			b3.setAlignment(Pos.BASELINE_LEFT);
			
			
			Label pos = lblCreate(Pos.BASELINE_LEFT, TextAlignment.RIGHT, "#ff0000", 
					15, String.valueOf(i+1),0.8);

			Label name = lblCreate(Pos.BASELINE_LEFT, TextAlignment.RIGHT, "#000000", 
					20, " "+gs.get(i).getNome(),0.8);
			
			Label punteggio =lblCreate(Pos.BASELINE_LEFT, TextAlignment.RIGHT, "#000000", 
					15, " "+String.valueOf(gs.get(i).getPunteggio()),0.8);
					new Label();
			
			b1.getChildren().add(pos);
			b2.getChildren().add(name);
			b3.getChildren().add(punteggio);
			super.add(b1, 0, i+2);
			super.add(b2, 1, i+2);
			super.add(b3, 2, i+2);
			HBox bbb=new HBox();
			bbb.setMinWidth(10);
			super.add(bbb, 3, i+2);
		}
	}
	
	public static Label lblCreate(Pos lableAlign,TextAlignment textAlign, String textColor,int textSize, String text, double opacity) {
			Label lbl = new Label();
			lbl.setAlignment(lableAlign);
			lbl.setTextAlignment(textAlign);
			lbl.setTextFill(Paint.valueOf(textColor));
			lbl.setFont(Font.font("Cambria", textSize));
			lbl.setText(text);
			lbl.setOpacity(opacity);
			return lbl;
	}
	
	public void setTitolo(String titiolo) {
		this.titolo.setText(titiolo);
	}

	public Classifica getClassifica() {
		return classifica;
	}

	public void setClassifica(Classifica classifica) {
		this.classifica = classifica;
	}

	public Label getTitolo() {
		return titolo;
	}

	public void setTitolo(Label titolo) {
		this.titolo = titolo;
	}
	
}
