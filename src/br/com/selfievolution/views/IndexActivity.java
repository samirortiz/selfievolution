package br.com.selfievolution.views;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.selfievolution.R;
import br.com.selfievolution.models.UsuarioModel;
import br.com.selfievolution.objects.Usuario;

import com.facebook.Session;

public class IndexActivity extends FragmentActivity {

	IndexActivity activity;
	private FacebookFragment mainFragment;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    /*
	    SharedPreferences pref = getApplicationContext().getSharedPreferences("SelfieSession", 0); 
    	Editor edit = pref.edit();
    	edit.clear();
    	edit.commit();
    	getApplicationContext().deleteDatabase(Environment.getExternalStorageDirectory().getPath()+"/selfie.db");
    	System.exit(0);
    	*/	    
	    
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
    	
    	EditText email = (EditText) findViewById(R.id.emailUsuario);
    	EditText senha = (EditText) findViewById(R.id.senhaUsuario);    	
    	
    	if(!email.getText().toString().equals("") && !senha.getText().toString().equals("")){
    	
    		UsuarioModel usuario = new UsuarioModel(this);
    		
    		int idUsuario = usuario.autenticacao(email.getText().toString(), senha.getText().toString());
    		
	    	if(idUsuario > 0){
	    		ContentValues cv = new ContentValues();
	    		cv = usuario.selectUser(String.valueOf(idUsuario));
	    		
	    		//salvo os dados na session
	        	SharedPreferences pref = getApplicationContext().getSharedPreferences("SelfieSession", 0); // 0 - for private mode
	        	Editor editor = pref.edit();
	        	editor.putBoolean("logado", true);
	        	editor.putString("nome", cv.get("ds_nome").toString());
	        	editor.commit();
	        	
	        	Intent i = new Intent(this, HomeActivity.class);
	        	startActivity(i);
	        	
	    	}else{
	    		Toast.makeText(this, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show();
	    	}
    	
    	}else{
    		Toast.makeText(this, "Preencha usuário e senha!", Toast.LENGTH_LONG).show();
    	}
    
    }    
	
}
