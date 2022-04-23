package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;



import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.*;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2.MapData.Layout;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUp {

	// -> inserire le variabili di ritorno
	int useRootcrash, useVirus;
	
	
	
	//intint qVs = get

	public static void selectMalware() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		
		Base base = new Base();
		int qRc = base.getQnt_rootcrash();
		int qVr = base.getQnt_virus();
		int useRc, useVr;
		
		Label rc = new Label("Rootcrash: ");
		Label vr = new Label("Virus:     ");
		Label rcQty = new Label(String.valueOf(qRc)); // verrà sostituita con il bottone per la scelta di quantità rc
		Label vrQty = new Label(String.valueOf(qVr)); // verrà sostituita con il bottone per la scelta di quantità vr
		Label use1 = new Label("Use: ");
		Label use2 = new Label("Use: ");
		TextField useRcTf = new TextField(); useRcTf.setMaxWidth(50);
		TextField useVrTf = new TextField(); useVrTf.setMaxWidth(50);
		
		Button button = new Button("Fight");
		button.setOnAction(e -> {
			stage.close();
		});
	
		GridPane layout = new GridPane();

		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(5);
		layout.setHgap(5);
		
		layout.add(rc, 0, 0);
		layout.add(rcQty, 1, 0);
		layout.add(use1, 2, 0);
		layout.add(useRcTf, 3, 0);
		layout.add(vr, 0, 1);
		layout.add(vrQty, 1, 1);
		layout.add(use2, 2, 1);
		layout.add(useVrTf, 3, 1);

		layout.add(button, 1, 3);								
		
		Scene scene = new Scene(layout, 220, 150);
		stage.setTitle("Seleziona Malware");
		stage.setScene(scene);						// va sempre assegnata una scena allo Stage
		stage.showAndWait();

		// -> inserire le variabili di ritorno
		//useRc = Integer.parseInt(useRcTf.getText());
		//useVr = Integer.parseInt(useVrTf.getText());
	}

	public static void development() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);

		/*
		 * TextField text1 = new TextField(); TextField text2 = new TextField();
		 */
		Button button = new Button("Submit");
		button.setOnAction(e -> {
			// username = text1.getText();
			// password = text2.getText();
			stage.close();
		});
		/*
		 * Label label1 = new Label(" Login Network System "); Label label2 = new
		 * Label("Username:"); Label label3 = new Label("Password:");
		 */
		GridPane layout = new GridPane();

		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(5);
		layout.setHgap(5);

		/*
		 * layout.add(text1, 1,1); layout.add(text2, 1,2);
		 */layout.add(button, 1, 3);/*
									 * layout.add(label1, 1,0); layout.add(label2, 0,1); layout.add(label3, 0,2);
									 */
		Scene scene = new Scene(layout, 250, 150);
		stage.setTitle("Development");
		stage.setScene(scene);
		stage.showAndWait();

		// -> inserire le variabili di ritorno
		// TODO
	}
	
	public static void powerUp() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);

		/*
		 * TextField text1 = new TextField(); TextField text2 = new TextField();
		 */
		Button button = new Button("Submit");
		button.setOnAction(e -> {
			// username = text1.getText();
			// password = text2.getText();
			stage.close();
		});
		/*
		 * Label label1 = new Label(" Login Network System "); Label label2 = new
		 * Label("Username:"); Label label3 = new Label("Password:");
		 */
		GridPane layout = new GridPane();

		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(5);
		layout.setHgap(5);

		/*
		 * layout.add(text1, 1,1); layout.add(text2, 1,2);
		 */layout.add(button, 1, 3);/*
									 * layout.add(label1, 1,0); layout.add(label2, 0,1); layout.add(label3, 0,2);
									 */
		Scene scene = new Scene(layout, 250, 150);
		stage.setTitle("Power up");
		stage.setScene(scene);
		stage.showAndWait();

		// -> inserire le variabili di ritorno
		// TODO
	}
	
	public static void market() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);

		/*
		 * TextField text1 = new TextField(); TextField text2 = new TextField();
		 */
		Button button = new Button("Submit");
		button.setOnAction(e -> {
			// username = text1.getText();
			// password = text2.getText();
			stage.close();
		});
		/*
		 * Label label1 = new Label(" Login Network System "); Label label2 = new
		 * Label("Username:"); Label label3 = new Label("Password:");
		 */
		GridPane layout = new GridPane();

		layout.setPadding(new Insets(10, 10, 10, 10));
		layout.setVgap(5);
		layout.setHgap(5);

		/*
		 * layout.add(text1, 1,1); layout.add(text2, 1,2);
		 */layout.add(button, 1, 3);/*
									 * layout.add(label1, 1,0); layout.add(label2, 0,1); layout.add(label3, 0,2);
									 */
		Scene scene = new Scene(layout, 250, 150);
		stage.setTitle("Market");
		stage.setScene(scene);
		stage.showAndWait();

		// -> inserire le variabili di ritorno
		// TODO
	}
}
