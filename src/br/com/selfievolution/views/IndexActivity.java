package br.com.selfievolution.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.IndexController;
import br.com.selfievolution.models.IndexModel;

public class IndexActivity extends ActionBarActivity {

	private IndexController controller;
	private IndexModel model;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		
		controller = new IndexController(model, this);
		controller.startApp();		
		
	}
	
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);               

        return super.onCreateOptionsMenu(menu);
    }	
    
    public void cadastrar(View v){
    	
    	Intent i = new Intent(this, UsuarioActivity.class);
    	startActivity(i);
    }
    
    public void entrar(View v){
    	
    	Intent i = new Intent(this, UsuarioActivity.class);
    	startActivity(i);
    }    
	
}
