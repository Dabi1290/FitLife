package model;


import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	int codice;
	String nome;
	String cognome;
	String telefono;
	String indirizzo;
	String email;
	String password;
	

	public UserBean() {
		codice = -1;
		nome = "";
		cognome = "";
		telefono = "";
		indirizzo= "";
		email= "";
		password= "";
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




	public String getCognome() {
		return cognome;
	}




	public void setCognome(String cognome) {
		this.cognome = cognome;
	}




	public String getTelefono() {
		return telefono;
	}




	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




	public String getIndirizzo() {
		return indirizzo;
	}




	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}

	
	
	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	@Override
	public String toString() {
		return nome + " " + cognome + " " + telefono + " " + indirizzo+ " " + email;
	}



	

}
