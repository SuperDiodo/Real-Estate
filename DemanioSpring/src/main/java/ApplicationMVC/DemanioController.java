package ApplicationMVC;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Vector;
import Dati.Concessione;
import Dati.Demanio;
import Dati.Server;
import Dati.metadata;
import filters.BT;
import filters.GT;
import filters.IN;
import filters.LT;
import filters.NOT;
import filters.ORAND;
import stats.IntegerStat;
import stats.StringStat;

@Controller
public class DemanioController {

	protected Server data;
	protected Vector<Concessione> vett;
	private final Demanio demanio;

	public DemanioController() throws IOException, JSONException {
		
		System.out.println("\n\n|--------------------|");
		System.out.println("|   FLUVIAL-DEALS    |");
		System.out.println("|--------------------|\n\n");
		
		data = new Server("https://www.dati.gov.it/api/3/action/package_show?id=a1dee418-ddd7-40c6-ad6c-7b35aa31f61a");
		vett = data.getDemanio().getConcessioni();
		demanio = data.getDemanio();
		
		System.out.println("\n\n|--------------------|");
		System.out.println("|  APPLICATION READY |");
		System.out.println("|--------------------|\n\n");
	}

	@GetMapping("/")
	public String welcome() {
		
		return "welcome";
	}
	
	/**
	 * 
	 * @return Ritorna i metadati dell'oggetto Concessione in formato
	 *         JSON con un codice HTTP 200.
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/metadata")
	public ResponseEntity meta() {
		return new ResponseEntity<Vector<metadata>>(Concessione.metadati(), HttpStatus.OK);
	}
	
	/**
	 * Permette di mostrare l'attuale database, filtrato o non, tramite HTML
	 * @param model
	 * @return
	 */
	@GetMapping("/show.html")
	public String table(Model model) {
		model.addAttribute("list", vett);
		return "table";
	}
	
	/**
	 * 
	 * @return Ritorna il data-set in formato JSON. 
	 * @throws IOException
	 * @throws JSONException
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/data")
	public ResponseEntity dati() throws IOException, JSONException {
		vett = data.getDemanio().getConcessioni();
		return new ResponseEntity<Vector<Concessione>>(vett, HttpStatus.OK);
	}

	/**
	 * 
	 * @param filter filtro desiderato da inserire in query string
	 * @return In caso di filtro non implementato viene stampata una stringa di
	 *         errore con codice Http 501, in caso di mancanza di risultati viene
	 *         stampata una stringa di avviso e codice Http 204, mentre in caso di
	 *         ricerca contenente risultati viene stampata in formato JSON la lista
	 *         dei risultati ottenuti con codice Http 200.
	 * @throws JSONException
	 */
	@GetMapping("/filtering")
	@SuppressWarnings("rawtypes")
	public ResponseEntity filters(@RequestParam String filter) throws JSONException {

		JSONObject filtro = new JSONObject(filter);

		switch (filtro.getString("type")) {
		
		case "gt":
			GT gt = new GT(filtro.getString("fields"), filtro.getInt("lower"));
			vett = gt.applica(demanio.getConcessioni(), false);
			break;

		case "gte":
			GT gte = new GT(filtro.getString("fields"), filtro.getInt("lower"));
			vett = gte.applica(demanio.getConcessioni(), true);
			break;

		case "lt":
			LT lt = new LT(filtro.getString("fields"), filtro.getInt("upper"));
			vett = lt.applica(demanio.getConcessioni(), false);
			break;
			
		case "lte":
			LT lte = new LT(filtro.getString("fields"), filtro.getInt("upper"));
			vett = lte.applica(demanio.getConcessioni(), true);
			break;
			
		case "bt":
			BT bt = new BT(filtro.getString("fields"), filtro.getInt("upper"), filtro.getInt("lower"));
			vett = bt.applica(demanio.getConcessioni(), true);
			break;
			
		case "not":
			NOT not = new NOT(filtro.getString("fields"), filtro.getInt("value"));
			vett = not.applica(demanio.getConcessioni(), true);
			break;
			
		case "only":
			NOT only = new NOT(filtro.getString("fields"), filtro.getInt("value"));
			vett = only.applica(demanio.getConcessioni(), false);
			break;
			
		case "in":
			IN in = new IN(filtro.getString("fields"), filtro.getJSONArray("value"));
			vett = in.applica(demanio.getConcessioni(), false);
			break;
			
		case "nin":
			IN nin = new IN(filtro.getString("fields"), filtro.getJSONArray("value"));
			vett = nin.applica(demanio.getConcessioni(), true);
			break;
			
		case "or":
			ORAND or = new ORAND(filtro.getString("fields"), filtro.getJSONArray("cities"));
			vett = or.applica(demanio.getConcessioni(), true);
			break;
			
		case "and":
			ORAND and = new ORAND(filtro.getString("fields"), filtro.getJSONArray("cities"));
			vett = and.applica(demanio.getConcessioni(), false);
			break;
			
		default:
			return new ResponseEntity<String>("Nessun filtro selezionato/esistente", HttpStatus.NOT_IMPLEMENTED);
		}

	
		if (vett.size() == 0) return new ResponseEntity<String>("Non ci sono stati risultati della ricerca", HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<Vector<Concessione>>(vett, HttpStatus.OK);
		
	}
	
	/**
	 * Restituisce le statistiche di tutta la collezione in base all'attributo passato
	 * Gli attributi possibili sono: 
	 * - "nome", "RagSoc", "IDCom", "cognome", "comune", "den" [STRINGHE]
	 * - "superficie","supwater","durata" [NUMERICI]
	 * 
	 * Di default un filtro è vuoto,immettendolo è possibile ottenere statistiche di una selezione 
	 * della collezione
	 * 
	 * @param field campo su cui calcolare le stats
	 * @param filter eventuale filtro da applicare
	 * @return  In base al tipo di risposta avremo dei risultati:
	 * 1) OK: hash map dei risultati.
	 * 2) NOT FOUND: stringa di errore, non sono trovate statistiche.
	 * 2) BAD REQUEST: stringa di errore, nel caso in cui il campo richiesto 
	 * 				   o il filtro non siano stati scritti correttamente
	 * @throws JSONException
	 */
	@SuppressWarnings("rawtypes")
	@GetMapping("/stats")
	public ResponseEntity statistiche(@RequestParam(required = true) String field, @RequestParam(required = false, defaultValue = "") String filter) throws JSONException {
		
		/* Se il filtro è nullo si cercano le stats di tutta la collezione*/
		if(filter.isEmpty()) {
		
		/* stats per i campi di tipo stringa */
		if (Arrays.asList("nome", "RagSoc", "IDCom", "cognome", "comune", "den").contains(field)) {
			StringStat stat = new StringStat(demanio.getConcessioni(), field);
			if(!stat.getStat().getStringhe().isEmpty()) return new ResponseEntity<Map<String, Object>> (stat.getStat().getStringhe(), HttpStatus.OK);
			else return new ResponseEntity<String>("Statistiche non presenti", HttpStatus.NOT_FOUND);
			}
		
		/* stats per i campi di tipo numerico */
		if (Arrays.asList("superficie","supwater","durata").contains(field)) {
			IntegerStat stat = new IntegerStat(demanio.getConcessioni(), field);
			if(!stat.getStat().getNumerici().isEmpty()) return new ResponseEntity<Map<String, Object>> (stat.getStat().getNumerici(), HttpStatus.OK);
			else return new ResponseEntity<String>("Statistiche non presenti", HttpStatus.NOT_FOUND);
			}
		}
		
		/* Se il filtro non è nullo cerchiamo una selezione*/
		else if(!filter.isEmpty()) {
			
		/* se il campo immesso è giusto e il filtro produrrà una risposta giusta (cioè esiste) calcoliamo le stats */
		if (Arrays.asList("nome", "RagSoc", "IDCom", "cognome", "comune", "den").contains(field) && filters(filter).getStatusCode() == HttpStatus.OK) {
			
			@SuppressWarnings("unchecked")
			Vector<Concessione> filtrati = (Vector<Concessione>) filters(filter).getBody();
			StringStat stat = new StringStat(filtrati, field);
			if(!stat.getStat().getStringhe().isEmpty()) return new ResponseEntity<Map<String, Object>> (stat.getStat().getStringhe(), HttpStatus.OK);
			else return new ResponseEntity<String>("Statistiche non presenti", HttpStatus.NOT_FOUND);
			}
		
		/* se il campo immesso è giusto e il filtro produrrà una risposta giusta (cioè esiste) calcoliamo le stats */
		if (Arrays.asList("superficie","supwater","durata").contains(field) && filters(filter).getStatusCode() == HttpStatus.OK) {
			
			@SuppressWarnings("unchecked")
			Vector<Concessione> filtrati = (Vector<Concessione>) filters(filter).getBody();
			IntegerStat stat = new IntegerStat(filtrati, field);
			if(!stat.getStat().getNumerici().isEmpty()) return new ResponseEntity<Map<String, Object>> (stat.getStat().getNumerici(), HttpStatus.OK);
			else return new ResponseEntity<String>("Statistiche non presenti", HttpStatus.NOT_FOUND);
			}	
		
		}
		
		return new ResponseEntity<String> ("attributo inestistente/non corretto oppure filtro non corretto", HttpStatus.BAD_REQUEST);
	}

}
