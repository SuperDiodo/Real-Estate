package filters;

import java.util.Vector;

import Dati.Concessione;
/**
 * 
 * @author Gruppo Real Estate
 *Classe che implementa i filtri condizionali "gt" e "gte".
 */
public class GT extends Filter {
	private int lower;
/**
 * 
 * @param fields campo a cui si vuole applicare il filtro.
 * @param lower valore minimo di riferimento.
 */
	public GT(String fields, int lower) {
		super(fields);
		setLower(lower);
	}

	public int getLower() {
		return lower;
	}

	public void setLower(int lower) {
		this.lower = lower;
	}
	/**
	 * @param elem elemento da controllare.
	 * @param equal  equal = true viene applicato il filtro "gte".
	 *               equal = false viene applicato il filtro "gt".
	 *               La funzione tramite il flag approvato dice se l'elemento processato rispetta i criteri di ricerca (approvato = true) o meno (approvato = false).
	 *  
	 */
	@Override
	public boolean approved(Concessione elem, boolean equal) {
		boolean approvato = true;
		switch (getFields()) {
		case "superficie":
			if (equal) {
				if (elem.getSuperficie() < getLower())
					approvato = false;
			} else {
				if (elem.getSuperficie() <= getLower())
					approvato = false;
			}
			break;
		case "supwater":
			if (equal) {
				if (elem.getSupwater() < getLower())
					approvato = false;
			} else {
				if (elem.getSupwater() <= getLower())
					approvato = false;
			}
			break;
		case "durata":
			if (equal) {
				if (elem.getDurata() < getLower())
					approvato = false;
			} else {
				if (elem.getDurata() <= getLower())
					approvato = false;
			}
			break;
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
	 */
	@Override
	public Vector<Concessione> applica(Vector<Concessione> list, boolean equal) {
		Vector<Concessione> trovati = new Vector<Concessione>();
		for (int i = 0; i < list.size(); i++) {
			if (approved(list.get(i), equal))
				trovati.add(list.get(i));
		}

		return trovati;
	}

}
