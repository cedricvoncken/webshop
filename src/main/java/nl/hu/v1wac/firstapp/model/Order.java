package nl.hu.v1wac.firstapp.model;

import javax.json.JsonValue;

public class Order {
	private int ordernummer;
	private String datum;
	private String postcode;
	private String huisnummer;
	private String land;
	private int artikelnummer;
	private String email;
	private String betaald;
	
	public Order(int ord, String dat, String post,String huisnm, String ln, int artnm, String mail, String bet) {
		ordernummer = ord;
		datum = dat;
		postcode = post;
		huisnummer = huisnm;
		land = ln;
		artikelnummer = artnm;
		email = mail;
		betaald = bet;
	}
	
	public int getOrdernummer() {
		return ordernummer;
	}
	public void setOrdernummer(int ordernummer) {
		this.ordernummer = ordernummer;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getArtikelnummer() {
		return artikelnummer;
	}
	public void setArtikelnummer(int artikelnummer) {
		this.artikelnummer = artikelnummer;
	}
	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getBetaald() {
		return betaald;
	}

	public void setBetaald(String betaald) {
		this.betaald = betaald;
	}

	public void setHuisnummer(String huisnummer) {
		this.huisnummer = huisnummer;
	}

	public String getHuisnummer() {
		return huisnummer;
	}	
}
