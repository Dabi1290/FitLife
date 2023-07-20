package model;

public class CarrelloBean {
	
	int codiceProdotto;
	int codiceCliente;
	int quantita;
	
	public CarrelloBean() {
		this.codiceProdotto = -1;
		this.codiceCliente = -1;
		this.quantita = -1;
	}

	public int getCodiceProdotto() {
		return codiceProdotto;
	}

	public void setCodiceProdotto(int codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public int getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(int codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	
	
	
	
}
