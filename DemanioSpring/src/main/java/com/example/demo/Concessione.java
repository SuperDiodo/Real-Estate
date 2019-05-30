package com.example.demo;
import java.util.Vector;

/**
 * 
 * @author Gruppo Real Estate Classe del record di un atto di concessione
 */
public class Concessione {
	private String nome;
	private String cognome;
	private String RagSoc;
	private String IDCom;
	private String comune;
	private String den;
	private int superficie;
	private int supwater;
	private int durata;

	/**
	 * 
	 * @param nome       Nome del proprietario della concessione
	 * @param cognome    Cognome del proprietario della concessione
	 * @param ragSoc     Ragione Sociale del proprietario della concessione
	 * @param iDCom      Identificativo del comune in cui ricade l'atto di
	 *                   concessione
	 * @param comune     Comune in cui ricade l'atto di concessione
	 * @param den        Luogo di rilascio dell'atto di concessione
	 * @param superficie Superficie in metri quadrati del terreno
	 * @param supwater   Superficie in metri quadrati dello specchio d'acqua
	 * @param durata     Durata in anni della concessione
	 * 
	 */
	public Concessione(String nome, String cognome, String ragSoc, String iDCom, String comune, String den,
			int superficie, int supwater, int durata) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		RagSoc = ragSoc;
		IDCom = iDCom;
		this.comune = comune;
		this.den = den;
		this.superficie = superficie;
		this.supwater = supwater;
		this.durata = durata;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getRagSoc() {
		return RagSoc;
	}

	public void setRagSoc(String ragSoc) {
		RagSoc = ragSoc;
	}

	public String getIDCom() {
		return IDCom;
	}

	public void setIDCom(String iDCom) {
		IDCom = iDCom;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getDen() {
		return den;
	}

	public void setDen(String den) {
		this.den = den;
	}

	public int getSuperficie() {
		return superficie;
	}

	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}

	public int getSupwater() {
		return supwater;
	}

	public void setSupwater(int supwater) {
		this.supwater = supwater;
	}

	public int getDurata() {
		return durata;
	}

	public void setDurata(int durata) {
		this.durata = durata;
	}

	/**
	 * La funzione crea un oggetto che contiene i metadati che verranno mostrati
	 * dopo la richiesta al Controller.
	 * 
	 * @return Meta Oggetto contenente i metadati dell'oggetto Concessione.
	 */
	public static Vector<metadata> metadati() {
		Vector<metadata> Meta = new Vector<metadata>();
		metadata nomemeta = new metadata("nome", "nome", "String");
		Meta.add(nomemeta);
		metadata cognomemeta = new metadata("cognome", "cognome", "String");
		Meta.add(cognomemeta);
		metadata ragmeta = new metadata("RagSoc", "Ragione Sociale", "String");
		Meta.add(ragmeta);
		metadata idcommeta = new metadata("IDCom", "ID_Comune", "String");
		Meta.add(idcommeta);
		metadata comunemeta = new metadata("comune", "Comune del bene oggetto di Concessione", "String");
		Meta.add(comunemeta);
		metadata denmeta = new metadata("den", "Denominazione_Luogo", "String");
		Meta.add(denmeta);
		metadata supmeta = new metadata("superficie", "superficie", "Integer");
		Meta.add(supmeta);
		metadata supwmeta = new metadata("supwater", "superficie specchio acqua", "Integer");
		Meta.add(supwmeta);
		metadata duratameta = new metadata("durata", "Durata concessione", "Integer");
		Meta.add(duratameta);
		return Meta;

	}
}
