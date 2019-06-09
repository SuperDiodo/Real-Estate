package InputOutput;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.json.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;



/**
 * Downloader del file CSV da dataset JSON
 * 
 */

public class Downloader {

	private String ext;
	private URL url;
	private String file_url;
	
	
	public Downloader(URL url, String ext) {
		setUrl(url); setExt(ext);
	}
	
	/**
	 * partendo dal file descrittivo del data-set ricerca l'url assoluto del file da scaricare
	 * @return false: nessun file da scaricare trovato
	 * @throws IOException
	 * @throws JSONException 
	 * 
	 */
	public boolean RicercaFile() throws IOException, JSONException {
	    
		Document doc = Jsoup.connect(getUrl().toString()).ignoreContentType(true).get();
		JSONObject obj = new JSONObject(doc.body().text()).getJSONObject("result");
		JSONArray array = obj.getJSONArray("resources");
		
		for(int i = 0; i < array.length(); i++) {
			String temp_url = array.getJSONObject(i).getString("url");
			if(temp_url.contains(getExt())) { setFile_url(array.getJSONObject(i).getString("url")); return true;}
		}
		
		return false;
	}
	
	/**
	 * converte, se necessario, l'url immesso in un file csv. Controlla se il file è già esistente.
	 * @param filename nome file da creare
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws JSONException
	 */
	public void url2file(String filename) throws MalformedURLException, IOException, JSONException {
		
		System.out.println("\n\n|--------------------|");
		System.out.println("|       DOWNLOAD     |");
		System.out.println("|--------------------|\n\n");
		
		if(new File(filename).exists()) {System.out.println("Non serve effettuare il download, file gia' presente\n"); return;};
		
		if(RicercaFile()) {
			FileUtils.copyURLToFile(new URL(getFile_url()), new File(filename));
			System.out.println("Download effettuato da: " + getFile_url() + ", file CSV generato\n");
		}
		else System.out.println("Download non effettuato\n");
	}
	
	
	/* GETTER E SETTER VARI */
	public String getExt() {
		return ext;
	}
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public URL getUrl() {
		return url;
	}
	
	public void setUrl(URL url) {
		this.url = url;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	/**************************/
}
