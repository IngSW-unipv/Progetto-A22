package sreverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.PrebattagliaView;
import javafx.application.Application;
import javafx.stage.Stage;

public class PrebattagliaTester extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		PrebattagliaView p=new PrebattagliaView(new UserAccount("Tawa", 100, 100, null, null));
	}

	public static void main(String[] args) {
		launch(args);
	}

}

