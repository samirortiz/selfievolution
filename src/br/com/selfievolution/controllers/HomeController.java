package br.com.selfievolution.controllers;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.selfievolution.R;
import br.com.selfievolution.models.AvaliacaoModel;
import br.com.selfievolution.models.HomeModel;
import br.com.selfievolution.objects.Avaliacao;
import br.com.selfievolution.views.HomeActivity;

public class HomeController{
    
	private HomeActivity activity;
    private HomeModel model;
    
    public HomeController(HomeModel model, HomeActivity activity){
        this.activity = activity;
        this.model = model;
       
    }
 
    public HomeActivity getActivity() {
        return activity;
    }
 
    public HomeModel getModel() {
        return model;
    }    
    
    public void startHome(){

    	ListView list = (ListView) activity.findViewById(R.id.listAvaliacoes);
    	ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
    	ArrayList<String> dados = new ArrayList<String>();
    	
    	AvaliacaoModel modelAvaliacao = new AvaliacaoModel(activity);
    	avaliacoes = modelAvaliacao.selectAll();
    	
    	if(avaliacoes.size() == 0){
			//adapter vazio
			View empty = activity.getLayoutInflater().inflate(R.layout.avaliacao_empty_list, null, true);
			activity.addContentView(empty, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			list.setEmptyView(empty);    		
    	}else{
    	
	    	for (int i = 0; i < avaliacoes.size(); i++) {
				Avaliacao avaliacao = new Avaliacao();
				avaliacao.setId(avaliacoes.get(i).getId());
				dados.add("Avaliação Nº "+avaliacao.getId());
			}
	    	
	    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, android.R.id.text1, dados);
	    	
	    	list.setAdapter(adapter);
    	}
    }
    
}
