package database;

import java.io.Serializable;

public class ProductBean implements Serializable{
	private static final long serialVersionUID = 1L;
	int codice;
	String nome;
	String categoria;
	Double prezzo;
	int Admin;
	public ProductBean() {
		this.codice = -1;
		this.nome = "";
		this.categoria = "";
		this.prezzo = -1.0;
		this.Admin = -1;
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
	public int getAdmin() {
		return Admin;
	}
	public void setAdmin(int admin) {
		Admin = admin;
	}
	
	
	
	
}
