package br.com.selfievolution.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.HomeController;
import br.com.selfievolution.models.HomeModel;

public class HomeActivity extends ActionBarActivity{

	private HomeModel model;
	private HomeController controller;
	AlertDialog.Builder alertbox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
        //pego o text view e coloco o nome do funcionário
        TextView tv = (TextView) findViewById(R.id.nome);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("SelfieSession", 0);
        tv.setText("Bem Vindo " + pref.getString("nome", null));		
		
		controller = new HomeController(model, this);
		controller.startHome();		
		
	}
	
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_action_bar, menu);               

        return super.onCreateOptionsMenu(menu);
    }	
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {   
    		// action with ID action_refresh was selected
    	case R.id.action_sync:
    		
    		//atualiza home
    		controller = new HomeController(model, this);
    		
    		break;
    		// action with ID action_settings was selected
    	case R.id.action_settings:
    		Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show();
    		break;
    		// action with ID action_settings was selected
    	case R.id.action_quit:

            AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
            alertbox.setTitle("Saindo do SelfiEvolution");
            alertbox.setMessage("Deseja mesmo sair?");

            alertbox.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                	public void onClick(DialogInterface arg0, int arg1) {
                  	  	//limpa a sessão
                    	SharedPreferences pref = getApplicationContext().getSharedPreferences("SelfieSession", 0);
                    	Editor edit = pref.edit();
                    	edit.clear();
                    	edit.commit();                        	
                    	finish();
                    }
                });

            alertbox.setNeutralButton("Não", new DialogInterface.OnClickListener() {
        		public void onClick(DialogInterface arg0, int arg1) {}
            });

            alertbox.show();

    		break;        
    	}

    	return true;
    }      
    
    @Override
    //ação de voltar, estando na home
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            alertbox = new AlertDialog.Builder(this);
            alertbox.setTitle("Saindo do SelfiEvolution");
            alertbox.setMessage("Deseja mesmo sair?");

            alertbox.setPositiveButton("Sim",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                      	  SharedPreferences pref = getApplicationContext().getSharedPreferences("SelfieSession", 0);
                          Editor edit = pref.edit();
                          edit.clear();
                          edit.commit();                        	
                          finish();
                        }
                    });

            alertbox.setNeutralButton("Não", new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface arg0, int arg1) {}
            });

            alertbox.show();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }    
    
    public void cadastrarAvaliacao(View v){
    	
    	Intent i = new Intent(this, AvaliacaoActivity.class);
    	startActivity(i);
    }
}
