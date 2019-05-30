package InputOutput;
import Dati.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Converter {

	private String input, output;
	
	public Converter(String input) {
		setInput(input);
	}
	
	/**
	 * Converte il file csv in una serie di oggetti di tipo concessione
	 * @return
	 * @throws IOException
	 */
	public Vector<Concessione> csv2objects() throws IOException {
		
		Vector<Concessione> vettore = new Vector<Concessione>();
		FileReader flow = new FileReader(new File(input));
		BufferedReader reader = new BufferedReader(flow);
		String line = reader.readLine(); 
		Concessione temp;
		while((line = reader.readLine()) != null) {
			String[] sections = line.split(Pattern.quote(";"));
		
			if(sections.length == 9 && !StringUtils.isAlpha(sections[8])) {
				temp = new Concessione(sections[0],sections[1],sections[2],sections[3],sections[4], sections[5], ParseInt(sections[6], 0), ParseInt(sections[7], 0), ParseDurata(sections[8]));
				vettore.add(temp);
			}
		}
	reader.close();
	return Correggi(Clean(vettore));
	}
	
	/**
	 * Permette di ottenere "elem" esimo numero da una stringa
	 * @param str
	 * @param elem
	 * @return
	 */
	
	public int ParseInt(String str, int elem) {
		String nums = str.split("(?=\\D)")[elem];
		if(nums.replaceAll("[^0-9\\.]+", "").isEmpty()) return 0;
		else return Integer.parseInt(nums.replaceAll("[^0-9\\.]+", ""));
	}
	
	/**
	 * Calcola la durata della concessione tenendo conto di anni, mesi, giorni
	 * @param str
	 * @return
	 */
	public int ParseDurata(String str) {
		str = str.toLowerCase();
		if(str.contains("anni")) return ParseInt(str,0)*365;
		if(str.contains("mesi")) return ParseInt(str,0)*30;
		if(str.contains("giorni")) return ParseInt(str,0);
		return ParseInt(str,0)*365;
	}
	
	/**
	 * Elimina dal Vector tutti gli elementi non consoni
	 * @param vett
	 * @return vector pulito
	 */
	public Vector<Concessione> Clean(Vector<Concessione> vett){
	
		boolean eliminare = false;
		
		for(int i = 0; i < vett.size(); i++) {	
			if((vett.get(i).getNome().isEmpty() || vett.get(i).getCognome().isEmpty()) && vett.get(i).getRagSoc().isEmpty()) eliminare=true;
			if(vett.get(i).getSuperficie() == 0 && vett.get(i).getSuperficie() == 0 && vett.get(i).getDurata()==0) eliminare = true;
			if(vett.get(i).getComune().isEmpty() || vett.get(i).getIDCom().isEmpty()) eliminare = true;
			if(eliminare) {vett.remove(i); eliminare = false;}
			}
		
		return vett;
	}
	
	/**
	 * Nel caso manchino parametri non necessari ricostruibili questa funzione permette di ottenerli
	 * @param vett
	 * @return restituisce un vettore di concessioni corrette al meglio
	 */
	public Vector<Concessione> Correggi(Vector<Concessione> vett){
		
		for(int i=0; i < vett.size(); i++) {
			if(vett.get(i).getRagSoc().isEmpty()) vett.get(i).setRagSoc(vett.get(i).getNome() + " " + vett.get(i).getCognome());
		}
		
		return vett;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
