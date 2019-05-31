package com.example.demo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
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

@Controller
public class DemanioController {

	protected Server data;
	protected Vector<Concessione> vett;
	private final Demanio demanio;

	public DemanioController() throws IOException, JSONException {
		data = new Server("https://www.dati.gov.it/api/3/action/package_show?id=a1dee418-ddd7-40c6-ad6c-7b35aa31f61a");
		vett = data.getDemanio().getConcessioni();
		demanio = data.getDemanio();
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
		return "site";
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

		if (vett.size() != 0) return new ResponseEntity<String>("Non ci sono stati risultati della ricerca", HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<Vector<Concessione>>(vett, HttpStatus.OK);
		
	}

}
