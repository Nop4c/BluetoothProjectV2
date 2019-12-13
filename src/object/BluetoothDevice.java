package object;

public class BluetoothDevice {
	
	private int id = 0;
	private String adresse_mac;
	private int nb_visite;
	private String date_derniereVisite;
	private boolean ficheExist;
	
	public BluetoothDevice(String adresse_mac, String date){
		this.id = id++;
		this.adresse_mac = adresse_mac;
		this.nb_visite = 1;
		this.date_derniereVisite = date;
		this.ficheExist = false;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getAdresse_mac() {
		return adresse_mac;
	}
	public void setAdresse_mac(String adresse_mac) {
		this.adresse_mac = adresse_mac;
	}

	public int getNb_visite() {
		return nb_visite;
	}
	public void setNb_visite(int nb_visite) {
		this.nb_visite = nb_visite;
	}

	public String getDate_derniereVisite() {
		return date_derniereVisite;
	}
	public void setDate_derniereVisite(String date_derniereVisite) {
		this.date_derniereVisite = date_derniereVisite;
	}
	
	public boolean isFicheExist() {
		return ficheExist;
	}
	public void setFicheExist(boolean ficheExist) {
		this.ficheExist = ficheExist;
	}
	
}
