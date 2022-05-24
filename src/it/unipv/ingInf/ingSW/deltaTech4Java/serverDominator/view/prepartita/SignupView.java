package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.util.ComponentCreator;

public class SignupView {

    //view nodes
    private Label titleHolderText = new Label("SERVER DOMINATOR singup");
    private Label subTitleHolderText;
    private Label errorMessageSignupLabel = new Label();
    private Label usernameSignup = new Label("username");
    private Label passwordSignup = new Label("password");
    private Label ipSignup = new Label("ip");
    private Label portSignup = new Label("port");
    private Button loginButtonChange = new Button("Log in"); //cambio scene
    private Button signupButtonLegit = new Button("Sign Up");
    private TextField usernameTextFieldSignup; // da correggere? Dovrebbe essere TextField per scrivere username e password ma da errore se collego a presistence facade
    private PasswordField passwordTextFieldSignup;
    private TextField emailTextField;
    private TextField titleTextField;
    private Stage signupStage;

    
    
    public Label getTitleHolderText(){
        return titleHolderText;
    }

    public Label getUsernameSignup(){
        return usernameSignup;
    }

    public Label getPasswordSignup(){
        return passwordSignup;
    }

    public Label getIpSignup() {
        return ipSignup;
    }
    
    public Label getSubTitleHolderText() {
    	return subTitleHolderText;
    }

    public Label getPortSignup() {
        return portSignup;
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
    

    private Scene signupView;
    //view
    Parent signup;

    public SignupView(){
        signup = createSignup();
        titleHolderText.setStyle(" -fx-text-fill: #ffff00; -fx-font-weight: bold");
        subTitleHolderText = new Label("REGISTRAZIONE");
        subTitleHolderText.setStyle(" -fx-text-fill: #ffff00");
		errorMessageSignupLabel = new Label("");
		errorMessageSignupLabel.setStyle(" -fx-text-fill: #ff0000");
		signupButtonLegit = ComponentCreator.getIstance().createButton("REGISTRAZIONE", Pos.CENTER);
		loginButtonChange = new Button("Login", null);
		loginButtonChange.setStyle("-fx-text-fill: #0606B8");
		usernameTextFieldSignup = ComponentCreator.getIstance().textFieldreator("Username");
		passwordTextFieldSignup = ComponentCreator.getIstance().passwordFieldCreator("password");
		
    }

    private VBox createSignup() {
        VBox xBox =  new VBox();
        xBox.setPrefHeight(350);
        xBox.setPrefWidth(300);
        xBox.setPadding(new Insets(15));

        xBox.getChildren().add(createTitle());
        xBox.getChildren().add(createUser());
        xBox.getChildren().add(createButtons());
        return xBox;
    }

    private Node createButtons() {
        HBox yBox = new HBox(15);
        yBox.getChildren().addAll(loginButtonChange, signupButtonLegit);
        return yBox;
    }

    private Node createUser() {
        GridPane gridPanex = new GridPane();
        Label usernameSignup = new Label("username");
        Label passwordSignup = new Label("password");
        Label ipSignup = new Label("email");
        //Label portSignup = new Label("port");
        TextField usernameTextSignup = new TextField("username");
        TextField passwordTextSignup = new TextField("password");
        TextField ipTextSignup = new TextField("email ");
        //TextField portTextSignup = new TextField("port");

        GridPane.setRowIndex(usernameSignup, 0); //in scala, il numero integer la posizione 0 è la più alta
        GridPane.setRowIndex(passwordSignup, 1);
        GridPane.setRowIndex(ipSignup, 2);
        //GridPane.setRowIndex(portSignup, 3);
        GridPane.setConstraints(usernameTextSignup, 1, 0);
        GridPane.setConstraints(passwordTextSignup, 1, 1);
        GridPane.setConstraints(ipTextSignup, 1,2);
        //GridPane.setConstraints(portTextSignup, 1, 3);

        gridPanex.getChildren().addAll(
                usernameSignup, passwordSignup, ipSignup, portSignup,
                usernameTextSignup, passwordTextSignup, ipTextSignup //portTextSignup
        );

        return gridPanex;
    }

    private Node createTitle() {
        Label signupTitle = new Label("Sign up");
        return signupTitle;
    }

    public Parent getSignup(){
        return signup;
    }

    public Stage getStage() {
		return signupStage;
	}
    
    
    public void setStage(Stage signupStage) {
		this.signupStage = signupStage;
	}



	public Label getErrorMessageLabel() {
		return errorMessageSignupLabel;
	}




}
