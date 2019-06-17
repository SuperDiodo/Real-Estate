package filters;

import Dati.Concessione;
import java.util.Vector;

/**
 * 
 * Classe che implementa il filtro condizionale "bt".
 */
public class BT extends Filter {
	private int upper;
	private int lower;

	/**
	 * 
	 * @param fields campo a cui si vuole applicare il filtro.
	 * @param upper  valore massimo di riferimento.
	 * @param lower  valore minimo di riferimento.
	 */
	public BT(String fields, int upper, int lower) {
		super(fields);
		setUpper(upper);
		setLower(lower);
	}

	public int getUpper() {
		return upper;
	}

	public void setUpper(int upper) {
		this.upper = upper;
	}

	public int getLower() {
		return lower;
	}

	public void setLower(int lower) {
		this.lower = lower;
	}

	/**
	 * @param elem  elemento da controllare.
	 * @param equal equal = true viene applicato il filtro "bt" esclusi i valori
	 *              estremi lower e upper. equal = false viene applicato il filtro
	 *              "bt". La funzione tramite il flag approvato dice se l'elemento
	 *              processato rispetta i criteri di ricerca (approvato = true) o
	 *              meno (approvato = false).
	 * 
	 */
	@Override
	public boolean approved(Concessione elem, boolean equal) {
		boolean approvato = true;

		switch (getFields()) {
		case "superficie":
			if (equal) {
				if (elem.getSuperficie() > getUpper() || elem.getSuperficie() < getLower())
					approvato = false;
			} else {
				if (elem.getSuperficie() >= getUpper() || elem.getSuperficie() <= getLower())
					approvato = false;
			}
			break;

		case "supwater":
			if (equal) {
				if (elem.getSupwater() > getUpper() || elem.getSupwater() < getLower())
					approvato = false;
			} else {
				if (elem.getSupwater() >= getUpper() || elem.getSupwater() <= getLower())
					approvato = false;
			}
			break;
		case "durata":
			if (equal) {
				if (elem.getDurata() > getUpper() || elem.getDurata() < getLower())
					approvato = false;
			} else {
				if (elem.getDurata() >= getUpper() || elem.getDurata() <= getLower())
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
	 * @param list  lista di elementi che possono essere eventualmente inseriti
	 *              nella lista filtrata.
	 * @param equal flag da passare alla funzione approved. Se il flag approvato
	 *              dell'elemento processato è true l'elemento viene inserito nella
	 *              lista filtrata, altrimenti viene processato l'elemento
	 *              successivo.
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
