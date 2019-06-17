package filters;

/* GET 1) http://localhost:8080/house/filtering?filter={"type":"gt", "fields":"prezzo", "lower":500000} 
   GET 2) http://localhost:8080/house/show.html */
//import com.example.demo.*;
import java.util.Vector;
import org.json.JSONException;
import Dati.Concessione;

/**
 * 
 * Classe astratta creata per includere i filtri logici e condizionali da
 * implementare. I filtri dovranno di conseguenza implementare i metodi approved
 * e applica.
 *
 */
public abstract class Filter {
	private String fields;

	public Filter(String fields) {
		setFields(fields);
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public abstract boolean approved(Concessione elem, boolean equal) throws JSONException;

	public abstract Vector<Concessione> applica(Vector<Concessione> list, boolean equal) throws JSONException;
}