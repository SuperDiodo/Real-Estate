package stats;

import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import Dati.Concessione;

public class IntegerStat extends statistiche{

	public IntegerStat(Vector<Concessione> vett, String field) {
		super(vett, field);
	}

	@Override
	public JSONObject getJsonStat() throws JSONException {
		
		JSONObject obj = new JSONObject();
		obj.put("sum", Op.sum(getVett(), getField()));
		obj.put("avg", Op.avg(getVett(), getField()));
		obj.put("min", Op.minimum(getVett(), getField()));
		obj.put("max", Op.maximum(getVett(), getField()));
		
		return obj;
	}

}
