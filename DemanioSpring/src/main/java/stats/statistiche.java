package stats;

import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import Dati.Concessione;

public abstract class statistiche {

	private String field;
	private Vector<Concessione> vett;
	private JSONObject stats;
	
	public statistiche(Vector<Concessione> vett, String field) {
		setVett(vett); setField(field);
	}
	
	public abstract JSONObject getJsonStat() throws JSONException;
	
	public String getField() {
		return field;
	}
	
	public void setField(String field) {
		this.field = field;
	}
	
	public JSONObject getStats() {
		return stats;
	}
	
	public void setStats(JSONObject stats) {
		this.stats = stats;
	}


	public Vector<Concessione> getVett() {
		return vett;
	}


	public void setVett(Vector<Concessione> vett) {
		this.vett = vett;
	}
	
}
