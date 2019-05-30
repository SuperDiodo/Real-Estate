package filters;

import java.util.Vector;
import Dati.Concessione;
/**
 * 
 * @author Gruppo Real Estate
 *Classe che implementa il filtro logico "not".
 */
public class NOT extends Filter{
private int value;
/**
 * 
 * @param fields campo a cui si vuole applicare il filtro.
 * @param value valore di riferimento.
 */
	public NOT (String fields,int value) {
		super(fields);
		setValue(value);
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
/**
 * @param elem elemento da controllare.
 * @param equal  equal = true viene applicato il filtro not (si ricercano gli elementi con valore diverso da quello di riferimento).
 *               equal = false vengono cercati gli elementi con valore uguale a quello di riferimento.
 *               La funzione tramite il flag approvato dice se l'elemento processato rispetta i criteri di ricerca (approvato = true) o meno (approvato = false).
 *  
 */
	@Override
	public boolean approved(Concessione elem, boolean equal) {
		boolean approvato = true;

		switch (getFields()) {
		case "superficie":
			if (equal)
				{if (elem.getSuperficie() == getValue())
					approvato = false;}
				else {if (elem.getSuperficie() != getValue())
					approvato = false;}
			break;

		case "supwater":
			if (equal)
				{if (elem.getSupwater() == getValue())
					approvato = false;}
				else {if (elem.getSupwater() != getValue())
					approvato = false;}
			break;
		case "durata":
			if (equal)
				{if (elem.getDurata() == getValue())
					approvato = false;}
				else {if (elem.getDurata() != getValue())
					approvato = false;}
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
	public Vector<Concessione> applica(Vector<Concessione> list, boolean equal){
		Vector<Concessione> trovati = new Vector<Concessione>();
		for(int i = 0; i < list.size(); i++) {
			if(approved(list.get(i),equal)) trovati.add(list.get(i));
		}
		
		return trovati;
	}
	
	
	
	
}
