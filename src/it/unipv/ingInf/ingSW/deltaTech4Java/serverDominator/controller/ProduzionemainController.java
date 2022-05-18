package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.model.MainDefinitivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.PartitaStage;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.view.partita.popUp.PopUpFacade;

public class ProduzionemainController {

	public static ProduzionemainController produzionemainController = null;
	private MainDefinitivo mainDefinitivo;
	private ProduzionemainController() {
		super();	
	}
	
	public static ProduzionemainController getInstance() {
		if(produzionemainController == null)
			produzionemainController = new ProduzionemainController();
	
		return produzionemainController;
	}
	
	/**
	 * 
	 * @param persistenceFacade
	 * @param popupFacade
	 * @param partitaStage
	 * Metodo associato al pulsante di conferma di produzione risorsa, nel pop up di azione 
	 */
	
	public void initProduzioneMain(PersistenceFacade persistenceFacade, PopUpFacade popupFacade,PartitaStage partitaStage, MainDefinitivo mainDefinitvo, UserAccount userAccount, Asset asset) {
		popupFacade.getPopUpDevelopment().getButtonDevelop().setOnAction(actionEvent -> {
			partitaStage.addProduzioneSoftware(partitaStage.getSelectedNode().getPossessore().getNome(),
			mainDefinitvo.softcheck(partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY()),
			userAccount.getAsetOwns().add(asset)
			,0); //richiamo getAsset non corretto e durata mancante
			
			
			//mainDefinitvo.creazioneSoftware(partitaStage.getSelectedBase().getPossessore(), persistenceFacade.getAssetOwndByUserAccount()
			mainDefinitvo.creazioneSoftware(null, 0, 
					
					partitaStage.getSelectedPoint().getIntX(), partitaStage.getSelectedPoint().getIntY());// nome ,quantita e coordinate
			
		});
	}
	
}
