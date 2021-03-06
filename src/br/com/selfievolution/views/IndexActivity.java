package br.com.selfievolution.views;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.IndexController;
import br.com.selfievolution.models.IndexModel;
import br.com.selfievolution.models.TreinadorModel;

import com.facebook.Session;

public class IndexActivity extends FragmentActivity {

	IndexActivity activity;
	IndexModel model;
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
	    
	    IndexController controller = new IndexController(model, this);
	    controller.startApp();
	    
	}	

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
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
    	Intent i = new Intent(this, RoleActivity.class);
    	startActivity(i);
    }
    
    public void entrar(View v){
    	
    	EditText email = (EditText) findViewById(R.id.emailUsuario);
    	EditText senha = (EditText) findViewById(R.id.senhaUsuario);    	
    	
    	if(!email.getText().toString().equals("") && !senha.getText().toString().equals("")){
    	
    		TreinadorModel usuario = new TreinadorModel(this);
    		
    		int idUsuario = usuario.autenticacao(email.getText().toString(), senha.getText().toString());
    		
	    	if(idUsuario > 0){
	    		ContentValues cv = new ContentValues();
	    		cv = usuario.selectUser(String.valueOf(idUsuario));
	    		
	    		//salvo os dados na session
	        	SharedPreferences pref = getApplicationContext().getSharedPreferences("SelfieSession", 0); // 0 - for private mode
	        	Editor editor = pref.edit();
	        	editor.putInt("id", idUsuario);
	        	editor.putBoolean("logado", true);
	        	editor.putString("nome", cv.get("nome").toString());
	        	editor.putString("email", email.getText().toString());

	        	editor.commit();
	        	
	        	Intent i = new Intent(this, HomeActivity.class);
	        	startActivity(i);
	        	
	    	}else{
	    		Toast.makeText(this, "Usu�rio ou senha inv�lidos!", Toast.LENGTH_LONG).show();
	    	}
    	
    	}else{
    		Toast.makeText(this, "Preencha usu�rio e senha!", Toast.LENGTH_LONG).show();
    	}
    
    }    
	
}
