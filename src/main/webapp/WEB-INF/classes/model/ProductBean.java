package model;

import java.io.Serializable;
import java.sql.Blob;



public class ProductBean implements Serializable{
	private static final long serialVersionUID = 1L;
	int codice;
	String nome;
	int categoria;
	Double prezzo;
	transient Blob immagine;
	String descrizione;
	int quantita;
	
	
	public ProductBean() {
		this.codice = -1;
		this.nome = "";
		this.categoria = -1;
		this.prezzo = -1.0;
		this.immagine=null;
		this.descrizione="";
		this.quantita=-1;
	}
	public Blob getImmagine() {
		return immagine;
	}
	public void setImmagine( Blob blob) {
		immagine = blob;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
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
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	
	
	
	
	
}
