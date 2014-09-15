package br.com.selfievolution.objects;


public class Usuario {

	private int id;
	private String ds_nome, ds_senha, ds_email, sexo;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome(){
		return ds_nome;
	}
	
	public void setNome(String nome){
		this.ds_nome = nome;
	}
	
	public String getEmail() {
		return ds_email;
	}
	
	public void setEmail(String ds_email) {
		this.ds_email = ds_email;
	}


	public String getSenha() {
		return ds_senha;
	}

	public void setSenha(String ds_senha) {
		this.ds_senha = ds_senha;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

}
