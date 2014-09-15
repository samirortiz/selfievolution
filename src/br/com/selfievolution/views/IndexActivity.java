package br.com.selfievolution.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import br.com.selfievolution.R;

import com.facebook.Session;

public class IndexActivity extends FragmentActivity {

	IndexActivity activity;
	private FacebookFragment mainFragment;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        mainFragment = new FacebookFragment();
	        getSupportFragmentManager().beginTransaction().add(android.R.id.content, mainFragment).commit();
	    } else {
	        // Or set the fragment from restored state info
	        mainFragment = (FacebookFragment) getSupportFragmentManager().findFragmentById(android.R.id.content);
	    }
	    
	}	

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}	 
	
	
	
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);               

        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }    
    
    public void onResume() {
    	super.onResume();	
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

      super.onSaveInstanceState(savedInstanceState);
      // Save UI state changes to the savedInstanceState.
      // This bundle will be passed to onCreate if the process is
      // killed and restarted.
      // etc.
    }    
    
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
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
