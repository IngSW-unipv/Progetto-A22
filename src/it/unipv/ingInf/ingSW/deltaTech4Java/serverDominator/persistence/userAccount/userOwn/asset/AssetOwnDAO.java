package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.userAccount.userOwn.asset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AssetOwn;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.AssetOwnId;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.bean.UserAccount;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.linguaManager.DBLinguaManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.linguaManager.ILanguageManager;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.util.conn.SessionFactory;

/**
 * AssetOwm query
 * @author TawaHabib
 * @version 1.0
 * @see AssetOwn
 * @see UserAcount
 * @see IAssetOwnDAO
 * @see Dbconnection 
 */
public class AssetOwnDAO implements IAssetOwnDAO {
	private Connection conn;
	private String propConn;
	public AssetOwnDAO(String propConn) {
		
		this.propConn=propConn;
	}

	@Override
	public ArrayList<AssetOwn> selectAll() {
		ArrayList<AssetOwn> result = new ArrayList<>();
		DBLinguaManager man=new DBLinguaManager(propConn);
		conn=SessionFactory.getSession().getConnection(propConn);
		Statement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * FROM ASET_OWN JOIN ASSET on idAsset=Asset_idAsset";
			st1=conn.createStatement();
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Asset a=new Asset(rs1.getInt(4), rs1.getInt(5),
						man.getLanguageValueByKay(rs1.getString(6), ILanguageManager.getCurrentLanguage()) ,
						man.getLanguageValueByKay(rs1.getString(7), ILanguageManager.getCurrentLanguage()),rs1.getInt(8));
				UserAccount b =new UserAccount(rs1.getString(2),0);
				AssetOwnId c= new AssetOwnId(a,b);
				AssetOwn d=new AssetOwn(c, rs1.getInt(3));
				result.add(d);
			}
		}catch (Exception e){e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<AssetOwn> selectByUserOwner(UserAccount assInput) {
		ArrayList<AssetOwn> result = new ArrayList<>();
		DBLinguaManager man=new DBLinguaManager(propConn);

		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT *  FROM ASET_OWN JOIN ASSET on idAsset=Asset_idAsset JOIN  USER_ACCOUNT ON USER_ACCOUNT_USERNAME=USERNAME WHERE USER_ACCOUNT_USERNAME=? and Passw=?";
			st1=conn.prepareStatement(query);
			st1.setString(1, assInput.getUsername());
			st1.setString(2, assInput.getPassw());
			rs1=st1.executeQuery();
			while(rs1.next())
			{
				Asset a=new Asset(rs1.getInt(4), rs1.getInt(5),
						man.getLanguageValueByKay(rs1.getString(6), ILanguageManager.getCurrentLanguage()) ,
						man.getLanguageValueByKay(rs1.getString(7), ILanguageManager.getCurrentLanguage()),rs1.getInt(8));
				UserAccount b =new UserAccount(rs1.getString(9), rs1.getInt(10));
				AssetOwnId c= new AssetOwnId(a,b);
				AssetOwn d=new AssetOwn(c, rs1.getInt(3));
				result.add(d);
			}
		}catch (Exception e){
			e.printStackTrace();
			//DbConnection.closeConnection(conn);
			return null;
			}

		//DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<UserAccount> selectByAssetOwnd(Asset assInput) {
		ArrayList<UserAccount> result = new ArrayList<>();

		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query="SELECT * FROM ASET_OWN WHERE ASSET_idAsset=?";
			st1=conn.prepareStatement(query);
			st1.setInt(1, assInput.getIdAsset());
			rs1=st1.executeQuery();

			while(rs1.next())
			{
				UserAccount a=new UserAccount(rs1.getString(2));
				result.add(a);
			}
		}catch (Exception e){e.printStackTrace();}

		//DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean insertAssetOwn(AssetOwn a) {
		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query1="insert  into ASET_OWN (ASSET_idAsset,USER_ACCOUNT_USERNAME,QUANTITA)values (?,?,?)";
			st1 = conn.prepareStatement(query1);
			st1.setInt(1, a.getPrimaryKey().getAsset().getIdAsset());
			st1.setString(2,a.getPrimaryKey().getUserAccount().getUsername());
			st1.setInt(3, a.getQuantita());

			st1.executeUpdate();

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updateQuantityAssetOwnById(AssetOwn newA) {
		PreparedStatement st1;

		boolean esito=true;
		if(this.existAssetOwn(newA)) {
			conn=SessionFactory.getSession().getConnection(propConn);
			try
			{
				String query1="UPDATE ASET_OWN SET QUANTITA=? WHERE ASSET_idAsset=? AND USER_ACCOUNT_USERNAME=?";
				
				st1 = conn.prepareStatement(query1);
				st1.setInt(1, newA.getQuantita());
				st1.setInt(2,newA.getPrimaryKey().getAsset().getIdAsset());
				st1.setString(3, newA.getPrimaryKey().getUserAccount().getUsername());
	
				st1.executeUpdate();
	
			}catch (Exception e){
				e.printStackTrace();
				esito=false;
			}
		}
		else {
			esito=this.insertAssetOwn(newA);
		}

		//DbConnection.closeConnection(conn);
		return esito;
		
	}
	
	public boolean existAssetOwn(AssetOwn newOU) {
		conn=SessionFactory.getSession().getConnection(propConn);
		PreparedStatement st1;
		ResultSet rs1;
		boolean esito=true;

		try
		{
			String query1="select count(*) FROM Aset_Own WHERE ASSET_idAsset=? AND USER_ACCOUNT_USERNAME=?";
			st1 = conn.prepareStatement(query1);
			st1.setInt(1,newOU.getPrimaryKey().getAsset().getIdAsset());
			st1.setString(2, newOU.getPrimaryKey().getUserAccount().getUsername());

			rs1=st1.executeQuery();
			while(rs1.next())
			{
				if(rs1.getInt(1)<1) {
					esito=false;
				}
				break;
			}

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		//DbConnection.closeConnection(conn);
		return esito;
	}
}
