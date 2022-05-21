package sreverDominator.view;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita.PrebattagliaView;
import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class PrebattagliaTester extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		PrebattagliaView p=new PrebattagliaView(new UserAccount());
		p.setUserAccount(new UserAccount("tawa",1000,1000,null,null));
		Menu menu=new Menu("SdMenu");
		MenuItem menuItem=new MenuItem("Logout");
		menuItem.setOnAction(event->{
			p.close();
			
		});
		menu.getItems().addAll(menuItem);
		p.getMenu().getMenus().addAll(menu);
	}

	public static void main(String[] args) {
		launch(args);
	}

}

