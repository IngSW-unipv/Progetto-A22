package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView{
        //Parent getLogin;
        //View Nodes
		private Label titleHolderText = new Label("SERVER DOMINATOR Login");
        private Label errorMessageLabel = new Label("");
        private Label usernameText = new Label("username");
        private Label passwordText = new Label("password");
        private Button loginButton = new Button("Log in");
        private Button signupButton = new Button("Sign Up?"); //cambio scene
        private TextField usernameTextField; // da correggere? Dovrebbe essere TextField per scrivere username e password ma da errore se collego a presistence facade
        private PasswordField passwordTextField;
        private TextField titleTextField;
        private Stage loginStage;

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

        public TextField getTitleTextField(){
            return  titleTextField;
        }

        //view
        Parent login;

    public LoginView(){
            login = createLogin();
        }



        private VBox createLogin() {
            VBox vBox = new VBox();
            vBox.setPrefHeight(250);
            vBox.setPrefWidth(300);
            vBox.setPadding(new Insets(15));

            vBox.getChildren().add(createTitle());
            vBox.getChildren().add(createUsernamePassword());
            vBox.getChildren().add(createLoginSignupButtons());

            return vBox;
        }
        //fields dove mettere username e password
        private Node createUsernamePassword() {
            GridPane gridPane = new GridPane();
            Label usernameHolderText = new Label("Username : ");
            TextField usernameTextField = new TextField("username");
            Label passwordText = new Label("Password : ");

            PasswordField passwordTexField = new PasswordField();

            GridPane.setRowIndex(usernameHolderText, 0); //in scala, il numero integer la posizione 0 è la più alta
            GridPane.setRowIndex(passwordText, 1);
            GridPane.setConstraints(usernameTextField, 1, 0);
            GridPane.setConstraints(passwordTextField, 1, 1);

            gridPane.getChildren().addAll(              //dai setConstraints metto in posizione ordinata i vari elementi, sovrapposti nell'ordine giusto
                    usernameHolderText, passwordText,
                    usernameTextField, passwordTextField
            );

            return gridPane;
        }

        //titolo dell'interfaccia
        private Node createTitle() {
            Label titleLabel = new Label("SERVER DOMINATOR Login");
            titleLabel.setMaxWidth(600);
            return titleLabel;

        }

        private Node createLoginSignupButtons() {
            HBox hBox = new HBox(15);
            hBox.getChildren().addAll(loginButton, signupButton, errorMessageLabel);
            return hBox;
        }

        public Parent getLogin(){
            return login;
        }
        
        public Stage getStage() {
    		return loginStage;
    	}
        
        
        public void setStage(Stage loginStage) {
        	this.loginStage = loginStage;
    	}

		public Labeled getErrorMessageLabel() {
			// TODO Auto-generated method stub
			return errorMessageLabel;
		}



		

}
