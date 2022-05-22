package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita;

import java.io.File;
import java.util.Collections;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;
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

public class LoginView{
	
		private Label titleHolderText;
        private Label errorMessageLabel;
        private Label usernameText;
        private Label passwordText;
        private Button loginButton;
        private Button signupButton ;
        private TextField usernameTextField; 
        private PasswordField passwordTextField;
        private Stage loginStage;
        
        public LoginView(){
        	titleHolderText = new Label("ACCEDI AL GIOCO");
        	errorMessageLabel = new Label("");
        	errorMessageLabel.setStyle(" -fx-text-fill: #ff0000");
            usernameText = new Label("USERNAME");
            passwordText = new Label("PASSWORD");
            loginButton = ComponentCreator.getIstance().createButton("ACCEDI", Pos.CENTER);
            signupButton = new Button("Sign Up",null);
            signupButton.setStyle( "-fx-text-fill: #0606B8");
            usernameTextField=ComponentCreator.getIstance().textFieldreator("Username"); 
            passwordTextField=ComponentCreator.getIstance().passwordFieldCreator("password");
            loginStage=new Stage();
        }
        public void disponi() {
        	ScrollPane sp=new ScrollPane();
        	VBox v=new VBox();
        	
        	
        	HBox title=new HBox();
        	title.setAlignment(Pos.CENTER);
        	title.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), null)));
        	titleHolderText.setAlignment(Pos.CENTER);
        	titleHolderText.setFont(new Font("",20));
        	title.getChildren().addAll(titleHolderText);
        	
        	
        	
        	HBox username=new HBox();
        	username.setAlignment(Pos.CENTER);
        	usernameText.setFont(new Font(15));
        	username.getChildren().addAll(usernameText,usernameTextField);
        	username.setSpacing(30);
        	
        	HBox pasword=new HBox();
        	pasword.setAlignment(Pos.CENTER);
        	pasword.getChildren().addAll(passwordText,passwordTextField);
        	passwordText.setFont(new Font(15));
        	pasword.setSpacing(30);
        	
        	HBox accedi=new HBox();
        	accedi.setAlignment(Pos.CENTER);
        	accedi.getChildren().addAll(loginButton);        	
        	
        	HBox error=new HBox();
        	error.setAlignment(Pos.CENTER);
        	error.getChildren().addAll(errorMessageLabel);
        	
        	HBox registrazione=new HBox();
        	registrazione.setAlignment(Pos.CENTER);
        	
        	registrazione.getChildren().addAll(new Label("non sei utente?"),signupButton);
        	signupButton.setStyle("-fx-background-color: transparent;-fx-text-fill: #0606B8;");
        	signupButton.setCursor(Cursor.HAND);
        	VBox container=new VBox();
        	container.setAlignment(Pos.CENTER);
        	v.setBackground( new Background(
                    Collections.singletonList(new BackgroundFill(
                            Color.WHITE,
                            null,
                            null)),
                    Collections.singletonList(new BackgroundImage(
                            new Image(new File("resources/img/bit.png").toURI().toString(), 1000, 503, false, true),
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundRepeat.NO_REPEAT,
                            BackgroundPosition.DEFAULT,
                            new BackgroundSize(1.0, 1.0, true, true, false, false)
                    ))));
        	container.getChildren().addAll(title,username,pasword,accedi,error,registrazione);
        	container.setSpacing(15);
        	v.getChildren().addAll(title,container);
        	v.setSpacing(50);
        	sp.setContent(v);
        	sp.setFitToHeight(true);
        	sp.setFitToWidth(true);
        	loginStage.setScene(new Scene(sp));
        }

        
        
        public Stage getStage() {
    		return loginStage;
    	}
        
        public Label getTitleHolderTextHolderText(){
            return titleHolderText;
        }
        
        public Label getUsernameText() {
            return usernameText;
        }

        public Label getPasswordText(){
            return passwordText;
        }

        public Button getLoginButton() {
            return loginButton;
        }

        public TextField getUsernameTextField(){
            return usernameTextField;
        }

        public PasswordField getPasswordTextField(){
            return passwordTextField;
        }

        public Button getSignupButton(){
            return signupButton;
        }

        public void setStage(Stage loginStage) {
        	this.loginStage = loginStage;
    	}

		public Labeled getErrorMessageLabel() {
			return errorMessageLabel;
		}

}
