package model;

import java.io.Serializable;

public class SelledProductBean implements Serializable{
	private static final long serialVersionUID = 1L;
	int codice;
	String nome;
	String categoria;
	Double prezzo;
	int ordine;
	public SelledProductBean() {
		this.codice = -1;
		this.nome = "";
		this.categoria = "";
		this.prezzo = -1.0;
		this.ordine = -1;
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
	public int getOrdine() {
		return ordine;
	}
	public void setOrdine(int ordine) {
		this.ordine = ordine;
	}
	
}
