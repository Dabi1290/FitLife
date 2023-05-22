package model;

import java.io.Serializable;

public class PromozioniBean implements Serializable{

	private static final long serialVersionUID = 1L;
	String codice;
	String categoria;
	int codiceAdmin;
	public PromozioniBean() {
		super();
		this.codice = "";
		this.categoria = "";
		this.codiceAdmin = -1;
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
	public int getCodiceAdmin() {
		return codiceAdmin;
	}
	public void setCodiceAdmin(int codiceAdmin) {
		this.codiceAdmin = codiceAdmin;
	}
	
	
	
}
