package InputOutput;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Con questa classe si può ricercare un url e scaricarne il contenuto
 * @author 
 * 
 */

public class Downloader {

	private String ext;
	private URL url;
	private String file_url;
	
	/**
	 * 
	 * @param url link che contiene il file
	 * @param ext estensione del file
	 * @param file_url link assoluto al file
	 */
	
	public Downloader(URL url, String ext) {
		setUrl(url); setArg(ext);
	}
	
	/**
	 * partendo dal file descrittivo del data-set ricerca l'url assoluto del file da scaricare
	 * @return false: nessun file da scaricare trovato
	 * @throws IOException
	 * 
	 */
	public boolean RicercaFile() throws IOException {
	    
		String links = null;
		Document doc = Jsoup.connect(getUrl().toString()).get();
		Elements link = doc.select("a[href]");
		
		for(int i = 0; i < link.size(); i++) {
			links = link.get(i).absUrl("href");
			if(links.equalsIgnoreCase(".csv")) {setFile_url(links); return true;}
		}
		
		return false;
	}
	
	public void url2file(String filename) throws MalformedURLException, IOException {
		
		if(RicercaFile()) {
			FileUtils.copyURLToFile(new URL(getFile_url()), new File(filename));
			System.out.println("Download effettuato da: " + getFile_url() + ", file CSV generato\n");
		}
		
		else System.out.println("Download non effettuato\n");
	}
	
	
	
	public String getArg() {
		return ext;
	}
	
	public void setArg(String ext) {
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
}
