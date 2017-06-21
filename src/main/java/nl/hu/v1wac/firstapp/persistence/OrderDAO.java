package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.model.Artikel;
import nl.hu.v1wac.firstapp.model.Order;

public class OrderDAO extends BaseDAO {
	
	public Order save(Order Order){
		String query = "INSERT INTO orders(ordernummer, datum, postcode, huisnummer, land, artikelnummer, email, betaald) VALUES ("
				+ Order.getOrdernummer() + ", '"
				+ Order.getDatum() + "', '"
				+ Order.getPostcode() + "', '"
				+ Order.getHuisnummer() + "', '"
				+ Order.getLand() + "', "
				+ Order.getArtikelnummer()+", '" 
				+ Order.getEmail() + "', '"
				+ Order.getBetaald() + "');";
		
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return findByCode(Order.getOrdernummer());
	}

	private List<Order> selectOrders(String query){
		List<Order> results = new ArrayList<Order>();
		
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()){
				int ordernummer = dbResultSet.getInt("ordernummer");
				String datum = dbResultSet.getString("datum");
				String postcode = dbResultSet.getString("postcode");
				String huisnummer = dbResultSet.getString("huisnummer");
				String land = dbResultSet.getString("land");
				int artikelnummer = dbResultSet.getInt("artikelnummer");
				String email =dbResultSet.getString("email");
				String betaald = dbResultSet.getString("betaald");
				
				Order newOrder = new Order(ordernummer, datum, postcode, huisnummer, land, artikelnummer, email, betaald);
				
				results.add(newOrder);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}

	public List<Order> findAll(){
		return selectOrders("SELECT * FROM orders");
	}
	
	public List<Order> findAllOpen() {
		return selectOrders("SELECT * FROM orders where betaald='nee'");
	}
	
	public List<Order> findAllBetaald(){
		return selectOrders("SELECT * FROM orders where betaald='Ja'");
	}
	
	public Order findByCode(int i){
		List<Order> results = selectOrders("SELECT * FROM orders WHERE ordernummer='" + i + "'");
		if(results.size() == 0){
			return null;
		} else {
			return selectOrders("SELECT * FROM orders WHERE ordernummer='" + i + "'").get(0);
		}
	}

	public Order update(Order order){
		
		String query = "UPDATE orders set betaald='"+order.getBetaald()+"' WHERE artikelnummer ="+order.getArtikelnummer()+"";
	
		try (Connection con = super.getConnection()){
			Statement stmt = con.createStatement();
			int aff = stmt.executeUpdate(query);
			System.out.println("Row(s) affected: "+aff);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return findByCode(order.getOrdernummer());
	}
	public boolean delete(Order order){
		boolean result = false;
		boolean orderExists = findByCode(order.getOrdernummer()) != null;
		
		if (orderExists){
			String queryOrder = "DELETE FROM orders WHERE ordernummer='" + order.getOrdernummer() + "'";
			try (Connection con = getConnection()) {
				
				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(queryOrder) == 1) { // 1 row updated!
					result = true;
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			
		}
		
		return result;
	}
}