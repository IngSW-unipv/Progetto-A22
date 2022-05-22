package sreverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class LoginTester extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		LoginView v=new LoginView();
		v.disponi();
		v.getStage().show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
