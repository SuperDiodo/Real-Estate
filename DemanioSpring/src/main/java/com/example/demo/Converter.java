package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


public class Converter {

	private File Input;
	private Vector<Concessione> concessione = new Vector<Concessione>();
	
	public Converter(String I) {
		Input = new File(I);
	}
	
	public void csv2objects() {
		try {
			FileReader read = new FileReader(getInput().getName());
			BufferedReader rd = new BufferedReader(read);
			String line = rd.readLine(); 
			while((line = rd.readLine()) != null) {
				String[] sections = line.split(";");
				Concessione tmp = new Concessione(sections[0], sections[1], sections[2], sections[3], sections[4], sections[5],Integer.parseInt(sections[6]),Integer.parseInt(sections[7]),Integer.parseInt(sections[8]));
				
				concessione.add(tmp);
			}
			rd.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public File getInput() {
		return Input;
	}
	
	public Vector<Concessione> getConcessione() {
		return concessione;
	}

}
