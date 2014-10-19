package br.com.selfievolution.views;

import android.widget.ImageView;

public class AlunoListView {
	private int id;
	private String nome;
	private ImageView imagem;


    public AlunoListView(ImageView imagem, int id, String nome){
    	this.id = id;
    	this.nome = nome;
    	this.imagem = imagem;
    }	
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ImageView getImagem() {
		return imagem;
	}

	public void setImagem(ImageView imagem) {
		this.imagem = imagem;
	}
}

