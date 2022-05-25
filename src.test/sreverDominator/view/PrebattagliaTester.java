package sreverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller.PartitaController;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Utente;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.LobbyView;
import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class PrebattagliaTester extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		LobbyView p=new LobbyView(new UserAccount());
		p.setUserAccount(new UserAccount("tawa",1000,1000,null,null));
		Menu menu=new Menu("SdMenu");
		MenuItem menuItem=new MenuItem("Logout");
		menuItem.setOnAction(event->{
			p.close();
			p.show();
		});
		
		menu.getItems().addAll(menuItem);
		p.getMenu().getMenus().addAll(menu);
		if(PartitaController.class.isAssignableFrom(new Utente("",5).getClass()))
			System.out.println("si");
		else System.err.println("no");
	}

	public static void main(String[] args) {
		launch(args);
	}

}

