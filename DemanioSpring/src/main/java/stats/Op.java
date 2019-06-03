package stats;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.json.JSONException;
import Dati.Concessione;


	public class Op {
		
		protected static Vector<Integer> conv(Vector<Concessione> vett, String field){
			Vector<Integer> ids = new Vector<Integer>();
			for(Concessione thing : vett)
			{
				switch(field) {
				case "superficie": ids.add(thing.getSuperficie()); break;
				case "supwater": ids.add(thing.getSupwater()); break;
				case "durata": ids.add(thing.getDurata()); break;
				default: return null;
				}
			     
			}
			return ids;
		}
		
		protected static Vector<String> convstr(Vector<Concessione> vett, String field){
			Vector<String> ids = new Vector<String>();
			for(Concessione thing : vett)
			{
				switch(field) {
				case "nome": ids.add(thing.getNome()); break;
				case "cognome": ids.add(thing.getCognome()); break;
				case"RagSoc": ids.add(thing.getRagSoc()); break;
				case "IDCom": ids.add(thing.getIDCom()); break;
				case "comune": ids.add(thing.getCognome()); break;
				case "den": ids.add(thing.getDen()); break;
				default: return null;
				}
			     
			}
			return ids;
		}
			

		public static int sum(Vector<Concessione> vett, String field) {
			int somma = 0;
			Vector<Integer> temp = conv(vett, field);
			for (int i = 0; i < temp.size(); i++)
				somma = somma + temp.get(i);
			return somma;
		}

		
		public static double avg(Vector<Concessione> vett, String field) {
			return sum(vett, field) / vett.size();
		}


		public static int maximum(Vector<Concessione> vett, String field) {
			Vector<Integer> temp = conv(vett, field);
			
			if(temp.size() == 0) return 0;
			else {
			int maximum = temp.get(0);
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) > maximum)
					maximum = temp.get(i);
				}
			return maximum;
			}
		}

		public static int minimum(Vector<Concessione> vett, String field) {
			Vector<Integer> temp = conv(vett, field);
			
			if(temp.size() == 0) return 0;
			else {
			int minimum = temp.get(0);
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) < minimum)
					minimum = temp.get(i);
				}
			return minimum;
			}
		}

		public static double devstd(Vector<Concessione> vett, String field) {
			Vector<Integer> temp = conv(vett, field);
			double scartiquad = 0;
			double media = avg(vett, field);
			for (int i = 0; i < vett.size(); i++) {
				scartiquad = scartiquad + Math.pow(Math.abs(media - temp.get(i)), 2);
			}
			double variance = scartiquad / (temp.size()); // Varianza per una popolazione
			// double variance = scartiquad / (age.size()-1); //Varianza per un campione
			return Math.sqrt(variance);
		}
		
		public static int count(Vector<Concessione> vett) {
			return vett.size();
		} 
		
		public static Map<String, Object> occorrence(Vector<Concessione> vett, String field) throws JSONException {
			
			Vector<String> vett_c = convstr(vett, field);
			Map<String, Object> map = new HashMap<String, Object>();
			
			int occ = 0; String str = null;
			Vector<String> temp = new Vector<String>();
			temp.addAll(vett_c);
			
			for(int i = 0; i < vett.size(); i++) {
				
			occ = 0;
				
			for(int j = 0; j < temp.size(); j++) {
					if(temp.get(j).equals(vett_c.get(i)) && !vett_c.get(i).isEmpty()) {
						occ++; 
						str = temp.get(j);
						temp.removeElementAt(j); j--;
						map.put(str, occ);
						}
				}
			}
		return map;
		}
	}