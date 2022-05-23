package sreverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LoginView;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.SignupView;
import javafx.application.Application;
import javafx.stage.Stage;

public class SignUpTester extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		SignupView v = new SignupView();
		v.disponi();
		v.getStage().show(); 
	}

	public static void main(String[] args) {
		launch(args);
	}
}
