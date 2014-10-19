package br.com.selfievolution.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.UsuarioController;
import br.com.selfievolution.models.UsuarioModel;

public class UsuarioActivity extends ActionBarActivity{

	private UsuarioModel model;
	private UsuarioController controller;
	boolean aluno;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
		if(i.getExtras() != null){
			aluno = i.getExtras().getBoolean("cadastroAluno");
		}
	
		if(aluno){
			setContentView(R.layout.activity_cadastro_aluno);
		}else{
			setContentView(R.layout.activity_usuario);
		}
		
		controller = new UsuarioController(model, this);		
		
	}
	
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);               

        return super.onCreateOptionsMenu(menu);
    }	
    
    public void cadastrarUsuario(View v){
    	
    	controller.salvarUsuario();
    }
    
    public void salvarAluno(View v){

    	controller.salvarAluno();
    }

}
