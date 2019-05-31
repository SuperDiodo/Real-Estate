package stats;

import java.util.Vector;

import org.json.JSONObject;

import Dati.Concessione;
import Dati.Concessione;

public class IntegerStat extends statistiche{

	public IntegerStat(Vector<Concessione> vett, String field) {
		super(vett, field);
	}

	@Override
	public JSONObject getJsonStat() {
		
		JSONObject obj = new JSONObject();
		obj.put("avg", Op)
		
	}

}
