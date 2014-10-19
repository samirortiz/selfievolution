package br.com.selfievolution.views;


public class AvaliacaoListView {
	private int id;
	private String data;


    public AvaliacaoListView(int id, String data){
    	this.id = id;
    	this.data = data;
    }	
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return data;
	}

	public void setDate(String data) {
		this.data = data;
	}

}

