package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.persistenceTest.prove.quesrie;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.implementDAO.AssetDAO;



public class AsetDAOProva {

	public static void main(String[] args) {
		AssetDAO fdao=new AssetDAO("resources/config/persistence/dataBase/connWith_sd_sys");

		if(fdao.insertAsset(new Asset(4,3,"ff",null,4)))
			System.out.println("insert asset ok");
		ArrayList<Asset> res= fdao.selectAll();
		fdao.insertAsset(new Asset(6,5,"ff","g",5)) ;
		for(Asset r : res)
			System.out.println(r.toString());
		fdao.updateAssetById(new Asset(4,3,"ff","hkhjbk",4));

	}

}
