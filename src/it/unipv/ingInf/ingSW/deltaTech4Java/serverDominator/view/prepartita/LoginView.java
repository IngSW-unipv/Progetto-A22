package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita;

import java.io.File;
import java.util.Collections;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginView {

	private Label titleHolderText;
	private Label subTitleHolderText;
	private Label errorMessageLabel;
	private Label usernameText;
	private Label passwordText;
	private Button loginButton;
	private Button signupButton;
	private TextField usernameTextField;
	private PasswordField passwordTextField;
	private Stage loginStage;

	public LoginView() {
		this.titleHolderText = new Label("Server Dominator");
		this.titleHolderText.setStyle(" -fx-text-fill: #ffff00; -fx-font-weight: bold");
		this.subTitleHolderText = new Label("ACCESSO");
		this.subTitleHolderText.setStyle(" -fx-text-fill: #ffff00");
		this.errorMessageLabel = new Label("");
		this.errorMessageLabel.setStyle(" -fx-text-fill: #ff0000");
		this.usernameText = new Label("USERNAME");
		this.passwordText = new Label("PASSWORD");
		this.loginButton = ComponentCreator.getIstance().createButton("ACCEDI", Pos.CENTER);
		this.signupButton = new Button("Sign Up", null);
		this.signupButton.setStyle("-fx-text-fill: #0606B8");
		this.usernameTextField = ComponentCreator.getIstance().textFieldreator("Username");
		this.passwordTextField = ComponentCreator.getIstance().passwordFieldCreator("password");
		this.loginStage = new Stage();
		this.disponi();
	}

	public void disponi() {
		
		ScrollPane sp = new ScrollPane();
		
		VBox v = new VBox();
		
		HBox superTitle = new HBox();
		superTitle.setAlignment(Pos.CENTER);
		superTitle.setBackground(
				new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), new Insets(10,50,10,50))));
		superTitle.setMinHeight(60);
		superTitle.setSpacing(20);
		
		VBox titleAndSubtitle = new VBox();
		titleAndSubtitle.setAlignment(Pos.CENTER);

		HBox title = new HBox();
		title.setAlignment(Pos.BOTTOM_CENTER);
		titleHolderText.setAlignment(Pos.CENTER);
		titleHolderText.setFont(new Font("", 15));
		title.getChildren().addAll(titleHolderText);		
		
		HBox subTitle = new HBox();
		subTitle.setAlignment(Pos.TOP_CENTER);
		subTitleHolderText.setAlignment(Pos.CENTER);
		subTitleHolderText.setFont(new Font("", 10));
		subTitle.getChildren().addAll(subTitleHolderText);
		
		titleAndSubtitle.getChildren().addAll(title, subTitle);
		
		superTitle.getChildren().add(titleAndSubtitle);

		HBox credenziali = new HBox();
		credenziali.setPrefWidth(400);
		credenziali.setPrefHeight(100);
		credenziali.setAlignment(Pos.CENTER);
		
		
		VBox userAndPassword = new VBox();
		userAndPassword.setAlignment(Pos.CENTER);
		
		HBox username = new HBox();
		username.setAlignment(Pos.CENTER);
		username.setPrefHeight(40);
		username.setSpacing(10);
		usernameText.setFont(new Font(15));
		usernameTextField.setPrefSize(200, 10);
		username.getChildren().addAll(usernameText, usernameTextField);
		

		HBox password = new HBox();
		password.setAlignment(Pos.CENTER);
		password.setPrefHeight(40);
		password.setSpacing(10);
		passwordText.setFont(new Font(15));
		passwordTextField.setPrefSize(200, 10);
		password.getChildren().addAll(passwordText, passwordTextField);

		userAndPassword.getChildren().addAll(username, password);
		
		credenziali.getChildren().add(userAndPassword);

		HBox accedi = new HBox();
		accedi.setAlignment(Pos.CENTER);
		accedi.setPrefHeight(50);
		
		accedi.getChildren().addAll(loginButton);

		HBox error = new HBox();
		error.setAlignment(Pos.CENTER);
		
		error.getChildren().addAll(errorMessageLabel);

		HBox registrazione = new HBox();
		registrazione.setAlignment(Pos.CENTER);
		signupButton.setStyle("-fx-background-color: transparent;-fx-text-fill: #0606B8;");
		signupButton.setCursor(Cursor.HAND);		
		
		registrazione.getChildren().addAll(new Label("Non sei utente?"), signupButton);
	
		v.getChildren().addAll(superTitle, credenziali, accedi, error, registrazione);
		//v.setSpacing(10);
		v.setBackground(new Background(Collections.singletonList(new BackgroundFill(Color.WHITE, null, null)),
				Collections.singletonList(new BackgroundImage(
						new Image(new File("resources/img/bit.png").toURI().toString(), 1000, 503, false, true),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						new BackgroundSize(1.0, 1.0, true, true, false, false)))));
		
		sp.setContent(v);
		sp.setFitToHeight(true);
		sp.setFitToWidth(true);
		loginStage.setScene(new Scene(sp));
	}

	public Stage getStage() {
		return loginStage;
	}

	public Label getTitleHolderTextHolderText() {
		return titleHolderText;
	}

	public Label getUsernameText() {
		return usernameText;
	}

	public Label getPasswordText() {
		return passwordText;
	}

	public Button getLoginButton() {
		return loginButton;
	}

	public TextField getUsernameTextField() {
		return usernameTextField;
	}

	public PasswordField getPasswordTextField() {
		return passwordTextField;
	}

	public Button getSignupButton() {
		return signupButton;
	}

	public void setStage(Stage loginStage) {
		this.loginStage = loginStage;
	}

	public Labeled getErrorMessageLabel() {
		return errorMessageLabel;
	}

}
