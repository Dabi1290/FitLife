package model;

import java.io.Serializable;

public class OrdineBean implements Serializable{
	private static final long serialVersionUID = 1L;
	int codice;
	String data;
	int codAdmin;
	int codCliente;
	int codGuest;
	public OrdineBean() {
		this.codice = -1;
		this.data = "";
		this.codAdmin=-1;
		this.codCliente=-1;
		this.codGuest=-1;
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getCodAdmin() {
		return codAdmin;
	}
	public void setCodAdmin(int codAdmin) {
		this.codAdmin = codAdmin;
	}
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	public int getCodGuest() {
		return codGuest;
	}
	public void setCodGuest(int codGuest) {
		this.codGuest = codGuest;
	}
	
}
