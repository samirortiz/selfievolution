package br.com.selfievolution.controllers;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.selfievolution.R;
import br.com.selfievolution.models.AvaliacaoModel;
import br.com.selfievolution.models.AvaliacoesModel;
import br.com.selfievolution.objects.Avaliacao;
import br.com.selfievolution.views.AdapterAvaliacoesListView;
import br.com.selfievolution.views.AlunoListView;
import br.com.selfievolution.views.AvaliacaoActivity;
import br.com.selfievolution.views.AvaliacaoListView;
import br.com.selfievolution.views.AvaliacoesActivity;

public class AvaliacoesController{
    
	private AvaliacoesActivity activity;
    private AvaliacoesModel model;
    
    public AvaliacoesController(AvaliacoesModel model, AvaliacoesActivity activity){
        this.activity = activity;
        this.model = model;
       
        SharedPreferences pref = getActivity().getSharedPreferences("SelfieSession", 0); // 0 - for private mode
    	Intent in = getActivity().getIntent();
        
    	final ListView list = (ListView) activity.findViewById(R.id.listAvaliacoes);
    	ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
    	ArrayList<AvaliacaoListView> dados = new ArrayList<AvaliacaoListView>();
    	
    	AvaliacaoModel modelAvaliacao = new AvaliacaoModel(activity);
    	avaliacoes = modelAvaliacao.selectByAluno(Integer.parseInt(in.getStringExtra("idAluno")));
    	
    	if(avaliacoes.size() == 0){
    		
			//adapter vazio
			View empty = activity.getLayoutInflater().inflate(R.layout.avaliacao_empty_list, null, true);
			activity.addContentView(empty, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			list.setEmptyView(empty);  
			
    	}else{
    	
	    	for (int i = 0; i < avaliacoes.size(); i++) {
				AvaliacaoListView a = new AvaliacaoListView(avaliacoes.get(i).getId(), avaliacoes.get(i).getDate());
				dados.add(a);
			}
	    	
	    	AdapterAvaliacoesListView adapter = new AdapterAvaliacoesListView(getActivity(), dados);
	    	
	    	//View header = (View) getActivity().getLayoutInflater().inflate(R.layout.header_alunos_list,null);
	    	//list.addHeaderView(header);
	    	
	    	list.setAdapter(adapter);
	    	
			//Cor quando a lista é selecionada para rolagem.
			list.setCacheColorHint(Color.TRANSPARENT);	    	
	    	list.setDividerHeight(2);
	    	
	    	list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent i = new Intent(getActivity(), ResultadoAvaliacaoActivity.class);
					
					i.putExtra("idAluno", list.getItemAtPosition(position).getClass());

					getActivity().startActivity(i);
				}
			});
    	}        
        
    }
 
    public AvaliacoesActivity getActivity() {
        return activity;
    }
 
    public AvaliacoesModel getModel() {
        return model;
    }    
    
    
    public void startAvaliacoes(){

    	
    }    
    
/*    public void startAvaliacoes(){

    	ListView list = (ListView) activity.findViewById(R.id.listAlunos);
    	ArrayList<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();
    	ArrayList<String> dados = new ArrayList<String>();
    	
    	AvaliacaoModel modelAvaliacao = new AvaliacaoModel(activity);
    	avaliacoes = modelAvaliacao.selectAll();
    	
    	if(avaliacoes.size() == 0){
			//adapter vazio
			View empty = activity.getLayoutInflater().inflate(R.layout.aluno_empty_list, null, true);
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
    }*/
    
}
