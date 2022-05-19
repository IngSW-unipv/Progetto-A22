package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.prepartita;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrebattagliaView {
    private static Stage prebattagliaStage;
    private Button easyGame = new Button("EASY");
    private Button mediumGame = new Button("MEDIUM");
    private Button hardGame = new Button("HARD");
    private RadioButton r1 = new RadioButton("EASY");
    private RadioButton r2 = new RadioButton("MEDIUM");
    private RadioButton r3 = new RadioButton("HARD");

    private Label gameTitle = new Label();
    private Label easyDescription = new Label("Bots fairly easy to beat");
    private Label mediumDescription = new Label("Bots are trouble");
    private Label hardDescription = new Label("Rip and tear... until it's done");
    private MenuButton menuButton = new MenuButton("Log Out");

    //private Main mainView;
    //private MainDefinitivo mainModello;
    //private PrebattagliaController prebattagliaController;

    public Button getEasyGame() {
        return easyGame;
    }

    public Button getMediumGame() {
        return mediumGame;
    }

    public Button getHardGame() {
        return hardGame;
    }

    public Label getEasyDescription() {
        return easyDescription;
    }

    public Label getMediumDescription() {
        return mediumDescription;
    }

    public Label getHardDescription() {
        return hardDescription;
    }

    



    //public PrebattagliaView(PrebattagliaView preBattagliaView, Main mainView, MainDefinitivo mainModello) {
        //istance(new PrebattagliaView(), mainView, mainModello);
    //}
    Parent prebattaglia;
    public PrebattagliaView() {
        prebattaglia = createPrebattaglia();
    }

    private Parent createPrebattaglia() {
        VBox vPre = new VBox();
        vPre.setPrefWidth(500);
        //vPre.setPrefHeight(250);
        vPre.setPadding(new Insets(15));
        vPre.setSpacing(50);

        //vPre.getChildren().add(createTitle());
        vPre.getChildren().add(createEasyGame());
        vPre.getChildren().add(createMediumGame());
        vPre.getChildren().add(createHardGame());

        return vPre;
    }


    //private void istance(PrebattagliaView prebattagliaView, Main mainView, MainDefinitivo mainModello) {
        // TODO Auto-generated method stub
        //this.mainView = mainView;


        //this.mainModello = mainModello;

        //to define
    //}

    //UI Design
    private HBox prebattagliaView() {
        HBox vPre = new HBox();
        vPre.setPrefWidth(800);
        vPre.setPrefHeight(250);
        vPre.setPadding(new Insets(10));

        vPre.getChildren().add(createTitle());
        //vPre.getChildren().add(createMenu());
        vPre.getChildren().add(createEasyGame());
        vPre.getChildren().add(createMediumGame());
        vPre.getChildren().add(createHardGame());

        return vPre;
    }

    //private Node createMenu() { //DA SISTEMARE BENE, sfasa il button difficolta HARD
        //VBox menuBox = new VBox();
        //Menu menu = new Menu("LOGO");
        //menu.setGraphic(new ImageView(""));

        //MenuButton logoutButton = new MenuButton("Log Out");
        //menuBox.getChildren().add(logoutButton);

        //return menuBox;
    //}

    private Node createHardGame() {
        GridPane gridPane = new GridPane();
        Button hardGame = new Button("Hard");
        Label hardDescription = new Label("Rip and tear, until it's done!");

        GridPane.setRowIndex(hardGame, 0); //in scala, il numero integer la posizione 0 è la più alta
        //GridPane.setRowIndex(hardDescription, 1);
        //GridPane.setConstraints(hardDescription, 1, 0);
        GridPane.setConstraints(hardDescription, 1, 0);

        gridPane.getChildren().addAll(
                hardGame, hardDescription
        );


        return gridPane;
    }

    private Node createMediumGame() {
        GridPane gridPane = new GridPane();
        Button mediumGame = new Button("Medium");
        Label mediumDescription = new Label("Fair challenge");

        GridPane.setRowIndex(mediumGame, 0); //in scala, il numero integer la posizione 0 è la più alta
        //GridPane.setRowIndex(hardGame, 1);
        GridPane.setConstraints(mediumDescription, 1, 0);
        //GridPane.setConstraints(hardDescription, 1, 1);

        gridPane.getChildren().addAll(
                mediumGame, mediumDescription
        );
        return gridPane;
    }

    private Node createEasyGame() {
        GridPane gridPane = new GridPane();
        Button easyGame = new Button("Easy");
        Label easyDescription = new Label("I'm too young to die, taskete");

        GridPane.setRowIndex(easyGame, 0); //in scala, il numero integer la posizione 0 è la più alta
        //GridPane.setRowIndex(hardGame, 1);
        GridPane.setConstraints(easyDescription, 1, 0);
        //GridPane.setConstraints(hardDescription, 1, 1);

        gridPane.getChildren().addAll(
                easyGame, easyDescription
        );
        return gridPane;
    }

    private Node createTitle() {
        Label titleLabel = new Label("SERVER DOMINATOR Choose Difficulty");
        titleLabel.setMaxWidth(600);
        return titleLabel;
    }




    public Parent getPrebattaglia(){
        return prebattaglia;
    }

    public static Stage getStage() {
        return prebattagliaStage;
    }


    public void setStage(Stage prebattagliaStage) {
        this.prebattagliaStage = prebattagliaStage;
    }




    //public void setLabelText(Label gameTitle, String stringTxt) {
     //   gameTitle.setText(stringTxt);
    //}

    //public void setEayButton(Button easyGame) {
      //  this.easyGame = easyGame;
    //}

    //public void setMediumButton(Button mediumGame) {
        //this.mediumGame = mediumGame;
    //}

    //public void setHardButton(Button hardGame) {
      //  this.hardGame = hardGame;
    //}


    //public Stage getPrebattagliaStage() {
      //  return stagePrebattaglia;
    //}


    //public void setStage(Stage prebattagliaStage) {
      //  this.prebattagliaStage = prebattagliaStage;
    //}

}