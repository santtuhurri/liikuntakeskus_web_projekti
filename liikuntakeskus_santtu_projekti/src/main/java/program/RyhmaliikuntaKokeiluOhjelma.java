package program;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Ryhmaliikunta;

// luodaan uusi ryhmäliikunta
public class RyhmaliikuntaKokeiluOhjelma {

	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		// luodaan uusi tyhjä lista
		List<Ryhmaliikunta> ryhmaliikunnat = new ArrayList<Ryhmaliikunta>();

		// luodaan uusi ryhmaliikunta-olio
		Ryhmaliikunta ryhmaliikunta1 = new Ryhmaliikunta("1", "Circuit",
				"Tehokas koko vartalon kattava harjoittelu aikaa vastaan", "Voima ja kestävyys", 30, 12.00,
				"Kiki Kekkonen", "Liikuntasali 1");

		// lisätään olio listaan
		ryhmaliikunnat.add(ryhmaliikunta1);

		// tulostetaan olion tiedot
		System.out.println("Tuntien tiedot: ");
		for (int i = 0; i < ryhmaliikunnat.size(); i++) {
			System.out.println(ryhmaliikunnat.get(i).getNimi());
			System.out.println(ryhmaliikunnat.get(i).getKuvaus() + "!");
			System.out.println("Tunnin kesto " + ryhmaliikunnat.get(i).getKesto() + " minuuttia.");
			System.out.println("Hinta: " + ryhmaliikunnat.get(i).getHinta() + "€");
			System.out.println("Ohjaajana: " + ryhmaliikunnat.get(i).getOhjaaja());
		}

	}

}
