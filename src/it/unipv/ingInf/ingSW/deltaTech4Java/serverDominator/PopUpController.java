package setupcontroller;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.giocatore.Giocatore;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.Main;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.PopUp;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Development;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Market;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.PopUpFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Powerup;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.Selectmalware;
import javafx.scene.Node;


public class PopUpController {

    private static PopUpController popUpController=null;

    private  PopUpController(){
        super();
    }


    public static PopUpController getIstance(){
        if(popUpController==null)
            popUpController=new PopUpController();
        return popUpController;
    }

    public void initMercatoController(MainDefinitivo mainModello,PopUpFacade popUpFacade){ //int x, int y passati dopo aver cliccato btn Market
        popUpFacade.getPopUpMarket().getButtonPay().setOnAction(actionEvent -> {
            mainModello.acquistoMercato(popUpFacade.getBaseUtente().getPossessore(),popUpFacade.getPopUpMarket().getQuantitaAntivirus(),
                    "Antivirus");
            mainModello.acquistoMercato(popUpFacade.getBaseUtente().getPossessore(),popUpFacade.getPopUpMarket().getQuantitaVirus(),
                    "Virus");
            mainModello.acquistoMercato(popUpFacade.getBaseUtente().getPossessore(),popUpFacade.getPopUpMarket().getQuantitaRootCrash(),
                    "Rootcrash");
            mainModello.acquistoMercato(popUpFacade.getBaseUtente().getPossessore(),popUpFacade.getPopUpMarket().getLivelloCPU(),
                    "CPU");
            mainModello.acquistoMercato(popUpFacade.getBaseUtente().getPossessore(),popUpFacade.getPopUpMarket().getLivelloRam(),
                    "RAM");
            mainModello.acquistoMercato(popUpFacade.getBaseUtente().getPossessore(),popUpFacade.getPopUpMarket().getLivelloFirewall(),
                    "Firewall");
            mainModello.acquistoMercato(popUpFacade.getBaseUtente().getPossessore(),popUpFacade.getPopUpMarket().getLivelloEnergiata(),
                    "Energia");
            popUpFacade.getPopUpMarket().getStage().close();
        });
    }

    public void initSelectMalware(MainDefinitivo mainModello,PopUpFacade popUpFacade,int x , int y){
        popUpFacade.getPopUpSelectmalware().getFightButton().setOnAction(actionEvent -> {
            mainModello.avvioBattaglia(popUpFacade.getBaseUtente().getPossessore(), x ,y);
            popUpFacade.getPopUpSelectmalware().getStage().close();
        });

    }

    public void initPowerUp(MainDefinitivo mainModello,PopUpFacade popUpFacade,int x, int y){
        popUpFacade.getPopUpPowerup().getButtonPowerUp().setOnAction(actionEvent -> {
            if(popUpFacade.getPopUpPowerup().getEnergy()>0)
                mainModello.powerup(x,y,"energia");
            if(popUpFacade.getPopUpPowerup().getCpu()>0)
                mainModello.powerup(x,y,"cpu");
            if(popUpFacade.getPopUpPowerup().getFirewall()>0)
                mainModello.powerup(x,y,"firewall");
            if(popUpFacade.getPopUpPowerup().getRam()>0)
                mainModello.powerup(x,y,"ram");
            popUpFacade.getPopUpPowerup().getStage().close();
        });

    }

    public void initDevelopment(MainDefinitivo mainModello,PopUpFacade popUpFacade,int x, int y){
        popUpFacade.getPopUpDevelopment().getButtonDevelop().setOnAction(actionEvent -> {
            mainModello.creazioneSoftware("antivirus",
                    popUpFacade.getPopUpDevelopment().getQuantitaAntivirus().getNumber().intValue(),x,y);
            mainModello.creazioneSoftware("virus",
                    popUpFacade.getPopUpDevelopment().getQuantitaVirus().getNumber().intValue(),x,y);
            mainModello.creazioneSoftware("rootcrash",
                    popUpFacade.getPopUpDevelopment().getQuantitaRootCrash().getNumber().intValue(),x,y);
            popUpFacade.getPopUpDevelopment().getStage().close();
        });


    }


}
