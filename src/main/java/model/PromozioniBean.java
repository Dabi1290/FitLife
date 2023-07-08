package model;

import java.io.Serializable;
import java.sql.Blob;

public class PromozioniBean implements Serializable{

	private static final long serialVersionUID = 1L;
	String codice;
	String categoria;
	Boolean IsCategoria;
	Blob Immagine;
	public PromozioniBean() {
		super();
		this.codice = "";
		this.categoria = "";
		this.IsCategoria=false;
		this.Immagine=null;
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
		return IsCategoria;
	}
	public void setIsCategoria(Boolean isCategoria) {
		IsCategoria = isCategoria;
	}
	public Blob getImmagine() {
		return Immagine;
	}
	public void setImmagine(Blob immagine) {
		Immagine = immagine;
	}
	
	
	
	
}
