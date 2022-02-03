package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.test.quesrie;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.imp.AssetDAO;



public class AsetDAOProva {

	public static void main(String[] args) {
		AssetDAO fdao=new AssetDAO("resources/config/dbConfig/connWith_root");

//		Fornitore f=new Fornitore(2,"CoolFornitures","Pavia");
//		fdao.insertFornitore(f);
		if(fdao.insertAsset(new Asset(0,3,"ff","ff",4)))
			System.out.println("insert asset ok");
		ArrayList<Asset> res= fdao.selectAll();
		fdao.insertAsset(new Asset(0,5,"ff","ff",5)) ;
		for(Asset r : res)
			System.out.println(r.toString());

	}

}
