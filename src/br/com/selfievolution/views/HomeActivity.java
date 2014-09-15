package br.com.selfievolution.views;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.HomeController;
import br.com.selfievolution.models.HomeModel;

import com.facebook.Session;

public class HomeActivity extends ActionBarActivity{

	private HomeModel model;
	private HomeController controller;
	AlertDialog.Builder alertbox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		SharedPreferences pref = getApplicationContext().getSharedPreferences("SelfieSession", 0);
		ImageView imgPerfil = (ImageView) findViewById(R.id.userImage);

		//permite reescrita na mainthread... quando possível, trocar para uma asynctask
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
	    //imagem do perfil do facebook
		 URL img = null;
		 try {
			img = new URL("https://graph.facebook.com/"+pref.getString("id_facebook", "")+"/picture");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		Bitmap mIcon1 = null;
		try {
			
			mIcon1 = BitmapFactory.decodeStream(img.openConnection().getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 imgPerfil.setImageBitmap(mIcon1);
		 		
		
        //pego o text view e coloco o nome do funcionário
        TextView tv = (TextView) findViewById(R.id.nome);

        tv.setText("Bem Vindo " + pref.getString("nome", null));		
		
		controller = new HomeController(model, this);
		controller.startHome();		
		
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
                    	
                        //facebook logout
                        Session session = Session.getActiveSession();
                        session.closeAndClearTokenInformation();                    	
                    	
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
                          
                          //facebook logout
                          Session session = Session.getActiveSession();
                          session.closeAndClearTokenInformation();
                          
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
