package br.com.selfievolution.controllers;


import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import br.com.selfievolution.R;
import br.com.selfievolution.models.AvaliacaoModel;
import br.com.selfievolution.objects.Avaliacao;
import br.com.selfievolution.views.AvaliacaoActivity;
import br.com.selfievolution.views.AvaliacoesActivity;
import br.com.selfievolution.views.HomeActivity;

public class AvaliacaoController{
    
	private AvaliacaoActivity activity;
    private AvaliacaoModel model;
    ArrayList<String> opcoes = new ArrayList<String>();
    
    public AvaliacaoController(AvaliacaoModel model, AvaliacaoActivity activity){
        this.activity = activity;
        this.model = model;
    }

    public AvaliacaoActivity getActivity() {
        return activity;
    }
 
    public AvaliacaoModel getModel() {
        return model;
    }    
    
    public void salvarAvaliacao(){
    	Avaliacao avaliacao = new Avaliacao();
    	model = new AvaliacaoModel(activity);

    	EditText idade  = (EditText) activity.findViewById(R.id.idade);
    	EditText peso  = (EditText) activity.findViewById(R.id.peso);
    	EditText altura  = (EditText) activity.findViewById(R.id.altura);
    	
    	avaliacao.setIdade(Integer.parseInt(idade.getText().toString()));
    	avaliacao.setPeso(Double.parseDouble(peso.getText().toString()));
    	avaliacao.setAltura(Double.parseDouble(altura.getText().toString()));
    	avaliacao.setIdAluno(Integer.parseInt(getActivity().getIntent().getStringExtra("idAluno")));

    	if(model.insert(avaliacao)){
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Sucesso!");
			builder.setMessage("Avaliação cadastrada com sucesso!");
			builder.setPositiveButton("Ok", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent i = new Intent(activity.getApplicationContext(), AvaliacoesActivity.class);
					i.putExtra("idAluno", getActivity().getIntent().getStringExtra("idAluno"));
					i.putExtra("nomeAluno", getActivity().getIntent().getStringExtra("nomeAluno"));
					activity.startActivity(i);
					
				}
			});
			
			builder.create().show();
			
    	}else{
    		
    		final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Erro!");
			builder.setMessage("Ocorreu um erro ao salvar avaliação!");
			builder.setNeutralButton("Ok", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					builder.create().dismiss();
				}
			});
			builder.create().show();
    	}
    }
    
}
