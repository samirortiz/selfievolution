package br.com.selfievolution.controllers;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import br.com.selfievolution.models.IndexModel;
import br.com.selfievolution.views.HomeActivity;

public class IndexController{
    
	private FragmentActivity activity;
    private IndexModel model;
  
    
    public IndexController(IndexModel model, FragmentActivity fragmentActivity){
        this.activity = fragmentActivity;
        this.model = model;
    }
 
    public FragmentActivity getActivity() {
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

    		Intent i = new Intent(getActivity(), HomeActivity.class);
    		activity.startActivity(i);

    	}
    }
    
}
