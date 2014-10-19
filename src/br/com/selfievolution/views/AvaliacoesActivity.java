package br.com.selfievolution.views;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.AvaliacoesController;
import br.com.selfievolution.models.AvaliacoesModel;

public class AvaliacoesActivity extends ActionBarActivity{

	private AvaliacoesModel model;
	private AvaliacoesController controller;
	AlertDialog.Builder alertbox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avaliacoes);

		SharedPreferences pref = getApplicationContext().getSharedPreferences("SelfieSession", 0);

        TextView nomeAluno = (TextView) findViewById(R.id.nomeAluno);
        TextView idAluno = (TextView) findViewById(R.id.idAluno);
	
        nomeAluno.setText("Lista de avalia��es de " + getIntent().getStringExtra("nomeAluno"));
        idAluno.setText(getIntent().getStringExtra("idAluno"));
        
        controller = new AvaliacoesController(model, this);

	}

	@Override
	public void onResume() {
	    super.onResume();
	}	
	
    public boolean onCreateOptionsMenu(Menu menu) {        // Inflate the menu items for use in the action bar
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);               

        return super.onCreateOptionsMenu(menu);
    }	
    
  
    public void cadastrarAvaliacao(View v){
    	
    	Intent i = new Intent(this, AvaliacaoActivity.class);
    	i.putExtra("idAluno", getIntent().getStringExtra("idAluno"));
    	i.putExtra("nomeAluno", getIntent().getStringExtra("nomeAluno"));
    	startActivity(i);
    }
}
