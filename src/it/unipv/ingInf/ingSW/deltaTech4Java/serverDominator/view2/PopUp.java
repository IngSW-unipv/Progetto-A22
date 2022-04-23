package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view2;

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
	static String password, username;

	public static String selectMalware() {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		
		Label rc = new Label("rootCrash");
		Label vr = new Label("Virus");
		
		Label rcQty = new Label("quantità rc"); // verrà sostituita con il bottone per la scelta di quantità rc
		Label vrQty = new Label("quantità vr"); // verrà sostituita con il bottone per la scelta di quantità vr

		/*
		 * TextField text1 = new TextField(); 
		 * TextField text2 = new TextField();
		 */
		Button button = new Button("Fight");
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
		stage.setTitle("Seleziona Malware");
		stage.setScene(scene);
		stage.showAndWait();

		// -> inserire le variabili di ritorno
		return username + "#" + password;
	}

	public static String development() {
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
		return username + "#" + password;
	}
	
	public static String powerUp() {
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
		return username + "#" + password;
	}
	
	public static String market() {
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
		return username + "#" + password;
	}
}
