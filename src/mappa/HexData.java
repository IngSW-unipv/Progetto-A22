package mappa;

import java.io.Serializable;

public class HexData implements Serializable {  // definisce le caratteristiche dell'esagono
    private static final long serialVersionUID = 1L;
    Terrain terrain;
    State state;
    Building building;
    Player player;

    public void setTerrain(Terrain terrain){
        if(this.terrain == terrain){
            this.terrain = null;
        }
        else{
            this.terrain = terrain;
        }
    }

    public void setState(State state){
        if(this.state == state){
            this.state = null;
        }
        else{
            this.state = state;
        }
    }

    public void setBuilding(Building building) {
        if(this.building == building){
            this.building = null;
        }
        else{
            this.building = building;
        }
    }

    public void setPlayer(Player player) {	// associa il giocatore
        if(this.player == player){
            this.player = null;
        }
        else{
            this.player = player;
        }
    }

    @Override
    public String toString() {
        return "HexData{" +
                "terrain=" + terrain +
                ", state=" + state +
                ", building=" + building +
                '}';
    }
}
