package br.com.selfievolution.objects;


public class Avaliacao {

	private int id, id_aluno, idade;
	private double peso, altura;
	private String data;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getIdAluno() {
		return id_aluno;
	}

	public void setIdAluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getDate() {
		return data;
	}

	public void setDate(String data) {
		this.data = data;
	}

	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public int getIdade() {
		return idade;
	}

}
