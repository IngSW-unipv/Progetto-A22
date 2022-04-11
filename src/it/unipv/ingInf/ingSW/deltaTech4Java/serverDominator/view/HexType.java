package mappa;

import java.io.Serializable;
import java.util.Objects;

// HexType serve per definire le caratteristiche dei layer di ogni esagono
// Terrain
// State
// Building
// Player

public abstract class HexType {
    private static final long serialVersionUID = 1L;

    abstract String getName();			// restituisce il nome
    abstract String getStrokeColor(); 	// restituisce il colore del bordo
    abstract String getFillColor();		// restituisce il colore riempimento
}

class Terrain extends HexType implements Serializable {
    String name;
    private int terrainCost;  // il valore di inizializzazione (= 0  per convenzione)
    boolean traversable; // serve per capire se attraversabile (= null per convenzione)
    String hexCode; // colore di Terrain (= null per convenzione)

    public Terrain(String name, String hexCode) {  // primo costruttore
        this.name = name;
        this.traversable = false;
        this.hexCode = hexCode;
    }

    public Terrain(String name, int terrainCost, String hexCode) {  // secondo costruttore
        this.name = name;
        this.terrainCost = terrainCost;
        this.traversable = true; 	// se passo il terrainCost allora � traversable
        this.hexCode = hexCode;
    }

    public int getTerrainCost() {
        return terrainCost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStrokeColor() {
        return "#000000";
    }

    @Override
    public String getFillColor() {
        return hexCode;
    }

    @Override
    public boolean equals(Object o) { 	// � un metodo dell'interfaccia Serializabile nella classe Terrain.equals
    									// serve per verificare se un terreno � uguale ad un altro
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;  // getClass() != o.getClass() verifica se le classi sono diverse
        Terrain terrain = (Terrain) o;
        return terrainCost == terrain.terrainCost && traversable == terrain.traversable && name.equals(terrain.name); // restituisce un true o false
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, terrainCost, traversable);  // restituisce il codice hash dell'insieme degli attributi
    }

    @Override
    public String toString() {
        return "Terrain{" +
                "name='" + name + '\'' +
                ", terrainCost=" + terrainCost +
                ", traversable=" + traversable +
                ", hexCode='" + hexCode + '\'' +
                '}';
    }
}

class State extends HexType implements Serializable {
    String name;
    String hexCode;

    public State(String name, String hexCode) {
        this.name = name;
        this.hexCode = hexCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(name, state.name) && Objects.equals(hexCode, state.hexCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hexCode);
    }

    @Override
    public String getStrokeColor() {
        return hexCode;
    }

    @Override
    public String getFillColor() {
        return hexCode;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", hexCode='" + hexCode + '\'' +
                '}';
    }
}


class Building extends HexType implements Serializable {
    String name;

    enum Shape {Circle, Square}
    Shape shape;

    public Building(String name) {
        this.name = name;
        shape = Shape.Circle;
    }

    public Building(String name, Shape shape) {
        this.name = name;
        this.shape = shape;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getStrokeColor() {
        return "#000000";
    }

    @Override
    public String getFillColor() {
        return "#000000";
    }

    @Override
    public String toString() {
        return "Building{" +
                "name='" + name + '\'' +
                ", shape=" + shape +
                '}';
    }
}

class Player extends HexType implements Serializable {
    String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    String getName() {
        return name;
    }

    @Override
    String getStrokeColor() {
        return "#E90D15";
    }

    @Override
    String getFillColor() {
        return "#E90D15";
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}