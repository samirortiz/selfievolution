package br.com.selfievolution.objects;

import java.util.ArrayList;


public class FacebookResponse {

    ArrayList <String> data = new ArrayList<>();

    public String id, first_name;
    
    public String getNome() {
		return first_name;
	}

	public void setNome(String first_name) {
		this.first_name = first_name;
	}

	public void setId(String id){
    	this.id = id;
    }
    
    public String getId(){
    	return id;
    }
    
    

	
}
