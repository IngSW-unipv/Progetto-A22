package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.test.quesrie;

import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Virus;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.imp.VirusDAO;

public class VirusDaoProva {

	public static void main(String[] args) {
		VirusDAO fdao=new VirusDAO("resources/config/dbConfig/connWith_sd_sys");
		ArrayList<Asset> prov= fdao.selectAll();
		Virus v=null;
		Virus v1=new Virus(new Asset(5,5,"ff","ff",5), 3);
		fdao.insertAsset((Asset)v1);
		for(Asset r : prov) {
			v=(Virus)r;
			System.out.println(v.toString()+"\n");
			}
		
			prov.clear();
			
		for(Asset r : prov) {
			System.out.println(r.toString()+"\n");
		}
	}


}
