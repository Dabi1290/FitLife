package model;

import java.io.Serializable;

public class GuestBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	int codice;
	String nome;
	String cognome;
	String telefono;
	String indirizzo;
	

	public GuestBean() {
		codice = -1;
		nome = "";
		cognome = "";
		telefono = "";
		indirizzo= "";
	}

	
	

	public int getCodice() {
		return codice;
	}




	public void setCodice(int codice) {
		this.codice = codice;
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




	public String getTelefono() {
		return telefono;
	}




	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




	public String getIndirizzo() {
		return indirizzo;
	}




	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	@Override
	public String toString() {
		return nome + " " + cognome + " " + telefono + " " + indirizzo+ " ";
	}



	

}



