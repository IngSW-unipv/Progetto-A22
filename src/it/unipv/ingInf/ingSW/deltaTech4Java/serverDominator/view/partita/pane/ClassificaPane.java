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
			b1.setBackground(new Background(new BackgroundFill(Color.gray(i%2 * (0.9 - 0.8) + 0.8),null,null)));
			b1.setMaxWidth(150);
			
			HBox b2=new HBox();
			b2.setAlignment(Pos.CENTER_LEFT);
			b2.setBackground(new Background(new BackgroundFill(Color.gray(i%2 * (0.9 - 0.8) + 0.8),null,null)));
			b1.setMaxWidth(400);
			
			HBox b3=new HBox();
			b3.setAlignment(Pos.CENTER_LEFT);
			b3.setBackground(new Background(new BackgroundFill(Color.gray(i%2 * (0.9 - 0.8) + 0.8),null,null)));
			b1.setMaxWidth(300);
			
			Label pos = new Label();
			pos.setAlignment(Pos.BASELINE_CENTER);
			pos.setTextAlignment(TextAlignment.RIGHT);
			pos.setTextFill(Paint.valueOf("#ff0000"));
			pos.setFont(Font.font("Cambria", 13));
			pos.setText(String.valueOf(i+1)+"  ");
			pos.setOpacity(0.3);

			Label name = new Label();
			name.setText(gs.get(i).getNome());
			name.setAlignment(Pos.BASELINE_CENTER);
			name.setFont(Font.font("Cambria", 18));
			name.setTextAlignment(TextAlignment.RIGHT);
			name.setOpacity(0.8);
			
			Label punteggio = new Label();
			punteggio.setAlignment(Pos.BASELINE_CENTER);
			punteggio.setTextAlignment(TextAlignment.RIGHT);
			punteggio.setTextFill(Paint.valueOf("#000000"));
			punteggio.setFont(Font.font("Cambria", 15));
			punteggio.setText(" "+String.valueOf(gs.get(i).getPunteggio()));
			punteggio.setOpacity(0.8);
			
			b1.getChildren().add(pos);
			b2.getChildren().add(name);
			b3.getChildren().add(punteggio);
			super.add(b1, 0, i);
			super.add(b2, 1, i);
			super.add(b3, 2, i);
			
		}
	}
	
}
