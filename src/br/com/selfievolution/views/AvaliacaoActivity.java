package br.com.selfievolution.views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.AvaliacaoController;
import br.com.selfievolution.controllers.AvaliacoesController;
import br.com.selfievolution.models.AvaliacaoModel;

public class AvaliacaoActivity extends ActionBarActivity{

	private AvaliacaoModel model;
	private AvaliacaoController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avaliacao);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setTitle("Voltar");
		
		//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 

		controller = new AvaliacaoController(model, this);	
	
	}
	
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);               

        return super.onCreateOptionsMenu(menu);
    }	
    
    public void salvarAvaliacao(View v){

		controller.salvarAvaliacao();	
    }
    
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	      // Respond to the action bar's Up/Home button
	      case android.R.id.home:
	    	  this.finish();
	          return true;
	    }
	 
	    return super.onOptionsItemSelected(item);
	  }
}
