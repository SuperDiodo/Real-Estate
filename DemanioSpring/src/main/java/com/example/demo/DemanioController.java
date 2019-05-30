package com.example.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Controller
public class DemanioController {
private Demanio demanio;
Vector<Concessione> vett = new Vector<Concessione>();






/**
 * 
 * @return Questo mapping ritorna i metadati dell'oggetto Concessione in formato JSON con un codice HTTP 200
 */
@SuppressWarnings("rawtypes")
@GetMapping("/metadata")
public ResponseEntity meta() {
	return new ResponseEntity<Vector<metadata>>(Concessione.metadati(), HttpStatus.OK);
}



}
