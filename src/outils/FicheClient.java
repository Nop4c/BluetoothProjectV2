package outils;

public class FicheClient {
	
	private String adresse_mac;
	private String nom;
	private String prenom;
	private String adresse;
	private String mail;
	private String numTelephone;
	
	public FicheClient(String adresse_mac, String nom, String prenom, String adresse, String mail, String numTelephone) {
		super();
		this.adresse_mac = adresse_mac;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.mail = mail;
		this.numTelephone = numTelephone;
	}

	public String getAdresse_mac() {
		return adresse_mac;
	}
	public void setAdresse_mac(String adresse_mac) {
		this.adresse_mac = adresse_mac;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getNumTelephone() {
		return numTelephone;
	}
	public void setNumTelephone(String numTelephone) {
		this.numTelephone = numTelephone;
	}
}
