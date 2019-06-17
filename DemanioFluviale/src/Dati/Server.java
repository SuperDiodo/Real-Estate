package Dati;

import java.io.IOException;
import java.net.URL;
import org.json.JSONException;
import InputOutput.Converter;
import InputOutput.Downloader;

/**
 * Collegamento tra i due progetti, dato l'url permette la costruzione del
 * demanio
 *
 */
public class Server {

	private Downloader down;
	private Converter conv;
	private Demanio demanio;

	public Server(String url) throws IOException, JSONException {

		down = new Downloader(new URL(url), ".csv");
		down.url2file("database.csv");
		conv = new Converter("database.csv");
		demanio = new Demanio(conv.csv2objects());
	}

	public Demanio getDemanio() {
		return demanio;
	}

	public void setDemanio(Demanio demanio) {
		this.demanio = demanio;
	}

}
