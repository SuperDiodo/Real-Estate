package stats;

import java.util.Vector;

import org.json.JSONException;

import Dati.Concessione;

/**
 * Genera le statistiche per tipo stringa:
 * 1) mostra quante volte si ripete ogni elemento di un campo
 * 
 * La collezione risultato è del tipo:
 * Hash map  elemento, occorrenze
 *
 */
public class StringStat {
	
	private Stats x;

	public StringStat(Vector<Concessione> vett, String field) throws JSONException {
		x = new Stats(Op.occorrence(vett, field), false);
	}
	
	public Stats getStat() {
		return x;
	}


}
