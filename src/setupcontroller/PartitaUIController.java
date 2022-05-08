package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.controller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class PartitaUIController {
    //model
    MainDefinitivo mainDefinitivo;

    public PartitaUIController(Main viewMain){
        setView(viewMain);
    }

    private void setView(Main viewMain) { //viewMain istanza della view della battaglia
        //get model
        mainDefinitivo = new MainDefinitivo(); //passa dei parametri?

        //link model - view
        //mainView.getAttackButton().bind(MainDefinitivo.nodecheck());


        //link - metodi per pulsanti

        //pulsante d'attacco
        viewMain.getAttackButton().setOnAction(actionEvent -> {
          //vuole metodo dei modelli con parametro solo il bottone in view , ERROR to fix
            mainDefinitivo.nodecheck(viewMain.getAttackButton());
            mainDefinitivo.battlecheck(viewMain.getAttackButton());
            mainDefinitivo.avvioBattaglia();
            actionEvent.consume();

        });


        //pulsante per accedere al mercato, cambio scene
        //sarebbe la view dell'interfaccia di gioco, cambia scene o apre pop up di market? Nel secondo caso da cambiare comando
        viewMain.getMarketButton().setOnAction(actionEvent -> primaryStage.setScene(marketView));
            //stage.setScene()
        );

        //pulsante per power up
        viewMain.getPowerupButton().setOnAction(actionEvent -> {
            mainDefinitivo.powerupCheck(viewMain.getPowerupButton());
            mainDefinitivo.powerup(viewMain.getPowerupButton());
        });

        //pulsante per creare software
        viewMain.getCreatesoftwareButton().setOnAction(actionEvent -> {

        });


    }

    private Object getClass(Button attackButton) { //metodo per controllare se il textfield ha qualcosa scritto o no, da definire

        return getClass();
    }
}
