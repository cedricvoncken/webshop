package nl.hu.v1wac.firstapp.model;

import java.util.List;

import nl.hu.v1wac.firstapp.persistence.ArtikelDAO;
import nl.hu.v1wac.firstapp.persistence.OrderDAO;

public class WorldService {
	private ArtikelDAO artikeldao = new ArtikelDAO();
	private OrderDAO orderdao = new OrderDAO();
	
	public WorldService() {	
	}
	
	
	public List<Artikel> getAllArtikelen() {
		return artikeldao.findAllArtikelen();
	}
	public List<Artikel> getAllShirts() {
		return artikeldao.findAllShirts();
	}
	public List<Artikel> getAllSchoenen() {
		return artikeldao.findAllSchoenen();
	}
	public List<Order> getAllOrders() {
		return orderdao.findAll();
	}
	public List<Order> getAllOrdersOpen() {
		return orderdao.findAllOpen();
	}
	public List<Order> getAllOrdersBetaald() {
		return orderdao.findAllBetaald();
	}	
	public Artikel getartikelByCode(int code) {
		return artikeldao.findByCode(code);
	}
	public Order getOrderByCode(int code) {
		return orderdao.findByCode(code);
	}
	
	public Artikel update(Artikel artikel){
		return artikeldao.update(artikel);
	}
	public Order update(Order order){
		return orderdao.update(order);
	}

	public boolean delete(Artikel artikel){
		return artikeldao.delete(artikel);
	}
	public boolean deleteOrder(Order order){
		return orderdao.delete(order);
	}
	public Artikel addartikel(Artikel artikel){
		return artikeldao.save(artikel);
	}
	public Order addOrder(Order order){
		return orderdao.save(order);
	}
}