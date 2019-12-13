package object;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import outils.FicheClient;

public class ConnexionBDD {
	private static Connection con;
	private static ResultSet rs;
	private static ResultSet rs2;
	private String url="jdbc:mysql://mysql-capontheo.alwaysdata.net:3306/capontheo_bluetoothproject";
	private String login="capontheo";
	private String password="root";
	private static PreparedStatement pstmt;
	private PreparedStatement pstmt2;
	private static List<FicheClient> res;
	
	public ConnexionBDD() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,login,password);
	}
	
	public void connexionClose() throws SQLException {
		pstmt.close();
		con.close();
	}
	
	public String selectedPeriph(String what, String adresseMac) throws SQLException {
		String res="";
		pstmt =con.prepareStatement("SELECT "+what+" FROM peripherique where adresseMac = ? ;");
		pstmt.setString(1, adresseMac);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			res= rs.getString(1);
		}else {
			res = null;
		}
		rs.close();
		return res;
	}
	
	public String selectedFiche(String what, String adresseMac) throws SQLException {
		String res="";
		pstmt =con.prepareStatement("SELECT "+what+" FROM ficheClient where adresseMac = ? ;");
		pstmt.setString(1, adresseMac);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			res= rs.getString(1);
		}else {
			res = null;
		}
		rs.close();
		return res;
	}
	
	public void insertFiche(String adresseMac, String nom, String prenom, String adresse, String eMail, String telephone, String genre, String dateNaissance) throws SQLException {
		pstmt =con.prepareStatement("INSERT INTO ficheClient (adresseMac, nom, prenom, adresse, adresseMail, telephone, genre, dateNaissance) VALUES (?,?,?,?,?,?,?,?);");	
		pstmt.setString(1, adresseMac);
		pstmt.setString(2, nom);
		pstmt.setString(3, prenom);
		pstmt.setString(4, adresse);
		pstmt.setString(5, eMail);
		pstmt.setString(6, telephone);
		pstmt.setString(7, genre);
		pstmt.setString(8, dateNaissance);
		pstmt.executeUpdate();
	}
	
	public void insertPeriph(String adresseMac, int nombreVisite, String derniereVisite, boolean ficheCree) throws SQLException {
		pstmt =con.prepareStatement("INSERT INTO peripherique (adresseMac, nombreVisite, derniereVisite, ficheCree) VALUES (?,?,?,?);");	
		pstmt.setString(1, adresseMac);
		pstmt.setInt(2, nombreVisite);
		pstmt.setString(3, derniereVisite);
		pstmt.setBoolean(4, ficheCree);
		pstmt.executeUpdate();
	}
	
	public void UpdatePeriphVisiteDate(String adresseMac) throws SQLException {
		Date ajd = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
		pstmt2 =con.prepareStatement("SELECT nombreVisite FROM peripherique where adresseMac = ? ;");
		pstmt2.setString(1, adresseMac);
		rs2=pstmt2.executeQuery();
		if(rs2.next()) {
			pstmt =con.prepareStatement("UPDATE peripherique SET nombreVisite = ? where adresseMac = ? ;");
			pstmt.setInt(1, rs2.getInt(1)+1);
			pstmt.setString(2, adresseMac);
			pstmt.executeUpdate();
			
			pstmt =con.prepareStatement("UPDATE peripherique SET derniereVisite = ? where adresseMac = ? ;");
			pstmt.setString(1, formater.format(ajd).toString());
			pstmt.setString(2, adresseMac);
			pstmt.executeUpdate();
		}
		pstmt2.close();
		rs2.close();
	}
	
	public List<FicheClient> displayAll() throws SQLException{
		res= new ArrayList<FicheClient>();
		pstmt =con.prepareStatement("SELECT * FROM ficheClient;");
		rs=pstmt.executeQuery();
	
		while(rs.next()) {
			System.out.println(rs.getString(2));
			res.add(new FicheClient(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
		}
		rs.close();
		return res;
	}
	
	public void deletePeriph(String adresseMac) throws SQLException {
		pstmt =con.prepareStatement("DELETE FROM peripherique where adresseMac = ? ;");	
		pstmt.setString(1, adresseMac);
		pstmt.executeUpdate();
	}
	
	public void deleteFicheClient(String adresseMac) throws SQLException {
		pstmt =con.prepareStatement("DELETE FROM ficheClient where adresseMac = ? ;");	
		pstmt.setString(1, adresseMac);
		pstmt.executeUpdate();
	}
	
	public void updateFiche(String adresseMac, String nom, String prenom, String adresse, String eMail, String telephone, String genre, String dateNaissance) throws SQLException {
		pstmt =con.prepareStatement("UPDATE ficheClient SET nom = ?, prenom = ?, adresse = ?, adresseMail = ?, telephone = ?, genre = ?, dateNaissance = ? where adresseMac = ?;");	
		pstmt.setString(1, nom);
		pstmt.setString(2, prenom);
		pstmt.setString(3, adresse);
		pstmt.setString(4, eMail);
		pstmt.setString(5, telephone);
		pstmt.setString(6, genre);
		pstmt.setString(7, dateNaissance);
		pstmt.setString(8, adresseMac);
		pstmt.executeUpdate();
	}
	
}
