package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Artikel;

public class ArtikelDAO extends BaseDAO {
	
	public Artikel save(Artikel artikel){
		String query = "INSERT INTO artikel(artikelnummer, prijs, type, kleur, afbeelding) VALUES ("
				+ artikel.getArtikelNummer() + ", "
				+ artikel.getPrijs() + ", '"
				+ artikel.getType() + "', '"
				+ artikel.getKleur() + "','"
				+ artikel.getAfbeelding() +"');";
		
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return findByCode(artikel.getArtikelNummer());
	}

	private List<Artikel> selectArtikelen(String query){
		List<Artikel> results = new ArrayList<Artikel>();
		
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()){
				int artikelnummer = dbResultSet.getInt("artikelnummer");
				double prijs = dbResultSet.getDouble("prijs");
				String type = dbResultSet.getString("type");
				String afbeelding = dbResultSet.getString("afbeelding");
				String kleur = dbResultSet.getString("kleur");
				
				Artikel newArtikel = new Artikel(artikelnummer, prijs, type, kleur, afbeelding);
				
				results.add(newArtikel);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	public List<Artikel> findAllArtikelen(){
		return selectArtikelen("SELECT * FROM artikel");
	}
	
	public List<Artikel> findAllShirts(){
		return selectArtikelen("SELECT * FROM artikel WHERE type = 't-shirt'");
	}
	
	public List<Artikel> findAllSchoenen(){
		return selectArtikelen("SELECT * FROM artikel WHERE type = 'schoen'");
	}
	
	public Artikel findByCode(int i){
		List<Artikel> results = selectArtikelen("SELECT * FROM artikel WHERE artikelnummer='" + i + "'");
		if(results.size() == 0){
			return null;
		} else {
			return selectArtikelen("SELECT * FROM artikel WHERE artikelnummer='" + i + "'").get(0);
		}
	}
	public Artikel update(Artikel artikel){
		
		String query = "UPDATE artikel set artikelnummer='"+artikel.getArtikelNummer()+
				"', prijs='"+artikel.getPrijs()+
				"', type='"+artikel.getType()+
				"', kleur="+artikel.getKleur()+ "'";
		
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			int aff = stmt.executeUpdate(query);
			System.out.println("Row(s) affected: "+aff);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return findByCode(artikel.getArtikelNummer());
	}
	
	public boolean delete(Artikel artikel){
		boolean result = false;
		boolean artikelExists = findByCode(artikel.getArtikelNummer()) != null;
		
		if (artikelExists){
			String queryArtikel = "DELETE FROM artikel WHERE artikelnummer='" + artikel.getArtikelNummer() + "'";
			try (Connection con = getConnection()) {
				
				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(queryArtikel) == 1) { // 1 row updated!
					result = true;
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			
		}
		
		return result;
	}
}
