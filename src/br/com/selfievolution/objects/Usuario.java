package br.com.selfievolution.objects;

import android.widget.ImageView;


public class Usuario {

	private int id, id_role, id_professor;
	private String nome, senha, email, sexo, nascimento;
	private ImageView imagem;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String ds_email) {
		this.email = ds_email;
	}


	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public int getIdRole() {
		return id_role;
	}

	public void setIdRole(int id_role) {
		this.id_role = id_role;
	}

	public int getIdProfessor() {
		return id_professor;
	}

	public void setIdProfessor(int id_professor) {
		this.id_professor = id_professor;
	}

	public ImageView getImagem() {
		return imagem;
	}

	public void setImagem(ImageView imagem) {
		this.imagem = imagem;
	}
	

}
