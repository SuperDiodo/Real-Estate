package filters;

import java.util.Vector;
import Dati.Concessione;

/**
 * 
 *Classe che implementa i filtri condizionali "lt" e "lte".
 */
public class LT extends Filter{
	private int upper;
	/**
	 * 
	 * @param fields campo a cui si vuole applicare il filtro.
	 * @param upper valore massimo di riferimento.
	 */
	public LT(String fields, int upper) {
		super(fields); setUpper(upper);
	}
	
	public int getUpper() {
		return upper;
	}
	public void setUpper(int upper) {
		this.upper = upper;
	}
	/**
	 * @param elem elemento da controllare.
	 * @param equal  equal = true viene applicato il filtro "lte".
	 *               equal = false viene applicato il filtro "lt".
	 *               La funzione tramite il flag approvato dice se l'elemento processato rispetta i criteri di ricerca (approvato = true) o meno (approvato = false).
	 *  
	 */
	@Override
	public boolean approved(Concessione elem, boolean equal) {
		boolean approvato = true;

		switch (getFields()) {
		case "superficie":
			if (equal) {
				if (elem.getSuperficie() > getUpper())
					approvato = false;
			} else {
				if (elem.getSuperficie() >= getUpper())
					approvato = false;
			}
			break;
		case "supwater":
			if (equal) {
				if (elem.getSupwater() > getUpper())
					approvato = false;
			} else {
				if (elem.getSupwater() >= getUpper())
					approvato = false;
			}
			break;
		case "durata":
			if (equal) {
				if (elem.getDurata() > getUpper())
					approvato = false;
			} else {
				if (elem.getDurata() >= getUpper())
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
	 * Se il flag approvato dell'elemento processato è true l'elemento viene inserito nella lista filtrata, altrimenti viene processato l'elemento successivo.
	 */
	@Override
	public Vector<Concessione> applica(Vector<Concessione> list,boolean equal){
		Vector<Concessione> trovati = new Vector<Concessione>();
		for(int i = 0; i < list.size(); i++) {
			if(approved(list.get(i),equal)) trovati.add(list.get(i));
		}
		
		return trovati;
	}
}
