package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RyhmaliikuntaTest {

	// testataan toimiiko Ryhmaliikunta-konstruktori oikein kaikkien arvojen kanssa
	@Test
	public void luoRyhmaliikunta() {
		Ryhmaliikunta ryhmaliikunta1 = new Ryhmaliikunta();
		ryhmaliikunta1.setId("15");
		ryhmaliikunta1.setNimi("Testi-jumppa");
		ryhmaliikunta1.setKuvaus("Testi-tunti");
		ryhmaliikunta1.setTyyppi("Testi-tyyppi");
		ryhmaliikunta1.setKesto(2);
		ryhmaliikunta1.setHinta(15.00);
		ryhmaliikunta1.setOhjaaja("Tero Testaaja");
		ryhmaliikunta1.setPaikka("Testi-huone");

		assertEquals("15", ryhmaliikunta1.getId());
		assertEquals("Testi-jumppa", ryhmaliikunta1.getNimi());
		assertEquals("Testi-tunti", ryhmaliikunta1.getKuvaus());
		assertEquals("Testi-tyyppi", ryhmaliikunta1.getTyyppi());
		assertEquals(2, ryhmaliikunta1.getKesto());
		assertEquals(15.00, ryhmaliikunta1.getHinta());
		assertEquals("Tero Testaaja", ryhmaliikunta1.getOhjaaja());
		assertEquals("Testi-huone", ryhmaliikunta1.getPaikka());
		assertEquals("Ryhmaliikunta [id=" + "15" + ", nimi=" + "Testi-jumppa" + ", kuvaus=" + "Testi-tunti"
				+ ", tyyppi=" + "Testi-tyyppi" + ", kesto=" + 2 + ", hinta=" + 15.00 + ", ohjaaja=" + "Tero Testaaja"
				+ ", paikka=" + "Testi-huone" + "]", ryhmaliikunta1.toString());
	}

	// testataan laskeeko metodi tunnille oikean hinnan
	@Test
	public void laskeTunninHinta() {
		Ryhmaliikunta ryhmaliikunta1 = new Ryhmaliikunta();
		ryhmaliikunta1.setKesto(30);

		assertEquals(5.0, ryhmaliikunta1.tunninHinta());
	}
}
