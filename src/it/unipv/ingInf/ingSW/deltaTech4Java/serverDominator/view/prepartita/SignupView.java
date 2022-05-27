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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignupView {

    private Label titleHolderText;
    private Label subTitleHolderText;
    private Label errorMessageSignupLabel;
    private Label usernameSignup;
    private Label passwordSignup;
    private Label email;
    private Button loginButtonChange;
    private Button signupButtonLegit;
    private TextField usernameTextFieldSignup; 
    private PasswordField passwordTextFieldSignup;
    private TextField emailTextField;
    private Stage signupStage;
    

    public SignupView(){
    	titleHolderText = new Label("Server Dominator");
        subTitleHolderText = new Label("SIGNUP");
        errorMessageSignupLabel = new Label();
        usernameSignup = new Label("Username");
        passwordSignup = new Label("Password");
        loginButtonChange = new Button("Log in"); 
        signupButtonLegit = ComponentCreator.getIstance().createButton("Sign Up", Pos.CENTER);
        email=new Label("Email\t");
        usernameTextFieldSignup=ComponentCreator.getIstance().textFieldreator("USERNAME");
        passwordTextFieldSignup=ComponentCreator.getIstance().passwordFieldCreator("PASSWORD");
        emailTextField=ComponentCreator.getIstance().textFieldreator("E-MAIL");
        signupStage = new Stage();
        this.titleHolderText.setStyle(" -fx-text-fill: #ffff00; -fx-font-weight: bold");
        this.subTitleHolderText.setStyle(" -fx-text-fill: #ffff00");
        disponi();
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
		usernameSignup.setFont(new Font(15));
		usernameTextFieldSignup.setPrefSize(200, 10);
		username.getChildren().addAll(usernameSignup, usernameTextFieldSignup);
		

		HBox password = new HBox();
		password.setAlignment(Pos.CENTER);
		password.setPrefHeight(40);
		password.setSpacing(10);
		passwordSignup.setFont(new Font(15));
		passwordTextFieldSignup.setPrefSize(200, 10);
		password.getChildren().addAll(passwordSignup, passwordTextFieldSignup);

		
		HBox email = new HBox();
		email.setAlignment(Pos.CENTER);
		email.setPrefHeight(40);
		email.setSpacing(10);
		this.email.setFont(new Font(15));
		emailTextField.setPrefSize(200, 10);
		email.getChildren().addAll(this.email, emailTextField);
		HBox.setHgrow(this.email, Priority.ALWAYS);
		HBox.setHgrow(emailTextField, Priority.ALWAYS);
		
		userAndPassword.getChildren().addAll(username,email, password);
		
		credenziali.getChildren().add(userAndPassword);

		HBox accedi = new HBox();
		accedi.setAlignment(Pos.CENTER);
		accedi.setPrefHeight(50);
		
		accedi.getChildren().addAll(signupButtonLegit);

		HBox error = new HBox();
		error.setAlignment(Pos.CENTER);
		
		error.getChildren().addAll(errorMessageSignupLabel);

		HBox registrazione = new HBox();
		registrazione.setAlignment(Pos.CENTER);
		loginButtonChange.setStyle("-fx-background-color: transparent;-fx-text-fill: #0606B8;");
		loginButtonChange.setCursor(Cursor.HAND);		
		
		registrazione.getChildren().addAll(new Label("Sei gia utente?"), loginButtonChange);
	
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
		signupStage.setScene(new Scene(sp));
	}
    
    public Stage getStage() {
		return signupStage;
	}
    
    public Label getTitleHolderText(){
        return titleHolderText;
    }

    public Label getUsernameSignup(){
        return usernameSignup;
    }

    public Label getPasswordSignup(){
        return passwordSignup;
    }


    public Button getLoginButtonChange(){
        return loginButtonChange;
    }

    public Button getSignupButtonLegit(){
        return signupButtonLegit;
    }
    
    public TextField getUsernameTextFieldSignup() {
    	return usernameTextFieldSignup;
    }
    
    public PasswordField getPasswordTextFieldSignup() {
    	return passwordTextFieldSignup;
    }
    
    public TextField getEmailTextField() {
    	return emailTextField;
    }

	public Label getErrorMessageLabel() {
		return errorMessageSignupLabel;
	}

	public Label getSubTitleHolderText() {
		return subTitleHolderText;
	}

	public void setSubTitleHolderText(Label subTitleHolderText) {
		this.subTitleHolderText = subTitleHolderText;
	}

	public Label getErrorMessageSignupLabel() {
		return errorMessageSignupLabel;
	}

	public void setErrorMessageSignupLabel(Label errorMessageSignupLabel) {
		this.errorMessageSignupLabel = errorMessageSignupLabel;
	}

	public Stage getSignupStage() {
		return signupStage;
	}

	public void setSignupStage(Stage signupStage) {
		this.signupStage = signupStage;
	}

	public void setTitleHolderText(Label titleHolderText) {
		this.titleHolderText = titleHolderText;
	}

	public void setUsernameSignup(Label usernameSignup) {
		this.usernameSignup = usernameSignup;
	}

	public void setPasswordSignup(Label passwordSignup) {
		this.passwordSignup = passwordSignup;
	}

	public void setLoginButtonChange(Button loginButtonChange) {
		this.loginButtonChange = loginButtonChange;
	}

	public void setSignupButtonLegit(Button signupButtonLegit) {
		this.signupButtonLegit = signupButtonLegit;
	}

	public void setUsernameTextFieldSignup(TextField usernameTextFieldSignup) {
		this.usernameTextFieldSignup = usernameTextFieldSignup;
	}

	public void setPasswordTextFieldSignup(PasswordField passwordTextFieldSignup) {
		this.passwordTextFieldSignup = passwordTextFieldSignup;
	}

	public void setEmailTextField(TextField emailTextField) {
		this.emailTextField = emailTextField;
	}

}