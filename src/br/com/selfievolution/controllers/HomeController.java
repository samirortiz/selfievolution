package br.com.selfievolution.controllers;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.selfievolution.R;
import br.com.selfievolution.models.AlunoModel;
import br.com.selfievolution.models.HomeModel;
import br.com.selfievolution.models.ProfessorModel;
import br.com.selfievolution.objects.Usuario;
import br.com.selfievolution.utils.ServerDelegate;
import br.com.selfievolution.utils.Utils;
import br.com.selfievolution.views.AdapterAlunosListView;
import br.com.selfievolution.views.AlunoListView;
import br.com.selfievolution.views.AvaliacoesActivity;
import br.com.selfievolution.views.HomeActivity;

public class HomeController{
    
	private HomeActivity activity;
    private HomeModel model;
    ProgressDialog pd;
    final SharedPreferences pref;
    
    public HomeController(HomeModel model, HomeActivity activity){
        this.activity = activity;
        this.model = model;
        
        pref = getActivity().getSharedPreferences("SelfieSession", 0); // 0 - for private mode
       
    }
 
    public HomeActivity getActivity() {
        return activity;
    }
 
    public HomeModel getModel() {
        return model;
    }    
    
    
    public void startHome(){
    	
    	final ListView list = (ListView) activity.findViewById(R.id.listAlunos);
    	ArrayList<Usuario> alunos = new ArrayList<Usuario>();
    	ArrayList<AlunoListView> dados = new ArrayList<AlunoListView>();
    	
    	AlunoModel modelAluno = new AlunoModel(activity);
    	alunos = modelAluno.selectByProfessor(pref.getString("email", null));
    	
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
	    	
			//Cor quando a lista é selecionada para rolagem.
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
    
    
    public String sincronizarDados(){
    	
    	if(Utils.verificaConexao(getActivity())){
    	
	    	final AsyncTask<Void, Void, Void> getDados = new AsyncTask<Void, Void, Void>() {
				
				@Override
				protected void onPreExecute() {
					
					pd = new ProgressDialog(activity);
					pd.setTitle("Sincronizando com o servidor");
					pd.setMessage("Por favor, aguarde!");
					pd.setCancelable(false);
					pd.setIndeterminate(true);
					pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
					pd.show();
				}
					
				@Override
				protected Void doInBackground(Void... arg0) {
					
					ProfessorModel modelProfessor = new ProfessorModel(getActivity());
					AlunoModel modelAluno = new AlunoModel(getActivity());
					
					ArrayList<Usuario> listUsuarios = new ArrayList<Usuario>();
					
					listUsuarios = modelProfessor.selectAllProfessoresToSync();
	
					if(ServerDelegate.sincronizarProfessores(listUsuarios).equals("ok")){
						for (int i = 0; i < listUsuarios.size(); i++) {
							modelProfessor.syncUsuario(listUsuarios.get(i));
						}
					}

					listUsuarios.clear();
					listUsuarios = modelAluno.selectAllAlunosToSync();
					
					if(ServerDelegate.sincronizarAlunos(listUsuarios).equals("ok")){
						for (int i = 0; i < listUsuarios.size(); i++) {
							modelAluno.syncUsuario(listUsuarios.get(i));
						}
					}				
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
					return null;
				}
	
				@Override
				protected void onPostExecute(Void result) {
					
					if (pd!=null) {
						pd.dismiss();
	
						startHome();
					}
				}
	
			};
	    	
			getDados.execute((Void[])null);	
    	
    	}else{
    		Toast.makeText(getActivity(), "Conecte à internet para sincronizar os dados!", Toast.LENGTH_LONG).show();
    	}
    	
		return null;
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
				dados.add("Avaliação Nº "+avaliacao.getId());
			}
	    	
	    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, android.R.id.text1, dados);
	    	
	    	list.setAdapter(adapter);
    	}
    }*/
    
}
