package program;

import java.util.ArrayList;
import java.util.List;

import database.RyhmaliikuntaDao;
import database.RyhmaliikuntaJdbcDao;
import model.Ryhmaliikunta;

// muokataan tietokannasta löytyvää ryhmäliikuntaa
public class RyhmaliikuntaMuokkausKokeiluOhjelma {

	public static void main(String[] args) {

		// luodaan uusi tyhjä lista
		List<Ryhmaliikunta> ryhmaliikunnat = new ArrayList<Ryhmaliikunta>();

		// luodaan uusi ryhmaliikunta-olio
		Ryhmaliikunta ryhmaliikunta1 = new Ryhmaliikunta("Testitunti", "Testiä testiä", "Testaus", 15, 5.0,
				"Testi Testeri", "Testiluola");

		// lisätään olio listaan
		ryhmaliikunnat.add(ryhmaliikunta1);

		RyhmaliikuntaDao ryhmaliikuntadao = new RyhmaliikuntaJdbcDao();

		/*
		 * tulostetaan ensin id:llä 1 löytyvän ryhmäliikunnan tiedot muokataan kyseisen
		 * ryhmäliikunnan tietoja tulostetaan uudestaan id:llä 1 löytyvän ryhmäliikunnan
		 * tiedot tarkistetaan onko muutos tapahtunut
		 */
		System.out.println(ryhmaliikuntadao.findOne(1));
		System.out.println(ryhmaliikuntadao.editRyhmaliikunta(1, ryhmaliikunta1));
		System.out.println(ryhmaliikuntadao.findOne(1));

	}

}
