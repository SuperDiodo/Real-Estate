package filters;

import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;

import Dati.Concessione;

/**
 * 
 * Classe che implementa i filtri logici "or" e "and".
 */
public class ORAND extends Filter {
	private JSONArray cities;

	/**
	 * 
	 * @param field  campo a cui si vuole applicare il filtro.
	 * @param cities vettore contenente stringhe o valori di riferimento.
	 */
	public ORAND(String field, JSONArray cities) {
		super(field);
		setCities(cities);
	}

	public JSONArray getCities() {
		return cities;
	}

	public void setCities(JSONArray cities) {
		this.cities = cities;
	}

	// esempio query string den:
	// http://localhost:8080/filtering?filter={"type":"and", "fields":
	// "den","cities":%5B "sponda","tevere" %5D}
	// esempio query string search:
	// http://localhost:8080/filtering?filter={"type":"and", "fields":
	// "search","cities":%5B "roma",6935 %5D}
	/**
	 * @param elem  elemento da controllare.
	 * @param equal equal = true viene applicato il filtro "or". equal = false viene
	 *              applicato il filtro "and" tra elementi dello stesso tipo.
	 *              Inoltre è stato aggiunto nello switch case il caso "search" che
	 *              implementa il filtro "and" tra il tipo comune ed il tipo durata
	 *              per cercare una concessione in un dato comune e con una certa
	 *              durata. La funzione tramite il flag approvato dice se l'elemento
	 *              processato rispetta i criteri di ricerca (approvato = true) o
	 *              meno (approvato = false).
	 * 
	 */
	@Override
	public boolean approved(Concessione elem, boolean equal) throws JSONException {
		boolean approvato = false;

		switch (getFields()) {
		case "comune":
			if (equal) {
				for (int i = 0; i < cities.length(); i++) {
					if (elem.getComune().equalsIgnoreCase(cities.get(i).toString())) {
						approvato = true;
						break;
					}
				}
				break;
			}

			else {
				approvato = true;
				for (int i = 0; i < cities.length(); i++) {
					if (!(elem.getComune().equalsIgnoreCase(cities.get(i).toString()))) {
						approvato = false;
						break;
					}
				}

				break;
			}

		case "den":
			if (equal) {
				for (int i = 0; i < cities.length(); i++) {
					if (elem.getDen().toUpperCase().contains(cities.get(i).toString().toUpperCase())) {
						approvato = true;
						break;
					}
				}
				break;
			}

			else {
				approvato = true;
				for (int i = 0; i < cities.length(); i++) {
					if (!(elem.getDen().toUpperCase().contains(cities.get(i).toString().toUpperCase())))
						approvato = false;
				}

				break;
			}

		case "IDCom":
			if (equal) {
				for (int i = 0; i < cities.length(); i++) {
					if (elem.getIDCom().equalsIgnoreCase(cities.get(i).toString())) {
						approvato = true;
						break;
					}
				}
				break;
			}

			else {
				approvato = true;
				for (int i = 0; i < cities.length(); i++) {
					if (!(elem.getIDCom().equalsIgnoreCase(cities.get(i).toString())))
						approvato = false;
				}

				break;
			}
		case "search":
			approvato = true;
			if (!(elem.getComune().equalsIgnoreCase(cities.get(0).toString())
					&& elem.getDurata() == Integer.parseInt(cities.get(1).toString()))) {
				approvato = false;
				break;
			}
		}
		return approvato;

	}

	/**
	 * @param list  lista di elementi che possono essere eventualmente inseriti
	 *              nella lista filtrata.
	 * @param equal flag da passare alla funzione approved. Se il flag approvato
	 *              dell'elemento processato è true l'elemento viene inserito nella
	 *              lista filtrata, altrimenti viene processato l'elemento
	 *              successivo.
	 */
	@Override
	public Vector<Concessione> applica(Vector<Concessione> list, boolean equal) throws JSONException {
		Vector<Concessione> trovati = new Vector<Concessione>();
		for (int i = 0; i < list.size(); i++) {
			if (approved(list.get(i), equal))
				trovati.add(list.get(i));
		}

		return trovati;
	}

}
