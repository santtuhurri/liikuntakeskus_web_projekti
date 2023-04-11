package program;

import java.util.List;
import java.util.Scanner;

import database.RyhmaliikuntaDao;
import database.RyhmaliikuntaJdbcDao;
import model.Ryhmaliikunta;

public class RyhmaliikuntaSovellus {

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Lukujärjestys-työkalu");
		int valinta = -1;
		while (valinta != 0) {
			System.out
					.print("\n 1 Listaa ryhmäliikunnat \n 2 Lisää ryhmäliikunta \n 3 Poista ryhmäliikunta \n 0 Lopeta");
			System.out.print("\n Syötä valintasi: ");
			valinta = input.nextInt();
			input.nextLine();

			if (valinta == 1) {
				listaaRyhmaliikunnat();
			} else if (valinta == 2) {
				lisaaRyhmaliikunta();
			} else if (valinta == 3) {
				poistaRyhmaliikunta();
			} else if (valinta == 0) {
				System.out.println("\nLukujärjestys-työkalu suljettiin.");
			} else {
				System.out.println("Virheellinen valinta, yritä uudestaan!");
			}
		}
		input.close();
	}

	// listataan tietokannasta löytyvät ryhmäliikunnat
	private static void listaaRyhmaliikunnat() {

		RyhmaliikuntaDao ryhmaliikuntadao = new RyhmaliikuntaJdbcDao();
		// kutsutaan findAll-metodia
		List<Ryhmaliikunta> ryhmaliikunnat = ryhmaliikuntadao.findAll();
		// tulostetaan kaikkien ryhmäliikuntojen tiedot
		System.out.println("\nRyhmäliikunnat: ");
		for (Ryhmaliikunta ryhmaliikunta : ryhmaliikunnat) {
			System.out.print(ryhmaliikunta.getId() + ". ");
			System.out.print(ryhmaliikunta.getNimi() + " - ");
			System.out.print(ryhmaliikunta.getTyyppi() + ", ");
			System.out.print(ryhmaliikunta.getKuvaus() + ", kesto: ");
			System.out.print(ryhmaliikunta.getKesto() + " minuuttia, hinta ");
			System.out.print(ryhmaliikunta.getHinta() + "€, ohjaajana toimii: ");
			System.out.print(ryhmaliikunta.getOhjaaja() + ", Paikassa: ");
			System.out.println(ryhmaliikunta.getPaikka());
		}
	}

	// lisätään tietokantaan uusi ryhmäliikunta
	private static void lisaaRyhmaliikunta() {

		Ryhmaliikunta uusiRyhmaliikunta = new Ryhmaliikunta();
		// pyydetään lisättävän Ryhmäliikunta-olion tiedot käyttäjältä
		System.out.println("\nSyötä uuden ryhmäliikuntatunnin tiedot!");
		System.out.print("Syötä ryhmäliikunnan nimi: ");
		uusiRyhmaliikunta.setNimi(input.nextLine());

		System.out.print("Syötä ryhmäliikunnan kuvaus: ");
		uusiRyhmaliikunta.setKuvaus(input.nextLine());

		System.out.print("Syötä ryhmäliikunnan tyyppi: ");
		uusiRyhmaliikunta.setTyyppi(input.nextLine());

		System.out.print("Syötä ryhmäliikunnan kesto minuutteina: ");
		uusiRyhmaliikunta.setKesto(input.nextInt());

		System.out.print("Syötä ryhmäliikunnan hinta (xx,xx): ");
		uusiRyhmaliikunta.setHinta(input.nextDouble());

		System.out.print("Syötä ryhmäliikunnan ohjaaja: ");
		uusiRyhmaliikunta.setOhjaaja(input.nextLine());

		System.out.print("Syötä ryhmäliikunnan paikka: ");
		uusiRyhmaliikunta.setPaikka(input.nextLine());
		input.nextLine();

		RyhmaliikuntaDao ryhmaliikuntaDao = new RyhmaliikuntaJdbcDao();

		// ryhmäliikunnan tietojen lisäys tietokantaan
		boolean lisaysOnnistui = ryhmaliikuntaDao.addRyhmaliikunta(uusiRyhmaliikunta);
		if (lisaysOnnistui) {
			System.out.println("Uusi ryhmäliikuntatunti lisätty!");
		} else {
			System.out.println("Ryhmäliikuntatunnin lisäys epäonnistui!");
		}

	}

	// poistetaan haluttu ryhmäliikunta
	private static void poistaRyhmaliikunta() {

		int ryhmaliikuntaId = 0;
		// pyydetään poistettavan ryhmäliikunnan id
		System.out.print("\nSyötä poistettavan ryhmäliikuntatunnin id: ");
		ryhmaliikuntaId = input.nextInt();
		input.nextLine();

		RyhmaliikuntaDao ryhmaliikuntaDao = new RyhmaliikuntaJdbcDao();

		// ryhmäliikuntarivin poistaminen tietokannasta
		boolean poistoOnnistui = ryhmaliikuntaDao.removeRyhmaliikunta(ryhmaliikuntaId);
		if (poistoOnnistui) {
			System.out.println("Ryhmäliikuntatunti poistettu!");
		} else {
			System.out.println("Ryhmäliikuntatunnin poisto epäonnistui!");
		}
	}

}
