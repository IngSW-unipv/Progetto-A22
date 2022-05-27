package sreverDominator.persistence.prove.quesrie;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.asset.AssetDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;



public class ProvaAsetDAO {

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
