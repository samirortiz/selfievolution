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
import android.widget.TextView;
import br.com.selfievolution.R;
import br.com.selfievolution.models.HomeModel;
import br.com.selfievolution.models.UsuarioModel;
import br.com.selfievolution.objects.Usuario;
import br.com.selfievolution.views.AdapterAlunosListView;
import br.com.selfievolution.views.AlunoListView;
import br.com.selfievolution.views.AvaliacoesActivity;
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

    	SharedPreferences pref = getActivity().getSharedPreferences("SelfieSession", 0); // 0 - for private mode
    	
    	final ListView list = (ListView) activity.findViewById(R.id.listAlunos);
    	ArrayList<Usuario> alunos = new ArrayList<Usuario>();
    	ArrayList<AlunoListView> dados = new ArrayList<AlunoListView>();
    	
    	UsuarioModel modelUsuario = new UsuarioModel(activity);
    	alunos = modelUsuario.selectByProfessor(pref.getInt("id", 0));
    	
    	if(alunos.size() == 0){
			
    		//adapter vazio
			View empty = activity.getLayoutInflater().inflate(R.layout.aluno_empty_list, null, true);
			activity.addContentView(empty, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			list.setEmptyView(empty);
			
    	}else{
    	
	    	for (int i = 0; i < alunos.size(); i++) {
				AlunoListView a = new AlunoListView(alunos.get(i).getImagem(),alunos.get(i).getId(), alunos.get(i).getNome());
				dados.add(a);
			}
	    	
	    	AdapterAlunosListView adapter = new AdapterAlunosListView(getActivity(), dados);
	    	
	    	//View header = (View) getActivity().getLayoutInflater().inflate(R.layout.header_alunos_list,null);
	    	//list.addHeaderView(header);
	    	
	    	list.setAdapter(adapter);
	    	
			//Cor quando a lista � selecionada para rolagem.
			list.setCacheColorHint(Color.TRANSPARENT);	    	
	    	list.setDividerHeight(2);
	    	
	    	list.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					Intent i = new Intent(view.getContext(), AvaliacoesActivity.class);
					
					TextView idAluno = (TextView) view.findViewById(R.id.idAluno);
					TextView nomeAluno = (TextView) view.findViewById(R.id.nomeAluno);

					i.putExtra("idAluno", idAluno.getText().toString());
					i.putExtra("nomeAluno", nomeAluno.getText().toString());

					getActivity().startActivity(i);
				}
			});
    	}
    }    
    
/*    public void startHome(){

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
				dados.add("Avalia��o N� "+avaliacao.getId());
			}
	    	
	    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, android.R.id.text1, dados);
	    	
	    	list.setAdapter(adapter);
    	}
    }*/
    
}
