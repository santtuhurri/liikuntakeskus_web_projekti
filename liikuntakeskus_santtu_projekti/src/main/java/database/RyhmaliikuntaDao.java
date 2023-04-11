package database;

import java.util.List;

import model.Ryhmaliikunta;

public interface RyhmaliikuntaDao {

	// listaa kaikki ryhmäliikunnat
	public List<Ryhmaliikunta> findAll();

	// lisää uusi ryhmäliikunta
	public boolean addRyhmaliikunta(Ryhmaliikunta ryhmaliikunta);

	// poista valittu ryhmäliikunta
	public boolean removeRyhmaliikunta(int ryhmaliikuntaId);

	// listaa valittu ryhmäliikunta
	public List<Ryhmaliikunta> findOne(int ryhmaliikuntaId);

	// muokkaa valittua ryhmäliikuntaa
	public boolean editRyhmaliikunta(int ryhmaliikuntaId, Ryhmaliikunta ryhmaliikunta);

	// lisää uusi ryhmäliikunta id:n avulla
	public boolean addRyhmaliikuntaWithId(Ryhmaliikunta ryhmaliikunta);

}
