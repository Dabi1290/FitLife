package model;

public class PostCheck {
	private String nome;
    private String cognome;
    private String indirizzo;
    private String telefono;
    private String carta;
    private String cvv;
    private String promozione;
    
    public PostCheck() {}
    
    
    
	public PostCheck(String nome, String cognome, String indirizzo, String telefono, String carta, String cvv,
			String promozione) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.carta = carta;
		this.cvv = cvv;
		this.promozione = promozione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCarta() {
		return carta;
	}
	public void setCarta(String carta) {
		this.carta = carta;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getPromozione() {
		return promozione;
	}
	public void setPromozione(String promozione) {
		this.promozione = promozione;
	}

}
