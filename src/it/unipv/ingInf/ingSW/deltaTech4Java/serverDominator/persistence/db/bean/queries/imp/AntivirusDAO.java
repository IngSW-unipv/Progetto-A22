package it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Asset;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.Antivirus;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.bean.queries.IAssetDAO;
import it.unipv.ingInf.ingSW.deltaTech4Java.serverDominator.persistence.db.util.DbConnection;

/**
 * Antivirus query
 * @author ME
 * @version 1.0
 * @see Asset
 * @see Antivirus
 * @see IAssetDAO
 * @see Dbconnection 
 */
public class AntivirusDAO implements IAssetDAO {
	private Connection conn;
	private String propConn;
	public AntivirusDAO(String propConn) {
		super();
		this.propConn=propConn;
	}
	@Override
	public ArrayList<Asset> selectAll() {
		ArrayList<Asset> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query=
					"SELECT T1.idAsset, T1.COSTO, T1.NOME, T1.DESCRIZIONE, T1.LIVELLO,"
					+ "T2.CAPACITA_DIFENSIVA"
					+ " T2.TIPO"
					+ "from ASSET AS T1 "
					+ "JOIN  "
					+ "ANTIVIRUS AS T2 ON (T1.idAsset=T2.ASSET_idAsset)";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Asset a=new Asset(rs1.getInt(1), rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getInt(5));
				Antivirus v=new Antivirus(a,rs1.getInt(6),rs1.getString(7));

				result.add(v);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public ArrayList<Asset> selectByPrice(Asset assInput) {
		ArrayList<Asset> result = new ArrayList<>();

		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		ResultSet rs1;

		try
		{
			String query=
					"SELECT T1.idAsset, T1.COSTO, T1.NOME, T1.DESCRIZIONE, T1.LIVELLO,"
					+ "T2.CAPACITA_DIFENSIVA, T2.TIPO"
					+ "from ASSET AS T1 "
					+ "JOIN  "
					+ "ANTIVIRUS AS T2 ON (T1.idAsset=T2.ASSET_idAsset)"
					+ "WHERE T1.COSTO=?";

			st1 = conn.prepareStatement(query);
			st1.setInt(1, assInput.getCosto());

			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Asset a=new Asset(rs1.getInt(1), rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getInt(5));
				Antivirus v=new Antivirus(a,rs1.getInt(6),rs1.getString(7));

				result.add(v);
			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return result;
	}

	@Override
	public boolean insertAsset(Asset a) {
		Antivirus v=(Antivirus)a;
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		PreparedStatement st2;

		boolean esito=true;

		try
		{
			String query1="INSERT INTO ASSET (idAsset,COSTO,NOME,DESCRIZIONE,LIVELLO) VALUES(?,?,?,?,?)";
			st1 = conn.prepareStatement(query1);
			st1.setInt(1, v.getAsset().getIdAsset());
			st1.setInt(2,v.getAsset().getCosto());
			st1.setString(3,v.getAsset().getNome());
			st1.setString(4,v.getAsset().getDescrizione());
			st1.setInt(5,v.getAsset().getLivello());
			
			st1.executeUpdate(query1);
		}catch (Exception e) {
			e.printStackTrace();
			DbConnection.closeConnection(conn);
			return false;
		}
		try
		{
			String query2="INSERT INTO ANTIVIRUS (ASSET_idAsset,CAPACITA_DIFENSIVA,TIPO) VALUES(?,?,?)";
			st2 = conn.prepareStatement(query2);
			st2.setInt(1, v.getAsset().getIdAsset());
			st2.setInt(2, v.getCapacitaDifensiva());
			st2.setString(3, v.getTipo());
			
			st2.executeUpdate(query2);
		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updateAssetById(Asset newA) {
		Antivirus v=(Antivirus)newA;
		if((v.getCapacitaDifensiva())<=0) {
			return false;
		}
		
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;
		PreparedStatement st2;

		boolean esito=true;

		try
		{
			String query1="UPDATE ASSET SET COSTO=?,NOME=?,DESCRIZIONE=?,LIVELLO=? WHERE idAsset=?";
			st1 = conn.prepareStatement(query1);
			st1.setInt(5, v.getAsset().getIdAsset());
			st1.setInt(1,v.getAsset().getCosto());
			st1.setString(2,v.getAsset().getNome());
			st1.setString(3,v.getAsset().getDescrizione());
			st1.setInt(4,v.getAsset().getLivello());
			
			String query2="UPDATE ANTIVIRUS SET CAPACITA_OFFENSIVA=?,TIPO=? WHERE asset_idAsset=?";
			st2 = conn.prepareStatement(query2);
			st2.setInt(1, v.getCapacitaDifensiva());
			st2.setString(2,v.getTipo());
			st2.setInt(3, v.getAsset().getIdAsset());
			
			st1.executeUpdate(query1);
			st2.executeUpdate(query2);

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public boolean updatePriceAssetByPrice(Asset oldPrice, Asset newPrice) {
		conn=DbConnection.startConnection(conn,propConn);
		PreparedStatement st1;

		boolean esito=true;

		try
		{
			String query="UPDATE ASSET SET COSTO=? WHERE COSTO=? AND"
					+ "(idAsset) IN SELECT ASSET_idAsset FROM ANTIVIRUS";
			st1 = conn.prepareStatement(query);
			st1.setInt(2, oldPrice.getCosto());
			st1.setInt(1,newPrice.getCosto());

			st1.executeUpdate(query);

		}catch (Exception e){
			e.printStackTrace();
			esito=false;
		}

		DbConnection.closeConnection(conn);
		return esito;
	}

	@Override
	public Asset selectAssetById(Asset id) {
		Antivirus risult=new Antivirus();
		conn=DbConnection.startConnection(conn,propConn);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query=
					"SELECT T1.idAsset, T1.COSTO, T1.NOME, T1.DESCRIZIONE, T1.LIVELLO,"
					+ "T2.CAPACITA_DIFENSIVA, T2.TIPO"
					+ "from ASSET AS T1 "
					+ "JOIN  "
					+ "ANTIVIRUS AS T2 ON (T1.idAsset=T2.ASSET_idAsset)"
					+ "WHERE T1.idAsset=?";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Asset a=new Asset(rs1.getInt(1), rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getInt(5));
				Antivirus result=new Antivirus(a,rs1.getInt(6),rs1.getString(7));
				risult.setAsset(result.getAsset());
				risult.setCapacitaDifensiva(result.getCapacitaDifensiva());
				risult.setTipo(risult.getTipo());

			}
		}catch (Exception e){e.printStackTrace();}

		DbConnection.closeConnection(conn);
		return risult;
	}
}
