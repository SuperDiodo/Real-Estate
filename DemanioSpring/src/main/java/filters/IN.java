package filters;

import java.util.Vector;
import Dati.Concessione;
import org.json.JSONArray;
import org.json.JSONException;


/**
 * 
 * @author Gruppo Real Estate
 *Classe che implementa i filtri logici "in" e "nin".
 */
public class IN extends Filter {
	private JSONArray value;
/**
 * 
 * @param fields campo a cui si vuole applicare il filtro.
 * @param value vettore contenente i valori di riferimento. 
 */
	public IN(String fields, JSONArray value) {
		super(fields);
		setValue(value);
	}

	public JSONArray getValue() {
		return value;
	}

	public void setValue(JSONArray value) {
		this.value = value;
	}

	/**
	 * @param elem elemento da controllare.
	 * @param equal  equal = true viene applicato il filtro "nin".
	 *               equal = false viene applicato il filtro "in".
	 *               La funzione tramite il flag approvato dice se l'elemento processato rispetta i criteri di ricerca (approvato = true) o meno (approvato = false).
	 *  
	 */
	@Override
	public boolean approved(Concessione elem, boolean equal) throws JSONException {
		boolean approvato = true;
		boolean[] app = new boolean[value.length()];
		for (int j = 0; j < value.length(); j++)
			app[j] = false;

		switch (getFields()) {
		case "superficie":

			if (equal) {
				for (int i = 0; i < value.length(); i++) {
					if (elem.getSuperficie() != value.getInt(i))
						app[i] = true;
				}
				for (boolean c : app)
					if (!c)
						approvato = false;
				break;
			}

			else {
				approvato = false;
				for (int i = 0; i < value.length(); i++) {// Cerco i valori del vettore value
					if (elem.getSuperficie() == value.getInt(i))
						app[i] = true;
				}
				for (boolean b : app)
					if (b)
						approvato = true;

				break;
			}
		case "supwater":

			if (equal) {
				for (int i = 0; i < value.length(); i++) {
					if (elem.getSupwater() != value.getInt(i))
						app[i] = true;
				}
				for (boolean c : app)
					if (!c)
						approvato = false;
				break;
			}

			else {
				approvato = false;
				for (int i = 0; i < value.length(); i++) {// Cerco i valori del vettore value
					if (elem.getSupwater() == value.getInt(i))
						app[i] = true;
				}
				for (boolean b : app)
					if (b)
						approvato = true;

				break;
			}

		case "durata":

			if (equal) {
				for (int i = 0; i < value.length(); i++) {
					if (elem.getDurata() != value.getInt(i))
						app[i] = true;
				}
				for (boolean c : app)
					if (!c)
						approvato = false;
				break;
			}

			else {
				approvato = false;
				for (int i = 0; i < value.length(); i++) {// Cerco i valori del vettore value
					if (elem.getDurata() == value.getInt(i))
						app[i] = true;
				}
				for (boolean b : app)
					if (b)
						approvato = true;

				break;
			}

		default:
			break;

		}

		if (approvato)
			return true;
		else
			return false;
	}
	/**
	 * @param list lista di elementi che possono essere eventualmente inseriti nella lista filtrata.
	 * @param equal flag da passare alla funzione approved.
	 * Se il flag approvato dell'elemento processato è true l'elemento viene inserito nella lista filtrata, altrimenti viene processato l'elemento successivo.
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
