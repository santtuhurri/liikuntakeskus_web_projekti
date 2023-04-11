package model;

public class Ryhmaliikunta {

	private String id;
	private String nimi;
	private String kuvaus;
	private String tyyppi;
	private int kesto;
	private double hinta;
	private String ohjaaja;
	private String paikka;

	public Ryhmaliikunta() {
		super();
		this.id = null;
		this.nimi = null;
		this.kuvaus = null;
		this.tyyppi = null;
		this.kesto = 0;
		this.hinta = 0.0;
		this.ohjaaja = null;
		this.paikka = null;
	}

	public Ryhmaliikunta(String nimi, String kuvaus, String tyyppi, int kesto, double hinta, String ohjaaja,
			String paikka) {
		super();
		this.id = null;
		this.nimi = nimi;
		this.kuvaus = kuvaus;
		this.tyyppi = tyyppi;
		this.kesto = kesto;
		this.hinta = hinta;
		this.ohjaaja = ohjaaja;
		this.paikka = paikka;
	}

	public Ryhmaliikunta(String id, String nimi, String kuvaus, String tyyppi, int kesto, double hinta, String ohjaaja,
			String paikka) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.kuvaus = kuvaus;
		this.tyyppi = tyyppi;
		this.kesto = kesto;
		this.hinta = hinta;
		this.ohjaaja = ohjaaja;
		this.paikka = paikka;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public void setTyyppi(String tyyppi) {
		this.tyyppi = tyyppi;
	}

	public void setKesto(int kesto) {
		this.kesto = kesto;
	}

	public void setHinta(double hinta) {
		this.hinta = hinta;
	}

	public void setOhjaaja(String ohjaaja) {
		this.ohjaaja = ohjaaja;
	}

	public void setPaikka(String paikka) {
		this.paikka = paikka;
	}

	public String getId() {
		return id;
	}

	public String getNimi() {
		return nimi;
	}

	public String getKuvaus() {
		return kuvaus;
	}

	public String getTyyppi() {
		return tyyppi;
	}

	public int getKesto() {
		return kesto;
	}

	public double getHinta() {
		return hinta;
	}

	public String getOhjaaja() {
		return ohjaaja;
	}

	public String getPaikka() {
		return paikka;
	}

	public double tunninHinta() {

		double tuntiHinta = 10;
		double ryhmaliikunnanHinta = (double) getKesto() / (double) 60 * tuntiHinta;
		return ryhmaliikunnanHinta;

	}

	@Override
	public String toString() {
		return "Ryhmaliikunta [id=" + id + ", nimi=" + nimi + ", kuvaus=" + kuvaus + ", tyyppi=" + tyyppi + ", kesto="
				+ kesto + ", hinta=" + hinta + ", ohjaaja=" + ohjaaja + ", paikka=" + paikka + "]";
	}

}
