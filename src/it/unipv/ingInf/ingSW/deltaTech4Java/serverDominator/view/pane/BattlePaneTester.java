package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.pane;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.Base;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BattlePaneTester  extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		BattlePane p=new BattlePane(new Base());
		p.addbattaglia("battaglia2", 6000);
		p.addbattaglia("battaglia3", 5000);
		p.addbattaglia("battaglia1", 7000);
		p.getBattle().get(0).setProgress(1000);
		System.out.println(p.getBattle().get(0).getProgress());
        Pane pane=p.getBattlePain(new Base());
        Scene s=new Scene(pane);
        primaryStage.setScene(s);
        primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}

}
