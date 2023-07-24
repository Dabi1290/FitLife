package model;

import java.io.Serializable;

public class OrdineBean implements Serializable{
	private static final long serialVersionUID = 1L;
	int codice;
	Boolean isProcessed;
	String data;
	int codCliente;
	
	public OrdineBean() {
		this.codice = -1;
		this.data = "";
		this.codCliente=-1;
		this.isProcessed=false;
	}
	public Boolean getIsProcessed() {
		return isProcessed;
	}
	public void setIsProcessed(Boolean isProcessed) {
		this.isProcessed = isProcessed;
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
	public int getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(int codCliente) {
		this.codCliente = codCliente;
	}
	
}
