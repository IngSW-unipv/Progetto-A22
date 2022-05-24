package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita;

import java.io.File;
import java.util.Collections;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignupView {

    //view nodes
    private Label titleHolderText = new Label("Server Dominator");
    private Label subTitleHolderText = new Label("SIGNUP");
	//subTitleHolderText.setStyle("-fx-text-fill: #ffff00");
    private Label errorMessageSignupLabel = new Label();
    private Label usernameSignup = new Label("username");
    private Label passwordSignup = new Label("password");
    private Label ipSignup = new Label("ip");
    Label portSignup = new Label("port");
    Button loginButtonChange = new Button("Log in"); //cambio scene
    Button signupButtonLegit = new Button("Sign Up");
    private TextField usernameTextFieldSignup; // da correggere? Dovrebbe essere TextField per scrivere username e password ma da errore se collego a presistence facade
    private PasswordField passwordTextFieldSignup;
    private TextField emailTextField;
    TextField titleTextField;
    public Stage signupStage = new Stage();
    
public void disponi() {
		
		ScrollPane sp = new ScrollPane();
		
		VBox v = new VBox();

		HBox error = new HBox();
		error.setAlignment(Pos.CENTER);
		error.getChildren().addAll(getErrorMessageLabel());

		v = createSignup();

		sp.setContent(v);
		sp.setFitToHeight(true);
		sp.setFitToWidth(true);
		signupStage.setScene(new Scene(sp));
	}

    private Scene signupView; //view
   
    Parent signup;

    public SignupView(){
        signup = createSignup();
        disponi();
    }

    private VBox createSignup() {
        VBox xBox =  new VBox();
        xBox.setPrefHeight(350);
        xBox.setPrefWidth(300);
        xBox.setPadding(new Insets(15));
        
        xBox.setBackground(new Background(Collections.singletonList(new BackgroundFill(Color.WHITE, null, null)),
				Collections.singletonList(new BackgroundImage(
						new Image(new File("resources/img/bit.png").toURI().toString(), 1000, 503, false, true),
						BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
						new BackgroundSize(1.0, 1.0, true, true, false, false)))));
		

        xBox.getChildren().add(createSuperTitle());
        xBox.getChildren().add(createUser());
        xBox.getChildren().add(createButtons());
        
        return xBox;
    }

    private Node createTitle() {
        Label signupTitle = new Label("Sign up");
        return signupTitle;
    }
    
    private HBox createSuperTitle() {
    	
    	HBox superTitle = new HBox();
		superTitle.setAlignment(Pos.CENTER);
		superTitle.setBackground(
				new Background(new BackgroundFill(Color.BLACK, new CornerRadii(10), new Insets(30,25,30,25))));
		superTitle.setMinHeight(100);
		
		
		VBox titleAndSubtitle = new VBox();
		titleAndSubtitle.setAlignment(Pos.CENTER);

		HBox title = new HBox();
		title.setAlignment(Pos.BOTTOM_CENTER);
		titleHolderText.setAlignment(Pos.CENTER);
		titleHolderText.setFont(new Font("", 15));
		titleHolderText.setStyle(" -fx-text-fill: #ffff00; -fx-font-weight: bold");
		title.getChildren().addAll(titleHolderText);		
		
		HBox subTitle = new HBox();
		subTitle.setAlignment(Pos.TOP_CENTER);
		subTitleHolderText.setAlignment(Pos.CENTER);
		subTitleHolderText.setFont(new Font("", 10));
		subTitleHolderText.setStyle(" -fx-text-fill: #ffff00");
		subTitle.getChildren().addAll(subTitleHolderText);
		
		titleAndSubtitle.getChildren().addAll(title, subTitle);
		
		superTitle.getChildren().add(titleAndSubtitle);
    	
    	return superTitle;
    }
      
	private HBox createUser() {

		HBox x = new HBox();
		x.setAlignment(Pos.CENTER);

		GridPane gridPanex = new GridPane();
		gridPanex.setHgap(10);
		gridPanex.setVgap(10);
		
		Label usernameSignup = new Label("username");
		usernameSignup.setStyle(" -fx-text-fill: #000000; -fx-font-weight: bold");
		usernameSignup.setFont(new Font("", 13));
		Label passwordSignup = new Label("password");
		passwordSignup.setStyle(" -fx-text-fill: #000000; -fx-font-weight: bold");
		passwordSignup.setFont(new Font("", 13));
		Label ipSignup = new Label("email");
		ipSignup.setStyle(" -fx-text-fill: #000000; -fx-font-weight: bold");
		ipSignup.setFont(new Font("", 13));
		// Label portSignup = new Label("port");
		
		TextField usernameTextSignup = new TextField("username");
		TextField passwordTextSignup = new TextField("password");
		TextField ipTextSignup = new TextField("email ");
		// TextField portTextSignup = new TextField("port");

		GridPane.setRowIndex(usernameSignup, 0); // in scala, il numero integer la posizione 0 è la più alta
		GridPane.setRowIndex(passwordSignup, 1);
		GridPane.setRowIndex(ipSignup, 2);
		// GridPane.setRowIndex(portSignup, 3);
		
		GridPane.setConstraints(usernameTextSignup, 1, 0);
		GridPane.setConstraints(passwordTextSignup, 1, 1);
		GridPane.setConstraints(ipTextSignup, 1, 2);
		// GridPane.setConstraints(portTextSignup, 1, 3);

		gridPanex.getChildren().addAll(
				usernameSignup, passwordSignup, ipSignup, 
				usernameTextSignup, passwordTextSignup, ipTextSignup
		);

		x.getChildren().add(gridPanex);

		return x;
	}
   
	private HBox createButtons() {
        HBox yBox = new HBox(15);
        yBox.setSpacing(10);
        yBox.setAlignment(Pos.CENTER);
        yBox.setPrefHeight(80);
        
        loginButtonChange.setStyle(
        	"-fx-background-color: #3c7fb1, linear-gradient(#fafdfe, #e8f5fc),linear-gradient(#eaf6fd 0%, #d9f0fc 49%, #bee6fd 50%, #a7d9f5 100%);"
          + "-fx-background-insets: 0,1,2;"
          + "-fx-background-radius: 3,2,1;"
          + "-fx-padding: 3 30 3 30;"
          + "-fx-text-fill: black;"
          + "-fx-font-size: 14px;");
       
        signupButtonLegit.setStyle(
        		"-fx-background-color: linear-gradient(#f0ff35, #a9ff00), radial-gradient(center 50% -40%, radius 200%, #b8ee36 45%, #80c800 50%);"
        	  + "-fx-background-radius: 3,2,1;"  
        	  + "-fx-background-insets: 0,1,2;"
        	  + "-fx-padding: 3 30 3 30;"
        	  + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );"  
        	  + "-fx-text-fill: #395306;"
        	  + "-fx-font-size: 14px;");
        
        yBox.getChildren().addAll(loginButtonChange, signupButtonLegit);
        return yBox; 
	}
        
        public void setStage(Stage signupStage) {
		this.signupStage = signupStage;
	}
    
    public Parent getSignup(){
        return signup;
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




}