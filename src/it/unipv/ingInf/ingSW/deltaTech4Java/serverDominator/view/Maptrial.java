package com.example.fxmltojavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class Maptrial extends Application {

    private final Canvas basicCanvas = new Canvas(980, 600);
    private final GraphicsContext basicGC = basicCanvas.getGraphicsContext2D();
    private final Canvas actionCanvas = new Canvas(980, 600);
    private final GraphicsContext actionGC = actionCanvas.getGraphicsContext2D();
    private final Canvas politicalCanvas = new Canvas(980, 600);
    private final GraphicsContext politicalGC = politicalCanvas.getGraphicsContext2D();
    private final List<Map> mapOrder = new ArrayList<>();
    private HBox politicalControls;
    private HBox terrainControls;
    Action currentAction;
    HexType currentType;
    boolean mapChange = true;

    public static void main(String[] args) {
        launch(args);    // fa partire la funzione start di JavaFX
    }

    @Override
    public void start(Stage primaryStage) {    // start della costruzione grafica
        primaryStage.setTitle("Server Dominator");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    private Region createContent() {

        VBox holder = new VBox();
        MapData mapData = new MapData();
        setUpMaps(mapData);

        Pane pane = new Pane(basicCanvas, actionCanvas, politicalCanvas);

        VBox mapTypesBox = new VBox(20);
        mapTypesBox.getChildren().addAll(setUpMaps(mapData));
        mapTypesBox.setPadding(new Insets(20));

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(new VBox(setUpTerrains(), setUpActionControls(), setUpStates(), setUpBuildings()));
        borderPane.setRight(mapTypesBox);
        holder.getChildren().addAll(createMenuBar(holder, mapData), borderPane);

        mapData.data.values().forEach(hex -> hex.setTerrain(new Terrain("Ocean", "#2273B8")));

        pane.setOnMouseClicked(event -> {
            Hexagon est = mapData.pixelToHex(new Point(event.getX(), event.getY()));
            if (currentAction != null) {
                switch (currentAction) {
                    case TerrainDrawing -> mapData.getHexData(est).setTerrain((Terrain) currentType);
                    case Move -> new ActionMap(mapData, actionGC).setMovable(est, 4);
                    case PoliticalDrawing -> mapData.getHexData(est).setState((State) currentType);
                    case BuildingDraw -> {
                        mapData.getHexData(est).setBuilding((Building) currentType);
                        System.out.println(est);
                        System.out.println(mapData.getHexData(est));
                    }
                }
                redrawMap();
            }
        });
        redrawMap();
        return holder;
    }

    private MenuBar createMenuBar(VBox holder, MapData mapData) {
        Menu fileMenu = new Menu("File");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem openItem = new MenuItem("Open");
        fileMenu.getItems().addAll(saveItem, openItem);

        Menu stateMenu = new Menu("State");
        MenuItem addStateItem = new MenuItem("Add");
        addStateItem.setOnAction(event -> addState());
        stateMenu.getItems().add(addStateItem);

        Menu terrainMenu = new Menu("Terrain");
        MenuItem addTerrainItem = new MenuItem("Add");
        addTerrainItem.setOnAction(event -> addTerrain());
        terrainMenu.getItems().add(addTerrainItem);

        FileChooser fileChooser = new FileChooser();
        saveItem.setOnAction(event -> {
            File file = fileChooser.showSaveDialog(holder.getScene().getWindow());
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                out.writeObject(mapData.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        openItem.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(holder.getScene().getWindow());
            try {
                ObjectInputStream out = new ObjectInputStream(new FileInputStream(file));
                mapData.setData((HashMap<Hexagon, HexData>) out.readObject());
                mapChange = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new MenuBar(fileMenu, stateMenu, terrainMenu);
    }

    private List<Node> setUpMaps(MapData mapData) {
        List<Node> checkBoxes = new ArrayList<>();
        Map basicMap = new BasicMap(mapData, basicGC);
        basicMap.selected = true;
        mapOrder.add(basicMap);
        checkBoxes.add(addMap(new TerrainMap(mapData, basicGC), "Terrain Map"));
        checkBoxes.add(addMap(new BuildingMap(mapData, basicGC), "Hex Info Map"));
        checkBoxes.add(addMap(new ActionMap(mapData, actionGC), "Action Map"));
        checkBoxes.add(addMap(new PoliticalMap(mapData, politicalGC), "Political Map"));
        checkBoxes.add(addMap(new HexInfoMap(mapData, basicGC), "Building Map"));
        return checkBoxes;
    }

    private Node addMap(Map map, String prompt) {
        CheckBox checkBox = new CheckBox(prompt);
        checkBox.selectedProperty().addListener((ov, old, newValue) -> {
            map.selected = newValue;
            redrawMap();
        });
        mapOrder.add(map);
        return checkBox;
    }

    public Region setUpTerrains() {
        terrainControls = createControls("Terrain Types",
                createType(new Terrain("Ocean", "#2273B8"), Action.TerrainDrawing),
                createType(new Terrain("Land", 1, "#F2CB84"), Action.TerrainDrawing),
                createType(new Terrain("Lake", "#8CC4DB"), Action.TerrainDrawing),
                createType(new Terrain("Forest", 2, "#558B29"), Action.TerrainDrawing),
                createType(new Terrain("Desert", 4, "#DE8D3A"), Action.TerrainDrawing));
        return terrainControls;
    }

    public Region setUpActionControls() {
        Button moveButton = new Button("Move");
        HBox actionControls = createControls("Action Types", moveButton);
        moveButton.setOnMouseClicked(event -> currentAction = Action.Move);
        return actionControls;
    }

    public Region setUpStates() {
        politicalControls = createControls("States",
                createType(new State("Roman Empire", "#66023C"), Action.PoliticalDrawing),
                createType(new State("Han Empire", "#0031BF"), Action.PoliticalDrawing),
                createType(new State("Parthian Empire", "#9ACD32"), Action.PoliticalDrawing));
        return politicalControls;
    }

    public Region setUpBuildings() {
        return createControls("Building Types",
                createType(new Building("Trade Post"), Action.BuildingDraw),
                createType(new Building("City", Building.Shape.Circle), Action.BuildingDraw));
    }

    private HBox createControls(String title, Node... controls) {
        HBox results = new HBox(10, new Label(title));
        results.setPadding(new Insets(10));
        results.getChildren().addAll(controls);
        return results;
    }

    public void addState() { // men� per aggiungere uno stato
        TextField nameInput = new TextField();
        TextField colorInput = new TextField();
        Button submitButton = new Button("Submit");
        VBox vbox = new VBox(createTextFieldBox(nameInput, "Name:  ", ""),
                createTextFieldBox(colorInput, "Color:  ", "Hexcode"),
                createButtonBox(submitButton));
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        showDialog(vbox, submitButton,
                () -> addType(politicalControls, new State(nameInput.getText(), colorInput.getText()), Action.PoliticalDrawing));
    }

    public void addTerrain() {
        TextField nameInput = new TextField();
        TextField colorInput = new TextField();
        Button submitButton = new Button("Submit");
        TextField costInput = new TextField();
        VBox vbox = new VBox(createTextFieldBox(nameInput, "Name:  ", ""),
                createTextFieldBox(colorInput, "Color:  ", "Hexcode"),
                createTextFieldBox(costInput, "Terrain Cost:  ", "Integer"),
                createButtonBox(submitButton));
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER_RIGHT);
        showDialog(vbox, submitButton,
                () -> addType(terrainControls,
                        new Terrain(nameInput.getText(),
                                Integer.parseInt(costInput.getText()), colorInput.getText()), Action.TerrainDrawing));
    }

    private void showDialog(Region content, Button submitButton, Runnable action) {
        Stage stage = new Stage();
        stage.setScene(new Scene(content));
        submitButton.setOnMouseClicked(event -> {
            action.run();
            stage.close();
        });
        stage.showAndWait();

    }

    private HBox createButtonBox(Button submitButton) {
        HBox buttonBox = new HBox();
        submitButton.setPadding(new Insets(5));
        buttonBox.getChildren().add(submitButton);
        buttonBox.setAlignment(Pos.CENTER);
        return buttonBox;
    }

    private HBox createTextFieldBox(TextField textField, String labelText, String promptText) {
        Label label = new Label(labelText);
        label.setPadding(new Insets(5));
        label.setMinSize(40, 10);
        textField.setMinSize(80, 10);
        textField.setPromptText(promptText);
        HBox results = new HBox(label, textField);
        results.setPadding(new Insets(10));
        return results;
    }

    private void redrawMap() {
        basicGC.clearRect(0, 0, basicCanvas.getWidth(), basicCanvas.getHeight());
        actionGC.clearRect(0, 0, actionCanvas.getWidth(), actionCanvas.getHeight());
        politicalGC.clearRect(0, 0, politicalCanvas.getWidth(), politicalCanvas.getHeight());

        for (Map map : mapOrder) {
            if (map.selected) {
                map.drawMap();
            }
        }
    }

    public void addType(HBox controls, HexType type, Action action) {
        Button button = new Button(type.getName());
        button.setOnMouseClicked(event -> {
            currentAction = action;
            currentType = type;
        });
        controls.getChildren().add(button);
    }

    public Node createType(HexType type, Action action) {
        Button button = new Button(type.getName());
        button.setOnMouseClicked(event -> {
            currentAction = action;
            currentType = type;
        });
        return button;
    }

    enum Action {TerrainDrawing, Move, PoliticalDrawing, BuildingDraw, Selector}
}