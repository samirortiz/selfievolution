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
import br.com.selfievolution.views.HomeActivity;

public class AvaliacaoController{
    
	private AvaliacaoActivity activity;
    private AvaliacaoModel model;
    private Spinner spinnerDobras;
    public int dobras;
    ArrayList<String> opcoes = new ArrayList<String>();
    ArrayAdapter<String> spinnerArrayAdapter;
    
    public AvaliacaoController(AvaliacaoModel model, AvaliacaoActivity activity){
        this.activity = activity;
        this.model = model;

        opcoes.add("3");
        opcoes.add("7");
        
        spinnerDobras = (Spinner) activity.findViewById(R.id.spinnerDobras);
        
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                 activity, android.R.layout.simple_spinner_item, opcoes);
        
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );

        spinnerDobras = (Spinner) activity.findViewById(R.id.spinnerDobras);
        spinnerDobras.setAdapter(spinnerArrayAdapter);
        
		spinnerDobras.setOnItemSelectedListener(new ListenerSpinner());
				
		
        
    }
 
    public class ListenerSpinner implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {

			spinnerDobras.setSelection(position);
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
    	
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

    	EditText idade = (EditText) activity.findViewById(R.id.idade);
    	EditText peso  = (EditText) activity.findViewById(R.id.peso);
    	spinnerDobras = (Spinner) activity.findViewById(R.id.spinnerDobras);
    	
    	avaliacao.setDobras(Integer.parseInt(spinnerDobras.getSelectedItem().toString()));
    	avaliacao.setIdade(Integer.parseInt(idade.getText().toString()));
    	avaliacao.setPeso(Integer.parseInt(peso.getText().toString()));

    	if(model.insert(avaliacao)){
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Sucesso!");
			builder.setMessage("Avaliação cadastrada com sucesso!");
			builder.setPositiveButton("Ok", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent i = new Intent(activity.getApplicationContext(), HomeActivity.class);
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
