package sreverDominator.persistence.jUnitTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.FilesLanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.ILanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.PersistenceFacade;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AssetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AssetOwnId;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObPunteggio;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Obiettivo;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.ObiettiviUser;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DBLinguaManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.DataBase;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.PropertiesFile;

/**
 * TEST MINIMI DI PERSISTENCE FACADE
 * @author TawaHabib
 *
 */
class PersistenceFacadeTest {
	private static PersistenceFacade p=PersistenceFacade.getInstance();
	private static ArrayList<UserAccount> us= new ArrayList<UserAccount>();
	private static ArrayList<Asset> ass= new ArrayList<Asset>();
	private static ArrayList<Obiettivo> ob=new ArrayList<Obiettivo>();
	private static ArrayList<ObPunteggio> obp=new ArrayList<ObPunteggio>();
	
	/*IMPORTANTE: file che devono esistere e il loro contenuto 
	 * #######currentLanguesge 
	 * ENGLISH
	 * #######testFile
	 * LEGA5=En lega 5
	 * LEGA6=En lega 6
	 * LEGA7=En lega 7
	 * asset4=en Asset 4
	 * hkgyfyi=en hkgyfyi
	 */
	
	@BeforeEach
	void setUp() throws Exception {
		Asset asset1=new Asset(1,10,"Ennome1","ENdescrizione1",1);
		Asset asset2=new Asset(2,20,"ennome2","Endescrizione2",1);
		Asset asset3=new Asset(3,30,"ENnome3","ENdescrizione3",1);
		ass.add(asset3);
		ass.add(asset2);
		ass.add(asset1);
		
		Obiettivo ob1=new Obiettivo(1,"EN LEGA 1",100);
		Obiettivo ob2=new Obiettivo(2,"EN LEGA 2",200);
		Obiettivo ob3=new Obiettivo(3,"EN LEGA 3",400);
		ob.add(ob3);
		ob.add(ob2);
		ob.add(ob1);
		
		
		ObPunteggio obp1=new ObPunteggio(ob1, 100);
		ObPunteggio obp2=new ObPunteggio(ob2, 200);
		ObPunteggio obp3=new ObPunteggio(ob3, 400);
		obp.add(obp3);
		obp.add(obp2);
		obp.add(obp1);
		
		
		UserAccount user1=new UserAccount("Gio","","123456789");
		user1.setMny(900);
		user1.setPunteggio(700);
		user1.addAssetOwn(new AssetOwn(new AssetOwnId(asset2,user1),2));
		user1.addObiettiviUser(new ObiettiviUser(ob1,user1,"COMPLETATO"));
		user1.addObiettiviUser(new ObiettiviUser(ob2,user1,"COMPLETATO"));
		user1.addObiettiviUser(new ObiettiviUser(ob3,user1,"COMPLETATO"));
		
		UserAccount user2=new UserAccount("Luca","","123456789");
		user2.setMny(900);
		user2.setPunteggio(800);
		user2.addObiettiviUser(new ObiettiviUser(ob1,user2,"COMPLETATO"));
		user2.addObiettiviUser(new ObiettiviUser(ob2,user2,"COMPLETATO"));
		user2.addObiettiviUser(new ObiettiviUser(ob3,user2,"COMPLETATO"));
		user2.addAssetOwn(new AssetOwn(new AssetOwnId(asset2,user2),2));
		
		UserAccount user3=new UserAccount("MatC","","123456789");
		user3.setMny(900);
		user3.setPunteggio(600);
		user3.addObiettiviUser(new ObiettiviUser(ob1,user3,"COMPLETATO"));
		user3.addObiettiviUser(new ObiettiviUser(ob2,user3,"COMPLETATO"));
		user3.addObiettiviUser(new ObiettiviUser(ob3,user3,"COMPLETATO"));
		user3.addAssetOwn(new AssetOwn(new AssetOwnId(asset1,user3),3));
		user3.addAssetOwn(new AssetOwn(new AssetOwnId(asset3,user3),1));
		
		UserAccount user4=new UserAccount("MatPara","","123456789");
		user4.setMny(900);
		user4.setPunteggio(500);
		user4.addObiettiviUser(new ObiettiviUser(ob1,user4,"COMPLETATO"));
		user4.addObiettiviUser(new ObiettiviUser(ob2,user4,"COMPLETATO"));
		user4.addObiettiviUser(new ObiettiviUser(ob3,user4,"COMPLETATO"));
		user4.addAssetOwn(new AssetOwn(new AssetOwnId(asset1,user4),3));
		user4.addAssetOwn(new AssetOwn(new AssetOwnId(asset2,user4),2));
		user4.addAssetOwn(new AssetOwn(new AssetOwnId(asset3,user4),1));
		
		UserAccount user5=new UserAccount("TawaHabib","","123456789");
		user5.setMny(900);
		user5.setPunteggio(400);
		user5.addObiettiviUser(new ObiettiviUser(ob1,user5,"COMPLETATO"));
		user5.addObiettiviUser(new ObiettiviUser(ob2,user5,"COMPLETATO"));
		user5.addObiettiviUser(new ObiettiviUser(ob3,user5,"COMPLETATO"));
		user5.addAssetOwn(new AssetOwn(new AssetOwnId(asset1,user5),3));
		user5.addAssetOwn(new AssetOwn(new AssetOwnId(asset3,user5),1));
		us.add(user1);
		us.add(user2);
		us.add(user3);
		us.add(user4);
		us.add(user5);
		
		try {
			Properties p=new Properties();
			p.put("primaConfigurazione", "1");
			PropertiesFile.savePropertyInCriptedFile(p, "resources/config/persistence/dataBase/connWith_root");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		DataBase.forceDataBaseCreation("jdbc:mysql://localhost:3306","admin","123456789");
		DBLinguaManager man=new DBLinguaManager("resources/config/persistence/dataBase/connWith_sd_sys");
		Properties p=null;
	
		try {
			p=PropertiesFile.loadPropertiesFromFile("resources/language/testFile");
		} catch (Exception e) {
			System.err.println("configurazione errata!!!!!");
		}
		man.insertLanguegeList(p, ILanguageManager.getCurrentLanguage());

		
	}
	@AfterEach
	void tearDown() throws Exception {
		us.clear();
		ass.clear();
		ob.clear();
		obp.clear();
	}
	/**
	 * Test method for {PersistenceFacade.getInstance()}.
	 */

	@Test
	void testGetInstance() {
		assertEquals(p, PersistenceFacade.getInstance());
	}
	/**
	 * Test method for {PersistenceFacade.selectAssetByPrice(Asset)}.
	 */
	
	@Test
	void testSelectAssetByPrice() {
		assertEquals(p.selectAssetByPrice(ass.get(1)).get(0), ass.get(1));
	}

	/**
	 * Test method for {PersistenceFacade.insertAsset(Asset)}.
	 */
	
	@Test
	void testInsertAsset() {
		assertFalse(p.insertAsset(ass.get(1)));
		assertTrue(p.insertAsset(new Asset(4,50,"en asset 4","en hkgyfyi",5)));
	}

	/**
	 * Test method for {PersistenceFacade.updateAssetById(Asset)}.
	 */
	
	@Test
	void testUpdateAssetById() {
		ass.get(0).setDescrizione("endescrizione3");
		assertTrue(p.updateAssetById(ass.get(0)));
		ass.get(0).setDescrizione("descrizione1");
		assertFalse(p.updateAssetById(ass.get(0)));
	}

	/**
	 * Test method for {PersistenceFacade.updatePriceAssetByPrice(Asset, Asset)}.
	 */
	
	@Test
	void testUpdatePriceAssetByPrice() {
		assertTrue(p.updatePriceAssetByPrice(ass.get(0), ass.get(0)));
		assertTrue(p.updatePriceAssetByPrice(ass.get(0),ass.get(2)));
	}

	/**
	 * Test method for {PersistenceFacade.selectAssetByAssetId(Asset)}.
	 */
	
	@Test
	void testSelectAssetByAssetId() {
		assertNotEquals(p.selectAssetByAssetId(ass.get(0)),(ass.get(1)));
		assertEquals(p.selectAssetByAssetId(ass.get(2)),(ass.get(2)));
	}

	/**
	 * Test method for {PersistenceFacade.insertAssetOwn(AsetOwn)}.
	 */
	
	@Test
	void testInsertAssetOwn() {
		assertTrue(p.insertAssetOwn(new AssetOwn(ass.get(0),us.get(0),3)));
	}

	/**
	 * Test method for {PersistenceFacade.updateQuantityAssetOwnByAssetOwnId(AsetOwn)}.
	 */
	
	@Test
	void testUpdateQuantityAssetOwnByAssetOwnId() {
		assertTrue(p.updateQuantityAssetOwnByAssetOwnId(us.get(3).getAsetOwns().get(0)));
	}

	/**
	 * Test method for {PersistenceFacade.selectObiettiviByRicompensa(Obiettivi)}.
	 */
	
	@Test
	void testSelectObiettiviByRicompensa() {
		assertEquals(p.selectObiettiviByRicompensa(obp.get(0)).get(0), obp.get(0));
		assertNotEquals(p.selectObiettiviByRicompensa(obp.get(0)),obp.get(1));
		assertTrue(p.selectObiettiviByRicompensa(ob.get(1)).get(0).equals(ob.get(1)));
	}

	/**
	 * Test method for {PersistenceFacade.insertObiettivo(Obiettivi)}.
	 */
	
	@Test
	void testInsertObiettivo() {
		
		assertTrue(p.insertObiettivo(new Obiettivo(5,"en LEGA 5",400)));
	}

	/**
	 * Test method for {PersistenceFacade.updateObiettiviByObiettivoId(Obiettivi)}.
	 */
	
	@Test
	void testUpdateObiettiviByObiettivoId() {
		ObPunteggio ob= obp.get(0);
		ob.setRicompensa(500);
		assertTrue(p.updateObiettiviByObiettivoId(ob));
		
		assertEquals(p.selectObiettiviByObiettiviId(ob), ob);
	}

	/**
	 * Test method for {PersistenceFacade.updateRicompensaObiettivoByRicompensa(Obiettivi, Obiettivi)}.
	 */
	
	@Test
	void testUpdateRicompensaObiettivoByRicompensa() {
		assertTrue(p.updateRicompensaObiettivoByRicompensa(obp.get(2), ob.get(0)));
	}

	/**
	 * Test method for {PersistenceFacade.selectObiettiviByObiettiviId(Obiettivi)}.
	 */
	
	@Test
	void testSelectObiettiviByObiettiviId() {
		assertEquals(obp.get(1), p.selectObiettiviByObiettiviId(obp.get(1)));
	}
	/**
	 * Test method for {PersistenceFacade.selectObiettiviUserByUserId(UserAccount)}.
	 */
	
	@Test
	void testSelectObiettiviUserByUserId() {
		boolean risult1=false;
		boolean risult2=false;
		for(ObiettiviUser o:p.selectObiettiviUserByUserId(us.get(0))) {
			for(ObiettiviUser ob: us.get(0).getObiettiviUsers()) {
				if(o.equals(ob)) {
					risult1=true;
					break;
				}
			}
			if(!risult1) {
				risult1=false;
				break;
			}
		}
		for(ObiettiviUser o:p.selectObiettiviUserByUserId(us.get(0))) {
			for(ObiettiviUser ob: us.get(2).getObiettiviUsers()) {
				if(o.equals(ob)) {
					risult2=true;
					break;
				}
			}
			if(!risult2) {
				risult2=false;
				break;
			}
		}
		assertTrue(risult1);
		assertFalse(risult2);
	}

	/**
	 * Test method for {PersistenceFacade.selectObiettiviUserByObiettiviId(Obiettivi)}.
	 */
	
	@Test
	void testSelectObiettiviUserByObiettiviId() {
		boolean ris=false;
		for(ObiettiviUser o:p.selectObiettiviUserByObiettiviId(ob.get(0))) {
			for(UserAccount us: us) {
				if(o.getUserAccount().equals(us)) {
					ris=true;
					break;
				}
			}
			if(!ris) {
				ris=false;
				break;
			}
		}
		assertTrue(ris);
	}

	/**
	 * Test method for {PersistenceFacade.insertObiettiviUser(ObiettiviUser)}.
	 */
	
	@Test
	void testInsertObiettiviUser() {
		//assegno obiettivo che non esiste 
		assertFalse(p.insertObiettiviUser(new ObiettiviUser(new Obiettivo(6,"LEGA6",600),us.get(3),"NON COMPLETATO")));
		p.insertObiettivo(new Obiettivo(7,"en LEGA 7",300));
		//assegno obiettivo che esiste
		assertTrue(p.insertObiettiviUser(new ObiettiviUser(new Obiettivo(7,"en LEGA7",300),us.get(3),"NON COMPLETATO")));
	}

	/**
	 * Test method for {PersistenceFacade.updateStatoObiettiviUserbyObiettiviUserId(ObiettiviUser)}.
	 */
	@Test
	void testUpdateStatoObiettiviUserbyObiettiviUserId() {
		ObiettiviUser ob=us.get(0).getObiettiviUsers().get(0);
		ob.setStato("NON COMPLETATO");
		assertTrue(p.updateStatoObiettiviUserbyObiettiviUserId(ob));
	}

	/**
	 * Test method for {PersistenceFacade.insetUserAccount(UserAccount)}.
	 */
	@Test
	void testInsetUserAccount() {
		assertTrue(p.insertUserAccount(new UserAccount("Username1","email1","pssword1")));
	}

	/**
	 * Test method for {PersistenceFacade.updateUserAccount(UserAccount)}.
	 */
	@Test
	void testUpdateUserAccount() {
		UserAccount uss=us.get(0);
		uss.setMny(200);
		uss.setEmail("cknjn");
		uss.setPunteggio(1000);
		assertTrue(p.updateUserAccount(uss));
	}

	/**
	 * Test method for {PersistenceFacade.updateUserAccountUsername(UserAccount, java.lang.String)}.
	 */
	
	@Test
	void testUpdateUserAccountUsername() {
		UserAccount uss=us.get(0);
		assertTrue(p.updateUserAccountUsername(uss,"hhvhv"));
	}

	/**
	 * Test method for {PersistenceFacade.getUserAccountById(UserAccount)}.
	 */
	@Test
	void testGetUserAccountById() {
		UserAccount user=us.get(0);
		user.setPassw("pssSbagliata");
		assertNull(p.getUserAccountById(user));
		assertNotNull(p.getUserAccountById(us.get(3)));
	}

	/**
	 * Test method for {PersistenceFacade.chengeUserAccountPassword(UserAccount, java.lang.String)}.
	 */
	@Test
	void testChengeUserAccountPassword() {
		assertTrue(p.chengeUserAccountPassword(us.get(0), "prova"));
	}

	/**
	 * Test method for {PersistenceFacade.getAssetOwndByUserAccount(UserAccount)}.
	 */
	@Test
	void testGetAssetOwndByUserAccount() {
		boolean ris=false;
		for(AssetOwn o:p.getAssetOwndByUserAccount(us.get(0))) {
			for(AssetOwn ou: us.get(0).getAsetOwns()) {
				if(o.equals(ou)) {
					ris=true;
					break;
				}
			}
			if(!ris) {
				ris=false;
				break;
			}
		}
		assertTrue(ris);
	}

	/**
	 * Test method for {PersistenceFacade.getObiettiviUserByUserAccount(UserAccount)}.
	 */
	@Test
	void testGetObiettiviUserByUserAccount() {
		boolean ris=false;
		for(ObiettiviUser o:p.getObiettiviUserByUserAccount(us.get(0))) {
			for(ObiettiviUser ou: us.get(0).getObiettiviUsers()) {
				if(o.equals(ou)) {
					ris=true;
					break;
				}
			}
			if(!ris) {
				ris=false;
				break;
			}
		}
		assertTrue(ris);
	}

	/**
	 * Test method for {PersistenceFacade.updateUserAccountPunteggio(UserAccount, int)}.
	 */
	@Test
	void testUpdateUserAccountPunteggio() {
		UserAccount u=us.get(0);
		u.setPunteggio(1000);
		assertTrue(p.updateUserAccountPunteggio(u, u.getPunteggio()));
	}

	/**
	 * Test method for 
	 * {PersistenceFacade.updateUserAccountMny(UserAccount, int)}.
	 */
	@Test
	void testUpdateUserAccountMny() {
		UserAccount u=us.get(0);
		u.setMny(1000);
		assertTrue(p.updateUserAccountMny(u, u.getMny()));
	}

}
