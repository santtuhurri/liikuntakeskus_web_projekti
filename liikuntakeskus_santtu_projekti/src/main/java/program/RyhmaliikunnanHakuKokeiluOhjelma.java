package program;

import database.RyhmaliikuntaDao;
import database.RyhmaliikuntaJdbcDao;

// etsitään tietokannasta ryhmäliikunta halutulla id:llä
public class RyhmaliikunnanHakuKokeiluOhjelma {

	public static void main(String[] args) {

		RyhmaliikuntaDao ryhmaliikuntadao = new RyhmaliikuntaJdbcDao();

		System.out.println(ryhmaliikuntadao.findOne(1));
	}
}
