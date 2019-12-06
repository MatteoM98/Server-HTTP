package it.uniupo.matteo.magri.http;

public class Studente {

	//Variabili
	private String nome;
	private String cognome;
	private String matricola;
	private String nomeCorso;
	private int anno;
	
	//Costruttori
	public Studente(String nome, String cognome, String matricola) {
		this.setNome(nome);
		this.setCognome(cognome);
		this.setMatricola(matricola);
	}
	
	public Studente(String nome, String cognome, String matricola,String nomeCorso, int anno) {
		this(nome,cognome,matricola);
		this.setNomeCorso(nomeCorso);
		this.setAnno(anno);
	}
	

	//Metodi accessori
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

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getNomeCorso() {
		return nomeCorso;
	}

	public void setNomeCorso(String nomeCorso) {
		this.nomeCorso = nomeCorso;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}
	
	//Metodi
	@Override
	public String toString() {
		return nome + "  "+ cognome + "  " + matricola + "\n";
	}
}
