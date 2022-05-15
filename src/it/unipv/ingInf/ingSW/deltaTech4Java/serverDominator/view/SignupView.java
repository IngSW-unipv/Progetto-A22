package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignupView {

    //view nodes
    Label titleHolderText = new Label("SERVER DOMINATOR singup");
    static Label errorMessageSignupLabel = new Label();
    Label usernameSignup = new Label("username");
    Label passwordSignup = new Label("password");
    Label ipSignup = new Label("ip");
    Label portSignup = new Label("port");
    Button loginButtonChange = new Button("Log in"); //cambio scene
    Button signupButtonLegit = new Button("Sign Up");
    TextField usernameTextFieldSignup; // da correggere? Dovrebbe essere TextField per scrivere username e password ma da errore se collego a presistence facade
    TextField passwordTextFieldSignup;
    TextField titleTextField;
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

    public Label getPortSignup() {
        return portSignup;
    }

    public Button getLoginButtonChange(){
        return loginButtonChange;
    }

    public Button getSignupButtonLegit(){
        return signupButtonLegit;
    }

    private Scene signupView;
    //view
    Parent signup;

    public SignupView(){
        signup = createSignup();
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
        Label ipSignup = new Label("ip");
        Label portSignup = new Label("port");
        TextField usernameTextSignup = new TextField("username");
        TextField passwordTextSignup = new TextField("password");
        TextField ipTextSignup = new TextField("ip ");
        TextField portTextSignup = new TextField("port");

        GridPane.setRowIndex(usernameSignup, 0); //in scala, il numero integer la posizione 0 è la più alta
        GridPane.setRowIndex(passwordSignup, 1);
        GridPane.setRowIndex(ipSignup, 2);
        GridPane.setRowIndex(portSignup, 3);
        GridPane.setConstraints(usernameTextSignup, 1, 0);
        GridPane.setConstraints(passwordTextSignup, 1, 1);
        GridPane.setConstraints(ipTextSignup, 1,2);
        GridPane.setConstraints(portTextSignup, 1, 3);

        gridPanex.getChildren().addAll(
                usernameSignup, passwordSignup, ipSignup, portSignup,
                usernameTextSignup, passwordTextSignup, ipTextSignup, portTextSignup
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



	public static Labeled getErrorMessageLabel() {
		// TODO Auto-generated method stub
		return errorMessageSignupLabel;
	}




}
