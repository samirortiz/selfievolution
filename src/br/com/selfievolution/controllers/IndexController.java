package br.com.selfievolution.controllers;


import android.content.Intent;
import android.content.SharedPreferences;
import br.com.selfievolution.models.IndexModel;
import br.com.selfievolution.views.HomeActivity;
import br.com.selfievolution.views.IndexActivity;

public class IndexController{
    
	private IndexActivity activity;
    private IndexModel model;
  
    
    public IndexController(IndexModel model, IndexActivity activity){
        this.activity = activity;
        this.model = model;
    }
 
    public IndexActivity getActivity() {
        return activity;
    }
 
    public IndexModel getModel() {
        return model;
    }    
    
    public void startApp(){
    	//testa a sessao - revisar
    	SharedPreferences pref = activity.getApplicationContext().getSharedPreferences("SelfieSession", 0); 
        
    	//temporário    	
    	/*Editor edit = pref.edit();
    	edit.clear();
    	edit.commit();
    	activity.getApplicationContext().deleteDatabase(Environment.getExternalStorageDirectory().getPath()+"/selfie.db");
    	System.exit(0);*/
    	//

    	if(pref.getBoolean("logado", false)){
    		Intent i = new Intent(activity, HomeActivity.class);
    		activity.startActivity(i);

    	}
    }
    
}
