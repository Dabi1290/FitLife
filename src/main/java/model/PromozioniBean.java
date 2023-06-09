package model;

import java.io.Serializable;
import java.sql.Blob;

public class PromozioniBean implements Serializable{

	private static final long serialVersionUID = 1L;
	String codice;
	String categoria;
	Boolean isCategoria;
	Blob immagine;
	public PromozioniBean() {
		super();
		this.codice = "";
		this.categoria = "";
		this.isCategoria=false;
		this.immagine=null;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Boolean getIsCategoria() {
		return isCategoria;
	}
	public void setIsCategoria(Boolean isCategoria) {
		this.isCategoria = isCategoria;
	}
	public Blob getImmagine() {
		return immagine;
	}
	public void setImmagine(Blob immagine) {
		this.immagine = immagine;
	}
	
	
	
	
}
