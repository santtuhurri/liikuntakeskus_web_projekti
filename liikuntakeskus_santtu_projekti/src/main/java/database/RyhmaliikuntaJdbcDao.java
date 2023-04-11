package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ryhmaliikunta;

public class RyhmaliikuntaJdbcDao implements RyhmaliikuntaDao {

	// listaa kaikki ryhmäliikunnat
	@Override
	public List<Ryhmaliikunta> findAll() {

		// alustetaan tietokantayhteys
		Connection dbyhteys = null;
		// alustetaan preparedstatement
		PreparedStatement sqlLause = null;
		// alustetaan tulostaulu
		ResultSet tulostaulu = null;
		// luodaan tyhjä ryhmaliikuntalista
		List<Ryhmaliikunta> ryhmaliikunnat = new ArrayList<Ryhmaliikunta>();
		// luodaan apuviittausmuuttuja uuden ryhmaliikunta-olion luontiin
		Ryhmaliikunta ryhmaliikunta = null;
		// muodostetaan tietokantayhteys
		dbyhteys = Database.getDBConnection();

		try {
			// luodaan preparedstatement, haetaan kaikki rivit ryhmaliikunta-taulusta
			String sqlLauseStr = ("SELECT id, nimi, kuvaus, tyyppi, kesto, hinta, ohjaaja, paikka "
					+ "FROM ryhmaliikunta;");
			// komennon valmistelu
			sqlLause = dbyhteys.prepareStatement(sqlLauseStr);
			// otetaan tulostauluun talteen SQL-kyselyn tulokset
			tulostaulu = sqlLause.executeQuery();
			// lisätään tauluun tietoja, niin kauan kuin löytyy seuraava rivi
			while (tulostaulu.next()) {
				ryhmaliikunta = createRyhmaliikuntaObjectFromRow(tulostaulu);
				ryhmaliikunnat.add(ryhmaliikunta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			// suljetaan tulostaulu, preparedstatement ja tietokantayhteys
		} finally {
			Database.closeDBConnection(tulostaulu, sqlLause, dbyhteys);
		}
		// palautetaan ryhmaliikunta-lista
		return ryhmaliikunnat;
	}

	// luodaan uusi Ryhmaliikunta-luokan olio annetuilla tiedoilla
	private Ryhmaliikunta createRyhmaliikuntaObjectFromRow(ResultSet resultset) {
		try {

			/*
			 * haetaan yhden ryhmäliikunnan tiedot kyselyn tulostaulun (ResultSet-tyyppinen
			 * resultset-olion) aktiiviselta tietoriviltä
			 */
			String id = resultset.getString("id");
			String nimi = resultset.getString("nimi");
			String kuvaus = resultset.getString("kuvaus");
			String tyyppi = resultset.getString("tyyppi");
			int kesto = resultset.getInt("kesto");
			double hinta = resultset.getDouble("hinta");
			String ohjaaja = resultset.getString("ohjaaja");
			String paikka = resultset.getString("paikka");

			// luodaan ja palautetaan uusi Ryhmaliikunta-luokan olio
			return new Ryhmaliikunta(id, nimi, kuvaus, tyyppi, kesto, hinta, ohjaaja, paikka);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// lisää uusi ryhmäliikunta
	@Override
	public boolean addRyhmaliikunta(Ryhmaliikunta ryhmaliikunta) {

		// alustetaan tietokantayhteys
		Connection dbyhteys = null;
		// alustetaan preparedstatement
		PreparedStatement sqlInsert = null;
		boolean executionSuccesful = false;
		// muodostetaan tietokantayhteys
		dbyhteys = Database.getDBConnection();

		/*
		 * luodaan komento, jolla luodaan uusi ryhmäliikunta tietokannan tauluun
		 * id-sarakkeen arvo generoituu automaattisesti eli sitä ei tarvita komennossa
		 * annetut arvot parametrisoidaan eli niistä muodostuu merkkijono -> estää
		 * SQL-injektion
		 */
		try {
			// luodaan preparedstatement, lisätään kaikki muut tiedot paitsi id-arvo
			String sqlInsertStr = ("INSERT INTO ryhmaliikunta "
					+ " (nimi, kuvaus, tyyppi, kesto, hinta, ohjaaja, paikka)" + " VALUES" + " (?, ?, ?, ?, ?, ?, ?);");

			// komennon valmistelu
			sqlInsert = dbyhteys.prepareStatement(sqlInsertStr);
			sqlInsert.setString(1, ryhmaliikunta.getNimi());
			sqlInsert.setString(2, ryhmaliikunta.getKuvaus());
			sqlInsert.setString(3, ryhmaliikunta.getTyyppi());
			sqlInsert.setInt(4, ryhmaliikunta.getKesto());
			sqlInsert.setDouble(5, ryhmaliikunta.getHinta());
			sqlInsert.setString(6, ryhmaliikunta.getOhjaaja());
			sqlInsert.setString(7, ryhmaliikunta.getPaikka());

			int rowAffected = sqlInsert.executeUpdate();
			// sqlInsert onnistui
			if (rowAffected == 1)
				executionSuccesful = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

			// suljetaan preparedstatement ja tietokantayhteys
		} finally {
			Database.closeDBConnection(sqlInsert, dbyhteys);
		}

		return executionSuccesful;
	}

	// poista valittu ryhmäliikunta
	@Override
	public boolean removeRyhmaliikunta(int ryhmaliikuntaId) {

		// alustetaan tietokantayhteys
		Connection dbyhteys = null;
		// alustetaan preparedstatement
		PreparedStatement sqlDelete = null;
		boolean executionSuccesful = false;
		// muodostetaan tietokantayhteys
		dbyhteys = Database.getDBConnection();

		// poistetaan haluttu ryhmäliikunta id:n avulla
		// annettu arvo parametrisoidaan eli niistä muodostuu merkkijono -> estää
		// SQL-injektion
		try {
			String sqlDeleteStr = ("DELETE FROM ryhmaliikunta WHERE id = ?;");
			sqlDelete = dbyhteys.prepareStatement(sqlDeleteStr);
			sqlDelete.setInt(1, ryhmaliikuntaId);

			int rowAffected = sqlDelete.executeUpdate();
			if (rowAffected == 1)
				executionSuccesful = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			// suljetaan preparedstatement ja tietokantayhteys
		} finally {
			Database.closeDBConnection(sqlDelete, dbyhteys);

		}
		return executionSuccesful;
	}

	// listaa valittu ryhmäliikunta
	@Override
	public List<Ryhmaliikunta> findOne(int ryhmaliikuntaId) {

		// alustetaan tietokantayhteys
		Connection dbyhteys = null;
		// alustetaan preparedstatement
		PreparedStatement sqlLause = null;
		// alustetaan tulostaulu
		ResultSet tulostaulu = null;
		// luodaan tyhjä ryhmaliikuntalista
		List<Ryhmaliikunta> ryhmaliikunnat = new ArrayList<Ryhmaliikunta>();
		// luodaan apuviittausmuuttuja uuden ryhmaliikunta-olion luontiin
		Ryhmaliikunta ryhmaliikunta = null;
		// muodostetaan tietokantayhteys
		dbyhteys = Database.getDBConnection();

		try {
			// luodaan preparedstatement, haetaan yksi rivi ryhmaliikunta-taulusta id:n avulla
			// annetut arvot parametrisoidaan eli niistä muodostuu merkkijono -> estää
			// SQL-injektion
			String sqlLauseStr = "SELECT id, nimi, kuvaus, tyyppi, kesto, hinta, ohjaaja, paikka "
					+ "FROM ryhmaliikunta WHERE id = ?;";
			sqlLause = dbyhteys.prepareStatement(sqlLauseStr);
			sqlLause.setInt(1, ryhmaliikuntaId);
			// otetaan tulostauluun talteen SQL-kyselyn tulokset
			tulostaulu = sqlLause.executeQuery();
			// lisätään tauluun tietoja, niin kauan kuin löytyy seuraava rivi
			// (tässä tapauksessa rivejä on vain yksi)
			while (tulostaulu.next()) {
				ryhmaliikunta = createRyhmaliikuntaObjectFromRow(tulostaulu);
				ryhmaliikunnat.add(ryhmaliikunta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			// suljetaan tulostaulu, preparedstatement ja tietokantayhteys
		} finally {
			Database.closeDBConnection(tulostaulu, sqlLause, dbyhteys);
		}
		// palautetaan ryhmaliikunta-lista
		return ryhmaliikunnat;
	}

	// muokkaa valittua ryhmäliikuntaa, valinta tehdään id:n avulla
	@Override
	public boolean editRyhmaliikunta(int ryhmaliikuntaId, Ryhmaliikunta ryhmaliikunta) {

		// alustetaan tietokantayhteys
		Connection dbyhteys = null;
		// alustetaan preparedstatement
		PreparedStatement sqlUpdate = null;
		boolean executionSuccesful = false;
		// muodostetaan tietokantayhteys
		dbyhteys = Database.getDBConnection();

		/*
		 * luodaan komento, jolla päivitetään haluttua ryhmäliikuntaa tietokannassa
		 * annetut arvot parametrisoidaan eli niistä muodostuu merkkijono -> estää
		 * SQL-injektion
		 */
		try {
			String sqlUpdateStr = "UPDATE ryhmaliikunta SET nimi = ?, kuvaus = ?, tyyppi = ?, kesto = ?, hinta = ?, ohjaaja = ?, paikka = ? "
					+ "WHERE id = ?;";
			// komennon valmistelu
			sqlUpdate = dbyhteys.prepareStatement(sqlUpdateStr);
			sqlUpdate.setString(1, ryhmaliikunta.getNimi());
			sqlUpdate.setString(2, ryhmaliikunta.getKuvaus());
			sqlUpdate.setString(3, ryhmaliikunta.getTyyppi());
			sqlUpdate.setInt(4, ryhmaliikunta.getKesto());
			sqlUpdate.setDouble(5, ryhmaliikunta.getHinta());
			sqlUpdate.setString(6, ryhmaliikunta.getOhjaaja());
			sqlUpdate.setString(7, ryhmaliikunta.getPaikka());
			sqlUpdate.setInt(8, ryhmaliikuntaId);

			int rowAffected = sqlUpdate.executeUpdate();
			// sqlUpdate onnistui
			if (rowAffected == 1)
				executionSuccesful = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

			// suljetaan preparedstatement ja tietokantayhteys
		} finally {
			Database.closeDBConnection(sqlUpdate, dbyhteys);
		}

		return executionSuccesful;
	}

	// lisää uusi ryhmäliikunta id:n kanssa
	@Override
	public boolean addRyhmaliikuntaWithId(Ryhmaliikunta ryhmaliikunta) {

		// alustetaan tietokantayhteys
		Connection dbyhteys = null;
		// alustetaan preparedstatement
		PreparedStatement sqlInsert = null;
		boolean executionSuccesful = false;
		// muodostetaan tietokantayhteys
		dbyhteys = Database.getDBConnection();

		/*
		 * luodaan komento, jolla luodaan uusi ryhmäliikunta tietokannan tauluun
		 * id-sarakkeen arvo lisätään myös komennossa, jotta ryhmäliikunta voidaan
		 * asettaa halutulle id:lle, annetut arvot parametrisoidaan eli niistä muodostuu
		 * merkkijono -> estää SQL-injektion
		 */
		try {
			String sqlInsertStr = ("INSERT INTO ryhmaliikunta "
					+ " (id, nimi, kuvaus, tyyppi, kesto, hinta, ohjaaja, paikka)" + " VALUES"
					+ " (?, ?, ?, ?, ?, ?, ?, ?);");

			// komennon valmistelu
			sqlInsert = dbyhteys.prepareStatement(sqlInsertStr);
			sqlInsert.setString(1, ryhmaliikunta.getId());
			sqlInsert.setString(2, ryhmaliikunta.getNimi());
			sqlInsert.setString(3, ryhmaliikunta.getKuvaus());
			sqlInsert.setString(4, ryhmaliikunta.getTyyppi());
			sqlInsert.setInt(5, ryhmaliikunta.getKesto());
			sqlInsert.setDouble(6, ryhmaliikunta.getHinta());
			sqlInsert.setString(7, ryhmaliikunta.getOhjaaja());
			sqlInsert.setString(8, ryhmaliikunta.getPaikka());
			int rowAffected = sqlInsert.executeUpdate();
			// sqlInsert onnistui
			if (rowAffected == 1)
				executionSuccesful = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);

			// suljetaan preparedstatement ja tietokantayhteys
		} finally {
			Database.closeDBConnection(sqlInsert, dbyhteys);
		}

		return executionSuccesful;
	}
}
