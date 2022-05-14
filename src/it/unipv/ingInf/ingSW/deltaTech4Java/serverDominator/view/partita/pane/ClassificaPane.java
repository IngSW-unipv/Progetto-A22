package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane;

import java.util.List;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Classifica;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.pane.observers.ObserverClassifica;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ClassificaPane extends GridPane {
	
	private Classifica classifica;
	
	public ClassificaPane(Classifica classifica) {
		super();
		super.setOpacity(0.5);
		super.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		super.setPrefHeight(800);
		super.setPrefHeight(800);
		this.classifica=classifica;
		this.classifica.addPropertyChangeListener(Classifica.LISTA_PROP, new ObserverClassifica(this));
	}
	
	public void dispone() {
		List<Giocatore> gs=classifica.getLista();
		super.getChildren().clear();
		for (int i = 0; i < gs.size(); i++) {
			
			HBox b1=new HBox();
			b1.setAlignment(Pos.CENTER_LEFT);
			b1.setBackground(new Background(new BackgroundFill(Color.gray(0),null,null)));
			b1.setMaxWidth(150);
			
			HBox b2=new HBox();
			b2.setAlignment(Pos.CENTER_LEFT);
			b2.setBackground(new Background(new BackgroundFill(Color.gray(i%2 * (0.9 - 0.8) + 0.8),null,null)));
			b1.setMaxWidth(400);
			
			HBox b3=new HBox();
			b3.setAlignment(Pos.CENTER_LEFT);
			b3.setBackground(new Background(new BackgroundFill(Color.gray(i%2 * (0.9 - 0.8) + 0.8),null,null)));
			b1.setMaxWidth(300);
			
			Label pos = lblCreate(Pos.BASELINE_CENTER, TextAlignment.RIGHT, "#ff0000", 
					15, String.valueOf(i+1),0.8);

			Label name = lblCreate(Pos.BASELINE_CENTER, TextAlignment.RIGHT, "#000000", 
					20, " "+gs.get(i).getNome(),0.8);
			
			Label punteggio =lblCreate(Pos.BASELINE_CENTER, TextAlignment.RIGHT, "#000000", 
					15, " "+String.valueOf(gs.get(i).getPunteggio()),0.8);
					new Label();
			
			b1.getChildren().add(pos);
			b2.getChildren().add(name);
			b3.getChildren().add(punteggio);
			super.add(b1, 0, i);
			super.add(b2, 1, i);
			super.add(b3, 2, i);
			
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
	
}
