package stats;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.json.JSONException;
import Dati.Concessione;

/**
 * Genera le statistiche per tipo numerico: 1) sommatoria 2) minimo 3) massimo
 * 4) media 5) deviazione standard 6) count
 * 
 * La collezione risultato è del tipo: Hash map tipologia stat, valore
 *
 */
public class IntegerStat {

	private Stats x;

	public IntegerStat(Vector<Concessione> vett, String field) throws JSONException {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Field", field);
		map.put("Sommatoria", Op.sum(vett, field));
		map.put("Minimo", Op.minimum(vett, field));
		map.put("Massimo", Op.maximum(vett, field));
		map.put("Media", Op.avg(vett, field));
		map.put("Deviazione STD", Op.devstd(vett, field));
		map.put("Count", Op.count(vett));

		x = new Stats(map, true);
	}

	public Stats getStat() {
		return x;
	}

}
