package model;

import java.io.Serializable;
import java.sql.Blob;



public class ProductBean implements Serializable{
	private static final long serialVersionUID = 1L;
	int codice;
	String nome;
	String categoria;
	Double prezzo;
	Blob immagine;
	String Descrizione;
	int quantita;
	
	
	public ProductBean() {
		this.codice = -1;
		this.nome = "";
		this.categoria = "";
		this.prezzo = -1.0;
		this.immagine=null;
		this.Descrizione="";
		this.quantita=-1;
	}
	public Blob getImmagine() {
		return immagine;
	}
	public void setImmagine( Blob blob) {
		immagine = blob;
	}
	public String getDescrizione() {
		return Descrizione;
	}
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantità) {
		this.quantita = quantità;
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	
	
	
	
	
}
