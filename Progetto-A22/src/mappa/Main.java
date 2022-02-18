package mappa;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

    enum Action {TerrainDrawing, Move, PoliticalDrawing, BuildingDraw, Selector}
    
    Action currentAction; 	// recupera l'azione in corso (una delle enum)

    HexType currentType;	// recupera il tipo di esagono su cui sto effettuando l'azione	

    boolean mapChange = true;	// abilita cambiamenti di mappa

    public static void main(String[] args) {
        launch(args);	// fa partire la funzione start di JavaFX
    }

    public void addType(HBox controls, HexType type, Action action){	// HBOX è un contenitore orizzontale (VBOX verticale)
        Button button = new Button(type.getName()); // do al bottone il nome del type
        button.setOnMouseClicked(event -> {
            currentAction = action; // setta la Action che ho passato al metodo addType
            currentType = type;		// setta il type che ho passato al metodo addType
        });

        controls.getChildren().add(button); // aggiunge il bottone al layout grafico
    }

    public void setUpTerrains(HBox terrainControls){					// costruisce la HBOX con i tipi di terreno
        addType(terrainControls, new Terrain("Ocean", "#2273B8"), Action.TerrainDrawing);
        addType(terrainControls, new Terrain("Land", 1, "#F2CB84"), Action.TerrainDrawing);
        addType(terrainControls, new Terrain("Lake", "#8CC4DB"), Action.TerrainDrawing);
        addType(terrainControls, new Terrain("Forest", 2, "#558B29"), Action.TerrainDrawing);
        addType(terrainControls, new Terrain("Desert", 4, "#DE8D3A"), Action.TerrainDrawing);
    }

    public void setUpStates(HBox politicalControls){					// costruisce la HBOX con i tipi di Stato
        addType(politicalControls, new State("Roman Empire", "#66023C") ,Action.PoliticalDrawing);
        addType(politicalControls, new State("Han Empire", "#0031BF") ,Action.PoliticalDrawing);
        addType(politicalControls, new State("Parthian Empire", "#9ACD32") ,Action.PoliticalDrawing);
    }

    public void setUpBuildings(HBox buildingControls){					// costruisce la HBOX con i tipi di Costruzione
        addType(buildingControls, new Building("Trade Post"), Action.BuildingDraw);
        addType(buildingControls, new Building("City", Building.Shape.Circle), Action.BuildingDraw);
    }

    public void setUpControls(HBox controls){ 	// definisce la distribuzione dei buttoni nella HBOX
        controls.setSpacing(10); // spazio tra un bottone e l'altro
        
        controls.setPadding(new Insets(10)); // margini del testo dentro al bottone
    }

    public void addState(HBox politicalControls){ // menù per aggiungere uno stato
        
    	VBox vbox = new VBox();
        
        HBox name = new HBox();
        
        Label nameLabel = new Label("Name:  ");
        nameLabel.setMinSize(40, 10);
        
        nameLabel.setPadding(new Insets(5));
        
        TextField nameInput = new TextField();
        nameInput.setMinSize(80, 10);
        name.getChildren().addAll(nameLabel, nameInput);
        
        name.setPadding(new Insets(10));

        HBox color = new HBox();
        
        Label colorLabel = new Label("Color:  ");
        
        colorLabel.setPadding(new Insets(5));
        colorLabel.setMinSize(40, 10);
       
        TextField colorInput = new TextField();
        colorInput.setMinSize(80, 10);
        colorInput.setPromptText("Hexcode");
        color.getChildren().addAll(colorLabel, colorInput);
        color.setPadding(new Insets(10));

        HBox buttonBox = new HBox();
        Button submitButton = new Button("Submit");
        submitButton.setPadding(new Insets(5));
        buttonBox.getChildren().add(submitButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(name, color, buttonBox);
        vbox.setAlignment(Pos.CENTER);
        Stage stage = new Stage();
        stage.setScene(new Scene(vbox));

        submitButton.setOnMouseClicked(event -> {
            addType(politicalControls, new State(nameInput.getText(), colorInput.getText()) ,Action.PoliticalDrawing);
            stage.close();
        });

        stage.showAndWait();
    }

    public void addTerrain(HBox terrainControls){ // menù per aggiungere un terreno
        VBox vbox = new VBox();
        HBox name = new HBox();
        Label nameLabel = new Label("Name:  ");
        nameLabel.setMinSize(40, 10);
        nameLabel.setPadding(new Insets(5));
        TextField nameInput = new TextField();
        nameInput.setMinSize(80, 10);
        name.getChildren().addAll(nameLabel, nameInput);
        name.setPadding(new Insets(10));

        HBox color = new HBox();
        Label colorLabel = new Label("Color:  ");
        colorLabel.setPadding(new Insets(5));
        colorLabel.setMinSize(40, 10);
        TextField colorInput = new TextField();
        colorInput.setMinSize(80, 10);
        colorInput.setPromptText("Hexcode");
        color.getChildren().addAll(colorLabel, colorInput);
        color.setPadding(new Insets(10));

        HBox cost = new HBox();								// definisco il costo del terreno
        Label costLabel = new Label("Terrain Cost:  ");
        costLabel.setPadding(new Insets(5));
        costLabel.setMinSize(40, 10);
        TextField costInput = new TextField();
        costInput.setMinSize(80, 10);
        costInput.setPromptText("Integer");
        cost.getChildren().addAll(costLabel, costInput);
        cost.setPadding(new Insets(10));


        HBox buttonBox = new HBox();
        Button submitButton = new Button("Submit");
        submitButton.setPadding(new Insets(5));
        buttonBox.getChildren().add(submitButton);
        buttonBox.setAlignment(Pos.CENTER);	// posizione del tasto submit all'interno della HBox

        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(name, color,cost, buttonBox); // buttonBox è il Submit
        vbox.setAlignment(Pos.CENTER_RIGHT);
        Stage stage = new Stage();	// Stage è il pop-up
        stage.setScene(new Scene(vbox));	// pubblica la VBox dentro allo Stage 

        submitButton.setOnMouseClicked(event -> {
            addType(terrainControls, new Terrain(nameInput.getText(), Integer.parseInt(costInput.getText()), colorInput.getText()), Action.TerrainDrawing);
            stage.close();
        });

        stage.showAndWait();		// mostra lo Stage addTerrain
    }


    @Override
    public void start(Stage primaryStage) {	// start della costruzione grafica
        primaryStage.setTitle("Server Dominator");

        VBox holder = new VBox();
        BorderPane borderPane = new BorderPane();

        // Canvas è lo spazio in cui disegna gli esagoni
        Canvas basicCanvas = new Canvas(980, 600);
        GraphicsContext basicGC = basicCanvas.getGraphicsContext2D();

        // Dimensione del layer in cui esercito le azioni. Coincide con la dimensione della mappa
        Canvas actionCanvas = new Canvas(980, 600);
        GraphicsContext actionGC = actionCanvas.getGraphicsContext2D();

        // lo strato Political Canvas contiene gli Stati. Coincide con la dimensione della mappa
        Canvas politicalCanvas = new Canvas(980, 600);
        GraphicsContext politicalGC = politicalCanvas.getGraphicsContext2D();

        // VBox che contiene le HBox sotto alla mappa
        VBox controls = new VBox();
        HBox terrainControls = new HBox(new Label("Terrain Types"));
        HBox politicalControls = new HBox(new Label("States"));
        HBox buildingControls = new HBox(new Label("Building Types"));
        HBox actionControls = new HBox();

        controls.getChildren().addAll(terrainControls, actionControls, politicalControls, buildingControls); //aggiungo le HBox alla VBox

        setUpTerrains(terrainControls);  // aggiunge i bottoni del terreno
        setUpControls(terrainControls);	// aggiunge le spaziature, i margini, etc...

        // bottoni di azione
        Label actionTypes = new Label("Action Types");
        Button moveButton = new Button("Move");
        actionControls.getChildren().addAll(actionTypes, moveButton);
        setUpControls(actionControls);

        setUpStates(politicalControls);	// aggiunge i bottoni degli stati
        setUpControls(politicalControls);

        setUpBuildings(buildingControls); // aggiunge i bottoni delle costruzioni
        setUpControls(buildingControls);

        /*
        moveButton.setOnMouseClicked(new onMouseClick() {   // questo è scritto in Java7
        	@override
        	public void onClick(Event event) {
        		currentAction = Action.Move;
        	}
        });
        */
        
        moveButton.setOnMouseClicked(event -> {  // azione del bottone Move (sovrascrive la currentAction)
            currentAction = Action.Move;   
        });

        MapData mapData = new MapData();

        //Always Will be rendered basically - Ci si riporta sempre alla mappa di base
       
        Map basicMap = new BasicMap(mapData, basicGC);
       
        basicMap.selected = true;

        TerrainMap terrainMap = new TerrainMap(mapData, basicGC);			// mappa dei terreni
        
        HexInfoMap hexInfoMap = new HexInfoMap(mapData, basicGC);			// mapp delle azioni
        
        ActionMap actionMap = new ActionMap(mapData, actionGC);				// mappa delle azioni
        
        PoliticalMap politicalMap = new PoliticalMap(mapData, politicalGC);	// mappa dei possedimenti
        
        BuildingMap buildingMap = new BuildingMap(mapData, basicGC);		

        ArrayList<Map> mapOrder = new ArrayList<>();
        mapOrder.add(basicMap);
        mapOrder.add(terrainMap);
        mapOrder.add(buildingMap);
        mapOrder.add(actionMap);
        mapOrder.add(politicalMap);
        mapOrder.add(hexInfoMap);

        //Map Type Checkboxes
        CheckBox terrainCheck = new CheckBox("Terrain Map");
        CheckBox hexInfoCheck = new CheckBox("Hex Info Map");
        CheckBox actionCheck = new CheckBox("Action Map");
        CheckBox politicalCheck = new CheckBox("Political Map");
        CheckBox buildingCheck = new CheckBox("Building Map");

        //MenuBar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem openItem = new MenuItem("Open");
        fileMenu.getItems().addAll(saveItem, openItem);

        Menu stateMenu = new Menu("State");
        MenuItem addStateItem = new MenuItem("Add");
        addStateItem.setOnAction(event -> addState(politicalControls));
        stateMenu.getItems().add(addStateItem);

        Menu terrainMenu = new Menu("Terrain");
        MenuItem addTerrainItem = new MenuItem("Add");
        addTerrainItem.setOnAction(event -> addTerrain(terrainControls));
        terrainMenu.getItems().add(addTerrainItem);

        menuBar.getMenus().addAll(fileMenu, stateMenu, terrainMenu);

        //Adding Canvases to BorderPane
        Pane pane = new Pane();
        pane.getChildren().addAll(basicCanvas, actionCanvas, politicalCanvas);

        //Adding Checkboxes to a VBox on Right
        VBox mapTypesBox = new VBox(terrainCheck, actionCheck, hexInfoCheck, politicalCheck, buildingCheck);
        mapTypesBox.setSpacing(20);
        mapTypesBox.setPadding(new Insets(20));

        //Setting BorderPane
        borderPane.setTop(menuBar);
        borderPane.setCenter(pane);
        borderPane.setBottom(controls);
        borderPane.setRight(mapTypesBox);

        //Adding MenuBar and BorderPane to a VBox Holder
        holder.getChildren().addAll(menuBar, borderPane);

        //Map Checkbox Listeners
        terrainCheck.selectedProperty().addListener((ov, old, newValue) -> {
            terrainMap.selected = newValue;
            mapChange = true;
        });

        actionCheck.selectedProperty().addListener((ov, old, newValue) -> {
            actionMap.selected = newValue;
            mapChange = true;
        });

        hexInfoCheck.selectedProperty().addListener((ov, old, newValue) -> {
            hexInfoMap.selected = newValue;
            mapChange = true;
        });

        politicalCheck.selectedProperty().addListener((ov, old, newValue) -> {
            politicalMap.selected = newValue;
            mapChange = true;
        });

        buildingCheck.selectedProperty().addListener((ov, old, newValue) -> {
            buildingMap.selected = newValue;
            mapChange = true;
        });

        FileChooser fileChooser = new FileChooser();

        //Save Data
        saveItem.setOnAction(event -> {
            File file = fileChooser.showSaveDialog(primaryStage);
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(mapData.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Loading Data
        openItem.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            try {
                ObjectInputStream out = new ObjectInputStream(new FileInputStream(file));
                mapData.setData((HashMap<Hexagon, HexData>) out.readObject());
                mapChange = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



        for(HashMap.Entry<Hexagon, HexData> entry : mapData.data.entrySet()){
            entry.getValue().setTerrain(new Terrain("Ocean", "#2273B8"));
        }

        pane.setOnMouseClicked(event -> {
            Hexagon est = mapData.pixelToHex(new Point(event.getX(), event.getY()));

            if(currentAction != null){
                switch (currentAction){
                    case TerrainDrawing:
                        mapData.getHexData(est).setTerrain((Terrain) currentType);
                        break;
                    case Move:
                        actionMap.setMovable(est, 4);
                        break;
                    case PoliticalDrawing:
                        mapData.getHexData(est).setState((State) currentType);
                        break;
                    case BuildingDraw:
                        mapData.getHexData(est).setBuilding((Building) currentType);
                        System.out.println(est);
                        System.out.println(mapData.getHexData(est));
                        break;
                }
                mapChange = true;

            }
        });

        primaryStage.setScene(new Scene(holder));
        primaryStage.show();

        new AnimationTimer(){
            @Override
            public void handle(long now) {
                if(mapChange){
                    basicGC.clearRect(0,0, basicCanvas.getWidth(), basicCanvas.getHeight());
                    actionGC.clearRect(0,0, actionCanvas.getWidth(), actionCanvas.getHeight());
                    politicalGC.clearRect(0,0, politicalCanvas.getWidth(), politicalCanvas.getHeight());

                    for(Map map : mapOrder){
                        if(map.selected){
                            map.drawMap();
                        }
                    }
                    mapChange = false;
                }
            }
        }.start();
    }
}