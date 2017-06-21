package nl.hu.v1wac.firstapp.model;

public class Artikel {
	private int artikelnummer;
	private double prijs;
	private String type;
	private String kleur;
	private String afbeelding;
	
	public Artikel(int artnm, double prijs2, String tp, String kl, String afb) {
		artikelnummer = artnm;
		prijs = prijs2;
		type = tp;
		kleur = kl;
		afbeelding = afb;
	}
	
	public int getArtikelNummer() {
		return artikelnummer;
	}
	
	public double getPrijs() {
		return prijs;
	}
	
	public String getType() {
		return type;
	}
	
	public String getKleur() {
		return kleur;
	}

	public int getArtikelnummer() {
		return artikelnummer;
	}

	public void setArtikelnummer(int artikelnummer) {
		this.artikelnummer = artikelnummer;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setKleur(String kleur) {
		this.kleur = kleur;
	}

	public String getAfbeelding() {
		return afbeelding;
	}
	public void setAfbeelding(String afb) {
		this.afbeelding = afb;
	}
}
