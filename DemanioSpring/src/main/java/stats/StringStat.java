package stats;

import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import Dati.Concessione;

public class StringStat extends statistiche{

	public StringStat(Vector<Concessione> vett, String field) {
		super(vett, field);
	}

	@Override
	public JSONObject getJsonStat() throws JSONException {
		
		JSONObject obj = new JSONObject();

		/* aggiungi stats */
		
		return obj;
	}

}
