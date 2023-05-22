package model;

import java.io.Serializable;

public class AdminBean implements Serializable{
private static final long serialVersionUID = 1L;
	
	int codice;
	String nome;
	String email;
	String password;
	

	public AdminBean() {
		codice = -1;
		nome = "";
		email = "";
		password = "";
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
		return nome + " " + email ;
	}



	







}
