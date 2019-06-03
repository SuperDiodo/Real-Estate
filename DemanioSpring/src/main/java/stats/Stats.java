package stats;

import java.util.Map;


/**
 * Classe contenente delle statistiche calcolate
 *
 */
public class Stats {
	
	private Map<String, Object> stringhe;
	private Map<String, Object> numerici; 
	
	/**
	 * TRUE: statistiche numeriche
	 * FALSE: statistiche stringhe
	 * @param map 
	 * @param numbers, quando vale true indica una collezione di elementi numerici
	 * 		  quando vale false invece indica una collezione di stringhe
	 */
	public Stats(Map<String, Object> map, boolean numbers) {
		if(numbers) setNumerici(map);
		else setStringhe(map);
	}
	
	
	public Map<String, Object> getStringhe() {
		return stringhe;
	}
	
	public void setStringhe( Map<String, Object> occ) {
		stringhe = occ;
	}

	public Map<String, Object> getNumerici() {
		return numerici;
	}

	public void setNumerici(Map<String, Object> numerici) {
		this.numerici = numerici;
	}



}
