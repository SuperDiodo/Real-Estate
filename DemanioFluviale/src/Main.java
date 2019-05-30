import java.io.IOException;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;

import Dati.Concessione;

public class Main {
	
	public static void main(String[] args) throws IOException, JSONException {
		
	Server serv = new Server("https://www.dati.gov.it/api/3/action/package_show?id=a1dee418-ddd7-40c6-ad6c-7b35aa31f61a");
	
	Vector<Concessione> vett = serv.getDemanio().getConcessioni();

		for(int i = 0; i < vett.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("Nome", vett.get(i).getNome());
			obj.put("Cognome", vett.get(i).getCognome());
			obj.put("Ragione Sociale", vett.get(i).getRagSoc());
			obj.put("ID comune", vett.get(i).getIDCom());
			obj.put("Comune", vett.get(i).getComune());
			obj.put("Denominazione", vett.get(i).getDen());
			obj.put("Superficie", vett.get(i).getSuperficie());
			obj.put("Specchio d'acqua", vett.get(i).getSupwater());
			obj.put("Durata", vett.get(i).getDurata());
			
			System.out.println(obj.toString(2)+"\n");
		}
		
	System.out.println(vett.size());		
	}
}