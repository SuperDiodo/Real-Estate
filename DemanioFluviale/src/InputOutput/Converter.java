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
		
		int riga = 0;
		
		boolean tenere;
		
		System.out.println("\n\n|--------------------|");
		System.out.println("|       PARSING      |");
		System.out.println("|--------------------|\n\n");
		
		System.out.println("Righe eliminate:\n");
		while((line = reader.readLine()) != null) {
			
			riga++;
			
			String[] sections = line.split(Pattern.quote(";"));
		
			tenere = true;
			
			if(sections.length == 9) {
			if(!StringUtils.isAlpha(sections[8]) && StringUtils.isAlpha(sections[3])) tenere = false; //IDCom e Durata no alfanumerici
			if(sections[3].isEmpty() || sections[3].length() != 4) tenere = false; //no IDCom
			if(sections[2].isEmpty() && (sections[0].isEmpty() || sections[1].isEmpty()) ) tenere = false; //serve almeno ragione sociale o nome e cognome
			if(ParseInt(sections[6], 0) == 0 && ParseInt(sections[7], 0) == 0 && ParseDurata(sections[8]) == 0) tenere = false; //non vogliamo gli ultimi tre parametri nulli
			
			if(tenere) {
				temp = new Concessione(sections[0],sections[1],sections[2],sections[3],sections[4], sections[5], ParseInt(sections[6], 0), ParseInt(sections[7], 0), ParseDurata(sections[8]));
				vettore.add(temp);
				}
			else System.out.println("La riga " + riga + " del file CSV per elemento mancante");
			}
			
			else System.out.println("La riga " + riga + " del file CSV per incompletezza");
		}
	reader.close();
	return Correggi(vettore);
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
